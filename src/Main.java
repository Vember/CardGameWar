import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        War war = new War();
        war.getTable().getDeck().shuffleDeck();
        war.dealHands();
        //Commented out manual turn iteration
        /*
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
         */
        //Automatic turns until winner
        while (true) {
            war.takeTurn(war.getPlayer1(), war.getPlayer2());
            war.getPlayer1().shuffleHand();
            war.getPlayer2().shuffleHand();
        }
    }
}
