public class Parser {

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
                    throw new DukeException("Please provide the task number.");
                }
                try {
                    storage.markTaskNotDone(Integer.parseInt(inputArr[1]));
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
}
