import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorldCup {

  private static WorldCup worldCup;
  private static ArrayList<Team> teams = new ArrayList<Team>();
  private static ArrayList<Stadium> stadiums = new ArrayList<Stadium>();
  private static ArrayList<Match> matches = new ArrayList<Match>();

  private WorldCup() {
  }

  public static WorldCup getInstance() {

    if (worldCup == null) {
      worldCup = new WorldCup();

      DataBase db = DataBase.getInstance();

      
      try {
        
        // get teams from database
        ResultSet res = db.query("SELECT * FROM TEAMS");

        while (res.next()) {
          Group group = Group.getGroup(res.getString("Group").charAt(0));
          Team team = new Team(res.getString("Name"), res.getString("Coach"), group);
          teams.add(team);
          group.addTeam(team);
        }


        // get stadiums from database
        res = db.query("SELECT * FROM STADIUMS");

        while (res.next()) {
          Stadium stadium = new Stadium(res.getString("Name"), res.getString("Description"), res.getInt("Capacity"));
          stadiums.add(stadium);
        }

      // get matches from database
        res = db.query("SELECT * FROM MATCHES");

        while (res.next()) {
          Team team1 = null, team2 = null;
          Stadium stadium = null;

          for (int i = 0; i < teams.size(); i++){
            if(teams.get(i).getName().equals(res.getString("Team1"))){
              team1 = teams.get(i);
            }
            if (teams.get(i).getName().equals(res.getString("Team2"))) {
              team2 = teams.get(i);
            }
          }
          
          for (int i = 0; i < stadiums.size(); i++) {
            if (stadiums.get(i).getName().equals(res.getString("Stadium"))) {
              stadium = stadiums.get(i);
            }
          }
          
          Match match = new Match(team1, team2, res.getInt("Team1Goals"), res.getInt("Team2Goals"), stadium, 
            res.getInt("wasPlayed"), res.getString("Date"), res.getString("Phase"));
          if(team1 != null)
            team1.addMatch(match);
          if(team2 != null)
            team2.addMatch(match);
          matches.add(match);

        }

        for (int i = 0; i < teams.size(); i++) {

          System.out.println(teams.get(i).matchesToString());
        }


      } catch (SQLException e) {

        System.out.println(e.getMessage());

      }

    }

    return worldCup;
  }

}
