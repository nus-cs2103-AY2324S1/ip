import java.util.Scanner;

public class Duke {
    private static final String chatBotName = "Cristiano";
    private static String[] list = new String[100];

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetUser();
        addAndMarkTasks();
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

    public static void echoUntilBye() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            printLine();
            System.out.println(input);
            printLine();
        }
        exit();
    }

    public static void addAndListTasks() {
        ToDoList tdl = new ToDoList();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            if (input.equals("list")) {
                tdl.list();
            } else {
                tdl.add(input);
            }
        }
        exit();
    }

    public static void addAndMarkTasks() {
        ToDoList tdl = new ToDoList();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            String[] words = input.split(" ");
            if (input.equals("list")) {
                tdl.list();
            } else if (words.length > 1 && (words[0].equals("mark") || words[0].equals("unmark"))) {
                // I added this try catch block because when I accidentally wrote "mark chores" there's a runtime error
                // due to trying to parse chores as an integer
                try {
                    int index = Integer.parseInt(words[1]);
                    if (words[0].equals("mark")) {
                        tdl.mark(index);
                    } else if (words[0].equals("unmark")) {
                        tdl.unmark(index);
                    }
                } catch (NumberFormatException e) {
                    printLine();
                    System.out.println("Please enter a valid index.");
                    printLine();
                }
            } else {
                tdl.add(input);
            }
        }
        exit();
    }












}
