public class Player {
    private String name;
    private int Age;
    private String position;
    private Team team;
    private boolean isCaptain = false;

    public Player(String name, int age, String position, Team team) {
        this.name = name;
        this.Age = age;
        this.team = team;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setCaptain(){
        isCaptain = true;
    }

    public boolean isCaptain() {
        return isCaptain;
    }
    
    public String getPosition() {
      return position;
    }

    public void setPosition(String position) {
      this.position = position;
    }
}
