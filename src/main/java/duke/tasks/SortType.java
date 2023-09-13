package duke.tasks;

import duke.exceptions.InvalidFormatException;

/**
 * Ways the list can be sorted.
 */
public enum SortType {
    ID,
    NAME,
    TYPE,
    DEADLINE;

    /**
     * Parses an input string to become an enum.
     *
     * @param string The input string
     * @return an enum for the sort type
     * @throws InvalidFormatException
     */
    public static SortType parseString(String string) throws InvalidFormatException {
        for (SortType s : SortType.values()) {
            if (s.name().equals(string.toUpperCase())) {
                return s;
            }
        }

        throw new InvalidFormatException("This sorting type is not allowed!", string);
    }
}

