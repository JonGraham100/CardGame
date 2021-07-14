import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void addCardToHand(Card newCard) {
        hand.add(newCard);
    }

    public int getTotalHandValue() {
        int totalValue = 0;
        for (Card card : hand)
            totalValue += card.getValue();

        return totalValue;
    }

    public String revealHand() {
        StringBuffer buffer = new StringBuffer();
        for (Card card : hand)
            buffer.append(card.toString()).append("\n\r");
        buffer.append(getName()).append("'s Total:").append(getTotalHandValue());
        return buffer.toString();
    }

    public String getName() {
        return name;
    }
}
