import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PowerBank {
    private final ArrayList<BatteryBank> batteryBanks;

    public PowerBank() {
        batteryBanks = new ArrayList<>();
    }

    public void addBatteryBank(BatteryBank batteryBank) {
        // Precondition: batteryBank must not be null
        if (batteryBank == null) {
            throw new IllegalArgumentException("Battery bank cannot be null");
        }
        batteryBanks.add(batteryBank);
    }

    public Long getTotalOutputJoltage() {
        Long totalOutputJoltage = 0L;
        for (BatteryBank batteryBank : batteryBanks) {
            totalOutputJoltage += batteryBank.turnOn(12);
        }
        // Postcondition: total output joltage must be non-negative
        assert totalOutputJoltage >= 0 : "Total output joltage must be non-negative";
        return totalOutputJoltage;
    }

    // Invariant accessor: returns unmodifiable view of battery banks
    public List<BatteryBank> getBatteryBanks() {
        return Collections.unmodifiableList(batteryBanks);
    }

    public int getBatteryBankCount() {
        return batteryBanks.size();
    }
}
