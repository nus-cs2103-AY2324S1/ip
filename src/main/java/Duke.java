import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String name = "Johnnythesnake";
        System.out.println("Hello I'm " + name + "\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        ArrayList<Tasks> tasksList = new ArrayList<>();
        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                Exit exit = new Exit();
                System.out.println(exit.exitMessage());
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list: ");
                if (!tasksList.isEmpty()) {
                    for (int i = 1; i <= tasksList.size(); i++) {
                        System.out.println(i + "." + tasksList.get(i - 1));
                    }
                }
            } else if (command.contains("unmark")) {
                int taskNumber = Integer.parseInt(command.substring(7)) - 1;
                if (taskNumber < tasksList.size()) {
                    Tasks task = tasksList.get(taskNumber);
                    task.setMarked(false);
                    tasksList.set(taskNumber, task);
                    System.out.println("OK, I've marked this task as not done yet:\n" + "  " + tasksList.get(taskNumber));
                }
            } else if (command.contains("mark")) {
                int taskNumber = Integer.parseInt(command.substring(5)) - 1;
                if (taskNumber < tasksList.size()) {
                    Tasks task = tasksList.get(taskNumber);
                    task.setMarked(true);
                    tasksList.set(taskNumber, task);
                    System.out.println("Nice! I've marked this task as done:\n" + "  " + tasksList.get(taskNumber));
                }


            } else {
                System.out.println("added: " + command);
                tasksList.add(new Tasks(command,false));

            }

        }
    }
}
