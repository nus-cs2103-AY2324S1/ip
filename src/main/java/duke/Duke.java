package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import duke.command.Command;
import duke.exceptions.DukeEmptyArgumentException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidDateException;
import duke.exceptions.DukeInvalidIndexException;
import duke.exceptions.DukeUnknownCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * The Duke program implements a
 * chatbot to keep track of your tasks.
 *
 * @author Andrew Daniel Janong
 */
public class Duke {
    private static final Storage storage = new Storage();
    private static final TaskList tasks = new TaskList();
    private static final Parser parser = new Parser();

    /**
     * Adds a task to the task list and sends a message of the task added.
     * A task can be a ToDo, Deadline, or Event.
     *
     * @param command Task command of input.
     * @param taskInfo Info regarding the task.
     * @return Response message to be sent by the bot.
     */
    private static String addTask(Command command, String taskInfo)
            throws DukeInvalidDateException {
        Task newTask;

        assert command == Command.TODO || command == Command.DEADLINE || command == Command.EVENT :
                "Command should be a TODO, DEADLINE, or EVENT command";

        if (command == Command.TODO) {
            newTask = new ToDo(taskInfo);
        } else if (command == Command.DEADLINE) {
            String[] deadlineInfo = taskInfo.split(" /by ");
            newTask = new Deadline(deadlineInfo[0], deadlineInfo[1]);
        } else if (command == Command.EVENT) {
            String[] eventInfo = taskInfo.split(" /from ");
            String[] eventTime = eventInfo[1].split(" /to ");

            newTask = new Event(eventInfo[0], eventTime[0], eventTime[1]);
        } else {
            return Ui.getResponse("Something went wrong :(");
        }

        return tasks.addTask(newTask);
    }

    /**
     * Edits a task in the list.
     * Editing a task can be deleting, marking, or unmarking a task.
     *
     * @param command Edit command of input.
     * @param taskIndex Index of task to edit.
     * @return Response message to be sent by the bot.
     */
    private static String editTask(Command command, int taskIndex) {
        assert taskIndex > 0 : "Task index should be a positive integer";
        assert command == Command.DELETE || command == Command.MARK || command == Command.UNMARK :
                "Command should be a DELETE, MARK, or UNMARK command";

        if (command == Command.DELETE) {
            return tasks.deleteTask(taskIndex);
        } else if (command == Command.MARK) {
            return tasks.markTask(taskIndex);
        } else if (command == Command.UNMARK) {
            return tasks.unmarkTask(taskIndex);
        } else {
            return Ui.getResponse("Something went wrong :(");
        }
    }

    /**
     * Executes find command.
     * Find command lists all tasks which contains a certain keyword.
     *
     * @param keyword Keyword to find tasks
     * @return Response message to be sent by the bot.
     */
    private static String executeFindCommand(String keyword) {
        return tasks.printTasksByKeyword(keyword);
    }

    /**
     * Executes single commands.
     * Single commands consists of: Listing all tasks, Printing goodbye message, Printing Help page.
     *
     * @param command Single command of input.
     * @return Response message to be sent by the bot.
     */
    private static String executeSingleCommand(Command command) {
        assert command == Command.HELP || command == Command.LIST || command == Command.BYE :
                "Command should be a LIST or BYE command";

        if (command == Command.LIST) {
            return Ui.getResponse(tasks.toString());
        } else if (command == Command.BYE) {
            return Ui.getExitMessage();
        } else if (command == Command.HELP) {
            return Ui.getHelpPage();
        } else {
            return Ui.getResponse("Something went wrong :(");
        }
    }

    private static void checkInput(Command command, String[] inputs) throws DukeException {
        ArrayList<Command> argumentNeededCommands = new ArrayList<Command>(Arrays.asList(Command.TODO, Command.DEADLINE
                , Command.EVENT, Command.DELETE, Command.FIND, Command.MARK, Command.UNMARK));
        ArrayList<Command> indexNeededCommands = new ArrayList<Command>(Arrays.asList(Command.DELETE, Command.MARK, Command.UNMARK));

        if (argumentNeededCommands.contains(command)) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException("OOPS!!! Argument for this command cannot be empty.");
            }
        }

        if (indexNeededCommands.contains(command)) {
            try {
                if (!tasks.isValidIndex(Integer.parseInt(inputs[1]))) {
                    throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
                }
            } catch (NumberFormatException exception) {
                throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
            }
        }
    }

    /**
     * Runs the command from the user input.
     *
     * @param command Command of the user input.
     * @param inputs Arguments of the input.
     * @return Response message to be sent by the bot.
     * @throws DukeException Error when executing the command.
     */
    private static String runCommandAndGetResponse(Command command, String[] inputs) throws DukeException {
        String response;
        checkInput(command, inputs);

        if (command == Command.BYE) {
            storage.writeTasks(tasks);
            response = executeSingleCommand(command);
            if (inputs.length > 1) {
                response = Ui.getResponse("Please enter 'bye' without any arguments to exit");
            }
            return response;
        } else if (command == Command.HELP) {
            response = executeSingleCommand(command);
        } else if (command == Command.TODO || command == Command.DEADLINE || command == Command.EVENT) {
            if (command == Command.TODO) {
                response = addTask(Command.TODO, inputs[1]);
            } else if (command == Command.DEADLINE) {
                response = addTask(Command.DEADLINE, inputs[1]);
            } else {
                response = addTask(Command.EVENT, inputs[1]);
            }
        } else if (command == Command.DELETE) {
            response = editTask(Command.DELETE, Integer.parseInt(inputs[1]));
        } else if (command == Command.LIST) {
            response = executeSingleCommand(Command.LIST);
        } else if (command == Command.FIND) {
            response = executeFindCommand(inputs[1]);
        } else if (command == Command.MARK) {
            response = editTask(Command.MARK, Integer.parseInt(inputs[1]));
        } else if (command == Command.UNMARK) {
            response = editTask(Command.UNMARK, Integer.parseInt(inputs[1]));
        } else {
            throw new DukeUnknownCommandException(inputs[0]);
        }

        return response;
    }

    /**
     * Gets all tasks from the data and store it in the storage.
     */
    public void getTasksData() {
        try {
            storage.getTasksFromData(tasks);
        } catch (IOException exception) {
            return;
        }
    }

    /**
     * Gets a greeting message to be sent went a user activates the chatbot.
     *
     * @return Greeting message to be sent.
     */
    public String getGreetingMessage() {
        return Ui.getGreetingMessage();
    }

    /**
     * Gets the response message based on the User's input.
     *
     * @param userInput User input.
     * @return Response message to be sent by the bot.
     */
    public String getResponse(String userInput) {
        String response;

        try {
            Command command = parser.parseInput(userInput);
            String[] inputs = userInput.split(" ", 2);

            response = runCommandAndGetResponse(command, inputs);
            storage.writeTasks(tasks);
        } catch (DukeException exception) {
            response = Ui.getResponse(exception.getMessage());
        }

        return response;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            storage.getTasksFromData(tasks);
        } catch (IOException exception) {
            Ui.printLines("Shutting down...");
            return;
        }

        while (true) {
            try {
                String userInput = sc.nextLine();
                Command command = parser.parseInput(userInput);
                String[] inputs = userInput.split(" ", 2);

                if (Duke.runCommandAndGetResponse(command, inputs) == Ui.getExitMessage()) {
                    break;
                }
                storage.writeTasks(tasks);

            } catch (Exception exception) {
                Ui.printLines(exception.getMessage());

            }
        }
    }
}
