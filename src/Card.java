public class Card {

    private final String color;
    private final String value;
    private final String suit;
    private final int rank;

    public Card(String color, String value, String suit, int rank) {

        this.color = color;
        this.value = value;
        this.suit = suit;
        this.rank = rank;

    }

    @Override
    public String toString() {
        return this.value + this.suit;
    }

    public int compareTo(Card other) {
        return Integer.compare(this.rank, other.rank);
    }

}
