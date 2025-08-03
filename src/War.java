import java.util.ArrayList;

public class War {

    private final Table table;
    private final ArrayList<Card> cardsInPlay;
    private final Player player1;
    private final Player player2;

    public War() {

        this.player1 = new Player("Player1", 26);
        this.player2 = new Player("Player2", 26);
        Deck deck = new Deck();
        this.table = new Table(deck, player1, player2);
        this.cardsInPlay = new ArrayList<>();

    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public void dealHands() {
        this.getTable().dealHands();
    }

    public void listPlayerHand(Player player) {
        System.out.print(player.getName() + "'s hand (" + player.getHand().size() + "): ");
        for (Card card : player.getHand()) {
            System.out.print(card + " ");
        }
        System.out.println();
    }

    public Card playTopCard(Player player) {
        if (!player.getHand().isEmpty()) {
            Card topCard = player.getHand().getFirst();
            System.out.print(topCard);
            this.cardsInPlay.add(topCard);
            player.getHand().remove(player.getHand().getFirst());
            return topCard;
        }
        return null;
    }

    public Card playTopCard(Player player, int howMany) {
        int x = 0;
        Card topCard = null;
        while (true) {
            if (!player.getHand().isEmpty()) {
                topCard = player.getHand().getFirst();
                System.out.print(topCard);
                this.cardsInPlay.add(topCard);
                player.getHand().remove(player.getHand().getFirst());
                x++;
            } else {
                break;
            }
            if (x == howMany) {
                break;
            }
        }
        System.out.print(" ");
        return topCard;
    }

    public int roundResult(Card player1Card, Card player2Card) {
        return player1Card.compareTo(player2Card);
    }

    public boolean bothPlayersCanWar(Player player1, Player player2) {
         return player1.getHand().size() > 4 && player2.getHand().size() > 4;
    }

    public boolean player1HasMore(Player player1, Player player2) {
        return player1.getHand().size() > player2.getHand().size();
    }

    public void roundWinner(Player player1, Player player2, Card player1Top, Card player2Top) {
        int roundWonBy = roundResult(player1Top, player2Top);
        switch (roundWonBy) {
            case 1:
                System.out.println("Player 1 wins");
                for (Card card : this.cardsInPlay) {
                    player1.getHand().add(card);
                }
                this.cardsInPlay.clear();
                break;
            case -1:
                System.out.println("Player 2 wins");
                for (Card card : this.cardsInPlay) {
                    player2.getHand().add(card);
                }
                this.cardsInPlay.clear();
                break;
            case 0:
                System.out.println("It's War!");
                activateWar(player1, player2, player1Top, player2Top);
                break;
        }
        if (player1.getHand().isEmpty()) {
            System.out.println("Player2 Wins the Game!");
            System.exit(0);
        }
        if (player2.getHand().isEmpty()) {
            System.out.println("Player1 Wins the Game!");
            System.exit(0);
        }
    }

    public void activateWar(Player player1, Player player2, Card player1Top, Card player2Top) {
        if (player1.getHand().isEmpty()) {
            System.out.println("Deciding with " + player2.getName() + "'s next card!");
            System.out.println("(1) Player1: " + player1Top);
            System.out.print("(" + (player2.getHand().size() + 1) + ") Player2: " + player2Top + " ");
            player2Top = playTopCard(player2);
            System.out.println();
            roundWinner(player1, player2, player1Top, player2Top);
            return;
        } else if (player2.getHand().isEmpty()) {
            System.out.println("Deciding with " + player1.getName() + "'s next card!");
            System.out.print("(" + (player1.getHand().size() + 1) + ") Player1: " + player1Top + " ");
            player1Top = playTopCard(player1);
            System.out.println();
            System.out.println("(1) Player2: " + player2Top + " ");
            roundWinner(player1, player2, player1Top, player2Top);
            return;
        }
        if (bothPlayersCanWar(player1, player2)) {
            System.out.print("Player1: " + player1Top + " ");
            playTopCard(player1, 3);
            player1Top = playTopCard(player1);
            System.out.println();
            System.out.print("Player2: " + player2Top + " ");
            playTopCard(player2, 3);
            player2Top = playTopCard(player2);
            System.out.println();
            roundWinner(player1, player2, player1Top, player2Top);
        } else if (player1HasMore(player1, player2)) {
            System.out.print("Player1: " + player1Top + " ");
            int cardsToPlay = player2.getHand().size();
            for (int i = 0; i < cardsToPlay - 1; i++) {
                playTopCard(player1);
            }
            System.out.print(" ");
            player1Top = playTopCard(player1);
            System.out.println();
            System.out.print("Player2: " + player2Top + " ");
            for (int i = 0; i < cardsToPlay - 1; i++) {
                playTopCard(player2);
            }
            System.out.print(" ");
            player2Top = playTopCard(player2);
            System.out.println();
            roundWinner(player1, player2, player1Top, player2Top);
        } else {
            System.out.print("Player1: " + player1Top + " ");
            int cardsToPlay = player1.getHand().size();
            for (int i = 0; i < cardsToPlay - 1; i++) {
                playTopCard(player1);
            }
            System.out.print(" ");
            player1Top = playTopCard(player1);
            System.out.println();
            System.out.print("Player2: " + player2Top + " ");
            for (int i = 0; i < cardsToPlay - 1; i++) {
                playTopCard(player2);
            }
            System.out.print(" ");
            player2Top = playTopCard(player2);
            System.out.println();
            roundWinner(player1, player2, player1Top, player2Top);
        }
    }

    public void takeTurn(Player player1, Player player2) {

        System.out.print("(" + player1.getHand().size() + ") Player1: ");
        Card player1Top = playTopCard(player1);
        System.out.println();
        System.out.print("(" + player2.getHand().size() + ") Player2: ");
        Card player2Top = playTopCard(player2);
        System.out.println();
        roundWinner(player1, player2, player1Top, player2Top);

    }

    public Table getTable() {
        return this.table;
    }
}
