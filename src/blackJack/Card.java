package blackJack;

public class Card {
	
	// Declaration of variables.
	private int card;
	private int suit;
	private static String[] cards = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	private static char[] suits = { (char) '\u2660', (char) '\u2666', (char) '\u2665', (char) '\u2666' };

	/** 
	 * constructor
	 * pre: none
	 * post: A Card object created. Suit and card values initialized to 0.
	 */
	public Card() {
		suit = 0;
		card = 0;
	}

	/**
	 * constructor
	 * pre: none
	 * post: A Card object created with suit and card values initialized to their parameters.
	 */
	public Card(int x, int y) {
		suit = x;
		card = y;
	}

	/**
	 * Overrides the default toString() method.
	 * pre: none
	 * post: returns the index of 'card' in the 'cards' array and the index of 'suit' in the 'suits' array.
	 */
	public String toString() {
		return (cards[card] + suits[suit]);
	}

	/**
	 * Returns the integer value of 'card'.
	 * pre: none
	 * post: Integer value of 'card' returned.
	 */
	public int getCard() {
		return card;
	}

	/**
	 * Returns the integer value of 'suit'.
	 * pre: none
	 * post: Integer value of 'suit' returned.
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * Returns the value of each card.
	 * pre: none
	 * post: Value of each card returned.
	 */
	public int getValue() {
		int value = 0;
		if (card >= 10)
			value = 10;
		else if (card == 0)
			value = 11;
		else
			value = card + 1;

		return value;
	}
}