package blackJack;

// Importing the needed libraries.
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Deck {
	// Instantiation of Scanner.
	Scanner sc = new Scanner(System.in);

	// Declaration of variables.
	private String name, choice;
	private int value;
	private double money, bet;
	private boolean choseToStay;
	ArrayList<Card> playerHand;
	private Card[] temp;

	/**
	 * constructor.
	 * pre: none
	 * post: A Player object created. All double and integer variables initialized to 0. 
	 * String variables initialized to "". 'playerHand' has been set to a new ArrayList of type Card.
	 */
	public Player() {
		money = 0; 
		bet = 0;
		value = 0;
		name = "";
		choice = "";
		choseToStay = false;
		playerHand = new ArrayList<Card>();
	}

	/**
	 * helper method
	 * pre: none
	 * post: Custom method to remove multiple spaces, 'str' with all unnecessary spaces removed returned.
	 */
	private static String delSpaces(String str) { 
		StringBuilder sb = new StringBuilder();
		for (String s : str.split(" ")) {

			if (!s.equals("")) // ignore space
				sb.append(s + " "); // add word with 1 space

		}
		return new String(sb.toString());
	}

	/**
	 * Initializes the name of the player.
	 * pre: none
	 * post: Name has been changed.
	 */
	public void setName() {
		name = sc.nextLine();
		name = delSpaces(name);
		String[] nameHelper = name.split(" ");
		name = "";
		for (int i = 0; i < nameHelper.length; i++) {
			nameHelper[i] = nameHelper[i].substring(0, 1).toUpperCase() + nameHelper[i].substring(1).toLowerCase();
			name += nameHelper[i] + " ";
		}
		name = name.trim();
	}

	/**
	 * Returns the name of the player.
	 * pre: none
	 * post: Player's name returned.
	 */
	public String getName() {
		return (name);
	}

	/**
	 * Initializes the amount of money the player has.
	 * pre: none
	 * post: Amount of money the player has will be stored in the instance variable 'money'.
	 */
	public void setMoney() {
		System.out.print("|" + name + ", How much money do you have, in dollars?\n|");
		while (true) {
			String temp = sc.nextLine();
			try {
				money = Double.parseDouble(temp);
				if (money > 0) break;
				else System.out.print("|The amount of money you have must be greater than 0 to play!\n|");
			} catch (NumberFormatException e) {
				System.out.print("|That is not a number!\n|");
			}
		}
	}

	/**
	 * Returns the amount of money the player has.
	 * pre: none
	 * post: Amount of money the player has returned.
	 */
	public double getMoney() {
		return (money);
	}

	/**
	 * Displays the player's current money.
	 * pre: none
	 * post: Player's current money displayed.
	 */
	public void displayMoney() {
		System.out.print("\n|Current Money: $");
		System.out.format("%.2f %n", money);
	}

	/**
	 * Displays the player's current bet.
	 * pre: none
	 * post: Player's current bet displayed.
	 */
	public void displayBet() {
		System.out.print("|Current Bet: $");
		System.out.format("%.2f %n", bet);
	}

	/**
	 * Subtracts the player's money with their bet.
	 * pre: none
	 * post: Player's money has been changed.
	 */
	public void lostBet() {
		money -= bet;
	}

	/**
	 * Adds the player's money with their bet.
	 * pre: none
	 * post: Player's money has been changed.
	 */
	public void wonBet() {
		money += bet;
	}

	/**
	 * Shows the player's first card before asking them for their bet.
	 * pre: none
	 * post: Player's first card shown.
	 */
	public void showPlayerFirstCard() {
		System.out.print("|Your first card is " + playerHand.get(0));
	}

	/**
	 * Sets the amount of money the player wants to bet to the instance variable 'bet'.
	 * pre: none
	 * post: post: Amount of betting money the player has will be stored in the instance variable 'bet'.
	 */
	public void setBet() {
		System.out.print("\n|" + name + ", How much money do you want to bet, in dollars?\n|");
		while (true) {
			String temp = sc.nextLine();
			try {
				bet = Double.parseDouble(temp);
				if (bet > money)
					System.out.print("|You cannot bet more that you have!\n|");
				else
					break;
			} catch (NumberFormatException e) {
				System.out.print("|That is not a number!\n|");
			}
		}
	}

	/**
	 * Returns the player's amount to bet.
	 * pre: none
	 * post: Player's amount to bet returned.
	 */
	public double getBet() {
		return (bet);
	}

	/**
	 * helper method
	 * pre: none
	 * post: Calculates and returns the score of the player's current hand.
	 */
	private int calcScore() {
		value = 0;
		temp = new Card[] {};
		temp = playerHand.toArray(temp);
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].getValue() == 11 && getScore() + 11 > 21)
				value += 1;
			else
				value += temp[i].getValue();
		}
		return value;
	}

	/**
	 * Initializes the player's starting two cards.
	 * pre: none
	 * post: Two cards have been added to the player's hand.
	 */
	public void startCards(Deck deck) {
		for (int i = 0; i < 2; i++) {
			playerHand.add(deck.draw());
		}
		calcScore();
	}

	/**
	 * Adds a card to the player's hand while removing that same card from the deck.
	 * pre: none
	 * post: A card has been added to the player's hand and that same card has been removed from the deck.
	 */
	public void addCard(Deck deck) {
		playerHand.add(deck.draw());
		calcScore();
	}

	/**
	 * Returns the current score of the player.
	 * pre: none
	 * post: Player's current score returned.
	 */
	public int getScore() {
		return value;
	}

	/**
	 * Displays the cards of the player's current hand and their current score.
	 * pre: none
	 * post: Player's current hand and score returned.
	 */
	public void showScore() {
		for (int i = 0; i < playerHand.size(); i++) {
			if (i == 0)
				System.out.print("|    ");
			System.out.print(playerHand.get(i) + " ");
		}
		System.out.println("\n|\n|Player's Score: " + value + "\n|---------------------------------------");
	}

	/**
	 * If the player's score is 21, return true, otherwise return false.
	 * pre: none
	 * True or false returned.
	 */
	public boolean hasBlackJack() {
		if (value == 21)
			return true;
		else
			return false;
	}

	/**
	 * If the player wants to hit, return true, otherwise return false.
	 * pre: none
	 * post: True or false returned.
	 */
	public boolean wantsToHit() {
		System.out.print("|" + name + ", would you like to Hit or Stay? [H/S]\n|");
		while (true) {
			choice = sc.nextLine().trim();
			if (choice.equalsIgnoreCase("H"))
				return true;
			else if (choice.equalsIgnoreCase("S")) {
				choseToStay = true;
				return false;
			} else
				System.out.print("\n|That is not a valid option!\n|");
		}
	}

	/**
	 * If the player has not already chosen to stay, return false, otherwise return true.
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
	 * This is used to display the table after each time the dealer draws after the player stays. (A waiting method)
	 * pre: none
	 * post: Table shown after each draw after the player has decided to stay.
	 */
	public void enterAnyKey() {
		System.out.println("Enter any key to continue");
		choice = sc.nextLine();
	}

	/**
	 * If the player's score is greater than 21, return true, otherwise return false.
	 * pre: none
	 * post: True or false returned.
	 */
	public boolean hasBusted() {
		if (value > 21)
			return true;
		else
			return false;
	}

	/**
	 * If the player wants to play again, return true, false otherwise.
	 * pre: none
	 * post: True or false returned.
	 */
	public boolean wantsToPlayAgain() {
		System.out.print("|Would you like to play again? [Y / N]\n|");
		while (true) {
			choice = sc.nextLine().trim();
			if (choice.equalsIgnoreCase("Y"))
				return true;
			else if (choice.equalsIgnoreCase("N"))
				return false;
			else
				System.out.print("|That is not a valid option!\n|");
		}
	}

	/**
	 * If the player decides they want to play again, their hand will be cleared as well as their score. 
	 * Boolean variable 'choseToStay' will be set back to false.
	 * pre: none
	 * post: Player's score set to 0, 'choseToStay' set to false, player's hand has been cleared.
	 */
	public void clearHand() {
		value = 0;
		choseToStay = false;
		playerHand.clear();
	}
}