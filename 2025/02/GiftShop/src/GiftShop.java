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

    private boolean isInvalidID(long id) {
        String idStr = String.valueOf(id);
        if (idStr.length() % 2 != 0) return false;

        String firstHalf = idStr.substring(0, idStr.length() / 2);
        String secondHalf = idStr.substring(idStr.length() / 2);
        return firstHalf.equals(secondHalf);
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
