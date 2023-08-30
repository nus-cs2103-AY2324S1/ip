package Tasks;
import Exceptions.DukeException;
import Exceptions.InvalidInputException;
import Exceptions.MissingTaskException;
import Utils.Commands;
import Utils.DukeDateFormat;
import Utils.Parser;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<String> stringTasks) {
        this.tasks = stringToTask(stringTasks);
    }

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

    public static Task createTask(String input, Commands command, int isDone) throws DukeException {
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

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

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
     * @throws DukeException Exceptions.InvalidInputException thrown if input cannot be recognised. Exceptions.MissingTaskException thrown
     * if task cannot be found in the task list.
     */
    public String changeTaskCompletion(String input, Commands command) throws DukeException{
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

        } catch (ArrayIndexOutOfBoundsException aoob) {
            throw new MissingTaskException("Missing Task");
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
     * @throws DukeException Exceptions.InvalidInputException thrown if input cannot be recognised. Exceptions.MissingTaskException thrown
     * if task cannot be found in the task list.
     */
    public String deleteTask(String input) throws DukeException {
        try {
            int taskNum = Integer.valueOf(input.split(" ")[1]);
            Task deleted = tasks.remove(taskNum - 1);
            return deleted.getTask() + " has been deleted!";

        } catch (ArrayIndexOutOfBoundsException aoob) {
            throw new MissingTaskException("Missing Task");
        } catch (IndexOutOfBoundsException ioob) {
            throw new MissingTaskException("Missing Task");
        } catch (Exception e) {
            throw new InvalidInputException("Invalid input");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

}
