import java.util.Scanner;
import java.util.ArrayList;

public class Remy {

    private static String divider = "____________________________________________________________\n";
    private static String shortDivider = "_____________";

    public static void main(String[] args) {

        String welcomeContent =
                "I'm Remy, and it is NOT nice to see you.\n" +
                        "Faster tell me what you want and go away.";

        String exitMessage = "Hope to never see you again!\n" + divider;


        ArrayList<Task> taskList = new ArrayList(100);

        printSandwichContent(welcomeContent, "long");
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            String taskType = parseTaskType(input);
            try {
                if (taskType.equals("bye")) {
                    System.out.println(exitMessage);
                    break;
                } else if (taskType.equals("list")) {
                    System.out.println(shortDivider);
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + taskList.get(i));
                    }
                    System.out.println(shortDivider);
                } else if (taskType.equals("mark")) {
                    // Marks item as done
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index >= 0 && index < taskList.size()) {
                        taskList.get(index).markAsDone();
                        String content = "Done. You happy?\n" + taskList.get(index).toString();
                        printSandwichContent(content, "short");
                    } else {
                        throw new ChatbotException("no such item lah.");
                    }
                } else if (taskType.equals("unmark")) {
                    // Marks item as undone
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= 0 && index < taskList.size()) {
                        taskList.get(index).markAsUndone();
                        String content = "Done. You happy?\n" + taskList.get(index).toString();
                        printSandwichContent(content, "short");
                    } else {
                        throw new ChatbotException("no such item lah.");
                    }
                } else if (taskType.equals("delete")) {
                    // Marks item as undone
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= 0 && index < taskList.size()) {
                        taskList.get(index).markAsUndone();
                        String content = "Done. You happy?\n" + taskList.get(index).toString();
                        printSandwichContent(content, "short");
                    } else {
                        throw new ChatbotException("no such item lah.");
                    }
                } else if (taskType.equals("todo")) {
                    if (input.length() < 5) throw new ChatbotException("missing info lah.");
                    String description = input.substring(5);
                    Todo temp = new Todo(description);
                    taskList.add(temp);
                    addTask(temp, taskList.size());
                } else if (taskType.equals("deadline")) {
                    if (input.length() < 9) throw new ChatbotException("missing info lah.");
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length == 2) {
                        Deadline temp = new Deadline(parts[0], parts[1]);
                        taskList.add(temp);
                        addTask(temp, taskList.size());
                    } else {
                        throw new ChatbotException("wrong format lah.");
                    }
                } else if (taskType.equals("event")) {
                    if (input.length() < 7) throw new ChatbotException("missing info lah.");
                    String[] parts = input.substring(6).split(" /from | /to ");
                    if (parts.length == 3) {
                        Event temp = new Event(parts[0], parts[1], parts[2]);
                        taskList.add(temp);
                        addTask(temp, taskList.size());
                    } else {
                        // printSandwichContent("Wrong format lah.", "short");
                        throw new ChatbotException("wrong format lah.");
                    }
                } else {
                    throw new ChatbotException("that's not a command.");
                }
            } catch (ChatbotException e) {
                printSandwichContent(e.toString(), "long");
            }
        }
    }

    public static String parseTaskType(String input) {
        if (input.equalsIgnoreCase("list")) {
            return "list";
        } else if (input.toLowerCase().startsWith("mark")) {
            return "mark";
        } else if (input.toLowerCase().startsWith("unmark")) {
            return "unmark";
        } else if (input.toLowerCase().startsWith("todo")) {
            return "todo";
        } else if (input.toLowerCase().startsWith("deadline")) {
            return "deadline";
        } else if (input.toLowerCase().startsWith("event")) {
            return "event";
        } else if (input.toLowerCase().startsWith("delete")) {
            return "delete";
        } else if (input.toLowerCase().equals("bye")) {
            return "bye";
        } else {
            return "invalid";
        }
    }

    public static void printSandwichContent(String content, String dividerType) {

        if (dividerType == "short") {
            System.out.println(shortDivider);
            System.out.println(content);
            System.out.println(shortDivider);
        } else {
            System.out.println(divider);
            System.out.println(content);
            System.out.println(divider);
        }
    }

    public static void addTask(Task task, int num) {
        String content = "Added, now scram.\n" +
                task.toString() + "\n" +
                "Now you have " + num + " tasks in the list.";
        printSandwichContent(content, "short");
    }
}
