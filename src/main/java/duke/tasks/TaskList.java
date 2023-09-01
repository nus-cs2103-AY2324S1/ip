package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.MissingTaskException;
import duke.utils.Commands;
import duke.utils.DukeDateFormat;
import duke.utils.Parser;

/**
 * Class that contains the list of tasks when the chatbot is active.
 */
public class TaskList {

    private List<Task> tasks;

    public TaskList(List<String> stringTasks) {
        this.tasks = stringToTask(stringTasks);
    }

    /**
     * Converts a list of task descriptions to a list of tasks.
     * @param input List of task descriptions.
     * @return List of tasks.
     */
    private static List<Task> stringToTask(List<String> input) {
        List<Task> output = new ArrayList<>();
        try {
            for (int i = 0; i < input.size(); i++) {
                String nextInput = input.get(i);
                Commands command = Parser.determineCommand(nextInput);
                int isDone = nextInput.contains("/UC") ? 0 : 1;
                Task task = createTask(nextInput, command, isDone);
                output.add(task);
            }
            return output;
        } catch (DukeException e) {
            return output;
        }
    }

    /**
     * Creates a task based on the input given by the user.
     * @param input The input given by the user.
     * @param command Type of command in the input
     * @param isDone A field for task created.
     * @return The task created.
     * @throws DukeException If title or date does not exist, or if input is invalid.
     * @throws DateTimeParseException If date exists but is in the wrong format.
     */
    public static Task createTask(String input, Commands command, int isDone)
            throws DukeException, DateTimeParseException {
        try {
            switch (command) {
            case TODO:
                String todoTitle = Parser.obtainTitle(input, Commands.TODO);
                return new ToDo(todoTitle, isDone);

            case DEADLINE:
                String deadlineTitle = Parser.obtainTitle(input, Commands.DEADLINE);
                String by = Parser.obtainDate(input, Commands.DEADLINE);
                LocalDate byDate = DukeDateFormat.stringToDate(by);
                return new Deadline(deadlineTitle, isDone, byDate);

            case EVENT:
                String eventTitle = Parser.obtainTitle(input, Commands.EVENT);
                String fromTo = Parser.obtainDate(input, Commands.EVENT);
                LocalDate from = DukeDateFormat.stringToDate(fromTo.split("/to")[0]);
                LocalDate to = DukeDateFormat.stringToDate(fromTo.split("/to")[1]);
                return new Event(eventTitle, isDone, from, to);

            default:
                throw new InvalidInputException("Invalid input");
            }
        } catch (DukeException | DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Checks if the list of tasks is empty.
     * @return True if list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a list of task descriptions.
     * @param typeOfDes The type of description to be returned.
     * @return A list of description to be stored locally if typeOfDes = 0 else
     *     a list of description to be read by the user.
     */
    public List<String> getTasksDes(int typeOfDes) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (typeOfDes == 0) {
                output.add(tasks.get(i).toString());
            } else {
                output.add(tasks.get(i).getTask());
            }
        }
        return output;
    }

    /**
     * Updates the completion status of their task and returns a String as the dialogue.
     * @param input The user input.
     * @param command Type of command given by the user.
     * @return Dialogue for the bot to confirm status of the task.
     * @throws DukeException Exceptions.InvalidInputException thrown if input
     *     cannot be recognised. Exceptions.MissingTaskException thrown
     * @throws DukeException InvalidInputException thrown if input
     *     cannot be recognised. MissingTaskException thrown
     *     if task cannot be found in the task list.
     */
    public String changeTaskCompletion(String input, Commands command) throws DukeException {
        try {
            int taskNum = Integer.valueOf(input.split(" ")[1]);
            Task task = tasks.get(taskNum - 1);

            if (command.equals(Commands.UNMARK)) {
                return task.markAsUndone();

            } else if (command.equals(Commands.MARK)) {
                return task.markAsDone();
            } else {
                throw new InvalidInputException("Invalid input");
            }

        } catch (IndexOutOfBoundsException ioob) {
            throw new MissingTaskException("Missing Task");
        } catch (Exception e) {
            throw new InvalidInputException("Invalid input");
        }
    }

    /**
     * Deletes a task from the task list and returns a String as the dialogue.
     * @param input The user input.
     * @return Dialogue to confirm the deletion of the task from the list.
     * @throws DukeException Exceptions.InvalidInputException thrown if input
     *     cannot be recognised. Exceptions.MissingTaskException thrown
     *     if task cannot be found in the task list.
     */
    public String deleteTask(String input) throws DukeException {
        try {
            int taskNum = Integer.valueOf(input.split(" ")[1]);
            Task deleted = tasks.remove(taskNum - 1);
            return deleted.getTask() + " has been deleted!";

        } catch (IndexOutOfBoundsException ioob) {
            throw new MissingTaskException("Missing Task");
        } catch (Exception e) {
            throw new InvalidInputException("Invalid input");
        }
    }

    /**
     * Returns tasks that matches the keyword provided by the user.
     * @param input The user input.
     * @return The tasks that matched the keyword.
     * @throws DukeException If user did not provide a keyword or if the input was invalid.
     */
    public List<String> findTask(String input) throws DukeException {
        try {
            String keyword = Parser.obtainTitle(input, Commands.FIND);

            List<String> results = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t.getTask().contains(keyword)) {
                    results.add(t.getTask());
                }
            }

            return results;
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Adds a task to the list of tasks.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

}