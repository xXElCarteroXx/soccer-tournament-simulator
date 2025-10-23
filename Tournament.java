import java.util.ArrayList;

public class Tournament {
    private ArrayList<Team> eTeams, totalTeams, quarterFinals, semiFinals, finale;
    private ArrayList<Match> matches;
    private Match finalGame;
    private ArrayList<Group> groups;
    private String name;

    public Tournament(String theName) { 
        this.eTeams = new ArrayList<>();
        this.totalTeams = new ArrayList<>();
        this.name = theName;
    }

    public void addTeam(Team team) {
        totalTeams.add(team);
    }

    public void knockoutTeams() {
        for(int i = 0; i < eTeams.size(); i++) {
            System.out.println("Knockout teams:");
            System.out.println(eTeams.get(i).getName());
        }
    }

    public void groupStage() {
        String letters = "ABCDEFGH";
        int chunkSize = 4;
        int i, j, k, l;
        int idIndex = 0;
        groups = new ArrayList<>();

        for(i = 0; i < totalTeams.size(); i += chunkSize) {
            j = i + 1;
            k = j + 1;
            l = k + 1;
            groups.add(new Group(letters.charAt(idIndex),
            totalTeams.get(i), totalTeams.get(j), totalTeams.get(k), totalTeams.get(l)));
            idIndex++;
        }

        for(Group group : groups) {
            System.out.println("-----------------------------------------------");
            group.generateMatches();
            System.out.println("-----------------------------------------------");
            group.simulateGroupStage();
            System.out.println("-----------------------------------------------");
            group.finalRanking();
            eTeams.addAll(group.getWinners());
        }
    }

    public void generateKnockout() {
        this.matches = new ArrayList<>();
        int chunkSize = 2;
        int j;

        for(int i = 0; i < eTeams.size(); i += chunkSize) {
            j = i + 1;
            matches.add(new Match(eTeams.get(i), eTeams.get(j)));
        }

        System.out.println("-----------------------------------------");
        System.out.println("Round of 16");
        for (Match match : matches) {
            match.displayMatch();
        }
        System.out.println("-----------------------------------------");
    }

    public void quarterFinals() {
        this.matches = new ArrayList<>();
        this.semiFinals = new ArrayList<>();
        int chunkSize = 2;
        int j;
        
        for (int i = 0; i < quarterFinals.size(); i += chunkSize) {
            j = i + 1;
            matches.add(new Match(quarterFinals.get(i), quarterFinals.get(j)));
        }

        System.out.println("-----------------------------------------");
        System.out.println("Quarter Finals");
        for (Match match : matches) {
            match.displayMatch();
        }

        System.out.println("-----------------------------------------");

        for (Match match : matches) {
            match.playKnockout();
            semiFinals.add(match.getWinner());
        }
    }

    public void semiFinal() {
        this.matches = new ArrayList<>();
        this.finale = new ArrayList<>();
        int chunkSize = 2;
        int j;

        for (int i = 0; i < semiFinals.size(); i += chunkSize) {
            j = i + 1;
            matches.add(new Match(semiFinals.get(i), semiFinals.get(j)));
        }

        System.out.println("----------------------------------------------------------------");
        System.out.println("Semifinals");
        
        for (Match match : matches)
            match.displayMatch();
        
        System.out.println("----------------------------------------------------------------");

        for (Match match : matches) {
            match.playKnockout();
            finale.add(match.getWinner());
        }
    }

    public void finale() {
        this.finalGame = new Match(finale.get(0), finale.get(1));

        System.out.println("----------------------------------------------------------------");
        System.out.println("Final");
        finalGame.displayMatch();
        System.out.println("----------------------------------------------------------------");
        finalGame.playKnockout();
        System.out.println(name + " winner: " + finalGame.getWinner().getName());
    }

    public void roundOf16() {
        this.quarterFinals = new ArrayList<>();

        System.out.println("-----------------------------------------------------");
        System.out.println("Round of 16:");
        
        for(Match match : matches) {
            match.playKnockout();
            quarterFinals.add(match.getWinner());
        }
        System.out.println("-----------------------------------------------------");
    }

    public void displayQuarterFinalTeams() {
        System.out.println("Quarter finals teams:");
        
        for (int i = 0; i < quarterFinals.size(); i++)
            System.out.print(quarterFinals.get(i).getName() + ", ");
        System.out.println();
    }
    public int displayTotSize() { return totalTeams.size(); }
}
