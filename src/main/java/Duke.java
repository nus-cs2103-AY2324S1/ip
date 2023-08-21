import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Bongo!\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                String goodbyeMessage = "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n";
                System.out.println(goodbyeMessage);
                break;
            }
            if (input.equals("list")) {
                StringBuilder allTasks = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    allTasks.append(String.format(" %d. %s\n", i + 1, tasks.get(i)));
                }
                String tasksList = "____________________________________________________________\n" +
                        allTasks +
                        "____________________________________________________________\n";
                System.out.println(tasksList);
                continue;
            }
            tasks.add(input);
            String echoMessage = "____________________________________________________________\n" +
                    String.format(" added: %s\n", input) +
                    "____________________________________________________________\n";
            System.out.println(echoMessage);
        }
    }
}

