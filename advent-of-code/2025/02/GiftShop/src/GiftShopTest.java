import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GiftShopTest {

    private GiftShop giftShop;

    @BeforeEach
    public void setUp() {
        giftShop = new GiftShop();
    }

    @Test
    public void theSumOfInvalidIDsIsZeroWhenTheListIsEmpty() {
        assertEquals(0, giftShop.getSumOfInvalidIDs());
    }

    @Test public void isAbleToDetectInvalidIDsFromAList() {
        String productIdRanges = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124";
        giftShop.bulkAddProductIDs(productIdRanges);
        assertEquals(4174379265L, giftShop.getSumOfInvalidIDs());
    }

    @Test public void isAbleToDetectInvalidIDsFromAnInputFile() {
        try {
            java.nio.file.Path path = java.nio.file.Paths.get("resources/input.txt");

            String ranges = java.nio.file.Files.readAllLines(path).getFirst();

            giftShop.bulkAddProductIDs(ranges);

            System.out.println("Invalid IDs: " + giftShop.getInvalidProductIDs().size());
            System.out.println("Sum of Invalid IDs: " + giftShop.getSumOfInvalidIDs());

        } catch (java.io.IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        assertEquals(33832678380L, giftShop.getSumOfInvalidIDs());
    }

    @ParameterizedTest
    @CsvSource({
            "11-20, 11",
            "11-22, 33",
            "95-115, 210",
            "998-1012, 2009",
            "1-5, 0"
    })
    public void identifyInvalidIDs(String range, long expectedSum) {
        giftShop.parseProductIDs(range);
        assertEquals(expectedSum, giftShop.getSumOfInvalidIDs());
    }}

