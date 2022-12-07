import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Team {

  private String name;
  private String coach;
  private int goals = 0;
  private int points = 0;
  private boolean eliminated = false;
  private Group group;
  private ArrayList<Player> players = new ArrayList<Player>();
  private ArrayList<Match> matches = new ArrayList<Match>();

  public Team(String name, String coach, Group group) {
    this.name = name;
    this.coach = coach;
    this.goals = 0;
    this.eliminated = false;
    this.group = group;

    DataBase db = DataBase.getInstance();

    // get teams from database

    try {

      ResultSet res = db.query("SELECT * FROM PLAYERS WHERE TeamID = '" + this.name + "'");

      while (res.next()) {
        Player player = new Player(res.getString("Name"), res.getInt("Age"), res.getString("Position"), this);
        players.add(player);
      }

    } catch (SQLException e) {

      System.out.println(e.getMessage());

    }

  }

  public void addGoals(int goals) {
    this.goals += goals;
  }

  public void addPoints(int points) {
    this.goals += points;
  }

  public void addMatch(Match match) {
    this.matches.add(match);
  }

  public int getPoints() {
    return points;
  }

  public void setEliminated(boolean eliminated) {
    this.eliminated = eliminated;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCoach() {
    return coach;
  }

  public void setCoach(String coach) {
    this.coach = coach;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public int getGoals() {
    return goals;
  }

  public boolean isEliminated() {
    return eliminated;
  }

  public ArrayList<Player> getPlayers() {
    return players;
  }

  public void setPlayers(ArrayList<Player> players) {
    this.players = players;
  }

  public String toString() {

    String out = String.format(
        "+-----------------------------------+%n" +
            "| %-33s |%n" +
            "+-----------+---------------+-------+%n" +
            "| Pontuação | Saldo de Gols | Grupo |%n" +
            "+-----------+---------------+-------+%n" +
            "| %-9d | %-13d | %-5c |%n" +
            "+-----------+---------------+-------+%n",
        this.name, this.points, this.goals, this.group.getLabel());

    return out;
  }

  public String playersToString() {

    String out = String.format(
        "+----------------------------------------------+%n" +
            "| %-44s |%n" +
            "+------------------------+-------------+-------+%n" +
            "| Nome                   | Posição     | Idade |%n" +
            "+------------------------+-------------+-------+%n",
        this.name);

    for (int i = 0; i < this.players.size(); i++) {
      out += String.format(
          "| %-22s | %-11s | %-5d |%n",
          this.players.get(i).getName(), this.players.get(i).getPosition(),
          this.players.get(i).getAge());
    }

    out += "+------------------------+-------------+-------+\n";

    return out;
  }

  public String matchesToString() {

    String out = String.format(
        "+----------------------------------------------------------------------------+%n" +
            "| Partidas %-33s                                 |%n" +
            "+-----------------------------------+---------------------+--------+---------+%n" +
            "| Seleções                          | Data                | Passou | Placar  |%n" +
            "+-----------------------------------+---------------------+--------+---------+%n",
        this.name);

    for (int i = 0; i < this.matches.size(); i++) {
      String score1 = this.matches.get(i).wasPlayed() ? String.valueOf(this.matches.get(i).getTeam1score()) : " ";
      String score2 = this.matches.get(i).wasPlayed() ? String.valueOf(this.matches.get(i).getTeam2score()) : " ";

      out += String.format(
          "| %-15s X %15s | %-19s | %-6s | %-2s - %2s |%n",
          this.matches.get(i).getTeam1().getName(), this.matches.get(i).getTeam2().getName(),
          this.matches.get(i).getDate(), this.matches.get(i).wasPlayed() ? "Sim" : "Não",
          score1, score2);
    }

    out += "+-----------------------------------+---------------------+--------+---------+\n";

    return out;
  }

}
