import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private TaskList fullList;
    private final FileStorage fileStorage;
    private String chatbot = "chuababyy chatbot";
    public void line() {
        System.out.println(line);
    }
    private static String line = "------------------------------------";
    private static String filePath = "./data/duke.txt";

    private static final String commands =
            line + "\n"
            + "List of commands\n"
            + "1. todo [description]\n"
            + "2. deadline [description] /by [deadline]\n"
            + "3. event [description] /from [start date] /to [end date]\n"
            + "4. mark [item_number]\n"
            + "5. unmark [item_number]\n"
            + "6. delete [item_number]\n"
            + "7. list\n"
            + "8. bye\n"
            + line ;

    public enum CommandType {
        TODO, DEADLINE, EVENT, MARK,
        UNMARK, DELETE, LIST, BYE, UNKNOWN, EMPTY
    }

    public static CommandType parseCommand(String input) {
        if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.equals("mark")) {
            return CommandType.MARK;
        } else if (input.equals("unmark")) {
            return CommandType.UNMARK;
        } else if (input.equals("todo")) {
            return CommandType.TODO;
        } else if (input.equals("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.equals("event")) {
            return CommandType.EVENT;
        } else if (input.equals("delete")) {
            return CommandType.DELETE;
        } else if (input.equals("bye")) {
            return CommandType.BYE;
        } else if (input.equals("")) {
            return CommandType.EMPTY;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    public Duke(String filePath) {
        this.fileStorage = new FileStorage(filePath);
        try {
            this.fullList  = new TaskList(fileStorage.loadFiles());
        } catch (FileCorruptedException e) {
            System.out.println("Saved tasks is corrupted. Please start adding new tasks");
            this.fullList = new TaskList();
        } catch (FileNoExistingTasksException e) {
            System.out.println("No saved tasks! Please start adding new tasks");
            this.fullList = new TaskList();
        } catch (FileLoadException e) {
            System.out.println("Error when reading saved tasks. Please start adding new tasks");
            this.fullList = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        line();
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?");
        line();

        while (true) {
            String userInput = scanner.nextLine().trim();
            String[] userInputParts = userInput.split(" ", 2);
            String command_user = userInputParts[0];
            CommandType command = parseCommand(command_user);

            switch (command) {
                case EMPTY:
                    line();
                    System.out.println("Item to be added cannot be empty");
                    line();
                    continue;

                case UNKNOWN:
                    System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                    continue;

                case BYE:
                    fileStorage.saveTasks(fullList);
                    line();
                    System.out.println("Bye. Hope to see you again soon!");
                    line();
                    break;

                case LIST:
                    if (userInputParts.length > 1) {
                        System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                        continue;
                    }
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(fullList.toString());
                    continue;

                case MARK:
                    String[] split_index = userInput.split(" ");
                    if (split_index.length <= 1 || split_index.length > 2) {
                        System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                        continue;
                    }
                    int index = Integer.parseInt(split_index[1]) - 1;
                    fullList.markItem(index);
                    continue;

                case UNMARK:
                    String[] split_index_unmark = userInput.split(" ");
                    if (split_index_unmark.length <= 1 || split_index_unmark.length > 2) {
                        System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                        continue;
                    }
                    int index_unmark = Integer.parseInt(split_index_unmark[1]) - 1;
                    fullList.unMarkItem(index_unmark);
                    continue;

                case TODO:
                    if (userInputParts.length <= 1) {
                        System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                        continue;
                    }
                    ToDos toDo = new ToDos(userInputParts[1].trim());
                    fullList.addToList(toDo);
                    continue;

                case DEADLINE:
                    String[] details = userInputParts[1].split("/by");
                    String description = details[0].trim();

                    if (details.length <= 1) {
                        System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                        continue;
                    }

                    String by = details[1].trim();
                    Deadline deadline = new Deadline(description, by);
                    fullList.addToList(deadline);
                    continue;

                case EVENT:
                    String[] det = userInputParts[1].split("/from");
                    if (det.length <=1) {
                        System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                        continue;
                    }
                    String[] dateParts = det[1].trim().split("/to");
                    if (dateParts.length <=1) {
                        System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                        continue;
                    }

                    String descr = det[0].trim();
                    String from = dateParts[0].trim();
                    String to = dateParts[1].trim();

                    Event event = new Event(descr, from, to);
                    fullList.addToList(event);
                    continue;

                case DELETE:
                    String[] split_i = userInput.split(" ");
                    if (split_i.length <= 1 || split_i.length > 2) {
                        System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
                        continue;
                    }
                    int ind = Integer.parseInt(split_i[1]) - 1;
                    fullList.deleteFromList(ind);
                    continue;
            }
            break;
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
