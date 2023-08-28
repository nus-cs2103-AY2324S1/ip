import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> todoList = new ArrayList<>();

        FileHandler taskFile = new FileHandler("duke.txt", "data");
        taskFile.createDirectoryIfNotPresent();
        taskFile.createFileIfNotPresent();

        String logo =
                " ___________________\n" +
                        ":' ,__________,  ':  `.\n" +
                        "| '            `  |    `.\n" +
                        "| |  Dukey     |  |      `.\n" +
                        "| |            |  |        \\\n" +
                        "| |  Hello!!!  |  |         ]\n" +
                        "| |            |  |~~~~~~.  )\n" +
                        "| `,__________,'  |\\__O\\_| ,'\n" +
                        "|    _______      |     \\.`\n" +
                        "|<> [___=___](@)<>|    .'\\\n" +
                        "':________________/__.'   )\n" +
                        "   (____________)        /\n" +
                        "                        /\n" +
                        "              _________/\n" +
                        "  ___________/______\n" +
                        " /''''=========='(@)\\___\n" +
                        " |[][][][][][][][][]|   \\ _______\n" +
                        " |[][][][][][][][][]|    \\__     \\\n" +
                        " |[][][][][][][][][]|    |  \\..  |\n" +
                        " \\------------------/    | ( # ) |\n" +
                        "                         |  '''  |\n" +
                        "                         \\_______/";
        System.out.println(logo);
        System.out.println("Hello! I am Dukey.\n"
                + "What can I do for you?");

        while (true) {
            String response = scanner.nextLine();

            if (response.isEmpty()) {
                System.out.println("You entered nothing! Try again!");
            } else if (response.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (response.equalsIgnoreCase("list")) {
                int i;
                for (i = 1; i < todoList.size() + 1; i++) {
                    System.out.println(i + ". " + todoList.get(i - 1).toString());
                }
                System.out.println(String.format("You have %d task(s) currently in the list", todoList.size()));
            } else if (response.startsWith("mark")) {
                try {
                    String[] parts = response.split(" ");
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    todoList.get(taskIndex).markDone();
                    taskFile.writeListToFile(todoList);
                    System.out.println("Good Job! I have marked this task as done!");
                    System.out.println(todoList.get(taskIndex).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task does not exist! Please try again!");
                } catch (NumberFormatException e) {
                    System.out.println("Please input the correct command");
                }
            } else if (response.startsWith("unmark")) {
                try {
                    String[] parts = response.split(" ");
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    todoList.get(taskIndex).markUndone();
                    taskFile.writeListToFile(todoList);
                    System.out.println("Aw man! I have marked this task as undone. We go again!");
                    System.out.println(todoList.get(taskIndex).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task does not exist! Please try again!");
                } catch (NumberFormatException e) {
                    System.out.println("Please input the correct command");
                }
            } else if (response.startsWith("delete")) {
                try {
                    String[] parts = response.split(" ");
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    Task toRemove = todoList.get(taskIndex);
                    todoList.remove(toRemove);
                    taskFile.writeListToFile(todoList);
                    System.out.println("Okay! I have removed this task from the list");
                    System.out.println(toRemove.toString());
                    System.out.println(String.format("You have %d task(s) currently in the list", todoList.size()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task does not exist! Please try again!");
                } catch (NumberFormatException e) {
                    System.out.println("Please input the correct command");
                }
            } else if (response.startsWith("deadline")) {
                if (response.equals("deadline")) {
                    System.out.println("Task description cannot be empty!");
                } else {
                    try {
                        Deadline newDeadline = new Deadline(response);
                        todoList.add(newDeadline);
                        taskFile.writeListToFile(todoList);
                        System.out.println("Roger! I have added the following task to the list");
                        System.out.println(newDeadline.toString());
                        System.out.println(String.format("You have %d task(s) currently in the list", todoList.size()));
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please specify the deadline!");
                    }
                }
            } else if (response.startsWith("event")) {
                if (response.equals("event")) {
                    System.out.println("Task description cannot be empty!");
                } else {
                    try {
                        Event newEvent = new Event(response);
                        todoList.add(newEvent);
                        taskFile.writeListToFile(todoList);
                        System.out.println("Roger! I have added the following task to the list");
                        System.out.println(newEvent.toString());
                        System.out.println(String.format("You have %d task(s) currently in the list", todoList.size()));
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Please specify both the start and end time!");
                    }
                }
            } else if (response.startsWith("todo")) {
                if (response.equals("todo")) {
                    System.out.println("Task description cannot be empty!");
                } else {
                    ToDo newTodo = new ToDo(response);
                    todoList.add(newTodo);

                    System.out.println("Roger! I have added the following task to the list");
                    System.out.println(newTodo.toString());
                    taskFile.writeListToFile(todoList);
                    System.out.println(String.format("You have %d task(s) currently in the list", todoList.size()));
                }
            } else {
                System.out.println("Sorry! Don't know what that is!");
            }

        }
        scanner.close();
        }
    }





