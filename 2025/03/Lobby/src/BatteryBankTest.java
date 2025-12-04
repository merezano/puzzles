import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BatteryBankTest {
    @Test
    public void requireBatteryBankIsNotEmpty() {
        String batteries = "";
        assertThrows(IllegalArgumentException.class, () -> {
            BatteryBank batteryBank = new BatteryBank(batteries);
        });
    }

    @Test
    public void requireBatteryBankIsNotNull() {
        String batteries = null;
        assertThrows(IllegalArgumentException.class, () -> {
            BatteryBank batteryBank = new BatteryBank(batteries);
        });
    }

    @ParameterizedTest
    @CsvSource({
        "987654321111111, 98",
        "811111111111119, 89",
        "234234234234278, 78",
        "818181911112111, 92"
    })
    public void ensureTheLargestJoltageIsTheNumberFormedByTheTwoHighestBatteryJoltages(String batteries, int expected) {
        BatteryBank batteryBank = new BatteryBank(batteries);
        assertEquals(expected, batteryBank.getLargestJoltage());
    }

}
