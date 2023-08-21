import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String getNumTasks(int num) {
        if (num == 1) {
            return "\t Now you have " + num + " task in the list.";
        }
        return "\t Now you have " + num + " tasks in the list.";
    }

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        int count = 0;
        boolean exit = false;
        String line = "\t____________________________________________________________";
        System.out.println(line);
        System.out.println("\t Hello! I'm Violet");
        System.out.println("\t What can I do for you?");
        System.out.println(line);

        while (!exit) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            Task task = new Task(command);
            System.out.println(line);
            if (command.equals("bye")) {
                System.out.println("\t Bye. Hope to see you again soon!");
                exit = true;
            } else if (command.equals("list")) {
                System.out.println("\t Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + (i + 1) + "." + list.get(i).toString());
                }
            } else if (command.startsWith("mark")) {
                int idx = Character.getNumericValue(command.charAt(5));
                Task t = list.get(idx - 1);
                t.markAsDone();
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t\t" + t.toString());
            } else if (command.startsWith("unmark")) {
                int idx = Character.getNumericValue(command.charAt(7));
                Task t = list.get(idx - 1);
                t.markAsUndone();
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.println("\t\t" + t.toString());
            } else if (command.startsWith("todo")) {
                Todo todo = new Todo(command.substring(5));
                list.add(todo);
                count++;
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t\t" + todo.toString());
                System.out.println(getNumTasks(count));
            } else if (command.startsWith("deadline")) {
                Deadline deadline = new Deadline(command.substring(9, command.indexOf("/") - 1),
                        command.substring(command.indexOf("/by") + 4));
                list.add(deadline);
                count++;
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t\t" + deadline.toString());
                System.out.println(getNumTasks(count));
            } else if (command.startsWith("event")) {
                Event event = new Event(command.substring(6, command.indexOf("/") - 1),
                        command.substring(command.indexOf("/from") + 6, command.indexOf("/to") - 1),
                        command.substring(command.indexOf("/to") + 4));
                list.add(event);
                count++;
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t\t" + event.toString());
                System.out.println(getNumTasks(count));
            } else {
                list.add(task);
                count++;
                System.out.println("\t added: " + command);
                System.out.println(getNumTasks(count));
            }
            System.out.println(line);
        }
    }
}
