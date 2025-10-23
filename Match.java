import java.util.Random;

public class Match {
    private final Team bigTeam, smallTeam;
    private Team winnerTeam = null;

    public Match(Team firstTeam, Team secondTeam) {
        if (firstTeam.getMedian() > secondTeam.getMedian()) {
            this.bigTeam = firstTeam;
            this.smallTeam = secondTeam;
        } else {
            this.bigTeam = secondTeam;
            this.smallTeam = firstTeam;
        }
    }

    public void playGame() {
        Random random = new Random();

        int loserScore = random.nextInt(5);
        int winnerScore = random.nextInt(3) + loserScore;
        int winner = random.nextInt(6);

        if (winner <= 4) { // team of greater median has a greater chance of winning
            bigTeam.updateStats(winnerScore, loserScore);
            smallTeam.updateStats(loserScore, winnerScore);
            System.out.printf("Match: %19s %d - %d %-19s%n",
            bigTeam.getName(), winnerScore, loserScore, smallTeam.getName());
        } else { // team of less median can also win
            bigTeam.updateStats(loserScore, winnerScore);
            smallTeam.updateStats(winnerScore, loserScore);
            System.out.printf("Match: %19s %d - %d %-19s%n",
            bigTeam.getName(), loserScore, winnerScore, smallTeam.getName());
        }
    }

    public Team getWinner() { return winnerTeam; }

    public void playKnockout() {
        Random random = new Random();

        int loserScore = random.nextInt(5);
        int winnerScore = random.nextInt(3) + loserScore;
        int winner = random.nextInt(6);

        if (winnerScore != loserScore) {
            if (winner <= 4) {
                System.out.printf("Match: %19s %d - %d %-19s%n",
                bigTeam.getName(), winnerScore, loserScore, smallTeam.getName());
                this.winnerTeam = bigTeam;
            } else {
                System.out.printf("Match: %19s %d - %d %-19s%n",
                bigTeam.getName(), loserScore, winnerScore, smallTeam.getName());
                this.winnerTeam = smallTeam;
            } 
        } else { // if draw, simulate penalties
            int teamOnePenalties = random.nextInt(5);
            int teamTwoPenalties = random.nextInt(5);
            bigTeam.updatePens(teamOnePenalties);
            smallTeam.updatePens(teamTwoPenalties);

            if (bigTeam.getPenalties() != smallTeam.getPenalties()) {
                if (bigTeam.getPenalties() > smallTeam.getPenalties()) { // big team won
                    System.out.printf("Match: %19s [%d] %d - %d [%d] %-19s%n",
                    bigTeam.getName(), bigTeam.getPenalties(), winnerScore,
                    loserScore, smallTeam.getPenalties(), smallTeam.getName());
                    this.winnerTeam = bigTeam;
                } else { // small team won
                    System.out.printf("Match: %19s [%d] %d - %d [%d] %-19s%n",
                    bigTeam.getName(), bigTeam.getPenalties(), winnerScore,
                    loserScore, smallTeam.getPenalties(), smallTeam.getName());
                    this.winnerTeam = smallTeam;
                }
            } else {
                while (bigTeam.getPenalties() == smallTeam.getPenalties()) { // both teams scored the same amount of penalties
                    teamOnePenalties = random.nextInt(5);
                    teamTwoPenalties = random.nextInt(5);
                    bigTeam.updatePens(teamOnePenalties);
                    smallTeam.updatePens(teamTwoPenalties);
                }

                if (bigTeam.getPenalties() > smallTeam.getPenalties()) { // big team won
                    System.out.printf("Match: %19s [%d] %d - %d [%d] %-19s%n",
                    bigTeam.getName(), bigTeam.getPenalties(), winnerScore,
                    loserScore, smallTeam.getPenalties(), smallTeam.getName());
                    this.winnerTeam = bigTeam;
                } else { // small team won
                    System.out.printf("Match: %19s [%d] %d - %d [%d] %-19s%n",
                    bigTeam.getName(), bigTeam.getPenalties(), winnerScore,
                    loserScore, smallTeam.getPenalties(), smallTeam.getName());
                    this.winnerTeam = smallTeam;
                }
            }
        }
    }

    public void displayMatch() {
        System.out.printf("Match: %19s vs %-19s%n", bigTeam.getName(), smallTeam.getName());
    }
}
