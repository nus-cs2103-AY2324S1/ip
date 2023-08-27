import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String NAME = "Kevin";
    private static final int SPLITTER_LENGTH = 50;
    private static String DATA_FILE_PATH = "./data/duke.txt";
    private static final Storage storage = new Storage(DATA_FILE_PATH);

    private static void lineSplitter() {
        for (int i = 0; i < Duke.SPLITTER_LENGTH; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void greet() {
        lineSplitter();
        System.out.println("Hello! I'm " + Duke.NAME + "\n" + "What can I do for you?\n");
    }

    private static void bye() {
        lineSplitter();
        System.out.println("Bye. Hope to see you again soon!\n");
        lineSplitter();
    }

    private static Task addTask(String input) throws CommandNotRecognizedException, NoCommandDetailException, TimeParsingException{
        String[] splittedInput = input.split(" ", 2); // Split into two parts: command and argument
        String command = splittedInput[0].toLowerCase();
        TaskType taskType = TaskType.valueOf(command.toUpperCase());


        switch (taskType) {
        case TODO:
            try {
                return new ToDo(splittedInput[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoCommandDetailException("OOPS!!! The description of a todo cannot be empty.");
            }
        case DEADLINE:
            try {
                String[] deadlineParts = splitByDelimiter(splittedInput[1], " /by ");
                return new Deadline(deadlineParts[0], deadlineParts[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoCommandDetailException("OOPS!!! The description of a deadline cannot be empty.");
            }
        case EVENT:
            try {
                String[] eventParts = splitByDelimiter(splittedInput[1], " /from ");
                String eventName = eventParts[0];
                String[] eventTimes = splitByDelimiter(eventParts[1], " /to ");
                return new Event(eventName, eventTimes[0], eventTimes[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoCommandDetailException("OOPS!!! The description of a event cannot be empty.");
            }
        default:
            throw new CommandNotRecognizedException("I'm sorry, but I don't know what that means :-(");
        }
    }

    // A helper method to handle splitting by a specific delimiter
    private static String[] splitByDelimiter(String input, String delimiter) {
        return input.split(delimiter, 2); // Split into two parts by the specified delimiter
    }

    public static void echo(List<Task> list) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine().trim();
            String[] splittedInput = input.split(" ", 2);
            String command = splittedInput[0].toLowerCase();

            switch (command) {
            case "bye":
                sc.close();
                return;

            case "list":
                lineSplitter();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
                lineSplitter();
                break;

            case "mark":
                int markIndex = Integer.parseInt(splittedInput[1]) - 1;
                list.get(markIndex).setDone();
                lineSplitter();
                System.out.println("I've marked this task as done:\n  " + list.get(markIndex));
                lineSplitter();
                break;

            case "delete":
                int deleteIndex = Integer.parseInt(splittedInput[1]) - 1;
                Task deletedTask = list.remove(deleteIndex);
                lineSplitter();
                System.out.println("Deleted this task:\n  " + deletedTask + "\n" + list.size() + " tasks in the list");
                lineSplitter();
                break;
            case "unmark":
                int unmarkIndex = Integer.parseInt(splittedInput[1]) - 1;
                list.get(unmarkIndex).setUndone();
                lineSplitter();
                System.out.println("I've marked this task as undone:\n  " + list.get(unmarkIndex));
                lineSplitter();
                break;

            default:
                try {
                    Task task = addTask(input);
                    if (task != null) {
                        list.add(task);
                        lineSplitter();
                        System.out.println("added: " + task + " \n" + list.size() + " tasks in the list");
                        lineSplitter();
                        storage.save(list);
                    }
                    break;
                } catch (CommandNotRecognizedException e) {
                    System.out.println(e.getMessage());
                    break;
                } catch (NoCommandDetailException e) {
                    System.out.println(e.getMessage());
                    break;
                } catch (StorageException e) {
                    System.out.println(e.getMessage());
                    break;
                } catch (TimeParsingException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
        try {
            list = storage.load();
        } catch (StorageException e) {
            System.out.println(e.getMessage());
            bye();
        }
        greet();
        echo(list);
        bye();
    }

    private TaskType getTaskType(String input) {
        return TaskType.valueOf(input.toUpperCase());
    }


    private enum TaskType {
        TODO, DEADLINE, EVENT
    }
}
