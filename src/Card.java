public class Card {

    public enum Rank {

        TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
        JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

        private final String value;

        Rank(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Suit {

        c("Black"), d("Red"), h("Red"), s("Black");

        private final String color;

        Suit(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {

        this.suit = suit;
        this.rank = rank;

    }

    public Rank getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return rank.value + suit;
    }

    public int compareTo(Card other) {
        return this.rank.compareTo(other.rank);
    }

}
