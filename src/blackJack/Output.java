package blackJack;

public class Output {
	/**
	 * class method.
	 * pre: none
	 * post: Blackjack game greetings shown.
	 */
	public static void greetings() {
		System.out.print("|Welcome to Black Jack! Please enter your name:\n|");
	}

	/**
	 * class method.
	 * pre: none
	 * post: New line has been created.
	 */
	public static void newLine() {
		System.out.println();
	}

	/**
	 * class method.
	 * pre: none
	 * post: Displays a message that this is the original deck.
	 */
	public static void originalDeck() {
		System.out.println("\nThis is the original deck:");
	}
	
	/**
	 * class method.
	 * pre: none
	 * post: Displays a message that this is the shuffled deck.
	 */
	public static void shuffledDeck() {
		System.out.println("\nThis is the shuffled deck:");
	}

	/**
	 * class method.
	 * pre: none
	 * post: Displays a message that the deck has been refreshed and here is the refreshed deck.
	 */
	public static void deckHasBeenRefreshed() {
		System.out.println("\nDeck has been refreshed!");
		System.out.println("This is the refreshed deck:");
	}

	/**
	 * class method.
	 * pre: none
	 * post: Displays a message that the user is out of money and therefore cannot play anymore.
	 */
	public static void outOfMoney() {
		System.out.println("|Sorry, you are out of money!");
	}
	
	/**
	 * class method.
	 * pre: none
	 * post: Displays a message thanking the user for playing.
	 */
	public static void exitMessage() {
		System.out.println("\nThanks for playing!");
	}
}