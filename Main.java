import java.util.*;
import java.io.*; 

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // we should have a function that should locate the seperate file that has our recipes and reads it
        ArrayList<Recipe> recipeList = new ArrayList<>();
        
        try { // will try to read the recipelist.txt
			// this scanner will scan through the file
			Scanner scan = new Scanner(new FileInputStream("recipelist.txt"));

			while (scan.hasNextLine()) {
				// the first line is the "NAME" line
				String[] NAME = scan.nextLine().split(":");
				// the second line is the "DESCRIPTION"
				String[] DESCRIPTION = scan.nextLine().split(":");

                // the third and fourth line are the lists that are separated by a '~'
                String[] INGREDIENTLIST = scan.nextLine().split(":")[1].split("~");
                INGREDIENTLIST[0] = INGREDIENTLIST[0].trim();
                String[] COOKINGSTEPS = scan.nextLine().split(":")[1].split("~");
                COOKINGSTEPS[0] = COOKINGSTEPS[0].trim();

                Recipe newRecipe = new Recipe(NAME[1].trim(), DESCRIPTION[1].trim(), INGREDIENTLIST, COOKINGSTEPS);

				// add the recipe to the recipeList
				recipeList.add(newRecipe);
			} 
			
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Program ending.");
		} 


        // beginning of the user prompted program
        System.out.println("Enter '1' for Recipe Creation or '2' for Recipe Retrieval");
        int option = input.nextInt();

        if(option == 1) { // Recipe Creation
            // one person work on this
            // this person should just receive the information for now as we dont have a template of how 
            // to read/write the recipes on another file
            ArrayList<String> ingredients = new ArrayList<>();
            ArrayList<String> steps = new ArrayList<>();
            String ingredient = "";
            String step = "";

            System.out.println("Please enter the name of your recipe: ");
            String inputname = scan.nextLine();
            System.out.println("Please enter the description of your recipe: ");
            String inputdesc = scan.nextLine();
            System.out.println("How many ingredients does your recipe have? ");
            int numingred = scan.nextInt();
            for (int i = 0; i < numingred; i++){
                System.out.println("Enter your ingredient: ");
                ingredient = scan.nextLine();
                ingredients.add(ingredient);
            }
            System.out.println("How many cooking steps does your recipe have? ");
            int numsteps = scan.nextInt();
            for (int i = 0; i < numsteps; i++){
                System.out.println("Enter your cooking step: ");
                step = scan.nextLine();
                steps.add(step);
            }
            Recipe newRecipe = new Recipe(inputname, inputdesc, ingredients, steps);
            recipeList.add(newRecipe);

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