package anto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Parser class handles all reading of inputs.
 */
public class Parser {

    private Ui ui;
    private TaskList taskList;

    /**
     * Creates a Parser class.
     *
     * @param ui Ui class that handles printing to command line.
     * @param taskList TaskList with all the current tasks.
     */
    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Reads inputs from argument and decide what actions to take.
     *
     * @param input Input from user.
     * @return Returns String response to user.
     */
    public String readInput(String input) {
        try {
            if (input.equals("bye")) {
                return this.byeCommand();
            } else if (input.equals("list")) {
                return this.listCommand();
            } else if (input.startsWith("mark")) {
                return this.markCommand(input);
            } else if (input.startsWith("unmark")) {
                return this.unmarkCommand(input);
            } else if (input.startsWith("todo")) {
                return this.todoCommand(input);
            } else if (input.startsWith("deadline")) {
                return this.deadlineCommand(input);
            } else if (input.startsWith("event")) {
                return this.eventCommand(input);
            } else if (input.startsWith("delete")) {
                return this.deleteCommand(input);
            } else if (input.startsWith("find")) {
                return this.findCommand(input);
            } else {
                throw new AntoException("OOPS!!! I'm sorry, but I don't know what that means :(");
            }
        } catch (AntoException e) {
            return this.ui.printError(e);
        }
    }

    private String byeCommand() {
        assert this.ui != null;
        return this.ui.sayBye();
    }

    private String listCommand() {
        assert this.taskList != null;
        assert this.ui != null;

        if (this.taskList.getLength() == 0) {
            return this.ui.printNoTasks();
        } else {
            return this.ui.printList();
        }
    }

    private String markCommand(String input) throws AntoException {
        // Throw error if there is no index
        if (input.length() < 6 || input.split(" ", 2).length < 2) {
            throw new InvalidIndexException("OOPS!!! You need to include an index to mark task.");
        }

        String strIndex = input.split(" ", 2)[1];
        // Throw error if index given is not an int
        if (!Parser.isInt(strIndex)) {
            throw new InvalidIndexException("OOPS!!! You need to include an index to mark task.");
        }

        int index = Integer.parseInt(strIndex) - 1;

        assert this.taskList != null;
        // Throw error if there is no task at index
        if (index >= this.taskList.getLength()) {
            throw new InvalidIndexException("OOPS!!! There is no task at that index.");
        }

        assert this.ui != null;
        this.taskList.markTaskAsDone(index);

        return this.ui.printMarkAsDone(index);
    }

    private String unmarkCommand(String input) throws AntoException {
        // Throw error if there is no index
        if (input.length() < 8 || input.split(" ", 2).length < 2) {
            throw new InvalidIndexException("OOPS!!! You need to include an index to unmark task.");
        }

        String strIndex = input.split(" ", 2)[1];
        // Throw error if index given is not an int
        if (!Parser.isInt(strIndex)) {
            throw new InvalidIndexException("OOPS!!! You need to include an index to unmark task.");
        }

        int index = Integer.parseInt(strIndex) - 1;
        // Throw error if there is no task at index
        if (index >= this.taskList.getLength()) {
            throw new InvalidIndexException("OOPS!!! There is no task at that index.");
        }


        assert this.taskList != null;
        this.taskList.unmarkTask(index);

        assert this.ui != null;
        return this.ui.printUnmark(index);
    }

    private String todoCommand(String input) throws AntoException {
        // Throw error if there is no description
        if (input.length() < 6) {
            throw new InvalidParametersException("OOPS!!! The description of a todo cannot be empty.");
        }

        String[] arr = input.split(" ", 2);
        Task newTask = new Todo(arr[1]);

        assert this.taskList != null;
        this.taskList.addToList(newTask);

        assert this.ui != null;
        return this.ui.printAdded(newTask);
    }

    private String deadlineCommand(String input) throws AntoException {
        try {
            String[] arr = input.split(" /by ");
            String taskDesc = arr[0].substring(9);
            String by = arr[1];
            Task newTask = new Deadline(taskDesc, by);

            assert this.taskList != null;
            this.taskList.addToList(newTask);

            assert this.ui != null;
            return this.ui.printAdded(newTask);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidParametersException("OOPS!!! The description and by of a deadline"
                    + " must be valid.");
        }
    }

    private String eventCommand(String input) throws AntoException {
        try {
            String[] arr = input.split(" /from ");
            String taskDesc = arr[0].substring(6);
            String[] arrBack = arr[1].split(" /to ");
            String from = arrBack[0];
            String to = arrBack[1];

            Task newTask = new Event(taskDesc, from, to);

            assert this.taskList != null;
            this.taskList.addToList(newTask);

            assert this.ui != null;
            return this.ui.printAdded(newTask);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidParametersException("OOPS!!! The description and from and to of an event"
                    + " must be valid.");
        }
    }

    private String deleteCommand(String input) throws AntoException {
        // Throw error if there is no index
        if (input.length() < 8 || input.split(" ", 2).length < 2) {
            throw new InvalidIndexException("OOPS!!! You need to include an index to delete task.");
        }

        String strIndex = input.split(" ", 2)[1];
        // Throw error if index given is not an int
        if (!Parser.isInt(strIndex)) {
            throw new InvalidIndexException("OOPS!!! You need to include an index to delete task.");
        }

        int index = Integer.parseInt(strIndex) - 1;
        // Throw error if there is no task at index
        if (index >= this.taskList.getLength()) {
            throw new InvalidIndexException("OOPS!!! There is no task at that index.");
        }

        assert this.taskList != null;
        Task removedTask = this.taskList.deleteTask(index);

        assert this.ui != null;
        return this.ui.printDelete(removedTask);
    }

    private String findCommand(String input) throws InvalidIndexException {
        // Throw error if there is no keyword
        if (input.length() < 6 || input.split(" ", 2).length < 2) {
            throw new InvalidIndexException("OOPS!!! You need to include a keyword to find tasks.");
        }

        String keyword = input.split(" ", 2)[1];

        assert this.taskList != null;
        ArrayList<Task> foundTasks = this.taskList.findTask(keyword);

        assert this.ui != null;
        return this.ui.printFoundTasks(foundTasks);
    }

    /**
     * Return boolean whether String represents an integer.
     *
     * @param strInt String to check.
     * @return True if string represents an integer.
     */
    public static boolean isInt(String strInt) {
        if (strInt == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strInt);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Check if String given is in correct datetime format.
     *
     * @param dateStr String to be checked.
     * @param formatter Format of DateTime to check with.
     * @return True if string is in correct DateTime format.
     */
    public static boolean isValidDate(String dateStr, DateTimeFormatter formatter) {
        try {
            LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
