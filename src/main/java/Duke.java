import java.util.Scanner;

public class Duke {
    private static final String chatBotName = "Cristiano";

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetUser();
        run();
    }

    public static void greetUser() {
        printLine();
        System.out.println("Hello! I'm " + chatBotName + "! SUIIII!!!");
        System.out.println("What can I do for you?");
    }

    public static void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

//    public static void echoUntilBye() {
//        Scanner scanner = new Scanner(System.in);
//        String input = "";
//        while (!input.equals("bye")) {
//            input = scanner.nextLine();
//            printLine();
//            System.out.println(input);
//            printLine();
//        }
//        exit();
//    }

    public static void run() {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            String[] words = input.split(" ");
            if (input.equals("list")) {
                taskManager.list();
            } else if (words.length > 1 && (words[0].equals("mark") || words[0].equals("unmark"))) {
                // I added this try catch block because when I accidentally wrote "mark chores" there's a runtime error
                // due to trying to parse chores as an integer
                try {
                    int index = Integer.parseInt(words[1]);
                    if (words[0].equals("mark")) {
                        taskManager.mark(index);
                    } else if (words[0].equals("unmark")) {
                        taskManager.unmark(index);
                    }
                } catch (NumberFormatException e) {
                    printLine();
                    System.out.println("Please enter a valid index.");
                    printLine();
                }
            } else if (words.length > 1 && (words[0].equals("todo") || words[0].equals("deadline") ||
                    words[0].equals("event"))) {
                if (words[0].equals("todo")) {
                    String taskName = input.substring(input.indexOf(" ") + 1);
                    Task task = new ToDo(taskName);
                    taskManager.add(task);
                } else if (words[0].equals("deadline")) {
                    String suffix = input.substring(input.indexOf(" ") + 1);
                    String[] parts = suffix.split(" /due ");
                    if (parts.length != 2) {
                        //invalid format
                        printLine();
                        System.out.println("Invalid format for deadline. Please use: deadline task name /due due Date");
                        printLine();
                        continue;
                    }
                    String taskName = parts[0];
                    String dueDate = parts[1];
                    Task task = new Deadline(taskName, dueDate);
                    taskManager.add(task);
            } else if (words[0].equals("event")) {
                    String suffix = input.substring(input.indexOf(" ") + 1);
                    String[] parts = suffix.split(" /from ");
                    if (parts.length != 2) {
                        //invalid format
                        printLine();
                        System.out.println("Invalid format for event. Please use: event task name /from start date /to end date");
                        printLine();
                        continue;
                    }
                    String taskName = parts[0];
                    String[] timeParts = parts[1].split(" /to ");
                    if (timeParts.length != 2) {
                        //invalid format
                        printLine();
                        System.out.println("Invalid format for event. Please use: event task name /from start date /to end date");
                        printLine();
                        continue;
                    }
                    String from = timeParts[0];
                    String to = timeParts[1];
                    Task task = new Event(taskName, from, to);
                    taskManager.add(task);
                }
            }
        }
        exit();
    }












}
