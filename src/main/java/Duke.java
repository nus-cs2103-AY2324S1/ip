import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) {
        String divider = "   ____________________________________ \n";
        String greeting = "   Hello! I'm Sana \n   What can I do for you? \n";
        String bye = "   Bye. Hope to see you again soon! \n";

        Scanner myObj = new Scanner(System.in);
        System.out.println(divider + greeting + divider);

        while(true) {
            String userInput = myObj.nextLine();
            StringBuilder cmd = new StringBuilder();

            for (int i = 0; i < userInput.length(); i++) {
                char c = userInput.charAt(i);

                if (!Character.isWhitespace(c)) {
                    cmd.append(c);
                } else {
                    break;
                }
            }

            String command = cmd.toString();

            if (command.equals("bye")) {
                System.out.println(divider + bye + divider);
                break;
            }

            switch (command) {
            case "list":
                StringBuilder task = new StringBuilder();
                for (int i = 0; i < tasksList.size(); i++) {
                    int id = i + 1;
                    String statusIcon = tasksList.get(i).getStatusIcon();
                    task.append("   " + id + "." + "[" + statusIcon + "]" + " "
                            + tasksList.get(i).getDescription() + "\n");
                }
                System.out.println(divider + "   Here are the tasks in your list: \n" + task
                        + divider);
                break;
            case "mark":
                int taskId = userInput.charAt(5) - '0';
                tasksList.get(taskId - 1).markAsDone();
                System.out.println(divider + "   Nice! I've marked this task as done: \n     [X] "
                        + tasksList.get(taskId - 1).getDescription() + "\n" + divider);
                break;
            case "unmark":
                taskId = userInput.charAt(7) - '0';
                tasksList.get(taskId - 1).markAsNotDone();
                System.out.println(divider + "   OK, I've marked this task as not done yet: \n     [ ] "
                        + tasksList.get(taskId - 1).getDescription() + "\n" + divider);
                break;
            default:
                tasksList.add(new Task(userInput));
                System.out.println(divider + "   added: " + userInput + "\n" + divider);
            }
        }
    }
}
