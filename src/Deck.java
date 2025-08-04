import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private final ArrayList<Card> cards;
    private final ArrayList<Card> discardPile;

    public Deck() {

        this.cards = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        initDeck();
    }

    public void initDeck() {

        String[] suits = {"s", "c", "h", "d"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            String color;
            if (suit.equals("s") || suit.equals("c")) {
                color = "Black";
            } else {
                color = "Red";
            }
            int rank = 2;
            for (String value : values) {
                cards.add(new Card(color, value, suit, rank));
                rank++;
            }
        }
    }

    public void listCardsInDeck() {
        for (Card card : this.cards) {
            System.out.print(card + " ");
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(this.cards);
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public Card removeTopCardFromDeck() {
        Card card = this.cards.getFirst();
        this.cards.remove(this.cards.getFirst());
        return card;
    }

    public void returnCardToDeck(Card card) {
        this.cards.add(card);
    }

    public void addCardToDiscardPile(Card card) {
        this.discardPile.add(card);
    }

}
