import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Duke {
    private static final String CHAT_BOT_NAME = "Genos";
    private static final HashMap<String, Command> commandMap = new HashMap<>(
            Map.of("todo", Command.TODO, "event", Command.EVENT, "mark", Command.MARK,
                    "unmark", Command.UNMARK, "list", Command.LIST, "bye", Command.EXIT,
                    "deadline", Command.DEADLINE)
    );
    private ArrayList<Task> tasks;
    private enum Command {
        MARK, UNMARK, LIST, EXIT, TODO, DEADLINE, EVENT, INVALID
    }

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    private static void greet() {
        System.out.println("Hello I'm " + Duke.CHAT_BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println("Please type your command below, I will store what you said");
        System.out.println("Usage: \"list\" to see the list of text stored, \"bye\" to exit");
        System.out.println("\"mark [number]\" to mark task no. [number] to be done, "
                + "\"unmark [number]\" to mark it as undone");
        System.out.println("\"todo [description]\" to add todo, \"event [description] /from [date] /to[date]\""
                + " to add event, \"deadline [description] /by [date]\" to add deadline");
    }

    private void converse() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String text = sc.nextLine();
            if (text.isEmpty()) {
                System.out.println("Sorry, empty command is not supported");
                continue;
            }
            Command cmd = textToCommand(text);

            // non tasks command.
            if (cmd == Command.EXIT) {
                System.out.println("    Goodbye, Hope to see you again soon.");
                return;
            } else if (cmd == Command.LIST) {
                this.listTasks();
                continue;
            } else if (cmd == Command.INVALID) {
                System.out.println("    Command given is invalid! You must start with a "
                        + "todo/event/deadline to add tasks, "
                        + "or list/mark/unmark/bye for other purposes");
                continue; // retype commands
            }

            // adding tasks or marking tasks
            try {
                if (text.split(" ").length == 1) {
                    throw new RuntimeException("    Description cannot be empty!");
                }
                Task task;
                switch (cmd) {
                    case MARK:
                        int num = Integer.parseInt(text.substring(5));
                        // index out of bounds error will be caught below
                        task = this.tasks.get(num-1);
                        task.doTask();
                        System.out.println("    I've marked this task as done:");
                        break;
                    case UNMARK:
                        int num2 = Integer.parseInt(text.substring(7));
                        // index out of bounds error will be caught below
                        task = this.tasks.get(num2-1);
                        task.undoTask();
                        System.out.println("    I've marked this task as not done yet:");
                        break;
                    case TODO:
                        String description = text.substring(5);
                        if (description.isEmpty()) {
                            throw new RuntimeException("Description of todo cannot be empty");
                        }
                        task = new Todo(description);
                        this.tasks.add(task);
                        System.out.println("    Added Todo to the list of tasks:");
                        break;
                    case EVENT:
                        String[] parts = (text.substring(6)).split("/");
                        if (parts.length != 3 || !parts[1].startsWith("from ") ||
                            !parts[2].startsWith("to ")) {
                            throw new RuntimeException("    Invalid format for adding an event! "
                            + "Please enter in this format:\n"
                            + "event [description] /from [date] /to [date]");
                        }
                        String fromTime = parts[1].substring(5); // remove the "from "
                        String toTime = parts[2].substring(3);  // remove the "to "
                        task = new Event(parts[0], fromTime, toTime);
                        this.tasks.add(task);
                        System.out.println("    Added Event to the list of tasks:" );
                        break;
                    case DEADLINE:
                        String[] parts2 = (text.substring(9)).split("/");
                        if (parts2.length != 2 || !parts2[1].startsWith("by ")) {
                            throw new RuntimeException("    Invalid format for adding a deadline! "
                                    + "Please enter in this format:\n"
                                    + "deadline [description] /by [date]");
                        }
                        String by = parts2[1].substring(3); // remove the "by "
                        task = new Deadline(parts2[0], by);
                        this.tasks.add(task);
                        System.out.println("    Added Deadline to the list of tasks:");
                        break;

                    default:
                        throw new RuntimeException("    There seems to be some error here");
                }
                System.out.println("    " + task.toString());
                System.out.println("    You currently have " + this.tasks.size() + " tasks in the list.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number. Please enter a valid number to mark/unmark");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The task number you are trying to mark/unmark "
                        + "does not exist yet.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + this.tasks.get(i));
        }
    }

    private Command textToCommand(String text) {
        String cmd = text.split(" ")[0];
        return Duke.commandMap.getOrDefault(cmd, Command.INVALID);
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Duke.greet();
        bot.converse();
    }
}
