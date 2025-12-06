import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private final List<String> freshIngredientIDs;
    private final List<String> ingredients;

    public Inventory() {
        freshIngredientIDs = new ArrayList<>();
        ingredients = new ArrayList<>();
    }

    public void addFreshIngredientIDs(String anIDRange) {
        freshIngredientIDs.add(anIDRange);
    }

    public void addIngredient(String ingredientID) {
        ingredients.add(ingredientID);
    }

    public boolean isIngredientFresh(String ingredientID) {
        for (String freshIDRange : freshIngredientIDs) {
            String[] idRangeParts = freshIDRange.split("-");
            Long startID = Long.parseLong(idRangeParts[0]);
            Long endID = Long.parseLong(idRangeParts[1]);
            if (Long.parseLong(ingredientID) >= startID && Long.parseLong(ingredientID) <= endID) {
                return true;
            }
        }
        return false;
    }
}
