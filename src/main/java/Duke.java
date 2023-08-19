import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "ForsakenX";
        String helloGreeting = String.format("Hello! I'm %s\nWhat can I do for you?\n", name);
        String byeGreeting = "Bye. Hope to see you again soon! o(╥﹏╥)o\n";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.print(output(helloGreeting));
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.print(output(byeGreeting));
                break;
            } else if (command.equals("list")) {
                StringBuilder msg = new StringBuilder("Here are the tasks in your list:\n");
                for (int i = 1; i <= taskList.size(); i++) {
                    msg.append(String.format("%d.%s\n", i, taskList.get(i - 1)));
                }
                System.out.print(output(msg.toString()));
            } else if (command.startsWith("mark") && !command.equals("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                Task task = taskList.get(index - 1);
                task.markIsDone();
                taskList.set(index - 1, task);
                System.out.print(output(
                        String.format("Nice! I've marked this task as done:\n\t%s\n", task)));
            } else if (command.startsWith("unmark") && !command.equals("unmark")){
                int index = Integer.parseInt(command.split(" ")[1]);
                Task task = taskList.get(index - 1);
                task.markNotDone();
                taskList.set(index - 1, task);
                System.out.print(output(
                        String.format("OK, I've marked this task as not done yet:\n\t%s\n", task)));
            } else if (command.startsWith("deadline")) {
                String[] temp = command.replace("deadline ", "").split("/by");
                Deadline deadline = new Deadline(temp[0].strip(), temp[1].strip());
                taskList.add(deadline);
                System.out.print(
                        output(String.format("Got it. I've added this task:" +
                                "\n\t%s\nNow you have %d tasks in the list.\n", deadline, taskList.size())));
            } else if (command.startsWith("event")) {
                String[] tempDesc = command.replace("event ", "").split("/from");
                String[] tempFromTo = tempDesc[1].strip().split("/to");
                Event event = new Event(tempDesc[0].strip(), tempFromTo[0].strip(), tempFromTo[1].strip());
                taskList.add(event);
                System.out.print(
                        output(String.format("Got it. I've added this task:" +
                                "\n\t%s\nNow you have %d tasks in the list.\n", event, taskList.size())));
            } else if (command.startsWith("todo")) {
                ToDo toDo = new ToDo(command.replace("todo ", ""));
                taskList.add(toDo);
                System.out.print(
                        output(String.format("Got it. I've added this task:" +
                                "\n\t%s\nNow you have %d tasks in the list.\n", toDo, taskList.size())));
            } else {
                taskList.add(new Task(command));
                System.out.print(output("added: " + command + "\n"));
            }
        }
    }

    static String output(String msg) {
        String line = "----------------------------(≧▽≦)----------------------------";
        return String.format("%s\n%s%s\n",
                line, msg, line);
    }
}
