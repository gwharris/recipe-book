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
            return;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Program ending.");
            return;
        }

        // beginning of the user prompted program
        System.out.println("\n\n--------------------------------------------------");
        System.out.println("Welcome to the Recipe Book! Here are your options!");
        System.out.println("--------------------------------------------------\n");
        boolean continueLoop = true;

        do {
            System.out.println("\nEnter (1) for Recipe Creation or (2) for Recipe Retrieval. Enter (0) to quit.");

            String userInput = "";
            boolean flag = true;
            while (flag) { // Keep looping until int
                userInput = input.nextLine();
                flag = isNumber(userInput);
            }
            double option = Double.parseDouble(userInput);
            int displayOpt = -1;

            if (option == 0) {
                continueLoop = false;

                System.out.println("\n\n----------------------------------------");
                System.out.println("   Thank you for visiting Recipe Book.");
                System.out.println(" Hope you have a great rest of your day!");
                System.out.println("----------------------------------------\n\n");
                System.out.println("EXITING PROGRAM...\n");
            } else if (option == 1) {
                String ingredient = "";
                String step = "";

                System.out.println("\n\n\n********** RECIPE CREATION **********\n\n");

                System.out.println("\nPlease enter the name of your recipe:");
                String inputname = input.nextLine();

                System.out.println("\nPlease enter the description of your recipe:");
                String inputdesc = input.nextLine();

                System.out.println("\nHow many ingredients does your recipe have?");
                String numval = input.nextLine();
                while (isNumber(numval)) {
                    numval = input.nextLine();
                }
                double numingred = Double.parseDouble(numval);
                int invalidFlag = 1;

                // Choose search option and make sure there are no invalid inputs
                while (invalidFlag == 1) {
                    if (numingred > 0) {
                        invalidFlag = 0;
                    } else {
                        System.out.println("That was an invalid integer. Please try again.");
                        numingred = input.nextInt();
                    }
                }

                String[] ingredients = new String[(int) numingred];
                System.out.print("\n");
                for (int i = 0; i < numingred; i++) {
                    System.out.print("-Enter ingredient " + (i + 1) + ": ");
                    ingredient = input.nextLine();
                    ingredients[i] = ingredient;
                }
                System.out.print("\n");

                System.out.println("\nHow many cooking steps does your recipe have?");
                numval = input.nextLine();
                while (isNumber(numval)) {
                    numval = input.nextLine();
                }
                double numsteps = Double.parseDouble(numval);

                // Choose search option and make sure there are no invalid inputs
                while (invalidFlag == 1) {
                    if (numsteps != 0) {
                        invalidFlag = 0;
                    } else {
                        System.out.println("That was an invalid integer. Please try again.");
                        numsteps = input.nextInt();
                    }
                }

                String[] steps = new String[(int) numsteps];
                System.out.print("\n");
                for (int i = 0; i < numsteps; i++) {
                    System.out.print("-Enter cooking step " + (i + 1) + ": ");
                    step = input.nextLine();
                    steps[i] = step;
                }
                System.out.print("\n");

                System.out.println("\n\n\n-------Recipe is now added onto your recipe list-------\n\n");

                Recipe newRecipe = new Recipe(inputname, inputdesc, ingredients, steps);
                recipeList.add(newRecipe);
            } else if (option == 2) {
                System.out.println("\n\n********** RECIPE SEARCH **********\n");

                System.out.println(
                        "\nWould you like to (1) search for a recipe, (2) browse recipes, or (3) get the chef's selection? Enter 1, 2, or 3.");
                int inputType = input.nextInt();
                int invalidFlag = 1;
                int searchType = 0;

                // Choose search option and make sure there are no invalid inputs
                while (invalidFlag == 1) {
                    if (inputType == 1) {
                        searchType = 1;
                        invalidFlag = 0;
                    } else if (inputType == 2 || inputType == 3) {
                        searchType = 2;
                        invalidFlag = 0;
                    } else {
                        System.out.println("That was an invalid option. Please try again.");
                        inputType = input.nextInt();
                    }
                }

                // Retrieve Recipes
                String inputSearch;

                // Search with name
                if (searchType == 1) {
                    System.out.println("\n\nWhat recipe would you like to look up? Type in below: ");
                    input.nextLine();
                    inputSearch = input.nextLine().toLowerCase();

                    searchRecipe(recipeList, inputSearch);
                    // Search by recipe list
                } else if (searchType == 2) {
                    int recipeNum = 0;
                    if (inputType == 3) {
                        recipeNum = (int) ((Math.random() * (recipeList.size() - 1)) + 1);
                    } else {
                        System.out.println("\nChoose a recipe from the list below.");
                        for (int i = 0; i < recipeList.size(); i++) {
                            String recipeName = Integer.toString(i + 1) + ". " + recipeList.get(i).Name;
                            System.out.println(recipeName);
                        }

                        System.out.println("\nPlease type the number of the recipe you would like: ");
                        try {
                            recipeNum = input.nextInt();
                        } catch (InputMismatchException x) {
                            System.out.println("Please provide an integer");
                        }
                    }
                    try {
                        if (recipeNum == 0) {
                            System.out.println("\nNot a valid integer. Please try again.");
                        } else {
                            recipeNum = recipeNum - 1;

                            System.out.println(
                                    "\nWould you like to (1) display the whole recipe or (2) display the recipe step-by-step?");
                            displayOpt = input.nextInt();

                            if (displayOpt == 1) {
                                try {
                                    printRecipe(recipeNum, recipeList);
                                } catch (IndexOutOfBoundsException a) {
                                    System.out.println("\nInvalid recipe choice. Please choose a valid option");
                                }
                            } else if (displayOpt == 2) {
                                try {
                                    printSteps(recipeNum, recipeList);
                                } catch (IndexOutOfBoundsException a) {
                                    System.out.println("\nInvalid recipe choice. Please choose a valid option");
                                }
                            } else {
                                System.out.println("\nERROR....INVALID OPTION");
                            }
                        }
                    } catch (NumberFormatException n) {
                        System.out.println("An error occurred. Please try again.");
                        // Can implement to loop back to search
                    }
                } else {
                    System.out.println("Not a valid integer. Please try again.");
                }
            } else {
                System.out.println("Not a valid integer. Please try again.");
            }
        } while (continueLoop);

        writeRecipes(recipeList);
    }

    // Method that checks numeric input
    public static Boolean isNumber(String arg) {
        try {
            Double.parseDouble(arg);
            return false; // Exit loop
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer option.");
            return true; // Keep looping
        }
    }

    // Method that adds all recipes from RecipeList onto text file
    public static void writeRecipes(ArrayList<Recipe> rList) {
        try {
            FileWriter myWriter = new FileWriter("recipelist.txt");

            for (int i = 0; i < rList.size(); i++) {
                myWriter.write("\n");
                if (i != 0)
                    myWriter.write("\n");
                myWriter.write("NAME: " + rList.get(i).getName() + "\n");
                myWriter.write("DESCRIPTION: " + rList.get(i).getDescription() + "\n");
                myWriter.write("INGREDIENT-LIST: " + rList.get(i).IngredientList[0]);
                for (int j = 1; j < rList.get(i).IngredientList.length; j++) {
                    myWriter.write("~" + rList.get(i).IngredientList[j]);
                }
                myWriter.write("\nCOOKING-STEPS: " + rList.get(i).CookingSteps[0]);
                for (int j = 1; j < rList.get(i).CookingSteps.length; j++) {
                    myWriter.write("~" + rList.get(i).CookingSteps[j]);
                }
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred and your recipe was not saved.");
            e.printStackTrace();
        }
    }

    // Method that prints out specific recipe in full
    public static void printRecipe(int recipeIndex, ArrayList<Recipe> rList) {
        System.out.println("------------------------------------------\n");
        System.out.println("Name: " + rList.get(recipeIndex).getName());
        System.out.println("Description: " + rList.get(recipeIndex).getDescription());
        System.out.println("Ingredient List: ");
        String[] allIngredients = rList.get(recipeIndex).IngredientList;
        for (int i = 0; i < allIngredients.length; i++) {
            System.out.println("\t- " + allIngredients[i]);
        }
        System.out.println("Recipe Steps: ");
        String[] allSteps = rList.get(recipeIndex).CookingSteps;
        for (int i = 0; i < allSteps.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + allSteps[i]);
        }
        System.out.println("\n------------------------------------------\n\n");
    }

    // Method that searches for recipe through specific name
    public static void searchRecipe(ArrayList<Recipe> rList, String searchName) {
        int recipeIndex = -1;
        Scanner input = new Scanner(System.in);
        int displayOpt = -1;

        // Checks to make sure recipe is in the list
        for (int i = 0; i < rList.size(); i++) {
            String recipeName = rList.get(i).Name.toLowerCase();
            if (searchName.equals(recipeName)) {
                recipeIndex = i;
                break;
            }
        }

        if (recipeIndex != -1) {
            // Implement Recipe Exploration (function would be easier as the other search
            // also needs it)
            System.out.println("\n\n\n-------Recipe Found-------\n\n");

            do {
                System.out.println(
                        "\nWould you like to (1) display the whole recipe or (2) display the recipe step-by-step?");
                displayOpt = input.nextInt();

                if (displayOpt == 1) {
                    printRecipe(recipeIndex, rList);
                } else if (displayOpt == 2) {
                    printSteps(recipeIndex, rList);
                } else {
                    System.out.println("\nERROR....INVALID OPTION....TRY AGAIN");
                }
            } while (displayOpt != 1 && displayOpt != 2);
        } else {
            System.out.println("\n\n-------There is no recipe under that search-------\n\n");
            // Could implement to make it loop back
        }
    }

    // Method that prints out specific recipe in Steps
    public static void printSteps(int recipeIndex, ArrayList<Recipe> rList) {
        int step = 0;
        int lastStep = rList.get(recipeIndex).getSteps().length;
        String[] CookingSteps = rList.get(recipeIndex).getSteps();
        boolean recipeSteps = false;
        int option = -1;
        Scanner input = new Scanner(System.in);

        do {
            if (!recipeSteps) {
                System.out.println("\n\n\n------------------------------------------\n");
                System.out.println("Name: " + rList.get(recipeIndex).getName());
                System.out.println("Description: " + rList.get(recipeIndex).getDescription());
                System.out.println("Ingredient List: ");
                String[] allIngredients = rList.get(recipeIndex).IngredientList;
                for (int i = 0; i < allIngredients.length; i++) {
                    System.out.println("\t- " + allIngredients[i]);
                }
                System.out.println("\n------------------------------------------\n");

                System.out.println(
                        "\nWould you like to (1) view the first step of the recipe? If not, input any number.");
                option = input.nextInt();

                if (option == 1) {
                    recipeSteps = true;
                } else {
                    System.out.println("Thank you! Breaking out of the recipe...");
                    recipeSteps = false;
                }
            } else if (recipeSteps) {
                do {
                    System.out.println("\n\nStep " + (step + 1) + ": ");
                    System.out.println("\t" + CookingSteps[step]);

                    if (step == 0) {
                        System.out.println(
                                "\n\nWould you like to (1) go back to the recipe information or (2) continue to the next step?");
                        option = input.nextInt();

                        if (option == 1) {
                            recipeSteps = false;
                            break;
                        } else if (option == 2) {
                            step += 1;
                        } else {
                            System.out.println("Invalid input, please try again.");
                        }
                    } else {
                        if (step == lastStep - 1) {
                            System.out.println(
                                    "\n\nWould you like to (1) go to the previous step or (2) finish the Recipe Retrieval.");
                        } else {
                            System.out.println(
                                    "\n\nWould you like to (1) go to the previous step or (2) continue to the next step.");
                        }
                        option = input.nextInt();

                        if (option == 1) {
                            step -= 1;
                        } else if (option == 2) {
                            step += 1;
                        } else {
                            System.out.println("Invalid input, please try again.");
                        }
                    }
                } while (step != lastStep);
            }
        } while (step != lastStep);
        System.out.println("\n");
    }
}
