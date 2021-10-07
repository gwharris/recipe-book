import java.util.*;
import java.io.*; 

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Recipe> recipeList = new ArrayList<>();
        String fileName = args[0]; // recipelist.txt

        File tempFile = new File(fileName);
        boolean exists = tempFile.exists();
        
        if(exists) { // checks if text file exists
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
        }

        // beginning of the user prompted program
        System.out.println("Enter '1' for Recipe Creation or '2' for Recipe Retrieval");
        int option = input.nextInt();
        input.nextLine();

        if(option == 1) { // Recipe Creation
            // one person work on this
            // this person should just receive the information for now as we dont have a template of how 
            // to read/write the recipes on another file
            String ingredient = "";
            String step = "";

            System.out.println("\nPlease enter the name of your recipe: ");
            String inputname = input.nextLine();


            System.out.println("\nPlease enter the description of your recipe: ");
            String inputdesc = input.nextLine();

            System.out.println("\nHow many ingredients does your recipe have? ");
            int numingred = input.nextInt();
            input.nextLine();
            String[] ingredients = new String[numingred];
            for (int i = 0; i < numingred; i++){
                System.out.println("Enter your ingredient: ");
                ingredient = input.nextLine();
                ingredients[i] = ingredient;
            }


            System.out.println("\nHow many cooking steps does your recipe have? ");
            int numsteps = input.nextInt();
            input.nextLine();
            String[] steps = new String[numsteps];
            for (int i = 0; i < numsteps; i++){
                System.out.println("Enter your cooking step: ");
                step = input.nextLine();
                steps[i] = step;
            }

            Recipe newRecipe = new Recipe(inputname, inputdesc, ingredients, steps);
            recipeList.add(newRecipe);

            // writes the recipe onto the new recipelist
            try {
                FileWriter myWriter = new FileWriter("recipelist.txt");
                myWriter.write("NAME: " + inputname + "\n");
                myWriter.write("DESCRIPTION: " + inputdesc + "\n");
                myWriter.write("INGREDIENT-LIST: " + ingredients[0]);
                for(int i = 1; i < ingredients.length; i++) {
                    myWriter.write("~" + ingredients[i]);
                }
                myWriter.write("\nCOOKING-STEPS: " + steps[0]);
                for(int i = 1; i < steps.length; i++) {
                    myWriter.write("~" + steps[i]);
                }
                myWriter.write("\n");

                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        } else if(option == 2) { // Recipe Retrieval
            // two people should work on this. 
            // 1) the search function and the browsing all existing recipes
            // 2) the recipe exploration...on the project 1 pdf

            System.out.println("(1) Would you like to search for a recipe or (2) browse recipes? Enter 1 or 2.");
            int inputType = input.nextInt();;
            int invalidFlag = 1;
            int searchType = 0;

            //Choose search option and make sure there are no invalid inputs
            while (invalidFlag == 1) {
                if (inputType == 1) {
                    searchType = 1;
                    invalidFlag = 0;
                } else if (inputType == 2) {
                    searchType = 2; 
                    invalidFlag = 0;
                } else {
                    System.out.println("That was an invalid option.");
                }
            }

            //Retrieve Recipes
            String inputSearch;

            //Search with name
            if (searchType == 1) {
                System.out.println("What recipe would you like to look up? Type in below: ");
                inputSearch = input.nextLine();
                //********DOES NOT ACCOUNT FOR NUMBERS IN SEARCH********* (will cause an error)
                String inputSearch_lower = inputSearch.toLowerCase();
                int recipeFound = 0;

                //Checks to make sure recipe is in the list
                for (int i = 0; i < recipeList.size(); i++) {
                    //********Issue retreiving the recipe object*********
                    String recipeName = recipeList.get(i).Name.toLowerCase();
                    if (inputSearch_lower.equals(recipeName)) {
                        recipeFound = 1;
                    } 
                }

                if (recipeFound == 1) {
                    //Implement Recipe Exploration (function would be easier as the other search also needs it)
                } else {
                    System.out.println("There is no recipe under that search.");
                    //Could implement to make it loop back
                }

            //Search by recipe list
            } else if (searchType == 2){ //searchType == 2
                System.out.println("Please choose a recipe from the list below.");
                for (int i = 0; i < recipeList.size(); i++) {
                    //********Issue retreiving the recipe object*********
                    String recipeName = Integer.toString(i + 1) + ". " + recipeList.get(i).Name;
                    System.out.println(recipeName);
                }
                System.out.println("Please type the number of the recipe you would like: ");
                try {
                    int recipeNum = input.nextInt() - 1;
                } catch (NumberFormatException n) {
                    System.out.println("An error occurred.");
                    //Can implement to loop back to search
                }
                //Implement Recipe Exploration use (recipeNum)
            } else {
                System.out.println("ERROR....Something went wrong.");
            }
            
            // the second person needs information from the first one but you can still work on it
        } else { // error, end the program
            System.out.println("ERROR....Invalid Option");
        }
    }
}
















