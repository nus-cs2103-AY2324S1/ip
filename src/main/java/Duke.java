import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Donovan Chan Jia Jun
 */
public class Duke {
    /**
     * Temporary data storage to store user text.
     */
    static ArrayList<Task> storage;
    static String line = "---------------------------------------------------------------------------------------------";

    /**
     * Starting point of the bot.
     * Says hello - Carries out data storage for user text - Says goodbye
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        // Initialise the data storage
        Duke.storage = new ArrayList<>();
        String chatbotName = "notDuke";
        String introMessage = "Hello! I'm " + chatbotName + "\n"
                        + "What can I do for you?";
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.printf("%s\n%s\n%s\n", Duke.line, introMessage, Duke.line);
        Duke.echo();
        System.out.printf("%s\n%s\n", exitMessage, Duke.line);
    }

    /**
     * Creates the tasks based on String input.
     *
     * @param input String input by user
     * @return Task Can be Events, Deadlines, Todos
     * @throws Exception
     */
    public static Task createTask(String input) throws Exception {
        String[] arrStrings = input.split("\\s+");
        String command = arrStrings[0];
        String name = "";
        if (command.equals("deadline")) {
            String deadline = "";
            boolean completedName = false;
            for (int i = 1; i < arrStrings.length; i ++) {
                if (arrStrings[i].equals("/by")) {
                    completedName = true;
                    continue;
                }
                if (completedName) {
                    deadline += arrStrings[i] + " ";
                } else {
                    name += arrStrings[i] + " ";
                }
            }
            return new Deadlines(name.substring(0, name.length() - 1), deadline.substring(0, deadline.length() - 1));
        } else if (command.equals("todo")) {
            if (arrStrings.length == 1) {
                throw new Exception("OOPS!!! The description of a todo cannot be empty.");
            }
            for (int i = 1; i < arrStrings.length; i ++) {
                name += arrStrings[i] + " ";
            }
            return new Todos(name.substring(0, name.length() - 1));
        } else if (command.equals("event")) {
            String from = "";
            String to = "";
            boolean completedName = false;
            boolean completedFrom = false;
            for (int i = 1; i < arrStrings.length; i ++) {
                if (arrStrings[i].equals("/from")) {
                    completedName = true;
                } else if (arrStrings[i].equals("/to")) {
                    completedFrom = true;
                } else if (!completedFrom && completedName) {
                    from += arrStrings[i] + " ";
                } else if (completedFrom && completedName) {
                    to += arrStrings[i] + " ";
                } else {
                    name += arrStrings[i] + " ";
                }
            }
            return new Events(name.substring(0, name.length() - 1), from.substring(0, from.length() - 1), to.substring(0, to.length() - 1));
        } else {
            throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Encapsulates the Handling and storing of user input.
     */
    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            String command = userInput.split("\\s+")[0];
            int choice;
            switch (command) {
                case "list":
                    int counter = 0;
                    System.out.println("Here are the tasks in your list:");
                    for (Task task : Duke.storage) {
                        counter ++;
                        System.out.printf("%d.%s\n", counter, task.toString());
                    }
                    break;
                case "mark":
                    choice = Integer.parseInt(userInput.split("\\s+")[1]);
                    Duke.storage.get(choice - 1).markDone();
                    System.out.printf("Nice! I've marked this task as done:\n" +
                            "  %s\n", Duke.storage.get(choice - 1).toString());
                    break;
                case "unmark":
                    choice = Integer.parseInt(userInput.split("\\s+")[1]);
                    Duke.storage.get(choice - 1).markUndone();
                    System.out.printf("OK, I've marked this task as not done yet:\n" +
                            "  %s\n", Duke.storage.get(choice - 1).toString());
                    break;
                case "delete":
                    choice = Integer.parseInt(userInput.split("\\s+")[1]);
//                    System.out.println(Duke.storage.size());
                    Task removedTask = Duke.storage.remove(choice - 1);
                    System.out.printf("Noted. I've removed this task:\n" +
                                        "  %s\n" +
                                        "Now you have %d tasks in the list.\n"
                                        , removedTask.toString(), Duke.storage.size());
                    break;
                default:
                    Task task = null;
                    try {
                        task = createTask(userInput);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    if (task != null) {
                        Duke.storage.add(task);
                        System.out.printf("Got it. I've added this task:\n" +
                                "  %s\n" +
                                "Now you have %d tasks in the list.\n", task.toString(), Duke.storage.size());
                    }
            }
            System.out.println(Duke.line);
            userInput = scanner.nextLine();
        }
    }
}
