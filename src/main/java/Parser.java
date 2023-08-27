import java.util.ArrayList;

/**
 * The class that provides the parser of the input.
 */
public class Parser {

    /**
     * Parses the input and prints the corresponding output.
     *
     * @param input The input from the user.
     * @param storage The storage object to store the tasks.
     * @throws DukeException An exception related to the chatbot.
     */
    public static void parse(String input, Storage storage) throws DukeException {
        if (input.isBlank()) {
            return;
        }
        String[] inputArr = input.split(" ");
        String description;
        switch(inputArr[0]) {
        case "mark":
            if (inputArr.length < 2) {
                throw new DukeException("\tPlease provide the task number.");
            }
            try {
                storage.markTaskDone(Integer.parseInt(inputArr[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("\tPlease provide a valid task number.");
            }
            break;
        case "unmark":
            if (inputArr.length < 2) {
                throw new DukeException("\tPlease provide the task number.");
            }
            try {
                storage.markTaskNotDone(Integer.parseInt(inputArr[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("\tPlease provide a valid task number.");
            }
            break;
        case "delete":
            if (inputArr.length < 2) {
                throw new DukeException("\tPlease provide the task number.");
            }
            try {
                storage.deleteTask(Integer.parseInt(inputArr[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("\tPlease provide a valid task number.");
            }
            break;
        case "list":
            storage.list();
            break;
        case "todo":
            description = input.substring(4);
            if (description.isBlank()) {
                throw new DukeException("\tHmm, the description of a todo cannot be empty :(");
            }
            storage.add(description);
            break;
        case "deadline":
            int indexOfBy = input.indexOf("/by");

            if (indexOfBy == -1) {
                throw new DukeException("\tOOPS!!! You forgot to specify the deadline.\n\tUse \"/by\" to do so.");
            }

            description = input.substring(8, indexOfBy);
            if (description.isBlank()) {
                throw new DukeException("\tHmm, the description of a deadline cannot be empty :(");
            }

            String deadline = input.substring(indexOfBy + 3);
            if (deadline.isBlank()) {
                throw new DukeException("\tOOPS!!! You forgot to specify the deadline.");
            }

            storage.add(description, deadline);
            break;
        case "event":
            int indexOfFrom = input.indexOf("/from");
            int indexOfTo = input.indexOf("/to");
            if (indexOfFrom == -1) {
                throw new DukeException("\tOOPS!!! You forgot to specify the starting date/time.\n\tUse \"/from\" to do so.");
            }
            if (indexOfTo == -1) {
                throw new DukeException("\tOOPS!!! You forgot to specify the ending date/time.\n\tUse \"/to\" to do so.");
            }

            description = input.substring(5, indexOfFrom);
            if (description.isBlank()) {
                throw new DukeException("\tHmm, the description of an event cannot be empty :(");
            }
            String from = input.substring(indexOfFrom + 5, indexOfTo);

            if (from.isBlank()) {
                throw new DukeException("\tOOPS!!! You forgot to specify the starting date/time.");
            }

            String to = input.substring(indexOfTo + 3);
            if (to.isBlank()) {
                throw new DukeException("\tOOPS!!! You forgot to specify the ending date/time.");
            }
            storage.add(description, from, to);
            break;
        default:
            throw new DukeException("\tOOPS!!! I'm sorry, but I don't understand what that means :-(");
        }
    }

    public static int parseToTask(ArrayList<Task> arrList, String text) {
        String[] textArr = text.split(" \\| ");
        int count = 0;
        switch (textArr[0]) {
        case "T":
            arrList.add(new Todo(textArr[1], textArr[2]));
            count++;
            break;
        case "D":
            arrList.add(new Deadline(textArr[1], textArr[2], textArr[3]));
            count++;
            break;
        case "E":
            arrList.add(new Event(textArr[1], textArr[2], textArr[3], textArr[4]));
            count++;
            break;
        default:
            break;
        }
        return count;
    }
}
