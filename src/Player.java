
public class Player implements Cloneable
{
   private Dice[] diceWon;
   private Dice die;
   private int maxWins; //determines max size on diceWon array
   private int numWins; //keeps track of how much of diceWon is filled
   
   /**
    * Constructor creates player object and sets maxWins to 10 
    * and sides on die to 6.
    * @param NONE
    */
   public Player() 
   {
      this(10, 6);
   }
   
   /**
    * Constructor creates player object and sets maxWins and sidesOnDie
    * to specified value.
    * @param maxWins Rounds to play this game
    * @param sideOnDie How many sides die used for game has
    */
   public Player(int maxWins, int sideOnDie) 
   {
      
      if(maxWins > 0) 
      {
         this.maxWins = maxWins;
      }
      else
      {
         this.maxWins = 10;
      }
      
      diceWon = new Dice[maxWins];
      die = new Dice(sideOnDie);
      numWins = 0;
   }
   
   /**
    * Getter method to get copy of diceWon array.
    * @param NONE
    * @return diceWonCopy Copy of diceWon array to protect original data
    */
   public Dice[] getDiceWon()
   {
      Dice[] diceWonCopy = new Dice[numWins];
      
      for(int i = 0; i < numWins; i++) {
         diceWonCopy[i] = diceWon[i].clone();
      }
      return diceWonCopy;
   }

   /**
    * Getter method to get copy of players die.
    * @param NONE
    * @return Copy of players die
    */
   public Dice getDie() 
   {
      return die.clone();
   }
   
   
   /**
    * Getter method for maxWins
    * @param NONE
    * @return maxWins
    */
   public int getMaxWins()
   {
      return maxWins;
   }
   
   /**
    * Getter method for numWins.
    * @param NONE
    * @return numWins
    */
   public int getNumWins() 
   {
      return numWins;
   }
   
   /**
    * Rolls die then return result of roll as int.
    * @param NONE
    * @return Int result of die roll
    */
   public int rollDie()
   {
      return die.roll();
   }
   
   /**
    * Get the last rolled value from die.
    * @param NONE
    * @return Last rolled value
    */
   public int getLastRoll()
   {
      return die.getLastRoll();
   }
   
   /**
    * Records win for player and stores won dice in diceWon array
    * @param winningDie Die won
    * @return winRecorded Status of recording win, if winningDie was null then returns
    *                     false because win could not be recorded, returns true if
    *                     win recorded without errors.
    */
   public boolean wonRound(Dice winningDie)
   {
      boolean winRecorded = false;
      
      if(winningDie != null)
      {
         diceWon[numWins] = winningDie;
         numWins++;
         winRecorded = true;
      }
      
      return winRecorded;
   }
   
   /**
    * Overridden toString method to display rounds won as score for player.
    * @param NONE
    * @return String with rounds won as score information.
    */
   public String toString()
   {
      return ("Score: " + numWins);
   }
   
   /**
    * Overridden clone method. Returns copy of Player object.
    * @param NONE
    * @return Copy of player object
    */
   public Player clone()
   {
      try
      {
         Player playerClone = (Player) super.clone();
         Dice[] diceWonCopy = new Dice[diceWon.length];
         
         for(int i = 0; i < diceWon.length; i++)
         {
            diceWonCopy[i] = diceWon[i].clone();

         }
         
         playerClone.diceWon = diceWonCopy;
         playerClone.die = die.clone();
         
         return playerClone;
      }
      catch(CloneNotSupportedException e)
      {
         return null; //shouldn't happen, keep compiler happy!
      }
         
   }
}
