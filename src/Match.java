public class Match {

  private Team team1;
  private Team team2;
  private int team1score = 0;
  private int team2score = 0;
  private Stadium stadium;
  private String date;
  private boolean wasPlayed = false;
  private String phase;

  public Match(Team team1, Team team2, int team1score, int team2score, Stadium stadium, int wasPlayed, String date,
      String phase) {
    this.team1 = team1;
    this.team1score = team1score;
    this.team2 = team2;
    this.team2score = team2score;
    this.stadium = stadium;
    this.date = date;
    this.wasPlayed = wasPlayed != 0;
    this.phase = phase;
  }

  public void loadResult(int team1score, int team2score) {
    this.team1score = team1score;
    this.team1.addGoals(team1score);
    this.team2score = team2score;
    this.team1.addGoals(team2score);


    if (team1score > team2score) {
      team1.addPoints(3);
    } else if (team1score < team2score) {
      team2.addPoints(3);
    } else {
      team1.addPoints(1);
      team2.addPoints(1);
    }
  }

  public void setResult(int team1score, int team2score) {
    if (!this.wasPlayed) {
      this.team1score = team1score;
      this.team1.addGoals(team1score);
      this.team2score = team2score;
      this.team1.addGoals(team2score);

      if(team1score > team2score){
        team1.addPoints(3);
      }
      else if(team1score < team2score){
        team2.addPoints(3);
      }
      else{
        team1.addPoints(1);
        team2.addPoints(1);
      }
      this.wasPlayed = true;

    } else {
      System.out.println("Match was already played");
    }
  }

  public Team getWinner() {
    if (this.wasPlayed) {
      if (team1score > team2score) {
        return team1;
      } else if (team1score < team2score) {
        return team2;
      }
    }

    return null;
  }

  public boolean wasPlayed() {
    return this.wasPlayed;
  }

  public Stadium getStadium() {
    return stadium;
  }

  public String getPhase() {
    return phase;
  }

  public String getDate() {
    return date;
  }

  public Team getTeam1() {
    return team1;
  }

  public Team getTeam2() {
    return team2;
  }

  public int getTeam1score() {
    return team1score;
  }

  public int getTeam2score() {
    return team2score;
  }
}
