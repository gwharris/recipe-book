import java.util.*;
import java.io.*;

public class Recipe {
    String Name;
    String Description;
    ArrayList<String> IngredientList;
    ArrayList<String> CookingSteps;
    int RecipeID;

    public Recipe(String n, String d, ArrayList<String> i, ArrayList<String> c) {
        this.Name = n;
        this.Description = d;
        this.IngredientList = i;
        this.CookingSteps = c;
    }

    
}