package duke.utilities;

import duke.commands.Commands;
import duke.exceptions.*;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Parser class handles the analysis of user inputs and triggers commands accordingly.
 * It interprets user commands and interacts with the UI, storage, tasks and commands.
 */
public class Parser {

    private Commands command;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Parser instance.
     *
     * @param ui The UI component for displaying messages to the user.
     * @param storage The storage component for loading and saving task data.
     * @param tasks The task list containing existing tasks.
     * @param command The commands component for executing actions on tasks.
     */
    public Parser(Ui ui, Storage storage, TaskList tasks, Commands command) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
        this.command = command;
    }

    /**
     * Analyzes the user input and triggers the appropriate actions.
     * Analyze inputs based on what inputs begin with.
     * If input is related to creating a new task, call next analyzer.
     * If input is related to modifying existing task, call relevant commands.
     * If is none of the above, throw MyBotExceptions accordingly.
     *
     * @param input The user input to be analysed.
     * @throws UnknownCommandException throws this exception users
     *     input are invalid commands/
     * @throws InvalidTaskException throws this exception users
     *      input are not todo/event/deadline task/
     */
    public void analyseInput(String input) {
        try {
            if (input.equals("quit") || input.equals("bye")) {
                ui.closeGreeting();
            } else if (input.equals("list")) {
                command.listTasks(tasks);
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5));
                command.markTask(taskIndex);
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7));
                command.unmarkTask(taskIndex);
            } else if (input.startsWith("delete ")) {
                int taskIndex = Integer.parseInt(input.substring(7));
                command.removeTask(taskIndex);
            } else if (input.startsWith("find ")) {
                String keyword = input.substring(5);
                command.findTasks(keyword);
            } else if (input.startsWith("proceed")) {
                String taskToAdd = tasks.getTempStoredTask();
                if (taskToAdd != "") {
                    analyseAddTaskInput(taskToAdd);
                } else {
                    throw new UnknownCommandException();
                }
            } else if (input.isEmpty()) {
                throw new UnknownCommandException();
            } else if (!input.startsWith("todo ") && !input.startsWith("deadline ") && !input.startsWith("event ")) {
                throw new InvalidTaskException();
            } else {
                analyseAddTaskInput(input);
            }
        } catch (MyBotExceptions e) {
            ui.printException(e);
        }
    }

    /**
     * Analyzes an input that is related to creating a new Task.
     * Determines type of task according to what inputs begin with.
     *
     * @param input The details of the Task
     * @throws NullPointerException throws a NullPointerException when
     *     command is a null.
     */
    public void analyseAddTaskInput(String input) {
        try {
            if (command == null) {
                throw new NullPointerException();
            }
            if (input.startsWith("todo ")) {
                analyseTodoTaskInput(input);
            } else if (input.startsWith("deadline ")) {
                analyseDeadlineTaskInput(input);
            } else if ((input.startsWith("event "))) {
                analyseEventTaskInput(input);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Analyzes the input of the todo task.
     * Checks if details of tasks keyed in based on format.
     * Calls respective commands if input is keyed in correct format,
     * or else throw an exception to tell users what's wrong.
     *
     * @param input The details of the Task
     * @throws EmptyDetailsException throws an Empty details exception, when
     *     there is a problem with how users' input format
     */
    public void analyseTodoTaskInput(String input) {
        try {
            String description = input.substring(5);
            // Checks if there's valid task input after todo
            if (!description.isBlank()) {
                command.addTodoTask(description);
            } else {
                throw new EmptyDetailsException("description", "todo");
            }
        } catch (MyBotExceptions e) {
            ui.printException(e);
        }
    }

    /**
     * Analyzes the input of the deadline task.
     * Checks if details of tasks keyed in based on format.
     * Calls respective commands if input is keyed in correct format,
     * or else throw an exception to tell users what's wrong.
     *
     * @param input The details of the Task
     * @throws EmptyDetailsException throws a Empty details exception, when
     *      there is a problem with how users' input format
     */
    public void analyseDeadlineTaskInput(String input) {
        try {
            // Checks if a deadline is entered for a deadline task
            if (!input.contains(" /by")) {
                throw new InvalidInputException("deadline", "duedate");
            }

            String description = input.substring(9, input.indexOf(" /by "));
            String by = (input.substring(input.indexOf(" /by ") + 4)).substring(1);

            // Check if task description/by input is blank
            if (description.isBlank()) {
                throw new EmptyDetailsException("description", "deadline");
            } else if (by.isBlank()) {
                throw new EmptyDetailsException("duedate", "deadline");
            } else {
                command.addDeadlineTask(description, by);
            }
        } catch (MyBotExceptions e) {
            ui.printException(e);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please enter a space after writing /by");
        }
    }

    /**
     * Analyzes the input of the event task.
     * Checks if details of tasks keyed in based on format.
     * Calls respective commands if input is keyed in correct format,
     * or else throw an exception to tell users what's wrong.
     *
     * @param input The details of the Task
     * @throws EmptyDetailsException throws a Empty details exception, when
     *      there is a problem with how users' input format
     */
    public void analyseEventTaskInput(String input) {
        try {
            // Checks if there is a start and end time
            if (!input.contains(" /from")) {
                throw new InvalidInputException("event", "start time");
            } else if (!input.contains(" /to")) {
                throw new InvalidInputException("event", "end time");
            }

            String description = input.substring(6, input.indexOf(" /from "));
            String from = (input.substring(input.indexOf(" /from ") + 6, input.indexOf(" /to"))).substring(1);
            String to = (input.substring(input.indexOf(" /to ") + 4)).substring(1);

            // Checks if any of the start, end or description is empty
            if (description.isBlank()) {
                throw new EmptyDetailsException("description", "event");
            } else if (from.isBlank()) {
                throw new EmptyDetailsException("start time", "event");
            } else if (to.isBlank()) {
                throw new EmptyDetailsException("end time", "event");
            } else {
                command.addEventTask(description, from, to);
            }
        } catch (MyBotExceptions e) {
            ui.printException(e);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
