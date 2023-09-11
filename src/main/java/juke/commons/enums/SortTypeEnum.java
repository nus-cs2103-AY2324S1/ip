package juke.commons.enums;

/**
 * Enumeration on the type of sort to be performed on the {@code TaskList}.
 */
public enum SortTypeEnum {
    /** Sort by description of task. */
    DESCRIPTION,

    /** Sort by start date of task. */
    START_DATE,

    /** Sort by end date of task. */
    END_DATE,

    /** Sort by deadline of task. */
    DEADLINE;

    /**
     * Parses the input string into a {@code SortTypeEnum} enum.
     *
     * @param type String representing the type of sort to perform
     * @return {@code SortTypeEnum} enum value
     */
    public static SortTypeEnum ofType(String type) {
        String lowerCaseType = type.toLowerCase().strip();
        switch (lowerCaseType) {
        case "d":
        case "desc":
        case "description":
            return SortTypeEnum.DESCRIPTION;
        case "s":
        case "start":
        case "start date":
            return SortTypeEnum.START_DATE;
        case "e":
        case "end":
        case "end date":
            return SortTypeEnum.END_DATE;
        case "dl":
        case "deadline":
            return SortTypeEnum.DEADLINE;
        default:
            throw new IllegalArgumentException("Oh no! The sort type you entered is invalid!");
        }
    }
}
