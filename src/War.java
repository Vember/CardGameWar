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

    public boolean bothPlayersCanWar() {
         return this.player1.getHand().size() > 4 && this.player2.getHand().size() > 4;
    }

    public boolean player1HasMore() {
        return this.player1.getHand().size() > this.player2.getHand().size();
    }

    public void player1Wins() {

        System.out.println(this.player1.getName() + " wins the round!");
        for (Card card : this.cardsInPlay) {
            this.player1.getHand().add(card);
        }
        this.cardsInPlay.clear();
    }

    public void player2Wins() {

        System.out.println(this.player2.getName() + " wins the round!");
        for (Card card : this.cardsInPlay) {
            this.player2.getHand().add(card);
        }
        this.cardsInPlay.clear();
    }

    public void playersTie(Card player1Top, Card player2Top) {

        System.out.println("It's War!");
        activateWar(player1Top, player2Top);
    }

    public void checkForGameEnd() {

        if (player1.getHand().isEmpty()) {
            System.out.println(player2.getName() + " wins the game!");
            System.exit(0);
        }
        if (player2.getHand().isEmpty()) {
            System.out.println(player1.getName() + " wins the game!");
            System.exit(0);
        }
    }

    public void roundWinner(Card player1Top, Card player2Top) {

        int roundWonBy = roundResult(player1Top, player2Top);
        switch (roundWonBy) {
            case 1 -> player1Wins();
            case -1 -> player2Wins();
            case 0 -> playersTie(player1Top, player2Top);
        }
        checkForGameEnd();
    }

    public void lastCardPlayer1(Card player1Top, Card player2Top) {

        System.out.println("Deciding with " + this.player2.getName() + "'s next card!");
        System.out.println("(1) " + this.player1.getName() + ": " + player1Top);
        System.out.print("(" + (this.player2.getHand().size() + 1) + ") " + this.player2.getName() + ": " + player2Top + " ");
        player2Top = playTopCard(this.player2);
        System.out.println();
        roundWinner(player1Top, player2Top);
    }

    public void lastCardPlayer2(Card player1Top, Card player2Top) {

        System.out.println("Deciding with " + this.player1.getName() + "'s next card!");
        System.out.print("(" + (this.player1.getHand().size() + 1) + ") " + this.player1.getName() + ": " + player1Top + " ");
        player1Top = playTopCard(this.player1);
        System.out.println();
        System.out.println("(1) " + this.player2.getName() + ": " + player2Top);
        roundWinner(player1Top, player2Top);
    }

    public void regularWar(Card player1Top, Card player2Top) {

        System.out.print(this.player1.getName() + ": " + player1Top + " ");
        playTopCard(this.player1, 3);
        player1Top = playTopCard(this.player1);
        System.out.println();
        System.out.print(player2.getName() + ": " + player2Top + " ");
        playTopCard(this.player2, 3);
        player2Top = playTopCard(this.player2);
        System.out.println();
        roundWinner(player1Top, player2Top);
    }

    public void lessThan5Player1(Card player1Top, Card player2Top) {

        System.out.print(this.player1.getName() + ": " + player1Top + " ");
        int cardsToPlay = this.player1.getHand().size();
        for (int i = 0; i < cardsToPlay - 1; i++) {
            playTopCard(this.player1);
        }
        System.out.print(" ");
        player1Top = playTopCard(this.player1);
        System.out.println();
        System.out.print(this.player2.getName() + ": " + player2Top + " ");
        for (int i = 0; i < cardsToPlay - 1; i++) {
            playTopCard(this.player2);
        }
        System.out.print(" ");
        player2Top = playTopCard(this.player2);
        System.out.println();
        roundWinner(player1Top, player2Top);
    }

    public void lessThan5Player2(Card player1Top, Card player2Top) {

        System.out.print(this.player1.getName() + ": " + player1Top + " ");
        int cardsToPlay = this.player2.getHand().size();
        for (int i = 0; i < cardsToPlay - 1; i++) {
            playTopCard(this.player1);
        }
        System.out.print(" ");
        player1Top = playTopCard(this.player1);
        System.out.println();
        System.out.print(this.player2.getName() + ": " + player2Top + " ");
        for (int i = 0; i < cardsToPlay - 1; i++) {
            playTopCard(this.player2);
        }
        System.out.print(" ");
        player2Top = playTopCard(this.player2);
        System.out.println();
        roundWinner(player1Top, player2Top);
    }

    public void activateWar(Card player1Top, Card player2Top) {

        if (this.player1.getHand().isEmpty()) {
            lastCardPlayer1(player1Top, player2Top);
            return;
        } else if (this.player2.getHand().isEmpty()) {
            lastCardPlayer2(player1Top, player2Top);
            return;
        }
        if (bothPlayersCanWar()) {
            regularWar(player1Top, player2Top);
        } else if (player1HasMore()) {
            lessThan5Player2(player1Top, player2Top);
        } else {
            lessThan5Player1(player1Top, player2Top);
        }
    }

    public void takeTurn() {

        System.out.print("(" + this.player1.getHand().size() + ") " + this.player1.getName() + ": ");
        Card player1Top = playTopCard(this.player1);
        System.out.println();
        System.out.print("(" + this.player2.getHand().size() + ") " + this.player2.getName() + ": ");
        Card player2Top = playTopCard(this.player2);
        System.out.println();
        roundWinner(player1Top, player2Top);

    }

    public Table getTable() {
        return this.table;
    }
}
