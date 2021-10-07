import java.util.*;
import java.io.*;

public class Recipe {
    String Name;
    String Description;
    String[] IngredientList;
    String[] CookingSteps;
    int RecipeID;

    public Recipe(String n, String d, String[] i, String[] c) {
        this.Name = n;
        this.Description = d;
        // shallow copy, dont think we need a deep copy  
        this.IngredientList = i;
        this.CookingSteps = c;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String n) {
        this.Name = n;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String d) {
        this.Description = d;
    }
}