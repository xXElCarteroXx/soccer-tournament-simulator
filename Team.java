public class Team {
    private final String name;
    private final int median, attack, midfield, defense;
    private int points, wins, losses, draws, goalsFor, goalsConceded, goalDifference, games, penalties;

    public Team(String theName, int att, int mid, int def) {
        this.name = theName;
        this.attack = att;
        this.midfield = mid;
        this.defense = def;
        this.median = (att + mid + def) / 3;
        this.points = 0;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.goalsFor = 0;
        this.goalsConceded = 0;
        this.games = 0;
        this.penalties = 0;
    }

    public void display() { System.out.println("Team: " + name + " | Med: " + median); }

    public void updateStats(int goalsScored, int goalsAgainst) {
        goalsFor += goalsScored;
        goalsConceded += goalsAgainst;
        goalDifference = goalsScored - goalsAgainst;
        games++;

        if (goalsScored > goalsAgainst) {
            wins++;
            points += 3; // win = 3 points
        } else if (goalsScored == goalsAgainst) {
            draws++;
            points++; // draw = 1 point
        } else {
            losses++; // loss = 0 points
        }
    }

    // Statistics handling section
    public void updatePens(int pens) { penalties += pens; }
    public void setPoints(int thePoints) { points = thePoints; }
    public void resetPoints() { points = 0; }
    public void incrOne() { points++; }
    public void incrTwo() { points += 2; }
    public void incrThree() { points += 3; }

    // Info request section
    public int getWins() { return wins; }
    public int getGF() { return goalsFor; }
    public int getGA() { return goalsConceded; }
    public int getGoalDifference() { return goalDifference; }
    public int getLosses() { return losses; }
    public int getDraws() { return draws; }
    public int getMedian() { return median; }
    public String getName() { return name; }
    public int getAttack() { return attack; }
    public int getMid() { return midfield; }
    public int getDef() { return defense; }
    public int getPoints() { return points; }
    public int getPenalties() { return penalties; }

    @Override
    public String toString() {
        return String.format("%-19s %2d %2d %2d %2d %3d:%-3d %2d",
        name, games, wins, draws, losses, goalsFor, goalsConceded, points);
    }
}
