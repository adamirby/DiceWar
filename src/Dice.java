import java.lang.Math;

public class Dice implements DiceInterface, Cloneable{
   private int sides;
   private int lastRoll;
   
   /**
    * Constructor creates die and sets sides to default dice size of 6.
    * @param NONE
    */
   public Dice(){
      this(6);
   }
   
   /**
    * Constructor creates die and sets sides to passed in size, if size is 0 or lower than sides
    * set to default value of 6.
    * @param sides Number of sides die has
    */
   public Dice(int sides){
      if(sides > 0){
         this.sides = sides;
      }else{
         this.sides = 6;
      }
      
      roll();
   }
   
   /**
    * Rolls die, generates random number in range of 1 - number of sides.
    * @param NONE
    * @return lastRoll Result of die roll
    */
   public int roll(){
      lastRoll = (int)( (Math.random() * sides) + 1);
      return lastRoll;
   }
   
   /**
    * Setter method for sides. Sets sides to specified amount as long as sides is > 0. If sides are 
    * 0 or lower then sides is not set.
    * @param sides Number of sides of die
    * @return sidesSet Result of setting the sides. If sides specified then value is set and true
    *                  is returned, if sides specified not valid, sides not set and false returned
    */
   public boolean setSides(int sides){
      boolean sidesSet = false;
      
      if(sides > 0){
         this.sides = sides;
         sidesSet = true;
      }
      
      return sidesSet;
   }
   
   /**
    * Getter method for sides of die.
    * @param NONE
    * @return sides Number of sides die has
    */
   public int getSides(){
      return sides;
   }

   /**
    * Getter method for last roll die made. 
    * If number returned is -1 then die has not been rolled before.
    * @param NONE
    * @return lastRoll Last roll die made
    */
   public int getLastRoll(){
      return lastRoll;
   }
   
   /**
    * Overridden toString method, returns lastRoll as String.
    * @param NONE
    * @return String value of lastRoll
    */
   public String toString(){
      return String.valueOf(lastRoll);
   }
   
   /**
    * Overridden clone method, returns copy of Dice object.
    * @param NONE
    * @return Copy of Dice object
    */
   public Dice clone(){
      try {
         Dice copy = (Dice)super.clone();
         return copy;
      }catch(CloneNotSupportedException e){
         return null; //shouldn't happen, keep compiler happy!
      }
   }   
}