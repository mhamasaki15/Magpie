/**
 * A program to carry on conversations with a human user.
 * @Original: Laurie White April 2012
 * Edited by Melanie Hamasaki
 */

import java.util.Random; //Importing the "random" utility so we can use it later.
  
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
   
    statement = statement.trim(); //Moved this to the beginning, to move the period if there is one.
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals("."))
  {
   statement = statement.substring(0, statement
     .length() - 1);
  }
   
   if (findKeyword(statement, "My", 0) >= 0)
   {
  statement = statement.replace("my", "your"); //Replaces "my" with "your" so sentences can be reworded as questions.
  statement = statement.replace("My", "your");
   }
   else 
   {
  statement = statement.replace("your", "my");
  statement = statement.replace("Your", "My");
   }
  
  String response = "";
  if (findKeyword(statement, "no", 0) >= 0)
  {
   response = "Why so negative?";
  }
  else if (findKeyword(statement, "mother", 0) >= 0
    || findKeyword(statement, "father", 0) >= 0
    || findKeyword(statement, "sister", 0) >= 0
    || findKeyword(statement, "brother", 0) >= 0)
  {
   response = "Tell me more about your family.";
  }
  else if (findKeyword(statement,"name", 0) >= 0)
  {
    response = "My name is Magpie. What is your name?";
  }
  else if (findKeyword(statement,"friend", 0) >= 0)
  {
    response = "Who is your best friend? My best friend is you.";
  }
  else if (findKeyword(statement, "school", 0) >= 0)
  {
    response = "Are you still in school? How old are you?";
  }
  else if (findKeyword(statement, "Mr. Landgraf", 0) >= 0 
    || findKeyword(statement, "Mr. Kiang", 0) >= 0)
  {
    response = "Oh wow. Isn't he that famous Computer Science teacher?";
  }  
  else if (statement.trim().length() == 0) //If the trimmed statement has no length, give this response.
  {
    response = "Say something, please.";
  }
  else if (findKeyword(statement, "I want to", 0) >= 0)
  {
   response = transformIWantToStatement(statement);
  }
  else if (findKeyword(statement, "I want", 0) >= 0) //Placed AFTER "I want to" check to make sure there is no "to" afterwards.
  {
   response = transformIWantStatement(statement);
  }
  else if (findKeyword(statement, "I am", 0) >= 0) //For "I am" statements
  {
    response = transformIAmStatement(statement);
  }
   else if (findKeyword(statement, "because", 0) >= 0) //Gets rid of these because statements and turns them into another question.
  {
     if (findKeyword(statement, "you", 0) >= 0)
      {
        statement = statement.replace("you ", "I ");
       }
        else
       {
        statement = statement.replace("I ", "you ");
       }
    response = "Is it really because " + statement.substring(findKeyword(statement, "because", 0) + 7).trim() + "?";
  }
  else if (findKeyword(statement, "you are", 0) >= 0) //For "you are" statements
  {
    response = transformYouAreStatement(statement);
  }
  else if (findKeyword(statement, "are", 0) >= 0) //For general "are" statements.
  {
   response = "Why are " + statement.trim().replace(" are ", " ").toLowerCase() + "?"; //Replacing are with nothing and puts "Why are" in the beginning of the question
  } //I statements, is
  else if (findKeyword(statement, "is", 0) >= 0)
  {
    response = "Why is " + statement.trim().replace(" is ", " ").toLowerCase() + "?";
  }
    else
  {
   // Look for a two word (you <something> me)
   // pattern
   int psn = findKeyword(statement, "you", 0);
   int psnI = findKeyword(statement, "I", 0);
     
   if (psn >= 0
     && findKeyword(statement, "me", psn) >= 0)
   {
    response = transformYouMeStatement(statement);
   }
   else if (psnI >= 0
     && findKeyword(statement, "you", psnI) >= 0)
   {
     response = transformIYouStatement(statement);
   }         
   else
   {
    response = getRandomResponse();
   }
  }
  return response;
 }
 

 /**
  * Take a statement with "I want to <something>." and transform it into 
  * "What would it mean to <something>?"
  * @param statement the user statement, assumed to contain "I want to"
  * @return the transformed statement
  */
 private String transformIWantToStatement(String statement)
 {
  
  int psn = findKeyword (statement, "I want to", 0);
  String restOfStatement = statement.substring(psn + 9).trim();
  return "What would it mean to " + restOfStatement + "?";
 }

 private String transformIWantStatement(String statement) //New String method for "I want" statements.
 {
   int psn = findKeyword (statement, "I want", 0);
   String restOfStatement = statement.substring(psn + 6).trim(); //"I want" is 6 characters, therefor to go past the "I want," we move the substring 6 characters past.
   return "Would you really be happy if you had " + restOfStatement + "?";
}
   
 
 
 /**
  * Take a statement with "you <something> me" and transform it into 
  * "What makes you think that I <something> you?"
  * @param statement the user statement, assumed to contain "you" followed by "me"
  * @return the transformed statement
  */
 
 private String transformIAmStatement(String statement)
 {

   int psnOfAm = findKeyword(statement, "am", 0);
   statement = statement.substring(psnOfAm + 2);
   return "Why are you " + statement.trim() + "?";
 }
 
 private String transformYouAreStatement(String statement)
 {
 
   int psnOfare = findKeyword(statement, "are", 0);
   statement = statement.substring(psnOfare + 3);
   return "Why do you think I am " + statement.trim() + "?";
 }
 
 private String transformYouMeStatement(String statement)
 {
  
  int psnOfYou = findKeyword (statement, "you", 0);
  int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
  
  String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
  return "What makes you think that I " + restOfStatement + " you?";
 }
 
 private String transformIYouStatement(String statement)
 {
   statement = statement.trim();
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals(".")) //Removing the period from the IYou statement.
  {
   statement = statement.substring(0, statement
     .length() - 1);
  } 
  
  int psnOfI = findKeyword (statement, "I", 0);
  int psnOfYou = findKeyword (statement, "you", psnOfI + 1);
  
  String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
  return "Why do you " + restOfStatement + " me?"; //Note that this structure sometimes sounds weird. Example: "I don't like you" -> "Why do you don't like me"
  //This could be improved by changing don't to not.
 }

 

 
 
 private int findKeyword(String statement, String goal,
   int startPos)
 {
  String phrase = statement.trim();
  // The only change to incorporate the startPos is in
  // the line below
  int psn = phrase.toLowerCase().indexOf(
    goal.toLowerCase(), startPos);

  // Refinement--make sure the goal isn't part of a
  // word
  while (psn >= 0)
  {
   // Find the string of length 1 before and after
   // the word
   String before = " ", after = " ";
   if (psn > 0)
   {
    before = phrase.substring(psn - 1, psn)
      .toLowerCase();
   }
   if (psn + goal.length() < phrase.length())
   {
    after = phrase.substring(
      psn + goal.length(),
      psn + goal.length() + 1)
      .toLowerCase();
   }

   // If before and after aren't letters, we've
   // found the word
   if (((before.compareTo("a") < 0) || (before
     .compareTo("z") > 0)) // before is not a
           // letter
     && ((after.compareTo("a") < 0) || (after
       .compareTo("z") > 0)))
   {
    return psn;
   }

   // The last position didn't work, so let's find
   // the next, if there is one.
   psn = phrase.indexOf(goal.toLowerCase(),
     psn + 1);

  }

  return -1;
 }
 
 
 
 
 /**
  * Pick a default response to use if nothing else fits.
  * @return a non-committal string 
  */
 private String getRandomResponse() //Replaces old method with an array instead.
 {
  Random r = new Random (); //Uses the utility we initialized earlier.
  return randomResponses[r.nextInt(randomResponses.length)];
 }
 
 private String[] randomResponses = {"Interesting, tell me more",
   "Hmmm.",
   "Do you really think so?",
   "You don't say.",
   "Wow, fascinating.",
   "Same.",
   "That's truly remarkable.",
   "Thanks for telling me that.",
   "Oh really?"
 };
 
}

 