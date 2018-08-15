
public class DiceWarModel
{
   private Player[] player;
   private Dice dieToMatch;
   private int numOfPlayers;
   private int timeRemaining;
   private int winGoal;
   private boolean timerMade;
   
   /*
    * Constructor creates DiceWarModel object. Sets to 2 players, 30 second games
    * and take dieToMatch to determine goal for die, and winGoal to see how many 
    * die matches to win game.
    * @param dieToMatch Die that players try to match with their rolls
    * @param winGoal Number of die matches to win game before time runs out
    */
   public DiceWarModel(Dice dieToMatch, int winGoal)
   {
      this(2, 30, dieToMatch, winGoal);
   }
   
   /**
    * Constructor creates DiceWarModel object. Sets to 2 players and takes
    * dieToMatch to determine goal for die, gameTimeLenth to determine length
    * of match, and winGoal to see how many die matches to win game.
    * @param dieToMatch Die that players try to match with their rolls
    * @param gameTimeLength Length in seconds the match will run
    * @param winGoal Number of roll matches to win game before time runs out
    */
   public DiceWarModel(Dice dieToMatch, int gameTimeLength, int winGoal)
   {
      this(2, gameTimeLength, dieToMatch, winGoal);
   }
   
   /**
    * Constructor creates DiceWarModel object. Takes numOfPlayers, gameTimeLength,
    * dieToMatch, and winGoal to set game options.
    * @param numOfPlayers Number of players to play game
    * @param gameTimeLength Length in seconds the match will run
    * @param dieToMatch Die that players try to match with their rolls
    * @param winGoal Number of roll matches to win game before time runs out
    */
   public DiceWarModel(int numOfPlayers, int gameTimeLength, Dice dieToMatch, int winGoal)
   {
      this.dieToMatch = dieToMatch;
      this.winGoal = winGoal;
      newGame(numOfPlayers, gameTimeLength);
      timerMade = false;
   }
   
   /**
    * Resets players and game time for new game
    * @param numOfPlayers Number of players playing to reset to default values
    * @param gameTimeLength Length of time of game match
    */
   public void newGame(int numOfPlayers, int gameTimeLength)
   {
      if(numOfPlayers > 0)
      {
         this.numOfPlayers = numOfPlayers;
      }
      else
      {
         this.numOfPlayers = 2;
      }
      
      if(gameTimeLength > 0)
      {
         timeRemaining = gameTimeLength;
      }
      else
      {
         timeRemaining = 30;
      }
      
      player = new Player[this.numOfPlayers];
      
      for(int i = 0; i < numOfPlayers; i++)
         player[i] = new Player(winGoal, dieToMatch.getSides());
   }
   
   /**
    * Getter method to get copy of Dice dieToMatch
    * @param NONE
    * @return Copy of dieToMatch
    */
   public Dice getDieToMatch()
   {
      return dieToMatch.clone();
   }
   
   /**
    * Rolls die to match so get new value
    * @param NONE
    * @return NONE
    */
   public void rollDieToMatch()
   {
      dieToMatch.roll();
   }
   
   /**
    * Getter method to get value of die to match as int
    * @param NONE
    * @return int value of dieToMatch last roll
    */
   public int getDieToMatchValue()
   {
      return dieToMatch.getLastRoll();
   }
   
   /**
    * Getter method to get specified players score.
    * @param playerNum Which player to see score of (0 for computer, 1 for human in 2 player game)
    * @return current score of selected player
    */
   public int getScore(int playerNum) 
   {
      return player[playerNum].getNumWins();
   }
   
   /**
    * Rolls selected players die and returns roll result as an int. If the roll
    * matches the dieToMatch roll value then wonRound is also called to
    * earn a point.
    * @param playerNum Player number for who is rolling (0 for computer, 1 for human in 2 player game)
    * @return int result of roll
    */
   public int roll(int playerNum)
   {
      player[playerNum].rollDie();
      
      if(player[playerNum].getLastRoll() == dieToMatch.getLastRoll())
      {
         player[playerNum].wonRound(dieToMatch.clone());
      }
         
      return player[playerNum].getLastRoll();
   }
   
   /**
    * Accessor to get last value for specified player.
    * @param playerNum Player number to get last roll from (0 for computer, 1 for human in 2 player game)
    * @return int result of last roll
    */
   public int getLastRoll(int playerNum)
   {
      return player[playerNum].getLastRoll();
   }
      
   /**
    * Accessor to get copy of Dice array that contains copies of dice that had
    * rolls matching the die to match roll.
    * @param playerNum Player number to get dice won array from (0 for comuter, 1 for human in 2 player game)
    * @return playersDiceWon Copy of Dice[] array with winning dice
    */
   public Dice[] getPlayersDiceWon(int playerNum)
   {
      Dice[] playersDiceWon = null;
      
      if(playerNum >= 1 && playerNum <= 2)
      {
         playersDiceWon = player[playerNum].getDiceWon();
      }
     
      return playersDiceWon;
   }
   
   /**
    * Sets time remaining to specified time in seconds (must be greater than 0 seconds).
    * @param timeRemaining Specified time in seconds to set time remaining to for use by timer
    * @return timerSet Result of setting timeRemaining, true if set, false if not
    */
   public boolean setTimeRemaining(int timeRemaining)
   {
      boolean timeSet = false;
      
      if(timeRemaining > 0)
      {
         this.timeRemaining = timeRemaining;
         timeSet = true;
      }
      
      return timeSet;
   }
   
   /**
    * Decreases timeRemaining by 1 second as long as time remaining is greater than 0.
    * @param NONE
    * @return NONE
    */
   public void decreaseTimeRemaining()
   {
      if(timeRemaining > 0)
         timeRemaining--;
   }
   
   /**
    * Getter for timeRemaining
    * @param NONE
    * @return timeRemaining
    */
   public int getTimeRemaining()
   {
      return timeRemaining;
   }
   
   /**
    * Checks to see if any of the end game conditions have been met.
    * These conditions are if timeRemaining reaches 0 or if players have
    * hit the winGoal.
    * @param NONE
    * @return gameOver Result of if end game condition has been met, true
    *                  if one yes, false if no
    */
   public boolean gameOver()
   {
      boolean gameOver = false;
      
      if(timeRemaining == 0 || player[0].getNumWins() == winGoal || player[1].getNumWins() == winGoal)
         gameOver = true;
      
      return gameOver;
   }
   
   /**
    * Sets timer made, this should be used when timer thread is
    * created. Used to make sure multiple timers are not made
    * when only one is needed in different thread. Then should
    * be set to false once timer is not needed anymore.
    * @param made Boolean value to set timerMade to
    * @return NONE
    */
   public void setTimerMade(boolean made)
   {
      timerMade = made;
   }
   
   /**
    * Checks to see if timer has been made to make sure
    * multiple unnessary timers are not created.
    * @param NONE
    * @return timerMade Result of if timer has been
    *                   made or not. True if made,
    *                   false if not.
    */
   public boolean timerMade()
   {
      return timerMade;
   }

}
