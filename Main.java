import java.util.*;
import java.io.*; 

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // we should have a function that should locate the seperate file that has our recipes and reads it
        ArrayList<Recipe> recipeList = new ArrayList<>();
        // one person should work on the txt file and how were going to read/write



        // beginning of the user prompted program
        System.out.println("Enter '1' for Recipe Creation or '2' for Recipe Retrieval");
        int option = input.nextInt();

        if(option == 1) { // Recipe Creation
            // one person work on this
            // this person should just receive the information for now as we dont have a template of how 
            // to read/write the recipes on another file
        } else if(option == 2) { // Recipe Retrieval
            // two people should work on this. 
            // 1) the search function and the browsing all existing recipes
            // 2) the recipe exploration...on the project 1 pdf

            // the second person needs information from the first one but you can still work on it
        } else { // error, end the program
            System.out.println("ERROR....Invalid Option");
        }
    }
}