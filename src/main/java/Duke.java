import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
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
        DELETE
    }

    // CONSTANTS
    private static final String LINE = "_______________________________________";
    private static final String DIR_NAME = "./data";
    private static final String FILE_NAME = "duke.txt";
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .optionalStart()
            .appendPattern(" HH:mm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    private static void sendIntroduction() {
        String logo = "                     _                 _      \n" +
                " _ __ ___  ___ _ __ (_)_ __ ___  _ __ (_)_  __\n" +
                "| '__/ _ \\/ __| '_ \\| | '__/ _ \\| '_ \\| \\ \\/ /\n" +
                "| | |  __/\\__ \\ |_) | | | | (_) | | | | |>  < \n" +
                "|_|  \\___||___/ .__/|_|_|  \\___/|_| |_|_/_/\\_\\\n" +
                "              |_|                             ";
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm your personal AI");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static List<Task> loadTasksFromStorage(String dirName, String fileName) {
        List<Task> tasks = new ArrayList<>();
        File file = new File(dirName + File.separator + fileName);

        // scan for the storage file
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {

                String curr = s.nextLine();
                String[] segments = curr.split(" \\| ");

                // check for the correct format - minimum 3 different segments
                if ((!segments[0].equals("T") && !segments[0].equals("D")
                        && !segments[0].equals("E")) || segments.length < 3) {
                    s.close(); // need to close scanner otherwise cannot replace file
                    throw new UnrecognisedFormatException();
                }

                boolean isDone = segments[1].equals("1");

                if (segments[0].equals("T")) { // To do task
                    tasks.add(new ToDo(segments[2], isDone));
                } else if (segments[0].equals("D")) { // Deadline task
                    tasks.add(new Deadline(segments[2], LocalDateTime.parse(segments[3]), isDone));
                } else { // Event task
                    String[] times = segments[3].split("--");
                    tasks.add(new Event(segments[2],
                            LocalDateTime.parse(times[0]),
                            LocalDateTime.parse(times[1]),
                            isDone));
                }

            }
        } catch (FileNotFoundException e) { // File does not exist
            try {
                if (new File(dirName).mkdir()) {
                    System.out.println("Sorry, directory does not exist. Creating now...");
                }
                if (file.createNewFile()) {
                    System.out.println("Sorry, file does not exist. Creating now...");
                }
            } catch (Exception error) {
                System.out.println("Error... Unable to create files");
            }

        } catch (UnrecognisedFormatException e) { // File is corrupted
            try {
                if (file.delete()) {
                    System.out.println("Deleting corrupted file...");

                    if (file.createNewFile()) {
                        System.out.println("Replacing file now...");
                    }

                }
            } catch (Exception error) {
                System.out.println("Error... Unable to create new file...");
            }
        }
        return tasks;
    }

    private static void writeFile(String filePath, String text) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(text);
            fw.close();
        } catch (Exception e) {
            System.out.println("Sorry... Unable to store tasks...");
        }

    }

    public static void main(String[] args) {
        List<Task> tasks = loadTasksFromStorage(DIR_NAME, FILE_NAME);

        Scanner scanner = new Scanner(System.in);

        sendIntroduction();

        try {
            while (true) {
                String input = scanner.nextLine();
                System.out.println(LINE);

                // Use Regex to extract the first word even with preceding whitespace
                String command = input.replaceAll("^\\W*\\b(\\w+).*", "$1").toUpperCase();

                try { // In case there are exceptions
                    // User wants to end the chatbot
                    if (command.equals(Command.BYE.name())) {

                        // store the data into the storage
                        StringBuilder textForStorage = new StringBuilder();
                        for (Task task : tasks) {
                            textForStorage.append(task.formatForStorage()).append("\n");
                        }

                        writeFile(DIR_NAME + File.separator + FILE_NAME, textForStorage.toString());

                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println(LINE);
                        break;
                    }

                    // List out all the tasks in the chatbot
                    if (command.equals(Command.LIST.name())) {

                        if (tasks.size() == 0) {
                            throw new EmptyTaskListException();
                        }

                        for (int i = 0; i < tasks.size(); i++) {
                            Task task = tasks.get(i);
                            System.out.println(String.format("%d. %s", i + 1, task));
                        }
                        System.out.println(LINE);
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
                        System.out.println("Got it!. I've added this task:");
                        System.out.println(" " + task);
                        System.out.printf("Now you have %d tasks in the list%n", tasks.size());
                        System.out.println(LINE);
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
                        System.out.println("Got it!. I've added this task:");
                        System.out.println(" " + task);
                        System.out.printf("Now you have %d tasks in the list%n", tasks.size());
                        System.out.println(LINE);
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

                        Task task = new Event(desc_time[0],
                                LocalDateTime.parse(times[0], FORMATTER),
                                LocalDateTime.parse(times[1], FORMATTER));
                        tasks.add(task);
                        System.out.println("Got it!. I've added this task:");
                        System.out.println(" " + task);
                        System.out.printf("Now you have %d tasks in the list%n", tasks.size());
                        System.out.println(LINE);
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

                    throw new UnknownCommandException(input);

                } catch (UnknownTimeException | UnknownCommandException | EmptyTaskListException |
                         NoDescriptionException | NoIndexException e) {
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
