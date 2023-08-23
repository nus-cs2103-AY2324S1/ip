import java.util.ArrayList;
import java.util.Scanner;

class Task {
    public boolean isDone = false;
    public String name = "";

    public Task(String name) {
        this.name = name;
    }
}
public class Duke {
    public static void main(String[] args) {
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Dookie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(intro);

        ArrayList<Task> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        // Get user command
        String cmd = sc.nextLine();

        // Exit if command is "bye"
        while (!cmd.equals("bye")) {
            // If cmd is "list", list items and wait for next command
            if (cmd.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isDone) {
                        System.out.println(i+1 + ".[X] " + list.get(i).name);
                        continue;
                    }
                    System.out.println(i+1 + ".[ ] " + list.get(i).name);
                }
            } else if (cmd.split(" ")[0].equals("mark")) {
                String arr[] = cmd.split(" ", 2);
                int taskNumber = Integer.parseInt(arr[1]) - 1;
                list.get(taskNumber).isDone = true;

                String message = "____________________________________________________________\n" +
                        " Nice! I've marked this task as done:\n" +
                        "   [X] " + list.get(taskNumber).name + "\n" +
                        "____________________________________________________________";
                System.out.println(message);
            } else if (cmd.split(" ")[0].equals("unmark")) {
                String arr[] = cmd.split(" ", 2);
                int taskNumber = Integer.parseInt(arr[1]) - 1;
                list.get(taskNumber).isDone = false;

                String message = "____________________________________________________________\n" +
                        " Nice! I've marked this task as done:\n" +
                        "   [ ] " + list.get(taskNumber).name + "\n" +
                        "____________________________________________________________";
                System.out.println(message);
            } else {    // else, echo command and add to the list
                String echo = "____________________________________________________________\n" +
                        " added: " + cmd + "\n" +
                        "____________________________________________________________";
                System.out.println(echo);
                Task newTask = new Task(cmd);
                list.add(newTask);
            }
            cmd = sc.nextLine();
        }

        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(exitMessage);
    }
}
