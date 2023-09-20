package gman;

import java.io.IOException;

/**
 * Parser class that handles user inputs.
 */
public class Parser {

    private static final String exitWord = "bye";
    private static String lastCommand = null;
    private static Task lastTaskDeleted = null;
    private static int lastIndexChanged;

    /**
     * Abstraction for exit when user inputs "bye".
     * @param taskList taskList that stores all tasks.
     * @return A String that outputs gman's response to the user in the GUI.
     */
    public static String exitCommand(TaskList taskList) {
        try {
            taskList.write();
            return Ui.goodbye();
        } catch (IOException e) {
            return Ui.showError(new GmanException("Sorry... I could not save your tasks :C"));
        }
    }

    /**
     * Abstraction for listing out tasks when user inputs "list".
     * @param taskList taskList that stores all tasks.
     * @return A String that outputs gman's response to the user in the GUI.
     */
    public static String listCommand(TaskList taskList) {
        if (taskList.getSize() == 0) {
            lastCommand = "error";
            return Ui.showError(new GmanException("There's nothing to print in the list bozo..."));
        }
        lastCommand = "list";
        return Ui.listTasks(taskList);
    }

    /**
     * Abstraction for un-marking a specified done task as undone.
     * @param taskList taskList that stores all tasks.
     * @param index int index of the task to be deleted. This is not zero indexed. Hence, the first task has index 1.
     *             The indexing follows the numbering printed to the user in the GUI when listing out tasks.
     * @return A String that outputs gman's response to the user in the GUI.
     */
    public static String unmarkCommand(TaskList taskList, int index) {
        lastCommand = "unmark";
        lastIndexChanged = index;
        return taskList.unmark(index);
    }

    /**
     * Abstraction for marking a specified undone task as done.
     * @param taskList taskList that stores all tasks.
     * @param index int index of the task to be deleted. This is not zero indexed. Hence, the first task has index 1.
     *             The indexing follows the numbering printed to the user in the GUI when listing out tasks.
     * @return A String that outputs gman's response to the user in the GUI.
     */
    public static String markCommand(TaskList taskList, int index) {
        lastCommand = "mark";
        lastIndexChanged = index;
        return taskList.mark(index);
    }
    /**
     * Abstraction for making a Todo task when user inputs "todo".
     * @param userInput User input that user types into the GUI. Used to obtain the description of the todo.
     * @param taskList taskList that stores all tasks.
     * @return A String that outputs gman's response to the user in the GUI.
     */
    public static String makeTodoCommand(String userInput, TaskList taskList) {
        if (userInput.substring(4).isEmpty()) {
            lastCommand = "error";
            return Ui.showError(new GmanException("OOOOOPs! The description of a todo cannot be empty!"));
        } else {
            String description = userInput.substring(4); //cut after the space
            lastCommand = "add_task";
            lastIndexChanged = taskList.getSize();
            //no need to + 1 since the getSize already + 1 (not zero indexed)
            return taskList.addTask(new Todo(description)) + " \n" + Ui.numberOfTasks(taskList);
        }
    }

    /**
     * Abstraction for making a Deadline task when user inputs "deadline".
     * @param userInput User input that user types into the GUI. Used to obtain the description of the deadline.
     * @param taskList taskList that stores all tasks.
     * @return A String that outputs gman's response to the user in the GUI.
     */
    public static String makeDeadlineCommand(String userInput, TaskList taskList) {
        if (userInput.substring(8).isEmpty()) {
            lastCommand = "error";
            return Ui.showError(new GmanException("OOOOOPs! The description of a deadline cannot be empty!"));
        } else {
            String[] segments = userInput.substring(8).split("/by ");
            String description = segments[0];
            String by = segments[1];
            lastCommand = "add_task";
            lastIndexChanged = taskList.getSize();
            return taskList.addTask(new Deadline(description, by)) + "\n" + Ui.numberOfTasks(taskList);
        }
    }

    /**
     * Abstraction for making an Event task when user inputs "event".
     * @param userInput User input that user types into the GUI. Used to obtain the description of the event.
     * @param taskList taskList that stores all tasks.
     * @return A String that outputs gman's response to the user in the GUI.
     */
    public static String makeEventCommand(String userInput, TaskList taskList) {
        if (userInput.substring(5).isEmpty()) {
            lastCommand = "error";
            return Ui.showError(new GmanException("OOOOOPs! The description of an event cannot be empty!"));
        } else {
            String[] segments = userInput.substring(5).split("/");
            String description = segments[0];
            String from = segments[1].substring(5); //cut aft space, below too
            String to = segments[2].substring(3);
            lastCommand = "add_task";
            lastIndexChanged = taskList.getSize();
            return taskList.addTask(new Event(description, from, to)) + "\n" + Ui.numberOfTasks(taskList);
        }
    }

