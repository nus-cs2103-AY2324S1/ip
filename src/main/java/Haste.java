import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;
public class Haste {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        // greet user and exit
        System.out.println("Hello! I'm HASTE\nWhat can I do for you?\n");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String cmd = sc.nextLine();
            // if str is bye
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (cmd.equals("list")) {
                // print list
                int counter = 1;
                for (Task a : taskList) {
                    System.out.println(a.id + ". " + a.toString());
                }

            } else if (cmd.startsWith("mark")) {
                int id = parseInt(cmd.split(" ")[1]) - 1;
                Task currTask = taskList.get(id);
                currTask.markDone();
                System.out.println("Nice! I've marked this task as done!");
                System.out.println(currTask.toString());

            } else if (cmd.startsWith("unmark")) {
                int id = parseInt(cmd.split(" ")[1]) - 1;
                Task currTask = taskList.get(id);
                currTask.markUndone();
                System.out.println("Okay, I've marked this task as not done");
                System.out.println(currTask.toString());

            } else {
                // add task to list
                Task newTask = new Task(cmd);
                taskList.add(newTask);
                System.out.println(cmd);
            }
        }

    }

}
