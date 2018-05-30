
import java.util.Scanner;

public class CrazyEights {

	private Deck deck;
	private Player[] players;
	private int turn; 
	
	
	public CrazyEights() {

	}
	
	/**
	 * Decodes a command and invokes the right method. You may update this method if you find
	 * it's necessary for your design, but be careful you don't break anything. If you make 
	 * changes to this method, do it in very small increments and frequently run the tests.
	 * 
	 * @param command The command to decode
	 * @return the results of the command.  "ok" if success but no result.
	 */
	public String handleCommand(String command) {
		Scanner input = new Scanner(command);
		String commandType = input.next();
		String toReturn = null;

		if(commandType.equals("start-game")) {
			int numPlayers = input.nextInt();
			String deckName = null;
			boolean shuffle = true;
			if (input.hasNext()) {
				deckName = input.next();
			}
			if (input.hasNextBoolean()) {
				shuffle = input.nextBoolean();
			}
			toReturn =  handleStartGame(numPlayers, deckName, shuffle);
		}
		else if (commandType.equals("play-card")) {
			String cardValue = input.next();
			toReturn = handlePlayCard(cardValue);
			
		}
		else if (commandType.equals("draw-card")) {
			toReturn = handleDrawCard();
		}
		else if(commandType.equals("exit")) {
			input.close();
			System.exit(0);
		}
		else {
			toReturn = "Unknown command " + commandType+"\n" + nextRound();
		}
		input.close();
		return toReturn;
	}
	
	/**
	 * Handles starting a game for the specified number of players with the specified deck.
	 */
	private String handleStartGame(int numPlayers, String deckName, boolean shuffle) {
		
		if( numPlayers < 2 || numPlayers > 4){
			return "Incorrect number of players, must be between 2 and 4.";		
		}
		players = new Player[numPlayers];
		this.turn = 0;
		if(deckName == null){
			this.deck = new Deck(NamedDecks.standardDeck);
		}
		else if(deckName.equals("testDeck")){
			this.deck = new Deck(NamedDecks.testDeck);
		}
		else if(deckName.equals("standardDeck")){
			this.deck = new Deck(NamedDecks.standardDeck);
		}
		if(shuffle == true){
			this.deck.shuffle();
		}
	
		for(int i = 0; i < numPlayers; i++){
			this.players[i] = new Player();
		}
		for(int i = 0; i < 7; i++){
			for(Player currPlayer: players){
				currPlayer.drawCard(this.deck.deal());
			}
		}
		this.deck.setTopDiscard(this.deck.deal());
		return "Cards dealt.\n"+ nextRound();		
	}
	
	
	/**
	 * Handles the play-card command.
	 * 
	 * @param cardValue - The value (rank and suit) of the card to play.
	 * @return The string message to display to the user before the game state text.
	 */
	private String handlePlayCard(String cardValue) {
		if(players[turn].hand.size()==1 && players[turn].playCard(cardValue, this.deck.getTopDiscard())){
			return "Player "+(turn+1)+" wins!";
		}
		if(players[turn].playCard(cardValue, this.deck.getTopDiscard())){
			this.deck.setTopDiscard(cardValue);
			turn = (turn + 1) % players.length;
			return "Card "+ cardValue + " played.\n" + nextRound();
		}
		//turn = (turn + 1) % players.length;
		return "Card was not valid for play. Please try again.\n" + nextRound();
	}
	
	/*
	 * This methdo returns the string which initiates the next round.
	 */
	private String nextRound() {
		return "Player " + (turn + 1) + ", your turn.\n" + "" + "Your cards are"
				+ players[turn].getHand() + "\nThe top discard is " + this.deck.getTopDiscard();
	}

	/**
	 * Handles the draw-card command.
	 * 
	 * @return The string message to display to the user before the game state text.
	 */
	private String handleDrawCard() {
		//TODO: Implement this
		
		return "Card " +players[turn].drawCard(this.deck.deal())+ " was drawn. \n" + nextRound();
		 
	}
	

	/**
	 * 
	 * Run the CrazyEights in console mode (that is, input is to come from
	 * the console).
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * THIS METHOD IS WRITTEN FOR YOU - no need to edit
	 * 
	 * @param args Command line arguments (ignored)
	 */
	public static void main(String[] args) {
		
		//Deck myDeck = new Deck(NamedDecks.testDeck);
		//System.out.println(myDeck.currentDeck);
		CrazyEights game = new CrazyEights();
		System.out.println("Welcome to CrazyEights.  Enter commands.  Type 'exit' to end.");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String commandLine = scanner.nextLine();
			String result = game.handleCommand(commandLine);
			System.out.println(result);
		}
		
	}

}
