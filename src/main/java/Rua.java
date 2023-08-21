import java.util.ArrayList;
import java.util.Scanner;

public class Rua {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "____________________________________________________________\n" +
                " Hello! I'm Rua, your ChatBot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String goodbye= "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        TaskList taskList = new TaskList();
        System.out.println(greeting);

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            if (command.equals("list")) {
                System.out.println(taskList);
            } else if (command.startsWith("mark")) {
                String indexStr = command.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(indexStr);
                taskList = taskList.mark(index);
            } else if (command.startsWith("unmark")) {
                String indexStr = command.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(indexStr);
                taskList = taskList.unmark(index);
            } else {
                Task newTask = new Task(command);
                String message = "added: " + command + "\n";
                System.out.println(message);
                taskList = taskList.add(newTask);
            }
            System.out.println("____________________________________________________________\n");
            command = sc.nextLine();
        }
        System.out.println(goodbye);

    }
}
