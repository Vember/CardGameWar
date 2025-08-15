import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GoFish {

    private final GoFishPlayer[] players;
    private final Deck deck;
    private final Table table;
    private int currentPlayerIndex;
    private GoFishPlayer currentPlayerTurn;

    public GoFish(Deck deck, Table table, GoFishPlayer... player) {

        this.players = player;
        this.deck = deck;
        this.table = table;
        this.currentPlayerIndex = 0;
        this.currentPlayerTurn = this.players[this.currentPlayerIndex];
        
    }

    public GoFishPlayer[] getPlayers() {
        return this.players;
    }

    public Table getTable() {
        return this.table;
    }

    public GoFishPlayer getCurrentPlayerTurn() {
        return this.currentPlayerTurn;
    }

    public void nextPlayerTurn() {

        if (this.currentPlayerIndex == this.players.length - 1) {
            this.currentPlayerIndex = 0;
            this.currentPlayerTurn = this.players[0];
        } else {
            this.currentPlayerTurn = this.players[++this.currentPlayerIndex];
        }
    }

    public static void printPlayerPairs(Map<Card.Rank, List<Card>> playerPairs) {

        if (playerPairs.isEmpty()) {
            System.out.println("No pairs made yet.");
            return;
        }

        String formattedPairs = playerPairs.entrySet().stream()
                .map(entry -> entry.getKey().getValue())
                .collect(Collectors.joining(" "));
        System.out.print("Pairs: " + formattedPairs);
    }

    public void listOtherPlayers(GoFishPlayer isPicking) {

        int playerCount = 1;
        for (GoFishPlayer player : this.players) {
            if (player != isPicking) {
                System.out.println(playerCount + ") " + player.getName() + " ");
                printPlayerPairs(player.getPairs());
                System.out.println();
                playerCount++;
            }
        }
    }

    public void listCurrentPlayerHand() {

        for (Card card : this.currentPlayerTurn.getHand()) {
            System.out.print(card.toString() + " ");
        }

    }

    public Card checkTargetForCard(Card targetCard, GoFishPlayer targetPlayer) {

        for (Card card : targetPlayer.getHand()) {
            if (card.compareTo(targetCard) == 0) {
                return card;
            }
        }
        return null;
    }

    public void extractFromPlayerToPlayer(Card card, GoFishPlayer toPlayer, GoFishPlayer fromPlayer) {

        fromPlayer.getHand().remove(card);
        toPlayer.getHand().add(card);

    }

    public void checkForPairs(GoFishPlayer player) {

        Map<Card.Rank, List<Card>> groupedCards = player.getHand().stream()
                .collect(Collectors.groupingBy(Card::getRank));

        groupedCards.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 2)
                .forEach(entry -> {
                    Card.Rank rank = entry.getKey();
                    List<Card> cardsOfSameRank = entry.getValue();

                    List<Card> newPair = new ArrayList<>();
                    newPair.add(cardsOfSameRank.get(0));
                    newPair.add(cardsOfSameRank.get(1));

                    player.getPairs().put(rank, newPair);
                    player.increaseScore();
                });
        
        removePairedCardsFromHand(player);
    }

    public static void removePairedCardsFromHand(GoFishPlayer player) {

        for (Map.Entry<Card.Rank, List<Card>> entry : player.getPairs().entrySet()) {
            List<Card> pairedCards = entry.getValue();
            player.getHand().removeAll(pairedCards);
        }
    }

    public void playerGoesFishing(GoFishPlayer player) {

        if (!this.deck.getCards().isEmpty()) {
            player.getHand().add(this.deck.removeTopCardFromDeck());
        }
        checkForPairs(player);
    }

    public GoFishPlayer checkForVictor() {

        GoFishPlayer winner = this.players[0];
        if (this.deck.getCards().isEmpty()) {
            for (GoFishPlayer player : this.players) {
                if (winner.getScore() < player.getScore()) {
                    winner = player;
                }
            }
        } else {
            winner = null;
        }
        return winner;
    }

    public void declareVictor(GoFishPlayer player) {
        System.out.println(player.getName() + " has won the game with " + player.getScore() + " pairs!");
    }

}
