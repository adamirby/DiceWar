public class Timer extends Thread 
{
   private DiceWarView view;
   private DiceWarModel model;
   
   /**
    * Constructor for timer, sets view and model to use
    * in separate thread and starts new thread.
    * @param view View to update in thread.
    * @param model Model to use in thread.
    */
   public Timer(DiceWarView view, DiceWarModel model)
   {
      this.view = view;
      this.model = model;
      model.setTimerMade(true);
      start();
   }

   /**
    * Overridden run method with actions to be done in second thread.
    * Computer rolls and decreases time remaining once every second.
    * View is updated with each pass with computers roll and updated
    * timer with one less second. When time runs out thread is ended
    * and gameOver condition is set.
    * @param NONE
    * @return NONE 
    */
   public void run()
   {
      while(!model.gameOver() && model.getTimeRemaining() != 0)
      {
         model.roll(0);
         model.decreaseTimeRemaining();
         view.updateRolls(model.getLastRoll(0), model.getLastRoll(1), model.getScore(0), model.getScore(1));
         view.updateTimer(model.getTimeRemaining());
         doNothing(1000);
      }
      
      view.gameOver(model.getScore(0), model.getScore(1));
      model.setTimerMade(false);
   }
   
   /**
    * Sleeps thread for specified amount of time,
    * this also handles InterruptedException.
    * @param milliseconds Length of time to sleep thread for
    * @return NONE
    */
   private void doNothing(int milliseconds) 
   {
      try
      {
         Thread.sleep(milliseconds);
      }
      catch(InterruptedException e)
      {
         System.out.println("Unexpected interrupt");
      }
   }
}
