import exceptions.*;

import command.Command;

import storage.Storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import ui.Ui;

import java.util.Scanner;

/**
 * A chatbot to keep track of your tasks.
 *
 * @author Andrew Daniel Janong
 */
public class Duke {
    private static Storage storage = new Storage();

    /**
     * Sends a greeting message to the user.
     */
    private static void greetingMessage() {
        Ui.greetUser();
    }

    /**
     * Adds a task to the task list and sends a message of the task added.
     * A task can be a task.ToDo, task.Deadline, or task.Event.
     * @param command
     * @param taskInfo
     */
    private static void addTask(Command command, String taskInfo) throws DukeInvalidDateException {
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

        storage.addTask(newTask);
    }

    /**
     * Edits a task in the list.
     * Editing a task can be deleting, marking, or unmarking a task.
     * @param command
     * @param taskIndex
     */
    private static void editTask (Command command, int taskIndex) {

        if (command == Command.DELETE) {
            storage.deleteTask(taskIndex);
        } else if (command == Command.MARK) {
            storage.markTask(taskIndex);
        } else if (command == Command.UNMARK) {
            storage.unmarkTask(taskIndex);
        }
    }

    /**
     * Executes single commands.
     * Single commands consists of: Listing all tasks, Printing goodbye message.
     * @param command
     */
    private static void executeSingleCommand(Command command) {
        if (command == Command.LIST) {
            Ui.printLines(storage.toString());
        } else if (command == Command.BYE) {
            Ui.printExitMessage();
        }
    }

    /**
     * Runs the command from the user input.
     * @param inputs
     * @return A boolean to stop the chatbot on "bye" command
     * @throws DukeException
     */
    private static boolean runCommand(String[] inputs) throws DukeException {
        String command = inputs[0].toLowerCase();

        if (command.equals("bye")) {
            Ui.printExitMessage();
            return false;
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException(command);
            }

            if (command.equals("todo")) {
                addTask(Command.TODO, inputs[1]);
            } else if (command.equals(("deadline"))) {
                addTask(Command.DEADLINE, inputs[1]);
            } else {
                addTask(Command.EVENT, inputs[1]);
            }

        } else if (command.equals("delete")) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException(command);
            }

            try {
                if (!storage.checkIndexValidity(Integer.parseInt(inputs[1]))) {
                    throw new DukeInvalidIndexException(Integer.toString(storage.getTasksSize()));
                }
            } catch (NumberFormatException e) {
                throw new DukeInvalidIndexException(Integer.toString(storage.getTasksSize()));
            }

            editTask(Command.DELETE, Integer.parseInt(inputs[1]));
        } else if (command.equals("list")) {
            executeSingleCommand(Command.LIST);
        } else if (command.equals("mark")) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException(command);
            }

            try {
                if (!storage.checkIndexValidity(Integer.parseInt(inputs[1]))) {
                    throw new DukeInvalidIndexException(Integer.toString(storage.getTasksSize()));
                }
            } catch (NumberFormatException e) {
                throw new DukeInvalidIndexException(Integer.toString(storage.getTasksSize()));
            }

            editTask(Command.MARK, Integer.parseInt(inputs[1]));
        } else if (command.equals("unmark")) {
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeEmptyArgumentException(command);
            }

            try {
                if (!storage.checkIndexValidity(Integer.parseInt(inputs[1]))) {
                    throw new DukeInvalidIndexException(Integer.toString(storage.getTasksSize()));
                }
            } catch (NumberFormatException e) {
                throw new DukeInvalidIndexException(Integer.toString(storage.getTasksSize()));
            }

            Duke.editTask(Command.UNMARK, Integer.parseInt(inputs[1]));
        } else {
            throw new DukeUnknownCommandException(command);
        }

        return true;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Ui.greetUser();

        while (true) {
            try {
                String userInput = sc.nextLine();
                String[] inputs = userInput.split(" ", 2);

                if (!Duke.runCommand(inputs)) break;

            } catch (Exception e) {
                Ui.printLines(e.getMessage());
            }
        }

    }
}
