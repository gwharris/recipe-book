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
                System.out.println("Enter ingredient " + (numingred+1) + ": ");
                ingredient = input.nextLine();
                ingredients[i] = ingredient;
            }


            System.out.println("\nHow many cooking steps does your recipe have?");
            int numsteps = input.nextInt();
            input.nextLine();
            String[] steps = new String[numsteps];
            for (int i = 0; i < numsteps; i++){
                System.out.println("Enter cooking step " + (numsteps+1) + ": ");
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
            
            // the second person needs information from the first one but you can still work on it
        } else { // error, end the program
            System.out.println("ERROR....Invalid Option");
        }
    }
}