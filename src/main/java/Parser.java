public class Parser {
    public static Command parse(String fullCommand) throws DukeException {

        String[] split = fullCommand.split(" ", 2);
        Command c = null;

        switch(split[0]) {
            case "bye":
                c = new ExitCommand();
                break;
            case "list":
                c = new ListCommand();
                break;
            case "mark":
            case "unmark":
                c = validateIndex(split, true);
                break;
            case "todo":
            case "deadline":
            case "event":
                c = validateTask(split);
                break;
            case "delete":
                c = validateIndex(split, false);
                break;
            default:
                c = new IncorrectCommand("I'm sorry, I couldn't understand that. Please try again!");
        }

        return c;
    }

    public static Command validateIndex(String[] split, boolean flag) {
        // Check if mark is receiving any input or receiving extra input
        if (split.length != 2 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid mark command!");
        }

        // Check if mark is not receiving a number.
        if (!Character.isDigit(split[1].charAt(0))) {
            return new IncorrectCommand("I cannot mark a character! Please enter a number.");
        }

        int index = Character.getNumericValue(split[1].charAt(0));

        // Check if index is greater than 0.
        if (index <= 0) {
            return new IncorrectCommand("Please enter a number greater than 0!");
        }

        return flag ? new MarkCommand(index, split[0]) : new DeleteCommand(index);
    }

    public static Command validateTask(String[] split) {

        if (split.length == 1 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid task.");
        }

        if (split[0].equals("deadline")) {

            if (!split[1].contains(" /by ")) {
                return new IncorrectCommand("Please enter a /by command to depict the deadline.");
            }

            String[] task = split[1].split(" /by ", 2);

            if (task.length <= 1 || task[1].isBlank()) {
                return new IncorrectCommand("Please enter a valid task and/or deadline.");
            }

            return new AddCommand(task[0], task[1]);

        } else if (split[0].equals("event")) {

            if (!split[1].contains(" /from ")) {
                return new IncorrectCommand("Please enter a /from command to depict the deadline.");
            }

            String[] task = split[1].split(" /from ", 2);

            // Check if task entered is empty
            if (task.length <= 1 || task[1].isBlank()) {
                return new IncorrectCommand("Please enter a valid task.");
            }

            // Check if /to is present
            if (!task[1].contains(" /to ")) {
                return new IncorrectCommand("There is no /to command present. Please try again.");
            }

            String[] to = task[1].split(" /to ", 2);

            if (to.length <= 1 || to[1].isBlank() || to[0].isBlank()) {
                return new IncorrectCommand("Please enter valid to & from dates");
            }

            return new AddCommand(task[0], to[0], to[1]);

        } else {
            return new AddCommand(split[1]);
        }
    }

}
