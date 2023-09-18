package juke.commons.enums;

/**
 * Enumeration on the order in which to sort the {@code TaskList} by.
 */
public enum SortOrderEnum {
    /** Ascending order sort. */
    ASCENDING,

    /** Descending order sort. */
    DESCENDING;

    /**
     * Parses the input string into a {@code SortOrderEnum} enum. switch-case
     * is adapted from GitHub Copilot.
     *
     * @param order String representing the order to sort by
     * @return {@code SortOrderEnum} enum value
     */
    public static SortOrderEnum ofOrder(String order) {
        String lowerCaseOrder = order.toLowerCase().strip();

        //@@author asdfghjkxd-reused
        // switch-case cases are adapted from GitHub Copilot, with some modifications made
        // to the cases Strings
        switch (lowerCaseOrder) {
        case "a":
        case "asc":
        case "ascend":
        case "ascending":
            return SortOrderEnum.ASCENDING;
        case "d":
        case "desc":
        case "descend":
        case "descending":
            return SortOrderEnum.DESCENDING;
        //@@author
        default:
            throw new IllegalArgumentException("Oh no! The sort order you entered is invalid!");
        }
    }
}
