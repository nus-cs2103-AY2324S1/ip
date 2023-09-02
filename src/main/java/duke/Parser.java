package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This class handles the logic for processing user input.
 */
public class Parser {
    /**
     * List of valid user commands.
     */
    public static enum Command {
        INVALID(""),
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        FIND("find");

        private final String text;

        Command(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return this.text;
        }
    }

    /**
     * Processes the user input and calls the relevant background functions.
     *
     * @param userInput The raw input entered by the user.
     * @param dukeBot   The context.
     * @return A Command enum that states the user command entered.
     * @throws DukeException If invalid input.
     */
    public static Command processInput(String userInput, Duke dukeBot) throws DukeException {
        String[] inputArray = userInput.split(" ", 2);
        Command userCommand = Command.INVALID;

        for (Command command : Command.values()) {
            if (command.toString().equals(inputArray[0])) {
                userCommand = command;
            }
        }

        String inputInfo = "";
        if (inputArray.length == 2) {
            inputInfo = inputArray[1];
        }

        switch (userCommand) {
            case BYE:
                break;
            case LIST:
                dukeBot.tasks.listTasks();
                break;
            case MARK:
                dukeBot.tasks.markTask(Integer.parseInt(inputInfo));
                break;
            case UNMARK:
                dukeBot.tasks.unmarkTask(Integer.parseInt(inputInfo));
                break;
            case DELETE:
                dukeBot.tasks.deleteTask(Integer.parseInt(inputInfo));
                break;
            case TODO: {
                String taskName = inputInfo;
                Task newTask = new Todo(taskName);
                dukeBot.tasks.addTask(newTask);
                break;
            }
            case DEADLINE: {
                String[] taskInfo = inputInfo.split(" /by ");

                if (taskInfo.length != 2) {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                String taskName = taskInfo[0];
                String deadline = taskInfo[1];
                Task newTask = new Deadline(taskName, deadline);
                dukeBot.tasks.addTask(newTask);
                break;
            }
            case EVENT: {
                String[] taskInfo = inputInfo.split(" /from ");

                if (taskInfo.length != 2 || taskInfo[1].split("/to").length != 2) {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                String taskName = taskInfo[0];
                String startTime = taskInfo[1].split(" /to ")[0];
                String endTime = taskInfo[1].split(" /to ")[1];
                Task newTask = new Event(taskName, startTime, endTime);
                dukeBot.tasks.addTask(newTask);
                break;
            }
            case FIND:
                dukeBot.tasks.findTasks(inputInfo);
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return userCommand;
    }
}
