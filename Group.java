import java.util.ArrayList;
import java.util.Comparator;

public class Group {
    private final ArrayList<Team> group;
    private ArrayList<Team> groupWinners;
    private ArrayList<Match> matches;
    private final char id;

    public Group(char id, Team firstTeam, Team secondTeam, Team thirdTeam, Team fourthTeam) {
        this.group = new ArrayList<>();
        this.id = id;
        this.group.add(firstTeam);
        this.group.add(secondTeam);
        this.group.add(thirdTeam);
        this.group.add(fourthTeam);
    }

    public void display() {
        System.out.println("Group " + id);
        System.out.println("--------------------");
        for (int i = 0; i < group.size(); i++) {
            System.out.println(group.get(i).getName() + " - " + group.get(i).getPoints() + " pts.");
        }
        System.out.println("--------------------");
    }

    public void finalRanking() {
    
        this.groupWinners = new ArrayList<>();

        group.sort(Comparator.comparingInt((Team t) -> t.getPoints()).reversed()
        .thenComparingInt(t -> t.getGoalDifference()).reversed()
        .thenComparingInt(t -> t.getGF()).reversed());

        System.out.println("Group " + id + " standings: ");
        System.out.printf("%-19s %2s %2s %2s %2s %6s %3s%n",
        "Team", "MP", "W", "D", "L", "GF:GA", "P");

        for(Team team : group) {
            System.out.println(team);
        }

        for(int i = 0; i < 2; i++) {
            this.groupWinners.add(group.get(i));
        }
    }

    public void sorter() {

        // This method uses a selection sort-like algorithm

        int currentHigh;
        Team temp;
        for(int i = 0; i < group.size(); i++) {
            currentHigh = i; // default high value

            for(int j = i + 1; j < group.size(); j++) {
                if (group.get(j).getPoints() > group.get(i).getPoints()) {
                    currentHigh = j; // higher value found
                }
            }
            // Swap the teams to respective position
            temp = group.get(i);
            group.set(i, group.get(currentHigh));
            group.set(currentHigh, temp);
        }
    }

    public void generateMatches() {
        matches = new ArrayList<>();

        // Round robin-like matches
        for(int i = 0; i < group.size(); i++) {
            for(int j = i + 1; j < group.size(); j++) {
                matches.add(new Match(group.get(i), group.get(j)));
            }
        }

        System.out.println("Group " + id);
        
        for(Match match : matches) {
            match.displayMatch(); // display the to-be-played game
        }
    }

    public void simulateGroupStage() {
        for(Match match : matches)
            match.playGame();
    }

    public ArrayList<Team> getWinners() { return groupWinners; }
}
