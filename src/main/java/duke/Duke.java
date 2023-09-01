package duke;

import java.io.IOException;

import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.DukeEmptyArgumentException;
import duke.exceptions.DukeInvalidDateException;
import duke.exceptions.DukeInvalidIndexException;
import duke.exceptions.DukeUnknownCommandException;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
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
     * Sends a greeting message to the user.
     */
    private static void greetUser() {
        Ui.greetUser();
    }

    /**
     * Adds a task to the task list and sends a message of the task added.
     * A task can be a ToDo, Deadline, or Event.
     *
     * @param command Task command of input.
     * @param taskInfo Info regarding the task.
     */
    private static void addTask(Command command, String taskInfo)
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

        tasks.addTask(newTask);
    }

    /**
     * Edits a task in the list.
     * Editing a task can be deleting, marking, or unmarking a task.
     *
     * @param command Edit command of input.
     * @param taskIndex Index of task to edit.
     */
    private static void editTask (Command command, int taskIndex) {
        if (command == Command.DELETE) {
            tasks.deleteTask(taskIndex);
        } else if (command == Command.MARK) {
            tasks.markTask(taskIndex);
        } else if (command == Command.UNMARK) {
            tasks.unmarkTask(taskIndex);
        }
    }

    /**
     * Executes single commands.
     * Single commands consists of: Listing all tasks, Printing goodbye message.
     *
     * @param command Single command of input.
     */
    private static void executeSingleCommand(Command command) {
        if (command == Command.LIST) {
            Ui.printLines(tasks.toString());
        } else if (command == Command.BYE) {
            Ui.printExitMessage();
        }
    }

    /**
     * Runs the command from the user input.
     *
     * @param command Command of the user input.
     * @param inputs Arguments of the input.
     * @return A boolean to stop the chatbot on "bye" command
     * @throws DukeException Error when executing the command.
     */
    private static boolean runCommand(Command command, String[] inputs) throws DukeException {
        if (command == Command.BYE) {
            Ui.printExitMessage();
            storage.writeTasks(tasks);

            return false;
        } else if (command == Command.TODO || command == Command.DEADLINE || command == Command.EVENT) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException("OOPS!!! Argument for this command is invalid.");
            }

            if (command == Command.TODO) {
                addTask(Command.TODO, inputs[1]);
            } else if (command == Command.DEADLINE) {
                addTask(Command.DEADLINE, inputs[1]);
            } else {
                addTask(Command.EVENT, inputs[1]);
            }
        } else if (command == Command.DELETE) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException("OOPS!!! Argument for this command is invalid.");
            }

            try {
                if (!tasks.isValidIndex(Integer.parseInt(inputs[1]))) {
                    throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
                }
            } catch (NumberFormatException exception) {
                throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            editTask(Command.DELETE, Integer.parseInt(inputs[1]));
        } else if (command == Command.LIST) {
            executeSingleCommand(Command.LIST);
        } else if (command == Command.MARK) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException("OOPS!!! Argument for this command is invalid.");
            }

            try {
                if (!tasks.isValidIndex(Integer.parseInt(inputs[1]))) {
                    throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
                }
            } catch (NumberFormatException exception) {
                throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            editTask(Command.MARK, Integer.parseInt(inputs[1]));
        } else if (command == Command.UNMARK) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException("OOPS!!! Argument for this command is invalid.");
            }

            try {
                if (!tasks.isValidIndex(Integer.parseInt(inputs[1]))) {
                    throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
                }
            } catch (NumberFormatException exception) {
                throw new DukeInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            Duke.editTask(Command.UNMARK, Integer.parseInt(inputs[1]));
        } else {
            throw new DukeUnknownCommandException(inputs[0]);
        }

        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greetUser();

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

                if (!Duke.runCommand(command, inputs)) break;
                storage.writeTasks(tasks);

            } catch (Exception exception) {
                Ui.printLines(exception.getMessage());
            }
        }
    }
}
