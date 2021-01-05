package blackJack;

public class BlackJack {
	public static void main(String[] args) {

		// Instantiation of objects.
		Player user = new Player();
		Dealer computer = new Dealer();
		Deck deck = new Deck();

		// Welcome message and setting up the user's name for the Player class.
		Output.greetings();
		user.setName();
		
		// This is just used to print a new line. (To make game look neater)
		Output.newLine();

		// Asking the player for how much money, in dollars, they have and storing that value.
		user.setMoney();

		// Showing the original deck by calling the showDeck() method from the Deck class.
		Output.originalDeck();
		deck.showDeck();

		// This 'while' statement will break only when the user does not want to play again or if they are out of money.
		while (true) {
			
			// If there are fewer than 10 cards remaining in the deck, then the deck will refresh itself to a new deck.
			if (deck.needsToBeRefreshed() == true) {
				deck = new Deck();
				Output.deckHasBeenRefreshed();
				deck.showDeck();
			}

			// Shuffling the Deck and printing out the shuffled deck.
			deck.shuffleDeck();
			Output.shuffledDeck();
			deck.showDeck();

			// Shuffling the deck again and making the computer set it's first two cards.
			deck.shuffleDeck();
			computer.startCards(deck);

			// Setting the player's starting two cards.
			user.startCards(deck);

			// Showing the player's first card to determine how much the player wants to bet.
			user.showPlayerFirstCard();

			// Determining how much money the player wants to bet.
			user.setBet();

			// This 'while' statement will break only when the player or dealer or both of them reaches a 'bust'.
			while (user.getScore() <= 21 && computer.getScore() <= 21) {

				// Showing the player's current money and bet.
				user.displayMoney();
				user.displayBet();

				// These 'if' statements will execute if either the player or the dealer or both of them have blackjack (value of 21).
				if (user.hasBlackJack() == true && computer.hasBlackJack() == true) {

					// Shows all of the computer's cards face up and real score.
					computer.showRealScore();

					// Show's the player's cards face up and score
					user.showScore();
					
					System.out.println("|\n|You both has blackjack! It's a tie!");
					break;
				} else if (user.hasBlackJack() == true && computer.hasBlackJack() == false) {
					
					// Since the player has won in this scenario, this method will add the player's current money with their bet.
					user.wonBet();
					
					computer.showRealScore();
					user.showScore();
					System.out.println("|\n|You have blackjack and have won your bet!");
					break;
				} else if (user.hasBlackJack() == false && computer.hasBlackJack() == true) {
					
					// Since the player has lost in this scenario, this method will subtract the player's current money with their bet.
					user.lostBet();
					
					computer.showRealScore();
					user.showScore();
					System.out.println("|\n|Dealer has blackjack and you have lost your bet!");
					break;
				}

				// Showing the computer's score only from their first card and printing out their first card.
				// The number of '?' that follows indicates the other cards the computer has that are face down.
				computer.showScore();

				// Showing the  player's current score.
				user.showScore();

				// If the player has not decided to 'stay' yet, this 'if' statement will execute.
				if (user.alreadyStays() == false) {
					
					// If the player wants to hit, this 'if' statement will execute.
					if (user.wantsToHit() == true)
						
						// This method adds a new card to the player's hand while also removing that card from the deck.
						user.addCard(deck);
				} 
				
				// If the player chose to 'stay', this 'else' statement will always execute in the future.
				else {
					// This method just prompts the player to enter any key to continue to enable the player to see the blackjack table each turn.
					user.enterAnyKey();
				}

				// Since the dealer needs at least 17 points to hit, the dealer must hit if he/she has less than 17 points
				if (computer.getScore() < 17) {
					System.out.println("|Dealer chooses to hit.");
					
					// Adds a new card to the dealer's hand while also removing that one from the deck.
					computer.addCard(deck);
				} else {
					System.out.println("|Dealer choses to stay.");
					
					// This stays() method indicates that the computer has decided to stop drawing more cards.
					computer.stays();
				}

				// If the computer chose to stay and the player chose to stay, the nested 'while' loop will break.
				if (computer.alreadyStays() == true && user.alreadyStays() == true)
					break;
			}

			// If the player does not have blackjack and the computer does not have blackjack, these 'if' statements nested inside will execute.
			if (user.hasBlackJack() == false && computer.hasBlackJack() == false) {
				
				// Many of these methods inside have already been explained generally before and the rest have very comprehensible names.
				if (user.hasBusted() == true && computer.hasBusted() == true) {
					computer.showRealScore();
					user.showScore();
					System.out.println("|\n|You both busted and have tied!");
				} else if (user.hasBusted() == true && computer.hasBusted() == false) {
					user.lostBet();
					computer.showRealScore();
					user.showScore();
					System.out.println("|\n|You busted and have lost your bet!");
				} else if (user.hasBusted() == false && computer.hasBusted() == true) {
					user.wonBet();
					computer.showRealScore();
					user.showScore();
					System.out.println("|\n|Dealer busted and you have won your bet!");
				} else if (user.getScore() > computer.getScore()) {
					user.wonBet();
					computer.showRealScore();
					user.showScore();
					System.out.println("|\n|You have a higher score than the dealer and won!");
				} else if (user.getScore() < computer.getScore()) {
					user.lostBet();
					computer.showRealScore();
					user.showScore();
					System.out.println("|\n|You have a lower score than the dealer and lost!");
				} else if (user.getScore() == computer.getScore()){
					computer.showRealScore();
					user.showScore();
					System.out.println("|\n|You have an equal score with the dealer and tied!");
				}
			}

			// If the player has no money left, the final 'while' statement will break.
			if (user.getMoney() == 0) {
				
				// This outputs that the player is out of money.
				Output.outOfMoney();
				break;
			}

			// Displaying the updated player's money.
			user.displayMoney();

			// Asking the player if they want to play again [Y / N].
			if (user.wantsToPlayAgain() == false)
				break;

			// The methods below clear the player's hand of cards and the dealer's hand of cards.
			user.clearHand();
			computer.clearHand();
		}

		// Thanking the user for playing.
		Output.exitMessage();
	}
}