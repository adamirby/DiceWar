import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;


public class DiceWarView extends JFrame
{
   public static final int MAX_PLAYERS = 2;
   public static final int MAX_winGoal = 5;
   private int numPlayers, winGoal;
   private JPanel pnlCompSide, pnlHumanSide, pnlPlayArea, pnlButtonArea, pnlTimer;
   private JLabel[] humanWins;
   private JLabel[] compWins;
   private JLabel[] diceRolled;
   private JLabel humanScore, compScore, humanRollTitle, compRollTitle, rollToMatchLabel, rollToMatchDie, timerLabel;
   private JButton rollButton, newGameButton;
   
   /**
    * Constructor to create JFrame, initialize all JLabels, JPanels, and JButtons.
    * Adds JPanels to JFrame. Sets title of window, number of players, and win 
    * goal for players to reach.
    * @param title Title for JFrame window
    * @param numPlayers Number of players in this game
    * @param winGoal Goal for players to reach
    */
   public DiceWarView(String title, int numPlayers, int winGoal)
   {
      super();
      setSize(650, 500);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle(title);
      
      //check numPlayers to make sure number entered it OK.
      //if not, set to default value of 2.
      if(numPlayers <= MAX_PLAYERS && numPlayers > 0)
      {
         this.numPlayers = numPlayers;
      }
      else
      {
         this.numPlayers = 2;
      }
      
      //check winGoal to make sure number entered is OK.
      //if not, set to default value of 10.
      if(winGoal <= MAX_winGoal && winGoal > 0)
      {
         this.winGoal = winGoal;
      }
      else
      {
         this.winGoal = 10;
      }
      
      //initialize JLabel arrays & JPanels
      humanWins = new JLabel[winGoal];
      compWins = new JLabel[winGoal];
      diceRolled = new JLabel[this.numPlayers];
      pnlHumanSide = new JPanel(new GridLayout(0, 2));
      pnlCompSide = new JPanel(new GridLayout(0, 2));
      pnlPlayArea = new JPanel(new GridLayout(3, 2));
      pnlButtonArea = new JPanel();
      pnlTimer = new JPanel();
      
      //set boarder and title for JPanels
      pnlHumanSide.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 5), "Player Matches"));
      pnlCompSide.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 5), "Computer Matches"));
      pnlPlayArea.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 5), "Play Area"));
      pnlTimer.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 5), "Timer"));
      
      //center titles for each JPanel
      ((TitledBorder)pnlHumanSide.getBorder()).setTitleJustification(TitledBorder.CENTER);
      ((TitledBorder)pnlCompSide.getBorder()).setTitleJustification(TitledBorder.CENTER);
      ((TitledBorder)pnlPlayArea.getBorder()).setTitleJustification(TitledBorder.CENTER);
      ((TitledBorder)pnlTimer.getBorder()).setTitleJustification(TitledBorder.CENTER);
      
      //set background color of each JPanel 
      //(dice are white so this makes them easier to see)
      pnlHumanSide.setBackground(Color.LIGHT_GRAY);
      pnlCompSide.setBackground(Color.LIGHT_GRAY);
      pnlPlayArea.setBackground(Color.LIGHT_GRAY);
      pnlButtonArea.setBackground(Color.LIGHT_GRAY);
      pnlTimer.setBackground(Color.LIGHT_GRAY);
      
      //add JPanels to JFrame
      add(pnlTimer, BorderLayout.NORTH);
      add(pnlHumanSide, BorderLayout.WEST);
      add(pnlCompSide, BorderLayout.EAST);
      add(pnlPlayArea, BorderLayout.CENTER);
      add(pnlButtonArea, BorderLayout.SOUTH);
   }
   
   /**
    * Removes everything from JPanels, creates all JLabels and JButtons and
    * adds them to appropriate panels.
    * @param matchValue Int value of roll from die to match
    * @return NONE
    */
   public void setUpBoard(int matchValue)
   {
      //removes everything from all JPanel
      pnlHumanSide.removeAll();
      pnlCompSide.removeAll();
      pnlPlayArea.removeAll();
      pnlButtonArea.removeAll();
      pnlTimer.removeAll();
      
      //create hidden labels for computer/human wins and add to appropriate
      //panels to have correct spacing
      //these are set to visible and to correct icon when point is won.
      for(int i = 0; i < winGoal; i++)
      {
         humanWins[i] = new JLabel(GUIDice.getBlankDie());
         compWins[i] = new JLabel(GUIDice.getBlankDie());
         
         humanWins[i].setVisible(false);
         compWins[i].setVisible(false);
         
         pnlHumanSide.add(humanWins[i]);
         pnlCompSide.add(compWins[i]);
      }
      
      //Create score JLabels and add to appropriate panels
      humanScore = new JLabel("Human Score: 0");
      compScore = new JLabel("Comp Score: 0");
      pnlHumanSide.add(humanScore);
      pnlCompSide.add(compScore);
      
      //Create roll and new game buttons, sets visibility, add to panel
      rollButton = new JButton("ROLL");
      newGameButton = new JButton();
      newGameButton.setVisible(false);
      pnlButtonArea.add(rollButton);
      pnlButtonArea.add(newGameButton);
      
      //Create JLabels for play area and add to panel
      rollToMatchLabel = new JLabel("Roll to Match", JLabel.CENTER);
      humanRollTitle = new JLabel("Player Roll", JLabel.CENTER);
      compRollTitle = new JLabel("Computer Roll", JLabel.CENTER);
      rollToMatchDie = new JLabel(GUIDice.getIconFromInt(matchValue));
      diceRolled[0] = new JLabel(GUIDice.getBlankDie());
      diceRolled[1] = new JLabel(GUIDice.getBlankDie());
      pnlPlayArea.add(rollToMatchLabel);
      pnlPlayArea.add(humanRollTitle);
      pnlPlayArea.add(compRollTitle);
      pnlPlayArea.add(rollToMatchDie);
      pnlPlayArea.add(diceRolled[0]);
      pnlPlayArea.add(diceRolled[1]);

      //Create JLabel for timer and add to panel
      timerLabel = new JLabel("0", JLabel.CENTER);
      pnlTimer.add(timerLabel);
      
      //repaint and set visibility
      repaint();
      setVisible(true);
   }
   
   /**
    * Adds action listeners to buttons.
    * @param rollListener Action listener for rollButton
    * @param newGameListener Action listener for newGameButton
    * @return NONE
    */
   public void addActionListeners(ActionListener rollListener, ActionListener newGameListener)
   {
      rollButton.addActionListener(rollListener);
      newGameButton.addActionListener(newGameListener);
   }
   
   /**
    * Updates all necessary JLabels after each roll with new die icon,
    * player score, and collected die from winning.
    * @param compRoll Last roll from computer player
    * @param playerRoll Last roll from human player
    * @param compScore Computer players score
    * @param humanScore Human players score
    */
   public void updateRolls(int compRoll, int playerRoll, int compScore, int humanScore)
   {
      diceRolled[0].setIcon(GUIDice.getIconFromInt(playerRoll));
      diceRolled[1].setIcon(GUIDice.getIconFromInt(compRoll));
      
      for(int i = 0; i < compScore; i++)
      {
         compWins[i].setIcon(rollToMatchDie.getIcon());
         compWins[i].setVisible(true);
      }
      
      for(int i = 0; i < humanScore; i++)
      {
         humanWins[i].setIcon(rollToMatchDie.getIcon());
         humanWins[i].setVisible(true);
      }
      
      this.compScore.setText("Comp Score: " + compScore);
      this.humanScore.setText("Human Score: " + humanScore);
   }
   
   /**
    * Changes board to game over state. Hides roll button and
    * reveals game over button to play new name.
    * @param compScore Computer players final score
    * @param humanScore Human players final score
    */
   public void gameOver(int compScore, int humanScore)
   {
      String winner;
      
      if(compScore > humanScore)
      {
         winner = "COMPUTER WINS!";
      }else if (humanScore > compScore)
      {
         winner = "YOU WIN!";
      }
      else
      {
         winner = "TIE!";
      }
      
      rollButton.setVisible(false);
      newGameButton.setText("<html><center>GAME OVER <br>" + winner+ "</html>");
      newGameButton.setVisible(true);
   }
   
   /**
    * Updates timer labels text with specified time.
    * @param time Time to set timer label to
    */
   public void updateTimer(int time) 
   {
      timerLabel.setText(String.valueOf(time));
   }
}
