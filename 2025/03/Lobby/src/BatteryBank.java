public record BatteryBank(String batteries) {
    public BatteryBank {
        // Precondition: batteries must not be null or empty
        if (batteries == null || batteries.isEmpty()) {
            throw new IllegalArgumentException("Battery bank cannot be empty");
        }
        // Precondition: batteries must contain only digit characters
        if (!batteries.matches("[0-9]+")) {
            throw new IllegalArgumentException("Battery bank must contain only digits");
        }
    }

    public long turnOn(int maxNumberOfBatteriesToTurnOn) {
        // Precondition: maxNumberOfBatteriesToTurnOn must be positive
        if (maxNumberOfBatteriesToTurnOn <= 0) {
            throw new IllegalArgumentException("Number of batteries to turn on must be positive");
        }
        // Precondition: cannot request more batteries than available
        if (maxNumberOfBatteriesToTurnOn > batteries.length()) {
            throw new IllegalArgumentException(
                "Cannot turn on " + maxNumberOfBatteriesToTurnOn + " batteries when only " + batteries.length() + " are available");
        }

        StringBuilder batteriesTurnedOn = new StringBuilder(maxNumberOfBatteriesToTurnOn);
        String remainingBatteries = batteries;

        for (int countOfBatteriesTurnedOn = 0; countOfBatteriesTurnedOn < maxNumberOfBatteriesToTurnOn; countOfBatteriesTurnedOn++) {
            int batteryIndex = nextBatteryToTurnOn(remainingBatteries, maxNumberOfBatteriesToTurnOn - countOfBatteriesTurnedOn);
            // Invariant: batteryIndex must be within bounds
            assert batteryIndex >= 0 && batteryIndex < remainingBatteries.length() 
                : "Battery index out of bounds: " + batteryIndex;
            batteriesTurnedOn.append(remainingBatteries.charAt(batteryIndex));
            remainingBatteries = remainingBatteries.substring(batteryIndex + 1);
        }

        long result = Long.parseLong(batteriesTurnedOn.toString());
        // Postcondition: result must be non-negative
        assert result >= 0 : "Result joltage must be non-negative";
        return result;
    }

    private int nextBatteryToTurnOn(String remainingBatteries, int additionalBatteriesNeeded) {
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
