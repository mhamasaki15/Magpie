/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *       Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
 /**
  * Get a default greeting  
  * @return a greeting
  */
 public String getGreeting()
 {
  return "Hello, let's talk.";
 }
 
 /**
  * This method will prioritize the if statements from top to bottom. 
  * This is because the code will run the first "if" statement, and then bypass the rest of the "else if" statements.
  * 
  * When the code encounters a keyword that is present in another word (ex: "no" in "know"), it will still run that code.
  * This is because the indexOf method only checks to see whether that string is present, not if it is a seperate word.
  */
 public String getResponse(String statement)
 {
  String response = "";
  if (statement.indexOf("no") >= 0)
  {
   response = "Why so negative?";
  }
  else if (statement.indexOf("mother") >= 0
    || statement.indexOf("father") >= 0
    || statement.indexOf("sister") >= 0
    || statement.indexOf("brother") >= 0)
  {
   response = "Tell me more about your family.";
  }
  else if (statement.indexOf("dog") >= 0 //if the statement contains cat or dog
    || statement.indexOf("cat") >= 0)
  {
    response = "Tell me more about your pets.";
  }
  else if (statement.indexOf("name") >= 0)
  {
    response = "My name is Magpie. What is your name?";
  }
  else if (statement.indexOf("friend") >= 0)
  {
    response = "Who is your best friend? My best friend is you.";
  }
  else if (statement.indexOf("school") >= 0)
  {
    response = "Are you still in school? How old are you?";
  }
  else if (statement.indexOf("Mr. Landgraf") >= 0 
    || statement.indexOf("Mr. Kiang") >= 0)
  {
    response = "Oh wow. Isn't he that famous Computer Science teacher?";
  }  
  else if (statement.trim().length() == 0) //If the trimmed statement has no length, give this response.
  {
    response = "Say something, please.";
  }
  else
  {
   response = getRandomResponse();
  }
  return response;
  }
 

 /**
  * Pick a default response to use if nothing else fits.
  * @return a non-committal string
  */
 private String getRandomResponse()
 {
  final int NUMBER_OF_RESPONSES = 6; //Added 2 more
  double r = Math.random();
  int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
  String response = "";
  
  if (whichResponse == 0)
  {
   response = "Interesting, tell me more.";
  }
  else if (whichResponse == 1)
  {
   response = "Hmmm.";
  }
  else if (whichResponse == 2)
  {
   response = "Do you really think so?";
  }
  else if (whichResponse == 3)
  {
   response = "You don't say.";
  }
  else if (whichResponse == 4) //2 More noncommital responses
  {
   response = "Wow, fascinating.";
  }
  else if (whichResponse == 5)
  {
   response = "Same.";
  }

  return response;
 }
}
