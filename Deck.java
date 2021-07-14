import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {

    private int SHUFFLE_CUT_REPITITIONS = 3;
    private int TOTAL_NUM_CARDS_IN_DECK = 52;

    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>(52);

        //add 13 cards fr each suit into the deck
        String suit = Card.SUIT_DIAMOND;
        for (int i = 0; i < 4; i++)
        {
            suit = switch (i) {
                case 1 -> Card.SUIT_CLUB;
                case 2 -> Card.SUIT_SPADE;
                case 3 -> Card.SUIT_HEART;
                default -> Card.SUIT_DIAMOND;
            };

            deck.add(new Card(Card.CARD_ACE, suit));
            for (int j = 2; j <= 10; j++)
            {
                deck.add(new Card(String.valueOf(j), suit));
            }
            deck.add(new Card(Card.CARD_JACK, suit));
            deck.add(new Card(Card.CARD_QUEEN, suit));
            deck.add(new Card(Card.CARD_KING, suit));
        }

        //Need to shuffle and cut deck to randomize it
        for (int i = 0; i < SHUFFLE_CUT_REPITITIONS; i++) {
            shuffle();
            cut();
        }
    }

    public void shuffle() {
        if (deck.isEmpty()) {
            System.err.println("Deck is Empty while trying to shuffle");
            throw new InvalidParameterException("Deck is Empty while trying to shuffle");
        }

        //Create two subsets of cards
        int halfDeckSize = deck.size() / 2;
        int randomOffset = (ThreadLocalRandom.current().nextInt(0, 2) * (deck.size() / 10));
        ArrayList<Card> subSet = new ArrayList<Card>();
        try {
            for (int i = 0; i < halfDeckSize + randomOffset; i++) {
                subSet.add(popTopCard());
            }
        } catch (Exception e) {}

        //Mix the cards from the two subsets into one deck
        ArrayList<Card> newDeck = new ArrayList<Card>();
        int deckIndex = 0;
        int subSetIndex = 0;
        while (newDeck.size() < TOTAL_NUM_CARDS_IN_DECK)
        {

            int numCardsToMove = ThreadLocalRandom.current().nextInt(1, 4);
            for (int j = 0; j < numCardsToMove && deckIndex < deck.size(); j++)
                newDeck.add(deck.get(deckIndex++));
            for (int k = 0; k < numCardsToMove && subSetIndex < subSet.size(); k++)
                newDeck.add(subSet.get(subSetIndex++));
        }
        deck = newDeck;
    }

    public void cut() {
        if (deck.isEmpty()) {
            System.err.println("Deck is Empty while trying to cut");
            throw new InvalidParameterException("Deck is Empty while trying to cut");
        }

        //Find a random middle portion of deck and put top half under the bottom half
        int halfDeckSize = deck.size() / 2;
        int randomOffset = (int) (Math.random() * deck.size() / 10);

        ArrayList<Card> subSet = new ArrayList<Card>();
        try {
            for (int i = 0; i < halfDeckSize + randomOffset; i++) {
                subSet.add(popTopCard());
            }
        } catch (Exception e) {}

        deck.addAll(subSet);
    }

    public Card popTopCard() throws Exception {
        if (deck.isEmpty())
        {
            System.err.println("Deck is EMPTY while trying to deal a card");
            throw new Exception("Deck is EMPTY while trying to deal a card");
        }
        return deck.remove(0);
    }

    public void addCard(Card newCard) {
        deck.add(newCard);
    }
}
