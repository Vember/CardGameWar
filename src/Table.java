public class Table {

    private final Player[] players;
    private final Deck deck;

    public Table(Deck deck, Player... player) {

        this.deck = deck;
        this.players = player;

    }

    public void dealHands() {
        int handSize = this.players[0].getMaxHandSize();
        for (int i = 0; i < handSize; i++) {
            for (Player player : this.players) {
                player.dealToPlayer(this.deck);
            }
        }
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public Deck getDeck() {
        return this.deck;
    }

}
