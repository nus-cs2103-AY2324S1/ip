import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a chatbot named Anya
 */
public class Anya {
    final static String LINE = "\n____________________________________________________________\n";
    static ArrayList<Task> tasks = new ArrayList<>();
    static String savedFile = "./data/Anya.txt";
    enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    enum TaskType {
        TODO, DEADLINE, EVENT, UNKNOWN
    }
    public static void main(String[] args) {
        greet();
        loadFile(savedFile);
        start();
        exit();
    }

    public static void greet() {
        System.out.println(LINE);
        System.out.println("    Hello! I'm Anya Forger\n"
                         + "    What can I do for you?\n"
                         + "    Enter a command: list/mark/unmark/todo/deadline/event/bye\n");
        System.out.println(LINE);
    }

    public static void exit() {
        System.out.println(LINE);
        System.out.println("    Bye. Hope to see you again soon!\n");
        System.out.println(LINE);
    }

    public static void start() {
        Scanner sc = new Scanner(System.in);

        scan:
        while (true) {
            try {
                String[] arguments = sc.nextLine().split(" ", 2);
                Command command = getCommand(arguments[0]);
                String details;
                if (arguments.length == 1) {
                    details = "";
                } else {
                    details = arguments[1];
                }

                switch (command) {
                    case BYE:
                        saveFile(savedFile);
                        break scan;
                    case LIST:
                        list();
                        break;
                    case MARK: {
                        // Error: No argument or Multiple arguments provided
                        if (details.isEmpty() || details.split(" ").length != 1) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please only input ONE integer after the word mark!"
                                    + LINE);
                        }
                        // Error: Argument provided is not a number
                        try {
                            Integer.parseInt(details);
                        } catch (NumberFormatException e) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please only input INTEGERs after the word mark!"
                                    + LINE);
                        }
                        // Error: Argument provided is not within task numbers
                        int taskNumber = Integer.parseInt(details);
                        if (taskNumber < 1 || taskNumber > tasks.size()) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! I don't see a task with the number:" + taskNumber
                                    + LINE);
                        }
                        // Error: Argument provided is already Done (Future implementation)

                        Task t = tasks.get(taskNumber - 1);
                        t.markAsDone();

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've marked this task as Done:");
                        System.out.println("    " + t);
                        System.out.println(LINE);
                        break;
                    }
                    case UNMARK: {
                        // Error: No argument or Multiple arguments provided
                        if (details.isEmpty() || details.split(" ").length != 1) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please only input ONE integer after the word unmark!"
                                    + LINE);
                        }
                        // Error: Argument provided is not a number
                        try {
                            Integer.parseInt(details);
                        } catch (NumberFormatException e) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please only input INTEGERs after the word unmark!"
                                    + LINE);
                        }
                        // Error: Argument provided is not within task numbers
                        int taskNumber = Integer.parseInt(details);
                        if (taskNumber < 1 || taskNumber > tasks.size()) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! I don't see a task with the number:" + taskNumber
                                    + LINE);
                        }

                        Task t = tasks.get(taskNumber - 1);
                        t.markAsNotDone();

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've marked this task as Not Done:");
                        System.out.println("    " + t);
                        System.out.println(LINE);
                        break;
                    }
                    case TODO: {
                        // Error: No argument provided
                        if (details.isEmpty()) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please input a description after the word todo!"
                                    + LINE);
                        }

                        Task t = new Todo(details);
                        tasks.add(t);

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've added this task:");
                        System.out.println("    " + t);
                        System.out.println("    Now you have " + tasks.size() + " tasks in the list!");
                        System.out.println(LINE);
                        break;
                    }
                    case DEADLINE: {
                        // Error: No argument or wrong no of arguments provided
                        String[] info = details.split("/by");
                        if (details.isEmpty() || info.length != 2) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please input in the following format: "
                                    + "    deadline <taskName> /by <deadline>"
                                    + LINE);
                        }

                        String taskName = info[0].trim();
                        String deadline = info[1].trim();
                        LocalDateTime deadlineDate = convertStringToDate(deadline);
                        Task t = new Deadline(taskName, deadlineDate);
                        tasks.add(t);

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've added this task:");
                        System.out.println("    " + t);
                        System.out.println("    Now you have " + tasks.size() + " tasks in the list!");
                        System.out.println(LINE);
                        break;
                    }
                    case EVENT: {
                        // Error: No argument provided
                        if (details.isEmpty()) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please input in the following format:\n"
                                    + "    event <taskName> /from <startTime> /to <endTime>"
                                    + LINE);
                        }
                        // Error: Does not contain /from and /to
                        if (!details.contains("/from") && !details.contains("/to")) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please input in the following format:\n"
                                    + "    event <taskName> /from <startTime> /to <endTime>"
                                    + LINE);
                        }

                        String taskName = details.split("/from")[0].trim();
                        String startTime = details.split("/from")[1].trim().split("/to")[0].trim();
                        LocalDateTime startTimeDate = convertStringToDate(startTime);
                        String endTime = details.split("/to")[1].trim();
                        LocalDateTime endTimeDate = convertStringToDate(endTime);
                        Task t = new Event(taskName, startTimeDate, endTimeDate);
                        tasks.add(t);

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've added this task:");
                        System.out.println("    " + t);
                        System.out.println("    Now you have " + tasks.size() + " tasks in the list!");
                        System.out.println(LINE);
                        break;
                    }
                    case DELETE: {
                        // Error: No argument or Multiple arguments provided
                        if (details.isEmpty() || details.split(" ").length != 1) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please input in the following format:\n"
                                    + "    delete <taskNumber>"
                                    + LINE);
                        }
                        // Error: Argument provided is not a number
                        try {
                            Integer.parseInt(details);
                        } catch (NumberFormatException e) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please only input INTEGERs after the word unmark!"
                                    + LINE);
                        }
                        // Error: Argument provided is not within task numbers
                        int taskNumber = Integer.parseInt(details);
                        if (taskNumber < 1 || taskNumber > tasks.size()) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! I don't see a task with the number:" + taskNumber
                                    + LINE);
                        }

                        Task t = tasks.get(taskNumber - 1);
                        tasks.remove(t);

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've removed this task as Not Done:");
                        System.out.println("    " + t);
                        System.out.println("    Now you have " + tasks.size() + " tasks in the list!");
                        System.out.println(LINE);
                        break;
                    }
                    default:
                        System.out.println(LINE);
                        System.out.println("☹ Waku waku!!! I'm sorry, but I don't know what that means (yet) :( ");
                        System.out.println(LINE);
                        break;
                }
            } catch (AnyaException e) {
                System.out.println(e);
            }
        }
    }

    public static void list() {
        System.out.println(LINE);
        System.out.println("    Waku waku!\n"
                         + "    Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    private static Command getCommand(String input) {
        if (input.equals("bye")) return Command.BYE;
        if (input.equals("list")) return Command.LIST;
        if (input.equals("mark")) return Command.MARK;
        if (input.equals("unmark")) return Command.UNMARK;
        if (input.equals("todo")) return Command.TODO;
        if (input.equals("deadline")) return Command.DEADLINE;
        if (input.equals("event")) return Command.EVENT;
        if (input.equals("delete")) return Command.DELETE;
        return Command.UNKNOWN;
    }
    private static TaskType getTaskType(String input) {
        if (input.equals("T")) return TaskType.TODO;
        if (input.equals("D")) return TaskType.DEADLINE;
        if (input.equals("E")) return TaskType.EVENT;
        return TaskType.UNKNOWN;
    }

    private static void loadFile(String filePath) {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();

            // Check if directory exists
            if (directory.mkdir()) {
                System.out.println("Directory was not found. New directory " + directory.getName() + " is created");
            }

            if (file.createNewFile()) {
                System.out.println("File is not found. New File created: " + file.getName());
            } else {
                System.out.println("File already exists. Your data is loaded");
                readFile(file);
            }
        } catch (Exception e) {
            System.out.println("An error occurred. " + e.getMessage());
        }
    }

    private static void readFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String[] arguments = sc.nextLine().split("\\|");

            TaskType taskType = getTaskType(arguments[0].trim());
            boolean isDone = arguments[1].trim().equals("1");
            String description = arguments[2].trim();
            LocalDateTime by = LocalDateTime.now();
            LocalDateTime from = LocalDateTime.now();
            LocalDateTime to = LocalDateTime.now();

            if (arguments.length == 4) {
                by = convertStringToDate(arguments[3].trim());
            } else if (arguments.length == 5) {
                from = convertStringToDate(arguments[3].trim());
                to = convertStringToDate(arguments[4].trim());
            }

            Task task;

            switch (taskType) {
            case TODO:
                task = new Todo(description);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            case DEADLINE:
                task = new Deadline(description, by);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            case EVENT:
                task = new Event(description, from, to);
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
                break;
            default:
                System.out.println("Unknown task type.");
                break;
            }
        }
    }

    private static void saveFile(String filePath) {
        // Overwrite everything - clear the file
        try {
            writeToFile(filePath, "");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < tasks.size(); i++) {
            String text = "";
            Task task = tasks.get(i);
            try {
                text += task.formatToSave() + System.lineSeparator();
                appendToFile(filePath, text);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void writeToFile(String filePath, String text) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(text);
        fw.close();
    }

    private static void appendToFile(String filePath, String text) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(text);
        fw.close();
    }

    private static LocalDateTime convertStringToDate(String dateString) {
        return LocalDateTime.parse(dateString);
    }
}
