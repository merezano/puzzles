void main() {
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
