import java.util.*;
import java.io.*; 

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Recipe> recipeList = new ArrayList<>();
        String fileName = args[0];

        // will read the txt file
        try { 
            Scanner scan = new Scanner(new FileInputStream(fileName));

            while (scan.hasNextLine()) {
                scan.nextLine();

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
        System.out.println("Welcome to the Recipe Book! Here are the options:");
        boolean continueLoop = true;

        do {
            System.out.println("\nEnter (1) for Recipe Creation or (2) for Recipe Retrieval. Enter (0) to quit.");
            
            String userInput = "";
            boolean flag = true;
            while(flag) { // Keep looping until int
                userInput = input.nextLine();
                flag = isNumber(userInput);
            }
            double option = Double.parseDouble(userInput);

            if(option == 0) {
                continueLoop = false;
            } else if(option == 1) {
                String ingredient = "";
                String step = "";

                System.out.println("\nPlease enter the name of your recipe:");
                String inputname = input.nextLine();

                System.out.println("\nPlease enter the description of your recipe:");
                String inputdesc = input.nextLine();

                System.out.println("\nHow many ingredients does your recipe have?");
                int numingred = input.nextInt();
                input.nextLine();
                String[] ingredients = new String[numingred];
                for (int i = 0; i < numingred; i++){
                    System.out.println("\nEnter ingredient " + (i+1) + ": ");
                    ingredient = input.nextLine();
                    ingredients[i] = ingredient;
                }

                System.out.println("\nHow many cooking steps does your recipe have?");
                int numsteps = input.nextInt();
                input.nextLine();
                String[] steps = new String[numsteps];
                for (int i = 0; i < numsteps; i++){
                    System.out.println("\nEnter cooking step " + (i+1) + ": ");
                    step = input.nextLine();
                    steps[i] = step;
                }

                System.out.println("\nRecipe is now added onto your recipe list\n");

                Recipe newRecipe = new Recipe(inputname, inputdesc, ingredients, steps);
                recipeList.add(newRecipe);
            } else if(option == 2) {
                // two people should work on this. 
                // 1) the search function and the browsing all existing recipes
                // 2) the recipe exploration...on the project 1 pdf

                System.out.println("\nWould you like to (1) search for a recipe or (2) browse recipes? Enter 1 or 2.");
                int inputType = input.nextInt();
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
                    System.out.println("\nWhat recipe would you like to look up? Type in below: ");
                    input.nextLine();
                    inputSearch = input.nextLine();
                    //********DOES NOT ACCOUNT FOR NUMBERS IN SEARCH********* (will cause an error)
                    // we can fix this with a try/catch statement and a loop to continue typing with no numbers
                    String inputSearch_lower = inputSearch.toLowerCase();
                    int recipeIndex = -1;

                    //Checks to make sure recipe is in the list
                    for (int i = 0; i < recipeList.size(); i++) {
                        String recipeName = recipeList.get(i).Name.toLowerCase();
                        if(inputSearch_lower.equals(recipeName)) {
                            recipeIndex = i;
                            break;
                        } 
                    }

                    if (recipeIndex != -1) {
                        //Implement Recipe Exploration (function would be easier as the other search also needs it)
                        System.out.println("\nRecipe Found\n\n");

                        printRecipe(recipeIndex, recipeList);
                    } else {
                        System.out.println("There is no recipe under that search.");
                        //Could implement to make it loop back
                    }

                //Search by recipe list
                } else if (searchType == 2){ //searchType == 2
                    System.out.println("\nChoose a recipe from the list below.");
                    for (int i = 0; i < recipeList.size(); i++) {
                        String recipeName = Integer.toString(i + 1) + ". " + recipeList.get(i).Name;
                        System.out.println(recipeName);
                    }

                    System.out.println("\nPlease type the number of the recipe you would like: ");
                    try {
                        int recipeNum = input.nextInt() - 1;
                        printRecipe(recipeNum, recipeList);
                    } catch (NumberFormatException n) {
                        System.out.println("An error occurred.");
                        //Can implement to loop back to search
                    }
                } else {
                    System.out.println("ERROR....Something went wrong.");
                }
            } else { // error, end the program
                System.out.println("ERROR....Invalid Option");
            }  
        } while(continueLoop);

        writeRecipes(recipeList);
    }

    // Method that checks numeric input
    public static Boolean isNumber(String arg) {
        try {
            Double.parseDouble(arg);
            return false; // Exit loop
        }
        catch(NumberFormatException e) {
            System.out.println("Please enter an integer option.");
            return true; // Keep looping
        }
    }

    // Method that adds all recipes from RecipeList onto text file
    public static void writeRecipes(ArrayList<Recipe> rList) {
        try {
            FileWriter myWriter = new FileWriter("recipelist.txt");

            for(int i = 0; i < rList.size(); i++) {
                myWriter.write("\n");
                if(i != 0) myWriter.write("\n");
                myWriter.write("NAME: " + rList.get(i).getName() + "\n");
                myWriter.write("DESCRIPTION: " + rList.get(i).getDescription() + "\n");
                myWriter.write("INGREDIENT-LIST: " + rList.get(i).IngredientList[0]);
                for(int j = 1; j < rList.get(i).IngredientList.length; j++) {
                    myWriter.write("~" + rList.get(i).IngredientList[j]);
                }
                myWriter.write("\nCOOKING-STEPS: " + rList.get(i).CookingSteps[0]);
                for(int j = 1; j < rList.get(i).CookingSteps.length; j++) {
                    myWriter.write("~" + rList.get(i).CookingSteps[j]);
                }
            }
        
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred and your recipe was not saved.");
            e.printStackTrace();
        }
    }

    public static void printRecipe(int recipeIndex, ArrayList<Recipe> rList) {
        System.out.println("Name: " + rList.get(recipeIndex).getName());
        System.out.println("Description: " + rList.get(recipeIndex).getDescription());
        System.out.println("Ingredient List: ");
        String[] allIngredients = rList.get(recipeIndex).IngredientList;
        for(int i = 0; i < allIngredients.length; i++) {
            System.out.println("\t- " + allIngredients[i]);
        }
        System.out.println("Recipe Steps: ");
        String[] allSteps = rList.get(recipeIndex).CookingSteps;
        for(int i = 0; i < allSteps.length; i++) {
            System.out.println("\t" + (i+1) + ". " + allSteps[i]);
        }
    }
}

