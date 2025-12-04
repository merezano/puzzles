//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Validating Product IDs in the Gift Shop");
        GiftShop giftShop = new GiftShop();
        try {
            java.nio.file.Path path = java.nio.file.Paths.get("resources/input.txt");

            String ranges = java.nio.file.Files.readAllLines(path).getFirst();

            giftShop.bulkAddProductIDs(ranges);

            System.out.println("Sum of Invalid IDs: " + giftShop.getSumOfInvalidIDs());

        } catch (java.io.IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}