    /**
     * Abstraction for deleting a task when user inputs "delete".
     * @param userInput User input that user types into the GUI. Used to obtain the index of the task in the taskList
     * to delete.
     * @param taskList taskList that stores all tasks.
     * @return A String that outputs gman's response to the user in the GUI.
     */
    public static String deleteCommand(String userInput, TaskList taskList) {
        String[] segments = userInput.split(" ");
        int indexToDelete = Integer.valueOf(segments[1]) - 1;
        lastTaskDeleted = taskList.getTask(indexToDelete);
        lastCommand = "delete";
        return taskList.deleteTask(indexToDelete) + "\n" + Ui.numberOfTasks(taskList);
    }

    /**
     * Abstraction for undoing the most recent command when user inputs "undo".
     * @param taskList taskList that stores all tasks.
     * @return A Stringt that outputs gman's response to the user in the GUI.
     */
    public static String undoCommand(TaskList taskList) {
        if (!isValidUndo()) {
            return Ui.showError(new GmanException("OOPS! Your last command is not undo-able!"));
        }
        switch (lastCommand) {
            case "delete":
                return undoDeleteTask(taskList);
            case "add_task":
                return undoAddTask(taskList, lastIndexChanged);
            case "mark":
                return undoMark(taskList, lastIndexChanged);
            case "unmark":
                return undoUnmark(taskList, lastIndexChanged);
            default:
                throw new IllegalStateException("Unexpected value: " + lastCommand);
        }
    }


    public static String undoDeleteTask(TaskList taskList) {
        return "Undo successful! \n" + taskList.addTask(lastTaskDeleted) + "\n" + Ui.numberOfTasks(taskList);
    }

    public static String undoAddTask(TaskList taskList, int indexToDelete) {
        return "Undo successful! \n" + taskList.deleteTask(indexToDelete) + "\n" + Ui.numberOfTasks(taskList);
    }

    public static String undoMark(TaskList taskList, int indexToChange) {
        return "Undo successful! \n" + taskList.unmark(indexToChange);
    }

    public static String undoUnmark(TaskList taskList, int indexToChange) {
        return "Undo successful! \n" + taskList.mark(indexToChange);
    }


    /**
     * Method that checks for if the last command given by the user can be undone.
     * @return boolean indicating if the command can be undone.
     */
    public static boolean isValidUndo() {
        if (lastCommand.equals("error") || lastCommand.isEmpty()) {
            return false;
        } else if (lastCommand.equals("delete") || lastCommand.equals("add_task") || lastCommand.equals("mark")
                || lastCommand.equals("unmark")) {
            return true;
        } else {
            return false;
        }
    }


    public static String readInput(String userInput, TaskList taskList) {
        if (userInput.equals(exitWord)) {
            return exitCommand(taskList);
        } else if (userInput.equals("list")) {
            return listCommand(taskList);
        }

        String[] words = userInput.split(" ");
        String prefix = words[0];

        if (prefix.equals("unmark")) {
            int index = Integer.valueOf(words[1]) - 1; //minus 1 here to get the index
            return unmarkCommand(taskList, index);
        } else if (prefix.equals("mark")) {
            int index = Integer.valueOf(words[1]) - 1; //minus 1 here to get the index
            return markCommand(taskList, index);
        } else if (prefix.equals("todo")) {
            return makeTodoCommand(userInput, taskList);
        } else if (prefix.equals("deadline")) {
            return makeDeadlineCommand(userInput, taskList);
        } else if (prefix.equals("event")) {
            return makeEventCommand(userInput, taskList);
        } else if (prefix.equals("delete")) {
            return deleteCommand(userInput, taskList);
        } else if (prefix.equals("find")) {
            String keyword = words[1];
            return taskList.findTasks(keyword);
        } else if (prefix.equals("undo")) {
            return undoCommand(taskList);
        }
        return Ui.showError(new GmanException("OOPS! I'm sorry, I don't know what that means! Please start " +
                "with keywords: todo, deadline, or event!"));
    }

}
