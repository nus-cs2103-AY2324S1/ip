import java.util.Scanner;

public class Duke {
    private static String[] tasks = new String[100];
    private static int numTasks = 0;

    private static void greet() {
        System.out.println("Hello! I'm Siyuan");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void echo(String input) {
        System.out.println(input);
    }

    private static void addTask(String input) {
        System.out.println("added: " + input);
        tasks[numTasks++] = input;
    }

    private static void listTasks() {
        for (int i = 0; i < numTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        
        while (!input.equals("bye")) {

            if (input.equals("list")) {
                listTasks();
            } else {
                addTask(input);
            }
            input = sc.nextLine();
        }

        sc.close();

        exit();
    }
}
