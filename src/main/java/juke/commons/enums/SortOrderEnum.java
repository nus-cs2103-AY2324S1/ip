package juke.commons.enums;

/**
 * Enumeration on the order in which to sort the {@code TaskList} by.
 */
public enum SortOrderEnum {
    /** Ascending order sort. */
    ASCENDING(1),

    /** Descending order sort. */
    DESCENDING(-1);

    /** Comparator multiplier to dictate order of sort, 1 for ascending and -1 for descending. */
    private final int multiplier;

    /**
     * Constructs a {@code SortOrderEnum} object. This method cannot be invoked by the user.
     */
    SortOrderEnum(int multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Parses the input string into a {@code SortOrderEnum} enum.
     *
     * @param order String representing the order to sort by
     * @return {@code SortOrderEnum} enum value
     */
    public static SortOrderEnum ofOrder(String order) {
        String lowerCaseOrder = order.toLowerCase().strip();
        switch (lowerCaseOrder) {
        case "a":
        case "asc":
        case "ascending":
            return SortOrderEnum.ASCENDING;
        case "d":
        case "desc":
        case "descending":
            return SortOrderEnum.DESCENDING;
        default:
            throw new IllegalArgumentException("Oh no! The sort order you entered is invalid!");
        }
    }

    /**
     * Returns the multiplier for the specified {@code SortOrderEnum} enum.
     *
     * @return 1 if the sort is ascending, -1 if the sort is descending
     */
    public int getMultiplier() {
        return this.multiplier;
    }
}
