import java.util.Scanner;
public class Harvard {
    public static void main(String[] args) {
        String line = "----------------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Harvard\nWhat can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (command.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                Task task = new Task(command);
                tasks[taskCount] = task;
                taskCount++;
                System.out.println(line);
                System.out.println("added: " + command);
            }
            System.out.println(line);
        }
        in.close();
    }
}
