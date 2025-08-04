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

    public void playTopCard(Player player, int howMany) {

        int x = 0;
        Card topCard;
        while (x < howMany) {
            if (!player.getHand().isEmpty()) {
                topCard = player.getHand().getFirst();
                System.out.print(topCard);
                this.cardsInPlay.add(topCard);
                player.getHand().remove(player.getHand().getFirst());
                x++;
            } else {
                break;
            }
        }
        System.out.print(" ");
    }

    public Card cardsToPlay(Player player, int cardsToPlay) {

        Card playerTop;
        for (int i = 0; i < cardsToPlay - 1; i++) {
            playTopCard(player);
        }
        System.out.print(" ");
        playerTop = playTopCard(player);
        System.out.println();
        return playerTop;
    }

    public boolean bothPlayersCanWar() {
         return this.player1.getHand().size() > 4 && this.player2.getHand().size() > 4;
    }

    public boolean player1HasMore() {
        return this.player1.getHand().size() > this.player2.getHand().size();
    }

    public Card playerWar(Player player, Card playerTop) {

        System.out.print(player.getName() + ": " + playerTop + " ");
        playTopCard(player, 3);
        playerTop = playTopCard(player);
        System.out.println();
        return playerTop;
    }

    public void regularWar(Card player1Top, Card player2Top) {

        player1Top = playerWar(this.player1, player1Top);
        player2Top = playerWar(this.player2, player2Top);
        roundWinner(player1Top, player2Top);
    }

    public void playerWins(Player player) {

        System.out.println(player.getName() + " wins the round!");
        for (Card card : this.cardsInPlay) {
            player.getHand().add(card);
        }
        this.cardsInPlay.clear();
    }

    public void playersTie(Card player1Top, Card player2Top) {

        System.out.println("It's War!");
        activateWarPart1(player1Top, player2Top);
    }

    public int roundResult(Card player1Card, Card player2Card) {
        return player1Card.compareTo(player2Card);
    }

    public void roundWinner(Card player1Top, Card player2Top) {

        int roundWonBy = roundResult(player1Top, player2Top);
        switch (roundWonBy) {
            case 1 -> playerWins(this.player1);
            case -1 -> playerWins(this.player2);
            case 0 -> playersTie(player1Top, player2Top);
        }
        checkForVictor();
    }

    public void lessThan5Cards(Player player, Card player1Top, Card player2Top) {

        int cardsToPlay = player.getHand().size();
        System.out.print(this.player1.getName() + ": " + player1Top + " ");
        player1Top = cardsToPlay(player1, cardsToPlay);
        System.out.print(this.player2.getName() + ": " + player2Top + " ");
        player2Top = cardsToPlay(player2, cardsToPlay);
        roundWinner(player1Top, player2Top);
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

    public void activateWarPart1(Card player1Top, Card player2Top) {

        if (this.player1.getHand().isEmpty()) {
            lastCardPlayer1(player1Top, player2Top);
            return;
        } else if (this.player2.getHand().isEmpty()) {
            lastCardPlayer2(player1Top, player2Top);
            return;
        }
        activateWarPart2(player1Top, player2Top);
    }

    public void activateWarPart2(Card player1Top, Card player2Top) {

        if (bothPlayersCanWar()) {
            regularWar(player1Top, player2Top);
        } else if (player1HasMore()) {
            lessThan5Cards(this.player2, player1Top, player2Top);
        } else {
            lessThan5Cards(this.player1, player1Top, player2Top);
        }
    }

    public void checkForVictor() {

        if (player1.getHand().isEmpty()) {
            System.out.println(player2.getName() + " wins the game!");
            System.exit(0);
        }
        if (player2.getHand().isEmpty()) {
            System.out.println(player1.getName() + " wins the game!");
            System.exit(0);
        }
    }

    public Card playersTopCard(Player player) {
        System.out.print("(" + player.getHand().size() + ") " + player.getName() + ": ");
        Card playerTop = playTopCard(player);
        System.out.println();
        return playerTop;
    }

    public void takeTurn() {

        Card player1Top = playersTopCard(this.player1);
        Card player2Top = playersTopCard(this.player2);
        roundWinner(player1Top, player2Top);

    }

    public Table getTable() {
        return this.table;
    }
}
