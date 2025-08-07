import java.util.ArrayList;
import java.util.Collections;

public class Player {

    private final String name;
    private final ArrayList<Card> hand;
    private int maxHandSize;

    public Player() {

        this.name = null;
        this.hand = new ArrayList<>();
        this.maxHandSize = 0;

    }

    public Player(String name) {

        this.name = name;
        this.hand = new ArrayList<>();
        this.maxHandSize = 0;
    }

    public Player(String name, int maxHandSize) {

        this.name = name;
        this.hand = new ArrayList<>();
        this.maxHandSize = maxHandSize;
    }

    public void shuffleHand() {
        Collections.shuffle(this.hand);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public void setMaxHandSize(int maxHandSize) {
        this.maxHandSize = maxHandSize;
    }

    public int getMaxHandSize() {
        return this.maxHandSize;
    }

    public void dealToPlayer(Deck deck) {
        if (!(this.maxHandSize <= this.hand.size())) {
            this.hand.add(deck.removeTopCardFromDeck());
        }
    }
}
