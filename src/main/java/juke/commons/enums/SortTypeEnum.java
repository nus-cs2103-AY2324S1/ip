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
     * Parses the input string into a {@code SortTypeEnum} enum. switch-case
     * is adapted from GitHub Copilot.
     *
     * @param type String representing the type of sort to perform
     * @return {@code SortTypeEnum} enum value
     */
    public static SortTypeEnum ofType(String type) {
        String lowerCaseType = type.toLowerCase().strip();

        //@@author asdfghjkxd-reused
        // switch-case cases are adapted from GitHub Copilot, with some modifications made
        // to the cases Strings
        switch (lowerCaseType) {
        case "d":
        case "des":
        case "descript":
        case "description":
            return SortTypeEnum.DESCRIPTION;
        case "s":
        case "st":
        case "start":
            return SortTypeEnum.START_DATE;
        case "e":
        case "en":
        case "end":
            return SortTypeEnum.END_DATE;
        case "dl":
        case "dead":
        case "deadln":
        case "deadline":
            return SortTypeEnum.DEADLINE;
        //@@author
        default:
            throw new IllegalArgumentException("Oh no! The sort type you entered is invalid!");
        }
    }
}
