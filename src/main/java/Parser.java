public class Parser {

    public static boolean isCreateTaskCommand(String input) {
        String trimmedInput = input.trim();
        return trimmedInput.startsWith("todo")
                || trimmedInput.startsWith("event")
                || trimmedInput.startsWith("deadline");
    }

    public static boolean isByeCommand(String input) {
        return input.equals("bye");
    }

    public static boolean isListCommand(String input) {
        return input.equals("list");
    }

    public static boolean isMarkCommand(String input) {
        return input.startsWith("mark");
    }

    public static boolean isUnmarkCommand(String input) {
        return input.startsWith("unmark");
    }

    public static boolean isDeleteCommand(String input) {
        return input.startsWith("delete");
    }

    public static boolean parseCommand(String input, TaskList tasks, Ui ui) throws DukeException {

        int taskIndex;
        if (isDeleteCommand(input)) {
            taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                tasks.removeTask(taskIndex);
            }
            return true;
        } else if (isMarkCommand(input)) {
            taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                tasks.markTask(taskIndex);
            }
            return true;
        } else if (isUnmarkCommand(input)) {
            taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                tasks.unmarkTask(taskIndex);
            }
            return true;
        }

        if (isByeCommand(input)) {
            return false;
        } else if (isListCommand(input)) {
            ui.showTaskList(tasks);
        } else if (isCreateTaskCommand(input)) {
            Task newTask = parseCreateTaskInput(input);
            tasks.addTask(newTask); // TODO Update this method call accordingly
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    public static Task convertToTask(String line) {

        String[] parts = line.split("\\|\\|");

        if (parts.length < 5) {
            System.err.println("Invalid task format in file: " + line);
            return null;
        }

        String taskType = parts[0].trim();
        String done = parts[1].trim();
        String taskDescription = parts[2].trim();
        System.out.println(taskDescription);
        String start = parts[3].trim();
        String end = parts[4].trim();
        Task task = null;

        try {
            switch (taskType) {
            case "T":
                task = new ToDo(taskDescription);
                break;
            case "D":
                task = new Deadline(taskDescription, end);
                break;
            case "E":
                task = new Event(taskDescription, start, end);
                break;
            }
            if (done.equals("0") && task != null) {
                task.isDone = false;
            }
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
        return task;
    }

    public static Task parseCreateTaskInput(String input) throws DukeException {
        TaskType taskType;
        String[] parts = input.split("/");
        String taskDetails = parts[0].trim();

        if (taskDetails.startsWith("todo")) {
            taskType = TaskType.TODO;
            taskDetails = taskDetails.substring(4).trim();
        } else if (taskDetails.startsWith("deadline")) {
            taskType = TaskType.DEADLINE;
            taskDetails = taskDetails.substring(8).trim();
        } else {
            taskType = TaskType.EVENT;
            taskDetails = taskDetails.substring(5).trim();
        }

        Task newTask = null;
        switch (taskType) {
        case TODO:
            newTask = new ToDo(taskDetails);
            break;
        case DEADLINE:
            if (parts.length != 2 || parts[1].length() < 2) {
                // prevent java.lang.StringIndexOutOfBoundsException
                throw new DukeException("Invalid input for a task with deadline. " +
                        "Please input 'deadline <task name> /by <end>'");
            }
            String date = parts[1].substring(2).trim();
            newTask = new Deadline(taskDetails, date);
            break;
        case EVENT:
            if (parts.length != 3 || parts[1].length() < 5 || parts[2].length() < 3) {
                // prevent java.lang.StringIndexOutOfBoundsException
                throw new DukeException("Invalid input for an event. " +
                        "Please input 'event <event name> /from <start> /to <end>'");
            }
            String start = parts[1].substring(5).trim();
            String end = parts[2].substring(3).trim();
            newTask = new Event(taskDetails, start, end);
            break;
        }
        return newTask;
    }
}