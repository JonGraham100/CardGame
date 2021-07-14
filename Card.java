import java.security.InvalidParameterException;

public class Card {
    //Suits
    public static String SUIT_DIAMOND = "Diamonds";
    public static String SUIT_CLUB = "Clubs";
    public static String SUIT_SPADE = "Spades";
    public static String SUIT_HEART = "Hearts";

    //Odd named cards
    public static String CARD_ACE = "Ace";
    public static String CARD_JACK = "Jack";
    public static String CARD_QUEEN = "Queen";
    public static String CARD_KING = "King";


    //Value is Numerical value
    //Face card values are set based off of blackjack rules.
    public static int ACE_VALUE = 1;
    public static int JACK_VALUE = 10;
    public static int QUEEN_VALUE = 10;
    public static int KING_VALUE = 10;

    private final String num;
    private final String suit;

    public Card(String num, String suit) {
        //Validating input
        if (!SUIT_DIAMOND.equals(suit) && !SUIT_CLUB.equals(suit) && !SUIT_SPADE.equals(suit) && !SUIT_HEART.equals(suit))
        {
            System.err.println("Invalid Suit");
            throw new InvalidParameterException("Invalid Suit");
        }
        try {
            if (!CARD_ACE.equals(num) && !CARD_JACK.equals(num) && !CARD_QUEEN.equals(num) && !CARD_KING.equals(num) &&
                    !(Integer.parseInt(num) < 2) && !(Integer.parseInt(num) < 11)) {
                System.err.println("Invalid Card Number");
                throw new InvalidParameterException("Invalid Card Number");
            }
        } catch (NumberFormatException nfe)
        {
            System.err.println("Not a Numerical Card Number");
            throw nfe;
        }

        //Create Card
        this.num = num;
        this.suit = suit;
    }

    public int getValue() {
        if (CARD_ACE.equals(num))
            return ACE_VALUE;
        if (CARD_JACK.equals(num))
            return JACK_VALUE;
        if (CARD_QUEEN.equals(num))
            return QUEEN_VALUE;
        if (CARD_KING.equals(num))
            return KING_VALUE;

        return Integer.parseInt(num);
    }

    @Override
    public String toString() {
        return num + " of " + suit;
    }
}
