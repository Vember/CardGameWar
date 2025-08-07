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

        for (Card.Suit suit : Card.Suit.values()) {

            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
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
