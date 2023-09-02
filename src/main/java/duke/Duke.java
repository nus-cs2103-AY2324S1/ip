package duke;

import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.IOException;

public class Duke {
    private static TaskList tasks = new TaskList();

    /**
     * Utility function to return the command type of the given command string.
     *
     * @param command The command string to parse
     * @return The Command type of the parsed string
     */
    private static Command getCommandType(String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException err) {
            return Command.INVALID;
        }
    }

    /**
     * Handler for the todo command. Generates the required response.
     *
     * @param input The parameters for the todo command
     * @return The response string to the user
     */
    private static String[] handleTodo(String input) {
        String[] response = new String[3];
        Todo task = new Todo(input);
        tasks.add(task);

        response[0] = "Got it. I've added this task:";
        response[1] = TextUi.INDENT + task;
        response[2] = String.format("Now you have %d task%s in the list.",
                tasks.size(),
                tasks.size() == 1 ? "" : "s");
        return response;
    }

    /**
     * Handler for the deadline command. Generates the required response.
     *
     * @param input The parameters for the deadline command
     * @return The response string to the user
     * @throws DukeException if input for deadline is missing the "/by" keyword
     */
    private static String[] handleDeadline(String input) throws DukeException {
        String[] response = new String[3];
        String[] tokens = input.split(" /by ", 2);
        if (tokens.length == 1) {
            throw new DukeException("Deadline is missing the /by keyword");
        }

        Deadline task = new Deadline(tokens[0], tokens[1]);
        tasks.add(task);

        response[0] = "Got it. I've added this task:";
        response[1] = TextUi.INDENT + task;
        response[2] = String.format("Now you have %d task%s in the list.",
                tasks.size(),
                tasks.size() == 1 ? "" : "s");
        return response;
    }

    /**
     * Handler for the event command. Generates the required response.
     *
     * @param input The parameters for the event command
     * @return The response string to the user
     * @throws DukeException if input for event is missing the "/from" or "/to" keyword
     */
    private static String[] handleEvent(String input) throws DukeException {
        String[] response = new String[3];
        String[] tokens = input.split(" /from ", 2);
        if (tokens.length == 1) {
            throw new DukeException("Event is missing the /from keyword");
        }
        String[] tokens2 = tokens[1].split(" /to ", 2);
        if (tokens2.length == 1) {
            throw new DukeException("Event is missing the /to keyword");
        }

        Event task = new Event(tokens[0], tokens2[0], tokens2[1]);
        tasks.add(task);

        response[0] = "Got it. I've added this task:";
        response[1] = TextUi.INDENT + task;
        response[2] = String.format("Now you have %d task%s in the list.",
                tasks.size(),
                tasks.size() == 1 ? "" : "s");
        return response;
    }

    /**
     * Handler for the list command. Generates the required response.
     *
     * @return The response string to the user
     */
    private static String[] handleList() {
        String[] response;
        int numTasks = tasks.size();
        if (numTasks == 0) {
            response = new String[1];
            response[0] = "There are no items in your list.";
        } else {
            response = new String[numTasks + 1];
            response[0] = "Here are the tasks in your list:";
            for (int i = 1; i <= numTasks; i++) {
                response[i] = (i + ". " + tasks.get(i));
            }
        }
        return response;
    }

    /**
     * Handler for the mark command. Generates the required response.
     *
     * @param input The parameters for the mark command
     * @return The response string to the user
     * @throws DukeException if input for mark is not an integer or
     *                       if the integer is out of bounds
     */
    private static String[] handleMark(String input) throws DukeException {
        String[] response = new String[2];
        int taskNum;
        try {
            taskNum = Integer.parseInt(input);
        } catch (NumberFormatException err) {
            throw new DukeException("Mark can only take an integer task number");
        }
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("Task number does not exist");
        }

        Task task = tasks.get(taskNum);
        task.mark();

        response[0] = "Nice! I've marked this task as done:";
        response[1] = TextUi.INDENT + task;
        return response;
    }

    /**
     * Handler for the unmark command. Generates the required response.
     *
     * @param input The parameters for the unmark command
     * @return The response string to the user
     * @throws DukeException if input for unmark is not an integer or
     *                       if the integer is out of bounds
     */
    private static String[] handleUnmark(String input) throws DukeException {
        String[] response = new String[2];
        int taskNum;

        try {
            taskNum = Integer.parseInt(input);
        } catch (NumberFormatException err) {
            throw new DukeException("Unmark can only take an integer task number");
        }
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("Task number does not exist");
        }

        Task task = tasks.get(taskNum);
        task.unmark();

        response[0] = "Ok, I've marked this task as not done yet:";
        response[1] = TextUi.INDENT + task;
        return response;
    }

    /**
     * Handler for the delete command. Generates the required response.
     *
     * @param input The parameters for the delete command
     * @return The response string to the user
     * @throws DukeException if input for delete is not an integer or
     *                       if the integer is out of bounds
     */
    private static String[] handleDelete(String input) throws DukeException {
        String[] response = new String[3];
        int taskNum;
        try {
            taskNum = Integer.parseInt(input);
        } catch (NumberFormatException err) {
            throw new DukeException("Delete can only take an integer task number");
        }
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("Task number does not exist");
        }

        Task task = tasks.get(taskNum);
        tasks.delete(taskNum);

        response[0] = "Noted. I've removed this task:";
        response[1] = TextUi.INDENT + task;
        response[2] = String.format("Now you have %d task%s in the list.",
                tasks.size(),
                tasks.size() == 1 ? "" : "s");
        return response;
    }

    public static void main(String[] args) {
        TextUi ui = new TextUi();
        Storage storage = new Storage();

        ui.showWelcomeMessage();

        boolean stopped = false;

        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showMessage("Error loading tasks");
            stopped = true;
        }
        while (!stopped) {
            String input = ui.getUserCommand();
            String[] params = input.split(" ", 2);
            Command command = getCommandType(params[0]);

            switch (command) {
                case TODO: {
                    if (params.length == 1) {
                        ui.showMessage("Sorry, but the description of a todo cannot be empty.");
                    } else {
                        String[] response = handleTodo(params[1]);
                        ui.showMessage(response);
                    }
                    break;
                }
                case DEADLINE: {
                    if (params.length == 1) {
                        ui.showMessage("Sorry, but the description of a deadline cannot be empty.");
                    } else {
                        try {
                            String[] response = handleDeadline(params[1]);
                            ui.showMessage(response);
                        } catch (DukeException err) {
                            ui.showMessage(err.getMessage() + ".");
                        }
                    }
                    break;
                }
                case EVENT: {
                    if (params.length == 1) {
                        ui.showMessage("Sorry, but the description of a event cannot be empty.");
                    } else {
                        try {
                            String[] response = handleEvent(params[1]);
                            ui.showMessage(response);
                        } catch (DukeException err) {
                            ui.showMessage(err.getMessage() + ".");
                        }
                    }
                    break;
                }
                case LIST: {
                    String[] response = handleList();
                    ui.showMessage(response);
                    break;
                }
                case MARK: {
                    try {
                        String[] response = handleMark(params[1]);
                        ui.showMessage(response);
                    } catch (DukeException err) {
                        ui.showMessage(err.getMessage() + ".");
                    }
                    break;
                }
                case UNMARK: {
                    try {
                        String[] response = handleUnmark(params[1]);
                        ui.showMessage(response);
                    } catch (DukeException err) {
                        ui.showMessage(err.getMessage() + ".");
                    }
                    break;
                }
                case DELETE: {
                    try {
                        String[] response = handleDelete(params[1]);
                        ui.showMessage(response);
                    } catch (DukeException err) {
                        ui.showMessage(err.getMessage() + ".");
                    }
                }
                break;
                case BYE: {
                    stopped = true;
                    ui.showGoodbyeMessage();
                    break;
                }
                default:
                    ui.showInvalidCommandMessage();
                    break;
            }
            try {
                storage.save(tasks);
            } catch (IOException e) {
                ui.showMessage("Error saving tasks");
            }
        }
    }
}
