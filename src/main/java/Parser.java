public class Parser {

    public static String validateToDoCommand(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return input.replace("todo", "");
    }

    public static String validateDeadlineCommand(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] description = input.replace("deadline", "").trim().split(" ");
        int index = -1;
        if (description.length > 2) {
            for (int i = 0; i < description.length; i++) {
                if (description[i].equals("/by")) {
                    index = i;
                    break;
                }
            }
        }

        if (index == -1 || index == 0 || index == description.length - 1) {
            throw new DukeException("☹ OOPS!!! The format of a deadline is invalid. Format: deadline <task name> /by <date>");
        }

        return input.replace("deadline", "");
    }

    public static String validateEventCommand(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }

        String[] description = input.replace("event", "").trim().split(" ");
        int fromIndex = -1;
        int toIndex = -1;
        if (description.length > 2) {
            for (int i = 0; i < description.length; i++) {
                if (description[i].equals("/from")) {
                    fromIndex = i;
                }
                if (description[i].equals("/to")) {
                    toIndex = i;
                }
            }
        }

        if (fromIndex == -1 || toIndex == -1 || fromIndex == 0 || toIndex == 0 || fromIndex >= toIndex || fromIndex == description.length - 1 || toIndex == description.length - 1) {
            throw new DukeException("☹ OOPS!!! The format of a event is invalid. Format: event <task name> /from <date> /to <date>");
        }

        return input.replace("event", "");
    }

    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please enter a valid number");
            return -1;
        }
    }
}
