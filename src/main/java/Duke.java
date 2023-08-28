import Exceptions.DukeException;
import Exceptions.InvalidInputException;
import Exceptions.MissingTaskException;
import Exceptions.MissingDateException;
import Exceptions.MissingTitleException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    /**
     * Returns the command that is called by the user.
     * @param input The user input.
     * @return The type of command called.
     */
    private static Commands determineCommand(String input) {
        for (Commands command: Commands.values()) {
            if (input.contains(command.name().toLowerCase())) {
                return command;
            }
        }
        return Commands.UNKNOWN;
    }

    /**
     * Returns the title of the task.
     * @param input The user input.
     * @param command Type of command given by the user.
     * @return The title of the task.
     * @throws DukeException Exceptions.InvalidInputException thrown if input cannot be recognised. Exceptions.MissingTitleException thrown
     * if user did not give a title for their task.
     */
    private static String obtainTitle(String input, Commands command) throws DukeException{
        try {
            if (command.equals(Commands.TODO)) {
                return input.split("todo ")[1];

            } else if (command.equals(Commands.DEADLINE)) {
                return input.split("deadline ")[1].split(" /by ")[0];

            } else if (command.equals(Commands.EVENT)) {
                return input.split("event ")[1].split(" /from ")[0];

            } else {
                throw new InvalidInputException("Invalid input");
            }

        } catch (ArrayIndexOutOfBoundsException oob) {
            throw new MissingTitleException("Missing Title");
        } catch (Exception e) {
            throw new InvalidInputException("Invalid input");
        }
    }

    /**
     * Returns the date specified by the user for their task.
     * @param input The user input.
     * @param command Type of command given by the user.
     * @return By date of deadlines or From and To date of events.
     * @throws DukeException Exceptions.InvalidInputException thrown if input cannot be recognised. Exceptions.MissingDateException thrown
     * if user did not give a by date for their deadline or either a from or to date for their event.
     */
    private static String obtainDate(String input, Commands command) throws DukeException{
        try {
            if (command.equals(Commands.DEADLINE)) {
                return input.split(" /by ")[1];

            } else if (command.equals(Commands.EVENT)) {
                String from = input.split(" /from ")[1].split(" /to ")[0];
                String to = input.split(" /from ")[1].split(" /to ")[1];
                return from + "/to" + to;

            } else {
                throw new InvalidInputException("Invalid Input");
            }
        } catch (ArrayIndexOutOfBoundsException oob) {
            throw new MissingDateException("Missing Date");
        } catch (Exception e) {
            throw new InvalidInputException("Invalid input");
        }
    }

    /**
     * Updates the completion status of their task and returns a String as the dialogue.
     * @param input The user input.
     * @param command Type of command given by the user.
     * @param tasks List of tasks added by the user.
     * @return Dialogue for the bot to confirm status of the task.
     * @throws DukeException Exceptions.InvalidInputException thrown if input cannot be recognised. Exceptions.MissingTaskException thrown
     * if task cannot be found in the task list.
     */
    private static String changeTaskCompletion(String input, Commands command, List<Task> tasks) throws DukeException{
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
            throw new MissingTaskException("Missing Tasks.Task");
        } catch (IndexOutOfBoundsException ioob) {
            throw new MissingTaskException("Missing Tasks.Task");
        } catch (Exception e) {
            throw new InvalidInputException("Invalid input");
        }
    }

    /**
     * Deletes a task from the task list and returns a String as the dialogue.
     * @param input The user input.
     * @param tasks List of tasks added by the user.
     * @return Dialogue to confirm the deletion of the task from the list.
     * @throws DukeException Exceptions.InvalidInputException thrown if input cannot be recognised. Exceptions.MissingTaskException thrown
     * if task cannot be found in the task list.
     */
    private static String deleteTask(String input, List<Task> tasks) throws DukeException {
        try {
            int taskNum = Integer.valueOf(input.split(" ")[1]);
            Task deleted = tasks.remove(taskNum - 1);
            return deleted.getTask() + " has been deleted!";

        } catch (ArrayIndexOutOfBoundsException aoob) {
            throw new MissingTaskException("Missing Tasks.Task");
        } catch (IndexOutOfBoundsException ioob) {
            throw new MissingTaskException("Missing Tasks.Task");
        } catch (Exception e) {
            throw new InvalidInputException("Invalid input");
        }
    }

    private static Task createTask(String input, Commands command, int isDone) throws DukeException {
        try {
            switch (command) {
                case TODO:
                    String todoTitle = obtainTitle(input, Commands.TODO);
                    return new ToDo(todoTitle, isDone);

                case DEADLINE:
                    String deadlineTitle = obtainTitle(input, Commands.DEADLINE);
                    String by = obtainDate(input, Commands.DEADLINE);
                    return new Deadline(deadlineTitle, isDone, by);

                case EVENT:
                    String eventTitle = obtainTitle(input, Commands.EVENT);
                    String fromTo = obtainDate(input, Commands.EVENT);
                    return new Event(eventTitle, isDone, fromTo.split("/to")[0], fromTo.split("/to")[1]);

                default:
                    throw new InvalidInputException("Invalid input");
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    private static List<Task> stringToTask(List<String> input) {
        List<Task> output = new ArrayList<>();
        try {
            for (int i = 0; i < input.size(); i++) {
                String nextInput = input.get(i);
                Commands command = determineCommand(nextInput);
                int isDone = nextInput.contains("/UC") ? 0 : 1;
                Task task = createTask(nextInput, command, isDone);
                output.add(task);
            }
            return output;
        } catch (DukeException e) {
            System.out.println("File input cannot be read");
            return output;
        }
    }

    private static List<String> taskToString(List<Task> input) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            output.add(input.get(i).toString());
        }
        return output;
    }
    public static void main(String[] args) {

        boolean end = false;

        Scanner scanner = new Scanner(System.in);

        List<Task> tasks = stringToTask(LocalFile.readFile());

        String greeting = "Hello! I'm Toothless.\n" +
                "What can I do for you today?\n" +
                "---------------------------------";
        String farewell = "Goodbye. Hope to see you soon!\n" +
                "---------------------------------";

        String addTask = "A new task has been added!\n ";

        System.out.println(greeting);

        while (!end) {

            try {
                String nextInput = scanner.nextLine().trim();
                Commands command = determineCommand(nextInput);

                switch (command) {
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        Task t = createTask(nextInput, command, 0);
                        tasks.add(t);
                        System.out.println(addTask + t.getTask());
                        break;

                    case LIST:
                        if (tasks.isEmpty()) {
                            System.out.println("You have no tasks! Yay :)");
                        } else {
                            System.out.println("Here's your list of tasks!\n");
                            for (int i = 0; i < tasks.size(); i++) {
                                Task task = tasks.get(i);
                                System.out.println((i + 1) + ": " + task.getTask());
                            }
                        }
                        break;

                    case UNMARK:
                        System.out.println(changeTaskCompletion(nextInput, Commands.UNMARK, tasks));
                        break;

                    case MARK:
                        System.out.println(changeTaskCompletion(nextInput, Commands.MARK, tasks));
                        break;

                    case DELETE:
                        System.out.println(deleteTask(nextInput, tasks));
                        break;

                    case BYE:
                        end = true;
                        System.out.println(LocalFile.saveToDisk(taskToString(tasks)));
                        break;

                    case UNKNOWN:
                        throw new InvalidInputException("Invalid input");
                }
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            } catch (Exception e) {
                System.out.println("There has been an internal error. Please try again!");
            }

            System.out.println("---------------------------------");
        }

        System.out.println(farewell);
    }
}
