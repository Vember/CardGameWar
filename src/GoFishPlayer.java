import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoFishPlayer extends Player {

    private Map<Card.Rank, List<Card>> pairs;

    public GoFishPlayer(String name, int maxHandSize) {

        super(name, maxHandSize);
        this.pairs = new HashMap<>();

    }

    public Map<Card.Rank, List<Card>> getPairs() {
        return this.pairs;
    }
}
