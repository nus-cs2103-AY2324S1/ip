import java.util.Scanner;

public class Duke {
    private static ToDoList list = new ToDoList();
    private static void greet() {
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        System.out.println("Hello! I'm Bot");
        System.out.println("What can I do for you?");
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
    }

    private static void exit() {
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println("–––––––––––––––––––––––––––––––––––––––––");
            System.out.println(input);
            System.out.println("–––––––––––––––––––––––––––––––––––––––––");
            input = scanner.nextLine();
        }
        Duke.exit();
    }
    public static void main(String[] args) {
        Duke.greet();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list.listTasks();
            } else if (input.startsWith("mark")) {
                list.markAsDone(Character.getNumericValue(input.charAt(5)));
            } else {
                list.addTask(input);
            }
            input = scanner.nextLine();
        }

        Duke.exit();
    }
}
