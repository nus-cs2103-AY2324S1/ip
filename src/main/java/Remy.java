import java.util.Scanner;
import java.util.ArrayList;

public class Remy {

    public static void main(String[] args) {
        String welcomeMessage = "____________________________________________________________\n" +
                "I'm Remy, and it is NOT nice to see you.\n" +
                "Faster tell me what you want and go away.\n" +
                "____________________________________________________________\n";
        String exitMessage = "Hope to never see you again!\n" +
                "____________________________________________________________\n";

        ArrayList<Task> taskList = new ArrayList(100);

        System.out.println(welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(exitMessage);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("__________");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + taskList.get(i));
                }
                System.out.println("__________");
            } else if (input.toLowerCase().startsWith("mark ")) {
                // Marks item as done
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index >= 0 && index < taskList.size()) {
                    taskList.get(index).markAsDone();
                    System.out.println("__________");
                    System.out.println("Done. You happy?");
                    System.out.println(taskList.get(index).toString());
                    System.out.println("__________");
                } else {
                    System.out.println("__________");
                    System.out.println("No such item lah.");
                    System.out.println("__________");
                }
            } else if (input.toLowerCase().startsWith("unmark ")) {
                // Marks item as undone
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index >= 0 && index < taskList.size()) {
                    taskList.get(index).markAsUndone();
                    System.out.println("__________");
                    System.out.println("Done. You happy?");
                    System.out.println(taskList.get(index).toString());
                    System.out.println("__________");
                } else {
                    System.out.println("__________");
                    System.out.println("No such item lah.");
                    System.out.println("__________");
                }
            } else {
                Task temp = new Task(input);
                taskList.add(temp);
                System.out.println("__________");
                System.out.println(" added: " + input);
                System.out.println("__________");
            }


        }
    }
}
