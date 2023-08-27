package duke;

public class DateTimeStub {

    public static DateTime createDateTime(String input) throws WrongInputTask {
        if (input == "25/08/2023 1800") {
            return DateTime.createDateTime("25/08/2023 1800");
        } else if (input == "25/08/2023 1900") {
            return DateTime.createDateTime("25/08/2023 1900");
        }
        else if (DateTimeParser.isValidDateTime(input)) {
            return DateTime.createDateTime(input);
        } else {
            throw new WrongInputTask("Invalid date and time format",
                    "Please enter a valid date and time format");
        }
    }


}
