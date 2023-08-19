import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "ForsakenX";
        String helloGreeting = String.format("Hello! I'm %s\nWhat can I do for you?", name);
        String byeGreeting = "Bye. Hope to see you again soon! o(╥﹏╥)o";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        int taskCount = 0;

        System.out.print(output(helloGreeting));
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.print(output(byeGreeting));
                break;
            } else if (command.equals("list")) {
                StringBuilder msg = new StringBuilder("Here are the tasks in your list:\n");
                for (int i = 1; i <= taskCount; i++) {
                    msg.append(String.format("%d.%s", i, taskList.get(i - 1)));
                    if (i != taskCount) {
                        msg.append("\n");
                    }
                }
                System.out.print(output(msg.toString()));
            } else if (command.startsWith("mark") && !command.equals("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                Task task = taskList.get(index - 1);
                task.markIsDone();
                taskList.set(index - 1, task);
                System.out.print(output(
                        String.format("Nice! I've marked this task as done:\n\t%s", task)));
            } else if (command.startsWith("unmark") && !command.equals("unmark")){
                int index = Integer.parseInt(command.split(" ")[1]);
                Task task = taskList.get(index - 1);
                task.markNotDone();
                taskList.set(index - 1, task);
                System.out.print(output(
                        String.format("OK, I've marked this task as not done yet:\n\t%s", task)));
            } else {
                taskList.add(new Task(command));
                taskCount++;
                System.out.print(output("added: " + command));
            }
        }
    }

    static String output(String msg) {
        String line = "----------------------------(≧▽≦)----------------------------";
        return String.format("%s\n%s\n%s\n",
                line, msg, line);
    }
}
