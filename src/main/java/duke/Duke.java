package duke;

import java.io.IOException;
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

        if (command == Command.TODO) {
            newTask = new ToDo(taskInfo);
        } else if (command == Command.DEADLINE) {
            String[] deadlineInfo = taskInfo.split(" /by ");
            newTask = new Deadline(deadlineInfo[0], deadlineInfo[1]);
        } else { // command == Command.EVENT
            String[] eventInfo = taskInfo.split(" /from ");
            String[] eventTime = eventInfo[1].split(" /to ");
            newTask = new Event(eventInfo[0], eventTime[0], eventTime[1]);
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
        if (command == Command.DELETE) {
            return tasks.deleteTask(taskIndex);
        } else if (command == Command.MARK) {
            return tasks.markTask(taskIndex);
        } else if (command == Command.UNMARK) {
            return tasks.unmarkTask(taskIndex);
        }

        return Ui.getResponse("Something went wrong :(");
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
     * Single commands consists of: Listing all tasks, Printing goodbye message.
     *
     * @param command Single command of input.
     * @return Response message to be sent by the bot.
     */
    private static String executeSingleCommand(Command command) {
        String response;

        if (command == Command.LIST) {
            response = Ui.getResponse(tasks.toString());
        } else if (command == Command.BYE) {
            response = Ui.getExitMessage();
        } else {
            response = Ui.getResponse("Something went wrong :(");
        }

        return response;
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

        if (command == Command.BYE) {
            response = Ui.getExitMessage();
            storage.writeTasks(tasks);
            return response;
        } else if (command == Command.TODO || command == Command.DEADLINE || command == Command.EVENT) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException("OOPS!!! Argument for this command cannot be empty.");
            }

            if (command == Command.TODO) {
                response = addTask(Command.TODO, inputs[1]);
            } else if (command == Command.DEADLINE) {
                response = addTask(Command.DEADLINE, inputs[1]);
            } else {
                response = addTask(Command.EVENT, inputs[1]);
            }
        } else if (command == Command.DELETE) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException("OOPS!!! Argument for this command cannot be empty.");
            }

            try {
                if (!tasks.isValidIndex(Integer.parseInt(inputs[1]))) {
                    throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
                }
            } catch (NumberFormatException exception) {
                throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            response = editTask(Command.DELETE, Integer.parseInt(inputs[1]));
        } else if (command == Command.LIST) {
            response = executeSingleCommand(Command.LIST);
        } else if (command == Command.FIND) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException("OOPS!!! Argument for this command cannot be empty.");
            }

            response = executeFindCommand(inputs[1]);
        } else if (command == Command.MARK) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException("OOPS!!! Argument for this command cannot be empty.");
            }

            try {
                if (!tasks.isValidIndex(Integer.parseInt(inputs[1]))) {
                    throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
                }
            } catch (NumberFormatException exception) {
                throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            response = editTask(Command.MARK, Integer.parseInt(inputs[1]));
        } else if (command == Command.UNMARK) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException("OOPS!!! Argument for this command cannot be empty.");
            }

            try {
                if (!tasks.isValidIndex(Integer.parseInt(inputs[1]))) {
                    throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
                }
            } catch (NumberFormatException exception) {
                throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            response = Duke.editTask(Command.UNMARK, Integer.parseInt(inputs[1]));
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
