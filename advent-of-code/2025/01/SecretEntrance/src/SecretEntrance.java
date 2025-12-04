public class SecretEntrance {
    private static final int STARTING_POSITION = 50;
    private static final int DIAL_SIZE = 100;
    private int dialPosition;
    private int number_of_times_the_dial_pointed_to_zero;

    public SecretEntrance() {
        dialPosition = STARTING_POSITION;
        number_of_times_the_dial_pointed_to_zero = 0;
    }

    public static void main(String[] args) {
        System.out.println("Entrance Secret");
        SecretEntrance entrance = new SecretEntrance();
        try {
            java.nio.file.Path path = java.nio.file.Paths.get("src/resources/input.txt");
            java.util.List<String> rotations = java.nio.file.Files.readAllLines(path);

            System.out.println("Processing " + rotations.size() + " rotations from file...");
            System.out.println("The dial starts by pointing at " + entrance.getStartingPosition());

            for (String rotation : rotations)
                if (!rotation.trim().isEmpty()) {
                    entrance.rotateDial(rotation.trim());
                }

            System.out.println("Final dial position: " + entrance.getDialPosition());
            System.out.println("Password (times dial pointed to zero): " + entrance.getPassword());

        } catch (java.io.IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public int getStartingPosition() {
        return STARTING_POSITION;
    }

    public void rotateDial(String rotation) {
        int number_of_clicks = Integer.parseInt(rotation.substring(1));

        int direction;
        if (rotation.charAt(0) == 'L') direction = -1;
        else direction = 1;

        //number_of_times_the_dial_pointed_to_zero += Math.abs(Math.floorDivExact(dialPosition + number_of_clicks * direction, DIAL_SIZE));
        //dialPosition = Math.floorMod(dialPosition + number_of_clicks * direction, DIAL_SIZE);
        click(number_of_clicks, direction);
    }

    public int getDialPosition() {
        return dialPosition;
    }

    private void click(int howMany, int direction) {
        for (int i = 0; i < howMany; i++) {
            dialPosition = Math.floorMod(dialPosition + direction, DIAL_SIZE);
            if (dialPosition == 0) number_of_times_the_dial_pointed_to_zero++;
        }
    }

    public int getPassword() {
        return number_of_times_the_dial_pointed_to_zero;
    }
}