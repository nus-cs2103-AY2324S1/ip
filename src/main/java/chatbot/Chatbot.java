package chatbot;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;
import java.util.List;

import chatbot.exception.InvalidCommandException;
import chatbot.exception.InvalidDeadlineException;
import chatbot.exception.InvalidEventException;
import chatbot.exception.InvalidTaskNumberException;
import chatbot.exception.InvalidTodoException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.Todo;

public class Chatbot {
    /**
     * Name of the Chatbot.
     */
    private static final String NAME = "Bro";

    /**
     * chatbot.TaskList for the Chatbot.
     */
    private TaskList taskList;

    /**
     * chatbot.Ui for the Chatbot.
     */
    private Ui ui;

    /**
     * chatbot.Storage for the Chatbot.
     */
    private Storage storage;

    /**
     * chatbot.Parser for the Chatbot.
     */
    private Parser parser;

    /**
     * Constructor for the Chatbot.
     */
    public Chatbot() {
        try {
            this.taskList = new TaskList();
            this.ui = new Ui(NAME);
            this.storage = new Storage();
            this.parser = new Parser();
            this.storage.loadTasksFromFile(this.taskList);
        } catch (Exception e) {
            this.ui.printExceptionMessage(e);
        }
    }

    /**
     * Greets the user.
     */
    private void greet() {
        this.ui.greet();
    }

    /**
     * Causes Chatbot to exit.
     */
    private void exit() {
        this.ui.exit();
    }

    /**
     * Adds task inputted by user to taskList. Prints out confirmation.
     *
     * @param input The command inputted by the user.
     * @throws InvalidTodoException       If format of todo entered is invalid.
     * @throws InvalidDeadlineException   If format of deadline entered is invalid.
     * @throws InvalidEventException      If format of event entered is invalid.
     * @throws InvalidCommandException    If format of command entered is invalid.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    private void add(String input) throws InvalidTodoException, InvalidDeadlineException,
            InvalidEventException, InvalidCommandException, InvalidTaskNumberException {
        Task newTask = null;
        List<String> taskArr = this.parser.parseAdd(input);
        String type = taskArr.get(0);

        if (type.equals("todo")) {
            String description = taskArr.get(1);
            newTask = new Todo(description);
        } else if (type.equals("deadline")) {
            try {
                String description = taskArr.get(1);
                String deadline = taskArr.get(2);
                newTask = new Deadline(description, deadline);
            } catch (DateTimeParseException e) {
                throw new InvalidDeadlineException();
            }
        } else if (type.equals("event")) {
            try {
                String description = taskArr.get(1);
                String start = taskArr.get(2);
                String end = taskArr.get(3);

                newTask = new Event(description, start, end);
            } catch (DateTimeParseException e) {
                throw new InvalidEventException();
            }
        } else {
            assert false; //Execution should not reach this point
        }

        this.taskList.add(newTask);
        this.ui.printAddConfirmation(this.taskList);
    }

    /**
     * Lists out all the tasks in taskList.
     *
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    private void listTasks() throws InvalidTaskNumberException {
        this.ui.listTasks(this.taskList);
    }

    /**
     * Marks specified task as done. Prints confirmation.
     *
     * @param input The user input.
     * @throws InvalidTaskNumberException If there is no task with the task number in the taskList.
     * @throws InvalidCommandException    If format of command entered is invalid.
     */
    private void markTaskStatusTrue(String input) throws InvalidTaskNumberException, InvalidCommandException {
        int taskNum = parser.parseMarkTaskStatusTrue(input);
        taskList.markTaskTrue(taskNum - 1);

        assert taskNum <= this.taskList.size();
        this.ui.printMarkDoneConfirmation(taskNum - 1, this.taskList);
    }

    /**
     * Marks specified task as undone. Prints confirmation.
     *
     * @param input The user input.
     * @throws InvalidTaskNumberException If there is no task with the task number in the taskList.
     * @throws InvalidCommandException    If format of command entered is invalid.
     */
    private void markTaskStatusFalse(String input) throws InvalidTaskNumberException, InvalidCommandException {
        int taskNum = parser.parseMarkTaskStatusFalse(input);
        taskList.markTaskFalse(taskNum - 1);

        assert taskNum <= this.taskList.size();
        this.ui.printMarkUndoneConfirmation(taskNum - 1, this.taskList);
    }

