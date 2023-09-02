package duke;

/**
 * This class is used to represent the date and time of a task.
 */
public class DateTimeStub {

    /**
     * Creates a DateTime object based on whether there is a valid input for testing
     * @param input the user's input
     * @return  a DateTime object
     * @throws WrongInputException
     */
    public static DateTime createDateTime(String input) throws WrongInputException {
        if (input == "25/08/2023 1800") {
            return DateTime.createDateTime("25/08/2023 1800");
        } else if (input == "25/08/2023 1900") {
            return DateTime.createDateTime("25/08/2023 1900");
        } else if (DateTimeParser.isValidDateTime(input)) {
            return DateTime.createDateTime(input);
        } else {
            throw new WrongInputException("Invalid date and time format",
                    "Please enter a valid date and time format");
        }
    }

}
