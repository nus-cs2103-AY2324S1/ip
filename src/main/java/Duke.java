import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) {
        String divider = "   _________________________________ \n";
        String greeting = "   Hello! I'm Sana \n   What can I do for you? \n";
        String bye = "   Bye. Hope to see you again soon! \n";

        Scanner myObj = new Scanner(System.in);
        System.out.println(divider + greeting + divider);

        while(true) {
            String command = myObj.nextLine();
            if (command.equals("bye")) {
                System.out.println(divider + "   " + bye + divider);
                break;
            }

            switch (command) {
            case "list":
                StringBuilder task = new StringBuilder("");
                for (int i = 0; i < tasksList.size(); i++) {
                    int id = i + 1;
                    task.append("   " + id + ". " + tasksList.get(i).getDescription() + "\n");
                }
                System.out.println(divider + task + divider);
                break;
            default:
                tasksList.add(new Task(command));
                System.out.println(divider + "   added: " + command + "\n" + divider);
            }
        }
    }
}
