import javax.swing.Icon;
import javax.swing.ImageIcon;

public class GUIDice
{
   private static Icon[] diceIcons = new ImageIcon[7];
   private static boolean iconsLoaded = false;
   
   /**
    * Gets icon from die passed in based on die's last roll.
    * @param die to get icon representation for
    * @return returnIcon Icon representation of die passed in
    */
   public static Icon getIconFromDie(Dice die)
   {
      Icon returnIcon = null;
      loadDiceIcons();
      
      if(die != null)
      {
         returnIcon =  diceIcons[(die.getLastRoll() - 1)];
      }
      
      return returnIcon;
   }
   
   /**
    * Gets an icon of a blank die.
    * @param NONE.
    * @return Icon of blank die.
    */
   public static Icon getBlankDie()
   {
      loadDiceIcons();
      
      return diceIcons[6];
   }
   
   /**
    * Gets icon from int passed in. Int has to be in range of 1-6.
    * @param side Int number in range of 1-6 to get dice icon for
    * @return returnIcon Icon representation of int passed in
    */
   public static Icon getIconFromInt(int side) 
   {
      Icon returnIcon = null;
      loadDiceIcons();
      
      if(side >= 1 && side <= 6)
      {
         returnIcon = diceIcons[side - 1];
      }
      
      return returnIcon;
   }
   
   /**
    * Loads icons into diceIcons array if they have not been loaded yet.
    * Dice icons must be in images folder and placed in project folder.
    * @param NONE
    * @return NONE
    */
   private static void loadDiceIcons()
   {

     if(!iconsLoaded)
     {
         String path = "images/", extension = ".gif";
         int diceNum = 0;
         
         for(int i  = 0; i < diceIcons.length; i++)
         {
            diceNum++;
            diceIcons[i] = new ImageIcon(path + diceNum + extension);
         }
      
         iconsLoaded = true;
     }
   }
}
