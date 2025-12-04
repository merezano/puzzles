public class BatteryBank {
    private final String batteries;

    public BatteryBank(String batteries) {
        if (batteries == null || batteries.isEmpty()) {
            throw new IllegalArgumentException("Battery bank cannot be empty");
        }

        this.batteries = batteries;
    }

    public int getLargestJoltage() {
        int maxNumberOfBatteriesToTurnOn = 2;
        int startIndex = 0;

        StringBuilder batteriesTurnedOn = new StringBuilder(maxNumberOfBatteriesToTurnOn);

        for (int countOfBatteriesTurnedOn = 0; countOfBatteriesTurnedOn < maxNumberOfBatteriesToTurnOn; countOfBatteriesTurnedOn++) {
            String remainingBatteries = batteries.substring(startIndex);
            int batteryToTurnOn = batteryToTurnOn(remainingBatteries, maxNumberOfBatteriesToTurnOn - countOfBatteriesTurnedOn);
            batteriesTurnedOn.append(remainingBatteries.charAt(batteryToTurnOn));
            startIndex = batteryToTurnOn + 1;
        }
        return Integer.parseInt(batteriesTurnedOn.toString());

    }

    private int batteryToTurnOn(String remainingBatteries, int additionalBatteriesNeeded) {
        int highestJoltage = 0;
        int batteryPosition = 0;
        int highestJoltagePosition = 0;

        while (batteryPosition <= remainingBatteries.length() - additionalBatteriesNeeded) {
            int joltage = remainingBatteries.charAt(batteryPosition) - '0';
            if (joltage > highestJoltage) {
                highestJoltage = joltage;
                highestJoltagePosition = batteryPosition;
            }
            batteryPosition++;
        }
        return highestJoltagePosition;
    }
}
