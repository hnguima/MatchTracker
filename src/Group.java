import java.util.ArrayList;

public class Group {

  private static Group[] groups = new Group[8];
  private static boolean isCreated = false;

  private ArrayList<Team> teams = new ArrayList<Team>();
  private char label;

  private Group() {
  }

  public void setTeams(ArrayList<Team> teams) {
    this.teams = teams;
  }

  public void addTeam(Team team) {
    this.teams.add(team);
  }

  public ArrayList<Team> getTeams() {
    return this.teams;
  }

  public char getLabel() {
    return this.label;
  }

  public static Group getGroup(int i) {

    if (!isCreated) {
      for (int index = 0; index < groups.length; index++) {
        groups[index] = new Group();
        groups[index].label = (char) (index + 65);
      }

      isCreated = true;
    }

    return groups[i];
  }

  public static Group getGroup(char c) {
    if (!isCreated) {
      for (int index = 0; index < groups.length; index++) {
        groups[index] = new Group();
        groups[index].label = (char) (index + 65);
      }

      isCreated = true;
    }

    return groups[c - 65];
  }

  public String toString() {

    String out = String.format(
    "+-----------------------------------------------+%n"+
    "| Grupo %c                                       |%n"+
    "+-------------------+-----------+---------------+%n"+
    "| Seleção           | Pontuação | Saldo de Gols |%n"+
    "+-------------------+-----------+---------------+%n", 
    this.label);

    for(int i = 0; i < this.teams.size(); i++){
      out += String.format(
      "| %-17s | %-9d | %-13d |%n",
     this.teams.get(i).getName(), this.teams.get(i).getPoints(), this.teams.get(i).getGoals());
    }

    out += 
    "+-------------------+-----------+---------------+\n";

    return out;
  }

}
