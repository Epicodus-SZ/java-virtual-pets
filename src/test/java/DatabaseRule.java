import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/virtual_pets_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deletePersonsQuery = "DELETE FROM persons *;";
      con.createQuery(deletePersonsQuery).executeUpdate();

      String deleteMonstersQuery = "DELETE FROM monsters *;";
      con.createQuery(deleteMonstersQuery).executeUpdate();

      String deleteCommunitiesQuery = "DELETE FROM communities *;";
      con.createQuery(deleteCommunitiesQuery).executeUpdate();

      String deleteJoinsQuery = "DELETE FROM communities_persons *;";
      con.createQuery(deleteJoinsQuery).executeUpdate();
    }
  }

}
