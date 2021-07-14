import com.sun.source.tree.TryTree;

import java.util.ArrayList;
import java.util.List;


public class CardGame {
    private static int NUM_PLAYERS = 5;
    private static int NUM_CARDS_TO_DEAL = 2;

    public static void main(String[] args) {
        //Setup deck
        Deck deck = new Deck();

        //Setup players and dealer
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < NUM_PLAYERS; ) {
            players.add(new Player("Player " + ++i));
        }
        Player dealer = new Player("Dealer");

        //Deal out the cards
        try {
            for (int i = 0; i < NUM_CARDS_TO_DEAL; i++) {
                for (Player player : players) {
                    player.addCardToHand(deck.popTopCard());
                }
                dealer.addCardToHand(deck.popTopCard());
            }
        } catch (Exception e) {}

        //Check results
        for (Player player: players)
        {
            System.out.println("Player: ".concat(player.getName()));
            System.out.println(player.revealHand());
            System.out.println("Player: ".concat(dealer.getName()));
            System.out.println(dealer.revealHand());

            Player winner = null;
            if (player.getTotalHandValue() > dealer.getTotalHandValue())
                winner = player;
            else if (player.getTotalHandValue() < dealer.getTotalHandValue())
                winner = dealer;

            if (winner != null)
                System.out.println(winner.getName() + " is the Winner!");
            else
                System.out.println("It was a tie");
            System.out.println("******* Next Player *******");
        }
    }
}