    /**
     * Adds a tag to the task. Prints confirmation.
     *
     * @param input The user input.
     * @throws InvalidTaskNumberException If there is no task with the task number in the taskList.
     * @throws InvalidCommandException    If format of command entered is invalid.
     */
    private void addTaskTag(String input) throws InvalidTaskNumberException, InvalidCommandException {
        List<String> res = parser.parseAddTaskTag(input);

        String taskNum = res.get(0);
        String desc = res.get(1);
        taskList.addTaskTag(taskNum, desc);

        this.ui.printAddTaskTagConfirmation();
    }

    /**
     * Deletes the specified task in the list.
     *
     * @param input The user input.
     * @throws InvalidTaskNumberException If there is no task with the task number in the taskList.
     * @throws InvalidCommandException    If format of command entered is invalid.
     */
    private void delete(String input) throws InvalidTaskNumberException, InvalidCommandException {
        int taskNum = parser.parseDelete(input);
        assert taskNum <= this.taskList.size();
        this.taskList.delete(taskNum - 1);

        this.ui.printDeleteConfirmation(taskNum - 1, this.taskList);
    }

    /**
     * Lists out the tasks in the taskList with the matching keyword.
     *
     * @param input The user input.
     * @throws InvalidCommandException    If format of command entered is invalid.
     * @throws InvalidTaskNumberException If there is no task with the task number in the taskList.
     */
    public void find(String input) throws InvalidCommandException, InvalidTaskNumberException {
        String keyword = parser.parseFind(input);
        this.ui.listMatchingTasks(keyword, this.taskList.find(keyword));
    }

    /**
     * Runs chatbot with given user input.
     * Update current state of tasks to data file.
     *
     * @params userInput The user input.
     */
    private void readInput(String userInput) {
        try {
            callMethod(userInput);
            this.saveTasks();
        } catch (Exception e) {
            this.ui.printExceptionMessage(e);
        }
    }

    /**
     * Runs chatbot after reading in user input from System.in.
     * Update current state of tasks to data file.
     */
    private void readInput() {
        String userInput = this.ui.getUserInput();

        try {
            callMethod(userInput);
            this.saveTasks();
        } catch (Exception e) {
            this.ui.printExceptionMessage(e);
        } finally {
            if (!userInput.equals("bye")) {
                this.readInput(this.ui.getUserInput());
            }
        }
    }

    /**
     * Identifies method to call based on userInput and calls the method.
     *
     * @param userInput The user input.
     * @throws InvalidTodoException       If format of todo entered is invalid.
     * @throws InvalidDeadlineException   If format of deadline entered is invalid.
     * @throws InvalidEventException      If format of event entered is invalid.
     * @throws InvalidCommandException    If format of command entered is invalid.
     * @throws InvalidTaskNumberException If there is no task with the given index in the taskList.
     */
    private void callMethod(String userInput) throws InvalidTaskNumberException, InvalidTodoException,
            InvalidDeadlineException, InvalidEventException, InvalidCommandException {
        if (userInput.equals("list")) {
            this.listTasks();
        } else if (userInput.equals("bye")) {
            this.exit();
        } else if (userInput.startsWith("add ")) {
            this.add(userInput);
        } else if (userInput.startsWith("mark ")) {
            this.markTaskStatusTrue(userInput);
        } else if (userInput.startsWith("unmark ")) {
            this.markTaskStatusFalse(userInput);
        } else if (userInput.startsWith("delete ")) {
            this.delete(userInput);
        } else if (userInput.startsWith("find ")) {
            this.find(userInput);
        } else if (userInput.startsWith("tag ")) {
            this.addTaskTag(userInput);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Returns the greeting of the bot.
     *
     * @return the greeting of the bot
     */
    public String getGreeting() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;

        System.setOut(new PrintStream(output));
        this.greet();

        System.setOut(original);
        return output.toString();
    }

    /**
     * Returns the response of the chatbot from System.out.
     *
     * @param userInput The user input.
     * @return The String representation of the response.
     */
    public String getResponse(String userInput) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;

        System.setOut(new PrintStream(output));
        this.readInput(userInput);

        System.setOut(original);
        return output.toString();
    }

    /**
     * Saves current taskList to data file.
     */
    public void saveTasks() {
        try {
            this.storage.saveTasks(this.taskList);
        } catch (Exception e) {
            this.ui.printExceptionMessage(e);
        }
    }

    public static void main(String[] args) {
        Chatbot chatbot = new Chatbot();
        chatbot.greet();
        chatbot.readInput();
    }
}
