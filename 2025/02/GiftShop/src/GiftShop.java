import java.util.ArrayList;
import java.util.List;

public class GiftShop {
    private final List<Long> invalidProductIDs = new ArrayList<>();
    private long sumOfInvalidIDs;

    public GiftShop() {
        sumOfInvalidIDs = 0;
    }

    public void parseProductIDs(String range) {
        String[] rangeBoundaries = range.split("-");
        for (long id = Long.parseLong(rangeBoundaries[0]); id <= Long.parseLong(rangeBoundaries[1]); id++)
            if (isInvalidID(id)) {
                invalidProductIDs.add(id);
                sumOfInvalidIDs += id;
            }
    }

    /* Now, an ID is invalid if it is made only of some sequence of digits repeated at least twice. So, 12341234 (1234 two times), 123123123 (123 three times), 1212121212 (12 five times), and 1111111 (1 seven times) are all invalid IDs. */

    private boolean isInvalidID(long id) {
        String idStr = String.valueOf(id);
        // Try all possible pattern lengths from 1 to half the string length
        for (int patternLength = 1; patternLength <= idStr.length() / 2; patternLength++) {
            String pattern = idStr.substring(0, patternLength);

            // Check if the entire string can be formed by repeating this pattern
            StringBuilder repeated = new StringBuilder();
            while (repeated.length() < idStr.length()) {
                repeated.append(pattern);
            }

            // If the repeated pattern matches the original string and repeats at least twice
            if (repeated.toString().equals(idStr) && idStr.length() / patternLength >= 2) {
                return true;
            }
        }

        return false;
    }

    public long getSumOfInvalidIDs() {
        return sumOfInvalidIDs;
    }

    public void bulkAddProductIDs(String ids) {
        String[] ranges = ids.split(",");
        for (String range : ranges)
            parseProductIDs(range);
    }

    public List<Long> getInvalidProductIDs() {
        return invalidProductIDs;
    }
}
