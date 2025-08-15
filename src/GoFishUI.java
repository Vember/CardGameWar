import java.util.Scanner;

public class GoFishUI {

    private final GoFish currentGame;
    private final Scanner scan;

    public GoFishUI(GoFish goFishGame) {

        this.currentGame = goFishGame;
        this.scan = new Scanner(System.in);

    }

    public void takeTurn() {

        this.currentGame.listOtherPlayers(this.currentGame.getCurrentPlayerTurn());
        System.out.print(this.currentGame.getCurrentPlayerTurn().getName() + "'s Hand: ");
        this.currentGame.listCurrentPlayerHand();
        System.out.println();
        System.out.println("Which player to target? (Type name)");
        GoFishPlayer target;
        boolean playerFound = false;
        while (!playerFound) {
            String input = scan.nextLine();
            input = input.toLowerCase();
            for (GoFishPlayer player : this.currentGame.getPlayers()) {
                if (input.equals(player.getName().toLowerCase())) {
                    target = player;
                    playerFound = true;
                    break;
                }
            }
            if (!playerFound) {
                System.out.println("Player not recognized. Please try again.");
            }
        }
        System.out.println("Which card to ask for?");
        boolean cardInHand = false;
        while (!cardInHand) {
            String input = scan.nextLine();
            Card targetCard;
            for (Card card : this.currentGame.getCurrentPlayerTurn().getHand()) {
                if (input.toUpperCase().equals(card.getRank().getValue())) {
                    targetCard = card;
                    cardInHand = true;
                    break;
                }
            }
            if (!cardInHand) {
                System.out.println("Card not recognized in " + this.currentGame.getCurrentPlayerTurn().getName() + "'s hand. Please try again.");
            }
        }
        this.currentGame.nextPlayerTurn();

    }

}
