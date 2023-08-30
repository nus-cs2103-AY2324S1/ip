import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A chatbot that helps you keep track of tasks.
 */
public class Duke {
    private static final String invalidIndexMessage = "Great heavens! You have entered an invalid index!\n";
    private static final String invalidInputMessage = "Great heavens! You have entered an invalid input!\n"
            + "Here are the valid commands:\n"
            + "  bye\n"
            + "  list\n"
            + "  mark|unmark <index>\n"
            + "  delete <index>\n"
            + "  todo <description>\n"
            + "  deadline <description> /by <date>\n"
            + "  event <description> /from <start> /to <end>\n";
    private static final String invalidMarkMessage = "Great heavens! The index of mark cannot be empty!\n"
            + "Usage: mark <index>\n";
    private static final String invalidUnmarkMessage = "Great heavens! The index of unmark cannot be empty!\n"
            + "Usage: unmark <index>\n";
    private static final String invalidTodoMessage = "Great heavens! The description of todo cannot be empty!\n"
            + "Usage: todo <description>\n";
    private static final String invalidDeadlineMessage = "Great heavens! Invalid usage of deadline!\n"
            + "Usage: deadline <description> /by <yyyy-mm-dd HHmm> (24h format)\n";
    private static final String invalidEventMessage = "Great heavens! Invalid usage of event!\n"
            + "Usage: event <description> /from <start> /to <end>\n";
    private static final String invalidDeleteMessage = "Great heavens! The index of delete cannot be empty!\n"
            + "Usage: delete <index>\n";
    private static final String regexPattern = "\\b(bye|list|unmark|mark|todo|deadline|event|delete)\\s*" // match command
            + "([^/]*[^/\\s])?\\s*"                     // match chars that are not / after command, trimming whitespace
            + "(?:(/by|/from)\\s+([^/]*[^/\\s]))?\\s*"  // match /by or /from command and argument, trimming whitespace
            + "(?:(/to)\\s+([^/]*[^/\\s]))?\\s*";       // match /to command and argument, trimming whitespace

    public static void main(String[] args) {
        Storage storage = new Storage("./data/duke.txt");
        Ui ui = new Ui();
        Pattern pattern = Pattern.compile(regexPattern);
        ArrayList<Task> tasks;
        try {
            tasks = storage.loadTasks();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new ArrayList<>();
        }
        ui.showGreeting();

        while (true) {
            try {
                String command = ui.readCommand();
                Matcher matcher = pattern.matcher(command);
                int index;
                Task newTask, currentTask;

                if (!matcher.matches()) {
                    // no match means input is not valid
                    throw new DukeException(Ui.line + invalidInputMessage + Ui.line);
                }

                if (matcher.group(1).equals("bye")) {
                    ui.showExitMessage();
                    break;
                }

                // match against the command given
                switch (matcher.group(1)) {
                    case "list":
                        ui.showList(tasks);
                        break;
                    case "mark":
                        if (matcher.group(2) == null) {
                            throw new DukeException(Ui.line + invalidMarkMessage + Ui.line);
                        }

                        index = Integer.parseInt(matcher.group(2));

                        if (index < 1 || index > tasks.size()) {
                            throw new DukeException(Ui.line + invalidIndexMessage + Ui.line);
                        }

                        currentTask = tasks.get(index - 1);
                        currentTask.markAsDone();
                        ui.showTaskDone(currentTask);
                        storage.saveTasks(tasks);
                        break;
                    case "unmark":
                        if (matcher.group(2) == null) {
                            throw new DukeException(Ui.line + invalidUnmarkMessage + Ui.line);
                        }

                        index = Integer.parseInt(matcher.group(2));

                        if (index < 1 || index > tasks.size()) {
                            throw new DukeException(Ui.line + invalidIndexMessage + Ui.line);
                        }

                        currentTask = tasks.get(index - 1);
                        currentTask.markAsUndone();
                        ui.showTaskUndone(currentTask);
                        storage.saveTasks(tasks);
                        break;
                    case "todo":
                        if (matcher.group(2) == null) {
                            throw new DukeException(Ui.line + invalidTodoMessage + Ui.line);
                        }

                        newTask = new Todo(matcher.group(2));
                        tasks.add(newTask);
                        ui.showTaskAdded(newTask, tasks.size());
                        storage.saveTasks(tasks);
                        break;
                    case "deadline":
                        if (matcher.group(2) == null
                                || matcher.group(3) == null
                                || !matcher.group(3).equals("/by")
                                || matcher.group(4) == null) {
                            throw new DukeException(Ui.line + invalidDeadlineMessage + Ui.line);
                        }

                        LocalDateTime parsedDate;
                        try {
                            parsedDate = LocalDateTime.parse(matcher.group(4),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        } catch (DateTimeParseException e) {
                            throw new DukeException(Ui.line + invalidDeadlineMessage + Ui.line);
                        }

                        newTask = new Deadline(matcher.group(2), parsedDate);
                        tasks.add(newTask);
                        ui.showTaskAdded(newTask, tasks.size());
                        storage.saveTasks(tasks);
                        break;
                    case "event":
                        if (matcher.group(2) == null
                                || matcher.group(3) == null
                                || !matcher.group(3).equals("/from")
                                || matcher.group(4) == null
                                || matcher.group(5) == null
                                || !matcher.group(5).equals("/to")
                                || matcher.group(6) == null) {
                            throw new DukeException(Ui.line + invalidEventMessage + Ui.line);
                        }

                        newTask = new Event(matcher.group(2), matcher.group(4), matcher.group(6));
                        tasks.add(newTask);
                        ui.showTaskAdded(newTask, tasks.size());
                        storage.saveTasks(tasks);
                        break;
                    case "delete":
                        if (matcher.group(2) == null) {
                            throw new DukeException(Ui.line + invalidDeleteMessage + Ui.line);
                        }

                        index = Integer.parseInt(matcher.group(2));

                        if (index < 1 || index > tasks.size()) {
                            throw new DukeException(Ui.line + invalidIndexMessage + Ui.line);
                        }

                        Task removed = tasks.remove(index - 1);
                        ui.showTaskDeleted(removed, tasks.size());
                        storage.saveTasks(tasks);
                        break;
                    default:
                        throw new DukeException(Ui.line + invalidInputMessage + Ui.line);
                }
            } catch (DukeException e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
