public class Parser {
    private static TaskList tasks;


    public static void parse(String input, TaskList tasks) throws DukeException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        switch (command) {
        case "list":
            tasks.listTask();
        case "delete":
            int task = parseDeleteCommand(parts);
            tasks.deleteTask(task);
        case "todo":
            ToDoTask toDoTask = parseTodoCommand(parts, false);
            tasks.addTask(toDoTask);
        case "deadline":
            DeadlineTask deadlineTask = parseDeadline(parts[1], false);
            tasks.addTask(deadlineTask);
        case "event":
            EventTask eventTask = parseEvent(parts[1], false);
            tasks.addTask(eventTask);
        case "mark":
            int markTask = parseMarkCommand(parts);
            tasks.getTask(markTask).markTask();
        case "unmark":
            int unmarkTask = parseUnmarkCommand(parts);
            tasks.getTask(unmarkTask).unmarkTask();
        default:
            throw new DukeException("You inputted an invalid command! Please try deadline, todo or event :)")
        }
    }

    //For the data loading
    public static re parse(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        default:
            throw new DukeException("You inputted an invalid command! Please try deadline, todo or event :)")
        }
    }



    private static int parseMarkCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Please provide a task number for 'done' command.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DukeException("Please pick a number instead of using letters!");
        }
    }

    private static int parseUnmarkCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Please provide a task number for 'done' command.");
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DukeException("Please pick a number instead of using letters!");
        }
    }

    private static int parseDeleteCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Please provide a task number for 'delete' command.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
        throw new DukeException("Please pick a number instead of using letters!");
        }
    }

    private static ToDoTask parseTodoCommand(String[] parts, boolean isDone) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new ToDoTask(parts[1], isDone);
    }

    private static DeadlineTask parseDeadline(String taskDetails, boolean isDone) throws DukeException {
        if (!taskDetails.contains("by:")) {
            throw new DukeException("Remember to include 'by:' after the deadline command!");
        }
        String[] details = taskDetails.split("by:", 2);
        String description = details[0];
        description = description.replace(" ", "");
        String by = details[1];
        by = by.replace(" ", "");
        return new DeadlineTask(description, by, isDone);
    }

    public static EventTask parseEvent(String taskDetails, boolean isDone) throws DukeException {
        if (!taskDetails.contains("from:") || !taskDetails.contains("to:")) {
            throw new DukeException("Remember to include 'from:' and 'to:' after the event command!");
        }
        String[] details = taskDetails.split("from:", 2);
        String[] innerDetails = details[1].split(" to:", 2);
        String description = details[0];
        description = description.replace(" ", "");
        String from = innerDetails[0];
        from = from.replace(" ", "");
        String to = innerDetails[1];
        to = to.replace(" ", "");

        return new EventTask(description, from, to, isDone);
    }
}
