package blackJack;

import java.util.ArrayList;

public class Dealer extends Deck {

	// Declaration of variables.
	private ArrayList<Card> dealerHand;
	private Card[] temp;
	private boolean choseToStay;
	private int dealerValue, firstValue;

	/** 
	 * constructor
	 * pre: none
	 * post: A Dealer object created. 'firstValue' and 'dealerValue' initialized to 0. 
	 * 'choseToStay' initialized to false and 'dealerHand' initialized to a new ArrayList of type Card.
	 */
	public Dealer() {
		firstValue = 0;
		dealerValue = 0;
		choseToStay = false;
		dealerHand = new ArrayList<Card>();
	}

	/**
	 * Adds the initial two starting cards to the dealer's hand.
	 * pre: none.
	 * post: Two cards have been added to the dealer's hand.
	 */
	public void startCards(Deck deck) {
		for (int i = 0; i < 2; i++) {
			dealerHand.add(deck.draw());
		}
		calcScore();
		temp = new Card[] {};
		temp = dealerHand.toArray(temp);
		for (int i = 0; i < temp.length; i++) {
			firstValue = temp[0].getValue();
		}
	}

	/**
	 * helper method.
	 * pre: none
	 * The value of the dealer's hand returned.
	 */
	private int calcScore() {
		dealerValue = 0;
		temp = new Card[] {};
		temp = dealerHand.toArray(temp);
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].getValue() == 11 && dealerValue + 11 > 21)
				dealerValue += 1;
			else
				dealerValue += temp[i].getValue();
		}
		return dealerValue;
	}

	/**
	 * Returns the current value of the dealer's hand.
	 * pre: none
	 * post: Value of dealer's hand returned.
	 */
	public int getScore() {
		return (dealerValue);
	}

	/**
	 * Displays the dealer's score of first card and first card face up, followed by the required number of '?', indicating their face down cards.
	 * pre: none
	 * post: Dealer's score of first card, first card face up, and the rest of their face down cards are shown.
	 */
	public void showScore() {
		System.out.println("\n|----------------------------------------------");
		System.out.println("|Dealer's Score: " + firstValue + "\n|");
		System.out.format("%s %6s", "|", dealerHand.get(0) + " ");
		for (int i = 0; i < dealerHand.size() - 1; i++) {
			System.out.print("?  ");
		}
		System.out.println("\n|");
	}

	/** 
	 * Displays the dealer's real score and all of their cards face up.
	 * pre: none
	 * post: Dealer's real score and all of their cards face up are shown.
	 */
	public void showRealScore() {
		System.out.println("|----------------------------------------------");
		System.out.println("|Dealer's Score: " + dealerValue + "\n|");
		for (int i = 0; i < dealerHand.size(); i++) {
			if (i == 0)
				System.out.print("|    ");
			System.out.print(dealerHand.get(i) + " ");
		}
		System.out.println("\n|");
	}

	/**
	 * Adds a card to dealer's hand, removes that card from the deck, and updates the dealer's current score.
	 * pre: none
	 * post: A card is added to the dealer's hand, that same card is removed form the deck, and the dealer's score is updated.
	 */
	public void addCard(Deck deck) {
		dealerHand.add(deck.draw());
		calcScore();
	}

	/**
	 * Initializes the boolean variable 'choseToStay' to true.
	 * pre: none
	 * post: Boolean variable 'choseToStay' has been initialized to true.
	 */
	public void stays() {
		choseToStay = true;
	}

	/**
	 * If the 'choseToStay' variable is false, then return false that the dealer has already stayed and vice versa.
	 * pre: none
	 * post: True or false returned.
	 */
	public boolean alreadyStays() {
		if (choseToStay == false)
			return false;
		else
			return true;
	}

	/**
	 * If the value of the dealer's hand is more than 21, then return true that the dealer has busted, false otherwise.
	 * pre: none
	 * post: True or false returned.
	 */
	public boolean hasBusted() {
		if (dealerValue > 21)
			return true;
		else
			return false;
	}

	/**
	 * If the value of the dealer's hand is 21, then return true that the dealer has blackjack, false otherwise.
	 * pre: none
	 * post: True or false returned.
	 */
	public boolean hasBlackJack() {
		if (dealerValue == 21)
			return true;
		else
			return false;
	}

	/**
	 * If the player wants to play again, the dealer's hand will be cleared of cards, as well as the properties that go with it.
	 * pre: none
	 * post: 'dealerValue' set to 0, 'firstValue' set to 0, 'choseToStay' set to false, dealer's hand has been cleared.
	 */
	public void clearHand() {
		dealerValue = 0;
		firstValue = 0;
		choseToStay = false;
		dealerHand.clear();
	}
}