import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[10];
        String horizontal_line = "____________________________________________________________\n";
        int count = 0;
        String welcomeMessage = horizontal_line +
                " Hello! I'm Blob\n" +
                " What can I do for you?\n" +
                horizontal_line;
        System.out.println(welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userCommand = scanner.nextLine();

            if ("bye".equals(userCommand)) {
                scanner.close();
                break;
            } else if ("list".equals(userCommand)) {
                System.out.println(horizontal_line);
                System.out.println("here are the tasks in your list: ");
                for (int i = 0; i < count; i++) {
                    Task task = tasks[i];
                    String completed = task.getCompleted() ? "X" : " ";
                    System.out.println((i + 1) + ". " + "[" + completed + "] " + task.getDescription());
                }
                System.out.println(horizontal_line);
            } else if (userCommand.startsWith("mark ")) {
                String[] parts = userCommand.split(" ");
                if (parts.length == 2) {
                    int num = Integer.parseInt(parts[1]);
                    Task task = tasks[num];
                    task.toggleCompleted();
                    System.out.println("Nice! I've marked this task as done:");
                    String completed = task.getCompleted() ? "X" : " ";
                    System.out.println("[" + completed + "] " + task.getDescription());
                }
            } else if (userCommand.startsWith("unmark ")) {
                String[] parts = userCommand.split(" ");
                if (parts.length == 2) {
                    int num = Integer.parseInt(parts[1]);
                    Task task = tasks[num];
                    task.toggleCompleted();
                    System.out.println("Okay. I see you haven't done this task yet");
                    String completed = task.getCompleted() ? "X" : " ";
                    System.out.println("[" + completed + "] " + task.getDescription());
                }
            } else {
                System.out.println(horizontal_line);
                System.out.println("added: " + userCommand);
                System.out.println(horizontal_line);
                tasks[count] = new Task(userCommand);
                count++;
            }
        }
        System.out.println(horizontal_line);
        System.out.println(" Bye. Come talk to Blob again soon!");
        System.out.println(horizontal_line);
    }
}
