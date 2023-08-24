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
     * @throws DukeException InvalidInputException thrown if input cannot be recognised. MissingTitleException thrown
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
     * @throws DukeException InvalidInputException thrown if input cannot be recognised. MissingDateException thrown
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
     * @throws DukeException InvalidInputException thrown if input cannot be recognised. MissingTaskException thrown
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
     * @param tasks List of tasks added by the user.
     * @return Dialogue to confirm the deletion of the task from the list.
     * @throws DukeException InvalidInputException thrown if input cannot be recognised. MissingTaskException thrown
     * if task cannot be found in the task list.
     */
    private static String deleteTask(String input, List<Task> tasks) throws DukeException {
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
    public static void main(String[] args) {

        boolean end = false;

        Scanner scanner = new Scanner(System.in);

        List<Task> tasks = new ArrayList<>();

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
                        String todoTitle = obtainTitle(nextInput, Commands.TODO);
                        ToDo todo = new ToDo(todoTitle);
                        tasks.add(todo);
                        System.out.println(addTask + todo.getTask());
                        break;

                    case DEADLINE:
                        String deadlineTitle = obtainTitle(nextInput, Commands.DEADLINE);
                        String by = obtainDate(nextInput, Commands.DEADLINE);
                        Deadline deadline = new Deadline(deadlineTitle, by);
                        tasks.add(deadline);
                        System.out.println(addTask + deadline.getTask());
                        break;

                    case EVENT:
                        String eventTitle = obtainTitle(nextInput, Commands.EVENT);
                        String fromTo = obtainDate(nextInput, Commands.EVENT);
                        Event event = new Event(eventTitle, fromTo.split("/to")[0], fromTo.split("/to")[1]);
                        tasks.add(event);
                        System.out.println(addTask + event.getTask());
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
