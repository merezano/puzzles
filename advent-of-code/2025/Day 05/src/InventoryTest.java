import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        inventory.addFreshIngredientIDs("3-5");
        inventory.addFreshIngredientIDs("10-14");
        inventory.addFreshIngredientIDs("16-20");
        inventory.addFreshIngredientIDs("12-18");
    }

    @ParameterizedTest
    @CsvSource({
            "1, false",
            "5, true",
            "8, false",
            "11, true",
            "17, true",
            "32, false"
    })
    public void canDetectWhenAnIngredientIsFresh(String input, boolean expected) {
        assertEquals(expected, inventory.isIngredientFresh(input));
    }

    @Test
    public void testFromInput() {
        Inventory inventory = new Inventory();
        int countOfFreshIngredients = 0;

        try {
            java.nio.file.Path path = java.nio.file.Paths.get("src/input.txt");
            java.util.List<String> lines = java.nio.file.Files.readAllLines(path);

            for (String line : lines) {
                if (line.isEmpty())
                    continue;
                if (line.contains("-"))
                    inventory.addFreshIngredientIDs(line);
                else
                    countOfFreshIngredients += inventory.isIngredientFresh(line) ? 1 : 0;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(811, countOfFreshIngredients);
    }
}
