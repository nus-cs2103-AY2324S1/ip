package seedu.duke;

/**
 * This class contains the Commands for Duke to execute.
 */
public class Command {

    static class EmptyDescriptionException extends Exception {
        public EmptyDescriptionException(String task) {
            super("OOPS!!! The description of a " + task + " cannot be empty.");
        }
    }

    static class WrongFormatException extends Exception {
        public WrongFormatException(String message) {
            super(message);
        }
    }

    static class UnknownCommandException extends Exception {
        public UnknownCommandException() {
            super("OOPS I'm sorry, but I don't know what that means :-P");
        }
    }

    public static String listCommand(TaskList tasks) {
        String output = "";
        output += "Here are the tasks in your list:" + "\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += ((i + 1) + ". " + tasks.get(i)) + "\n";
        }
        return output;
    }

    public static String findCommand(TaskList tasks, String filterWord) {
        String output = "";
        output += "Here are the matching in your list:";
        TaskList filteredTaskList = tasks.findmatching(filterWord.substring(5));
        for (int i = 0; i < filteredTaskList.size(); i++) {
            output += ((i + 1) + ". " + filteredTaskList.get(i).toString()) + "\n";
        }
        return output;
    }

    public static String markCommand(TaskList tasks, String[] inputParts, CommandType commandType) {
        int taskIndex = Integer.parseInt(inputParts[1]) - 1;
        try {
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task task = tasks.get(taskIndex);
                if (commandType == CommandType.MARK) {
                    task.markAsDone();
                    return ("Nice! I've marked this task as done: " + task);
                } else {
                    task.markAsNotDone();
                    return ("OK, I've marked this task as not done yet: " + task);
                }
            } else {
                return ("Invalid task index.");
            }
        } catch (Exception e) {
            return "Invalid task index";
        }
    }

    public static String deleteCommand(TaskList tasks, String[] inputParts) {
        try {
            if (inputParts[1].equals("All")) {
                tasks.clear();
                return "I have cleared all tasks from your todo list";
            }
            int index = Integer.parseInt(inputParts[1]) - 1;
            Task task = tasks.get(index);
            tasks.remove(index);
            return ("Noted. I've removed this task: \n" + task + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            return "Invalid task index";
        }
        
    }

    public static String processUserInput(String userInput, TaskList tasks) {
        String[] inputParts = userInput.split(" ");
        String commandStr = inputParts[0];
        CommandType commandType = getCommandType(commandStr);

        switch (commandType) {
            case BYE:
                break;
            case LIST:
                return Command.listCommand(tasks);
            case MARK:
            case UNMARK:
                return Command.markCommand(tasks, inputParts, commandType);
            case DELETE:
                return Command.deleteCommand(tasks, inputParts);
            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    return Command.taskCommand(userInput, tasks, commandType);
                } catch (WrongFormatException|EmptyDescriptionException|UnknownCommandException e) {
                    return e.getMessage();
                }
            case FIND:
                return Command.findCommand(tasks, userInput);
            default:
                return "Sorry I dont know what you mean";
        }
        return "Sorry I dont know what you mean";
    }

    public static void todoCommand(TaskList tasks, String[] inputParts, String userInput) throws EmptyDescriptionException {
        if (inputParts.length <= 1) {
            throw new EmptyDescriptionException(inputParts[0]);
        }
        tasks.add(new Todo(userInput.substring(5)));
    }

    public static void deadlineCommand(TaskList tasks, String[] inputParts, String userInput) throws EmptyDescriptionException, WrongFormatException{
        if (inputParts.length <= 1) {
            throw new EmptyDescriptionException(inputParts[0]);
        }
        try {
            String[] deadlineParts = userInput.split(" /by ");
            String description = deadlineParts[0].substring(9);
            String by = deadlineParts[1];
            tasks.add(new Deadline(description, by));
        } catch (Exception e) {
            throw new WrongFormatException("OOPS deadlines need to be in this format, deadline return book /by YYYY-MM-DD");
        }
    }

    public static void eventCommand(TaskList tasks, String[] inputParts, String userInput) throws EmptyDescriptionException, WrongFormatException{
        if (inputParts.length <= 1) {
            throw new EmptyDescriptionException(inputParts[0]);
        } try {
            String[] eventParts = userInput.split(" /from | /to ");
            String description = eventParts[0].substring(6);
            String from = eventParts[1];
            String to = eventParts[2];
            tasks.add(new Event(description, from, to));
        } catch (Exception e) {
            throw new WrongFormatException("OOPS events need to be in this format, event project meeting /from Mon 2pm /to 4pm");
        }
    }




    public static String taskCommand(String userInput, TaskList tasks, CommandType commandType) throws EmptyDescriptionException, UnknownCommandException, WrongFormatException {
        String[] inputParts = userInput.split(" ");

        if (commandType == CommandType.TODO) {
            Command.todoCommand(tasks,inputParts,userInput);
        } else if (commandType == CommandType.DEADLINE) {
            Command.deadlineCommand(tasks,inputParts,userInput);
        } else if (commandType == CommandType.EVENT) {
            Command.eventCommand(tasks,inputParts,userInput);
        } else {
            throw new UnknownCommandException();
        }
        String output = "";
        output += "Got it. I've added this task: \n";
        output += "  " + tasks.get(tasks.size() - 1) + "\n";
        output += "Now you have " + tasks.size() + " tasks in the list. \n";
        return output;
    }


    public static CommandType getCommandType(String commandStr) {
        try {
            return CommandType.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    enum CommandType {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, BYE, UNKNOWN
    }

}