import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 * @original: Laurie White  April 2012
 * Edited by Melanie Hamasaki
 */
public class MagpieRunner
{

 /**
  * Create a Magpie, give it user input, and print its replies.
  */
 public static void main(String[] args)
 {
  Magpie maggie = new Magpie();
  
  System.out.println (maggie.getGreeting());
  Scanner in = new Scanner (System.in);
  String statement = in.nextLine();
  
  while (!statement.equals("Bye"))
  {
   System.out.println (maggie.getResponse(statement));
   statement = in.nextLine();
  }
 }

}
