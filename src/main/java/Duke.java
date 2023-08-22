import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();
        String line = "    ______________________________________________";
        System.out.println(line + "\n    Hello, I'm your task manager :)\n    What can I do for you?\n" + line);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            System.out.println(line);
            if (command.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("    %d.[%s] " + list.get(i).getDescription(),
                            i+1, list.get(i).getStatusIcon()));
                }
            } else if (command.startsWith("mark ")) {
                try {
                    int toMark = Integer.parseInt(command.substring(5));
                    Task task = list.get(toMark - 1);
                    task.markAsDone();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println(String.format("      [%s] %s", task.getStatusIcon(), task.getDescription()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    Task does not exist.");
                }
            } else if (command.startsWith("unmark ")) {
                try {
                    int toMark = Integer.parseInt(command.substring(7));
                    Task task = list.get(toMark - 1);
                    task.markAsUndone();
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println(String.format("      [%s] %s", task.getStatusIcon(), task.getDescription()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    Task does not exist.");
                }
            } else {
                System.out.println("    added: " + command);
                list.add(new Task(command));
            }
            System.out.println(line);
            command = scanner.nextLine();
        }
        System.out.println(line + "\n    Bye. Hope to see you again soon!\n" + line);
    }
}
