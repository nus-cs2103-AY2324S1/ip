public class Parser {

    public static void parse(String input, Storage storage) throws DukeException {
        if (input.isBlank()) {
            return;
        }
        String[] inputArr = input.split(" ");
        String description;
        switch(inputArr[0]) {
            case "mark":
                storage.markTaskDone(Integer.parseInt(inputArr[1]));
                break;
            case "unmark":
                storage.markTaskNotDone(Integer.parseInt(inputArr[1]));
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

                // for the case of without "/by"
                // ensure that description cannot be empty is checked first
                if (indexOfBy == -1 && input.substring(8).isBlank()) {
                    throw new DukeException("\tHmm, the description of a deadline cannot be empty :(");
                }
                if (indexOfBy == -1) {
                    throw new DukeException("\tOOPS!!! You forgot to specify the deadline.\n\tUse \"/by\" to do so.");
                }

                description = input.substring(8, input.indexOf("/by"));
                String[] deadlineArr = input.split("/by");
                if (description.isBlank()) {
                    throw new DukeException("\tHmm, the description of a deadline cannot be empty :(");
                }
                if (deadlineArr.length < 2 || deadlineArr[1].isBlank()) {
                    throw new DukeException("\tOOPS!!! You forgot to specify the deadline.");
                }
                storage.add(description, deadlineArr[1]);
                break;
            case "event":
                int indexOfFrom = input.indexOf("/from");
                int indexOfTo = input.indexOf("/to");
                if (indexOfFrom == -1 && input.substring(5).isBlank()) {
                    throw new DukeException("\tHmm, the description of an event cannot be empty :(");
                }
                if (indexOfFrom == -1) {
                    throw new DukeException("\tOOPS!!! You forgot to specify the starting date/time.\n\tUse \"/from\" to do so.");
                }

                description = input.substring(5, input.indexOf("/from"));
                if (description.isBlank()) {
                    throw new DukeException("\tHmm, the description of an event cannot be empty :(");
                }
                String[] fromArr = input.split("/from");
                String[] toArr = input.split("/to");
                if (fromArr.length < 2 || fromArr[1].split("/to")[0].isBlank()) {
                    throw new DukeException("\tOOPS!!! You forgot to specify the starting date/time.");
                }
                if (indexOfTo == -1) {
                    throw new DukeException("\tOOPS!!! You forgot to specify the ending date/time.\n\tUse \"/to\" to do so.");
                }
                if (toArr.length < 2 || toArr[1].isBlank()) {
                    throw new DukeException("\tOOPS!!! You forgot to specify the ending date/time.");
                }
                storage.add(description, fromArr[1].split("/to")[0], toArr[1]);
                break;
            default:
                throw new DukeException("\tOOPS!!! I'm sorry, but I don't understand what that means :-(");
        }
    }
}
