import java.util.Scanner;
public class Duke {
    static Task[] storage;
    static int storagePointer;
    static int totalTasks;

    public static void main(String[] args) {
        // Initialise the data storage
        Duke.storage = new Task[100];
        Duke.storagePointer = 0;
        Duke.totalTasks = 0;
        String chatbotName = "notDuke";
        String introMessage = "Hello! I'm " + chatbotName + "\n"
                        + "What can I do for you?\n";
        String exitMessage = "Bye. Hope to see you again soon!\n";
        System.out.println(introMessage);
        Duke.echo();
        System.out.println(exitMessage);
    }

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
                throw new Exception("OOPS!!! The description of a todo cannot be empty");
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

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            String command = userInput.split("\\s+")[0];
            int choice;
            switch (command) {
                case "list":
                    for (int i = 1; i <= Duke.storagePointer; i++) {
                        Task item = Duke.storage[i - 1];
                        System.out.printf("%d.%s\n", i, item.toString());
                    }
                    userInput = scanner.nextLine();
                    break;
                case "mark":
                    choice = Integer.parseInt(userInput.split("\\s+")[1]);
                    Duke.storage[choice - 1].markDone();
                    System.out.printf("Nice! I've marked this task as done:\n" +
                            "  %s\n", Duke.storage[choice - 1].toString());
                    userInput = scanner.nextLine();
                    break;
                case "unmark":
                    choice = Integer.parseInt(userInput.split("\\s+")[1]);
                    Duke.storage[choice - 1].markUndone();
                    System.out.printf("OK, I've marked this task as not done yet:\n" +
                            "  %s\n", Duke.storage[choice - 1].toString());
                    userInput = scanner.nextLine();
                    break;
                default:
                    Task task = null;
                    try {
                        task = createTask(userInput);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        echo();
                    }
                    Duke.storage[Duke.storagePointer] = task;
                    Duke.totalTasks ++;
                    Duke.storagePointer ++;
                    System.out.printf("Got it. I've added this task:\n" +
                            "  %s\n" +
                            "Now you have %d tasks in the list.\n", task.toString(), Duke.totalTasks);
                    userInput = scanner.nextLine();
            }
        }
    }
}
