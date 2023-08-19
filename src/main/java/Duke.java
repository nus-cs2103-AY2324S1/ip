import java.util.Scanner;

public class Duke {
    
    private static TaskList tasks = new TaskList();

    private static void greet() {
        System.out.println("Hello! I'm Siyuan");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                tasks.listAllTasks();
            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                tasks.markTaskAsDone(taskNumber);
            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                tasks.markTaskAsUndone(taskNumber);
            } else {
                tasks.addTask(new Task(input));
            }
            input = sc.nextLine();
        }

        sc.close();

        exit();
    }
}
