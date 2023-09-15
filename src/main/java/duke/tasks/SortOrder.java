package duke.tasks;

import duke.exceptions.InvalidFormatException;

/**
 * The direction for sorting.
 */
public enum SortOrder {
    ASC,
    DESC;

    /**
     * Parses an input string to detect the inputted sorted order.
     *
     * @param string The input string
     * @return an enum for the sort order
     * @throws InvalidFormatException
     */
    public static SortOrder parseString(String string) throws InvalidFormatException {
        for (SortOrder s : SortOrder.values()) {
            if (s.name().equals(string.toUpperCase())) {
                return s;
            }
        }

        throw new InvalidFormatException("This sorting order is not allowed!", string);
    }
}
