import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Scanner;

/**
 * Encapsulates the logic of a Chat bot
 *
 * @author Rayson
 */
public class Duke {

    /**
     * Represents the different commands accepted by the chatbot
     */
    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        CHECK,
    }

    // CONSTANTS
    static final String LINE = "_______________________________________";
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .optionalStart().appendPattern(" HH:mm").optionalEnd()
            .optionalStart().appendPattern(" HHmm").optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    public static void main(String[] args) {
        Storage storage = new Storage(DIR_NAME + File.separator + FILE_NAME);
        TaskList tasks = new TaskList(storage.loadTasksFromStorage());
        Ui ui = new Ui();
        Scanner scanner = new Scanner(System.in);

        ui.showWelcome();

        try {
            while (true) {
                String input = scanner.nextLine();
                System.out.println(LINE);

                // Use Regex to extract the first word even with preceding whitespace
                String command = input.replaceAll("^\\W*\\b(\\w+).*", "$1").toUpperCase();

                try { // In case there are exceptions
                    // User wants to end the chatbot
                    if (command.equals(Command.BYE.name())) {

                        storage.writeFile(tasks.retrieveForStorage());

                        ui.showOutro();
                        break;
                    }

                    // List out all the tasks in the chatbot
                    if (command.equals(Command.LIST.name())) {

                        if (tasks.size() == 0) {
                            throw new EmptyTaskListException();
                        }

                        ui.printTasks(tasks);
                        continue;
                    }

                    // Mark a task as done
                    if (command.equals(Command.MARK.name())) {
                        String desc = input.replaceAll("[^0-9]", "");
                        if (desc.equals("")) {
                            throw new NoIndexException("No Index");
                        }
                        int index = Integer.parseInt(desc);
                        if (tasks.size() < index || index < 1) {
                            throw new NoIndexException(Integer.toString(index));
                        }

                        Task task = tasks.get(index - 1);
                        task.markDone();
                        System.out.println("Great job completing the task! I've marked it as done.");
                        System.out.println(task);
                        System.out.println(LINE);
                        continue;
                    }

                    // Unmark a done task
                    if (command.equals(Command.UNMARK.name())) {
                        String desc = input.replaceAll("[^0-9]", "");
                        if (desc.equals("")) {
                            throw new NoIndexException("No Index");
                        }
                        int index = Integer.parseInt(desc);
                        if (tasks.size() < index || index < 1) {
                            throw new NoIndexException(Integer.toString(index));
                        }

                        Task task = tasks.get(index - 1);
                        task.markUndone();
                        System.out.println("Oops... Did you mark it incorrectly?");
                        System.out.println(task);
                        System.out.println(LINE);
                        continue;
                    }

                    // Add a todo to the chatbot
                    if (command.equals(Command.TODO.name())) {
                        String desc = input.replaceAll("^\\s*todo\\s*", "");
                        if (desc.equals("")) {
                            throw new NoDescriptionException("todo");
                        }

                        Task task = new ToDo(desc);
                        tasks.add(task);
                        ui.showAddMessage(task, tasks.size());
                        continue;
                    }


                    // Add a deadline
                    if (command.equals(Command.DEADLINE.name())) {
                        String desc_time = input.replaceAll("^\\s*deadline\\s*", "");

                        String[] strings = desc_time.split(" /by ");

                        if (desc_time.equals("")) {
                            throw new NoDescriptionException("deadline");
                        }
                        if (strings.length == 1) {
                            throw new UnknownTimeException(strings[0]);
                        }

                        Task task = new Deadline(strings[0],
                                LocalDateTime.parse(strings[1], FORMATTER));
                        tasks.add(task);
                        ui.showAddMessage(task, tasks.size());
                        continue;
                    }

                    // Add an Event
                    if (command.equals(Command.EVENT.name())) {
                        String content = input.replaceAll("^\\s*event\\s*", "");
                        if (content.equals("")) {
                            throw new NoDescriptionException("event");
                        }

                        String[] desc_time = content.split(" /from ");
                        String[] times = desc_time[1].split(" /to ");

                        if (times.length == 1) {
                            throw new UnknownTimeException(desc_time[0]);
                        }

                        LocalDateTime start = LocalDateTime.parse(times[0], FORMATTER);
                        LocalDateTime end = LocalDateTime.parse(times[1], FORMATTER);

                        if (start.isAfter(end)) {
                            throw new BackwardsTimeException();
                        }

                        Task task = new Event(desc_time[0], start, end);
                        tasks.add(task);
                        ui.showAddMessage(task, tasks.size());
                        continue;
                    }

                    // Delete a task from the chatbot
                    if (command.equals(Command.DELETE.name())) {
                        String desc = input.replaceAll("[^0-9]", "");
                        if (desc.equals("")) {
                            throw new NoIndexException("No Index");
                        }
                        int index = Integer.parseInt(desc);

                        if (tasks.size() < index || index < 1) {
                            throw new NoIndexException(Integer.toString(index));
                        }
                        Task task = tasks.get(index - 1);
                        tasks.remove(index - 1);
                        System.out.println("Noted... I've removed this task:");
                        System.out.println(" " + task);
                        System.out.printf("Now you have %d tasks in the list%n", tasks.size());
                        System.out.println(LINE);
                        continue;
                    }

                    // check for tasks on a day
                    if (command.equals(Command.CHECK.name())) {
                        String content = input.replaceAll("^\\s*check\\s*", "");
                        ui.printScheduledTasks(tasks, LocalDateTime.parse(content, FORMATTER));
                        continue;
                    }

                    throw new UnknownCommandException(input);

                } catch (UnknownTimeException | UnknownCommandException | EmptyTaskListException |
                         NoDescriptionException | NoIndexException | BackwardsTimeException e) {
                    System.out.println(e.getMessage());
                    System.out.println(LINE);
                }


            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Respironix has encountered an issue; exiting");
        }
    }
}
