import java.util.Timer;
import org.sql2o.*;
import java.util.List;

public class FireMonster extends Monster {
  private int fireLevel;
  public static final int MAX_FIRE_LEVEL = 10;

  public FireMonster(String name, int personId) {
    this.name = name;
    this.personId = personId;
    playLevel = MAX_PLAY_LEVEL / 2;
    sleepLevel = MAX_SLEEP_LEVEL / 2;
    foodLevel = MAX_FOOD_LEVEL / 2;
    fireLevel = MAX_FIRE_LEVEL / 2; //new property
    timer = new Timer();
  }

  // Getter methods //////////////////////////

  public int getFireLevel(){
    return fireLevel;

  }

  // Other Methods ///////////////////////////

  public static List<FireMonster> all() {
    String sql = "SELECT * FROM monsters;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(FireMonster.class);
    }
  }

  public static FireMonster find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM monsters where id=:id";
      FireMonster monster = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(FireMonster.class);
      return monster;
    }
  }
}
