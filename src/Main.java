import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Commented out War
        /*
        Scanner scan = new Scanner(System.in);

        War war = new War();
        war.getTable().getDeck().shuffleDeck();
        war.dealHands();
        while (true) {
            System.out.println("Enter Key -> Next Turn (0: Quit)");
            String input = scan.nextLine();
            if (input.equals("0")) {
                System.exit(0);
            }
            war.takeTurn(war.getPlayer1(), war.getPlayer2());
            war.getPlayer1().shuffleHand();
            war.getPlayer2().shuffleHand();
        }
        while (true) {
            war.takeTurn();
        }
        */

        GoFishPlayer player1 = new GoFishPlayer("Player1", 7);
        GoFishPlayer player2 = new GoFishPlayer("Player2", 7);
        GoFishPlayer player3 = new GoFishPlayer("Player3", 7);
        GoFishPlayer player4 = new GoFishPlayer("Player4", 7);
        Deck deck = new Deck();
        Table table = new Table(deck, player1, player2, player3, player4);
        GoFish goFishGame = new GoFish(deck, table, player1, player2, player3, player4);
        goFishGame.getTable().getDeck().shuffleDeck();
        goFishGame.getTable().dealHands();
        GoFishUI goFishUI = new GoFishUI(goFishGame);

        int turnCount = 1;
        while (true) {

            System.out.println("Turn: " + turnCount + " (" + goFishGame.getCurrentPlayerTurn().getName() + "'s turn)");
            goFishUI.takeTurn();
            goFishGame.checkForPairs(goFishGame.getCurrentPlayerTurn());
            turnCount++;
        }

    }
}
