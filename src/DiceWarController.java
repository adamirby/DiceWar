import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiceWarController
{
   private DiceWarModel model;
   private DiceWarView view;

   /**
    * Constructor creates model and view with default game values
    * @param NONE
    */
   public DiceWarController()
   {
      model = new DiceWarModel(2, 30, new Dice(), 10);
      view = new DiceWarView("Dice War", 2, 10);
      resetGame();
   }
   
   /**
    * Resets game board and re-adds listeners to newly created buttons
    * on game board
    * @param NONE
    * @return NONE
    */
   private void resetGame()
   {
      model.rollDieToMatch();
      view.setUpBoard(model.getDieToMatchValue());
      view.addActionListeners(new RollButtonListener(), new NewGameButtonListener());
   }
   
   /**
    *  Inner class to hold action listener for roll button
    */
   private class RollButtonListener implements ActionListener
   {
      /**
       * Action listener for roll button. If first time clicked, second thread
       * for computer is made and ran. This rolls players die, updates
       * view with results, then checks to see if the game is over.
       * @param e ActionEvernt information for button push
       * @return NONW
       */
      public void actionPerformed(ActionEvent e)
      {
         if(!model.timerMade())
            new Timer(view, model);
         
         model.roll(1);
         
         view.updateRolls(model.getLastRoll(0), model.getLastRoll(1), model.getScore(0), model.getScore(1));
         
         if(model.gameOver())
         {
            view.gameOver(model.getScore(0), model.getScore(1));
         }
      }
   }
   
   /**
    * Inner class to hold action listener for new game button
    */
   private class NewGameButtonListener implements ActionListener
   {
      /**
       * Action listener for new game button. This resets model and view informaiton
       * to get ready for another round.
       */
      public void actionPerformed(ActionEvent e)
      {
         model.newGame(2,30);
         resetGame();
      }
   }
}

