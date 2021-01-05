package blackJack;

import java.util.Collections;
import java.util.ArrayList;

public class Deck extends Card {

	// Declaration of variables
	private static final int SUITS = 4;
	private static final int RANKS = 13;
	private ArrayList<Card> deck = new ArrayList<Card>();

	/**
	 * constructor. 
	 * pre: none 
	 * post: ArrayList 'deck' of type Card has been set to an ordered deck.
	 */
	public Deck() {

		// The first for loop is the different types of suits.
		for (int i = 0; i < SUITS; i++) {

			// The second for loop is how many different 'ranks' there are.
			for (int j = 0; j < RANKS; j++) {

				// Adding a new card to the deck by taking in a different suit(i) and rank(j)
				// value each time.
				deck.add(new Card(i, j));
			}
		}
	}

	/**
	 * If the deck size is less than 10, refresh the deck by returning true, false otherwise. 
	 * pre: none 
	 * post: True or false returned.
	 */
	public boolean needsToBeRefreshed() {
		if (deck.size() < 10)
			return true;
		else
			return false;
	}

	/**
	 * Displays the current deck. 
	 * pre: none 
	 * post: Current deck displayed.
	 */
	public void showDeck() {
		for (int i = 0; i < deck.size(); i++) {
			if (i != 0 && i % RANKS == 0)
				System.out.println();
			System.out.format("%4s", deck.get(i).toString() + " ");
		}
		System.out.println("\n----------------------------------------------------");
	}

	/**
	 * Shuffles the current deck by using the method 'Collections.shuffle()' from the 'java.util.Collections' library. 
	 * pre: none 
	 * post: Deck has been shuffled randomly.
	 */
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

	/**
	 * Returns the card at index 0 of the deck while also removing it from the deck.
	 * pre: none 
	 * post: A card has been returned from the deck, a card has been removed from the deck.
	 */
	public Card draw() {
		return deck.remove(0);
	}
}