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

    @Test
    public void requireBatteryBankContainsOnlyDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BatteryBank("123abc456");
        });
    }

    @Test
    public void requirePositiveNumberOfBatteriesToTurnOn() {
        BatteryBank batteryBank = new BatteryBank("123456789012345");
        assertThrows(IllegalArgumentException.class, () -> {
            batteryBank.turnOn(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            batteryBank.turnOn(-1);
        });
    }

    @Test
    public void requireSufficientBatteriesAvailable() {
        BatteryBank batteryBank = new BatteryBank("12345");
        assertThrows(IllegalArgumentException.class, () -> {
            batteryBank.turnOn(10);
        });
    }

    @ParameterizedTest
    @CsvSource({
        "987654321111111, 987654321111",
        "811111111111119, 811111111119",
        "234234234234278, 434234234278",
        "818181911112111, 888911112111"
    })
    public void ensureTheLargestJoltageIsProducedWhenTurnedOn(String batteries, long expected) {
        BatteryBank batteryBank = new BatteryBank(batteries);
        assertEquals(expected, batteryBank.turnOn(12));
    }

}
