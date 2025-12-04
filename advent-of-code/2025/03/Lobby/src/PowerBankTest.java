import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PowerBankTest {
    @Test
    public void ensureTotalOutputJoltageIsTheSumOfTheMaximumJoltagesFromEachBank() {
        PowerBank powerBank = new PowerBank();
        powerBank.addBatteryBank(new BatteryBank("987654321111111"));
        powerBank.addBatteryBank(new BatteryBank("811111111111119"));
        powerBank.addBatteryBank(new BatteryBank("234234234234278"));
        powerBank.addBatteryBank(new BatteryBank("818181911112111"));

        assertEquals(3121910778619L, powerBank.getTotalOutputJoltage());
    }

    @Test
    public void requireBatteryBankIsNotNull() {
        PowerBank powerBank = new PowerBank();
        assertThrows(IllegalArgumentException.class, () -> {
            powerBank.addBatteryBank(null);
        });
    }

    @Test
    public void ensureBatteryBanksAreTracked() {
        PowerBank powerBank = new PowerBank();
        assertEquals(0, powerBank.getBatteryBankCount());
        powerBank.addBatteryBank(new BatteryBank("987654321111111"));
        assertEquals(1, powerBank.getBatteryBankCount());
    }
}
