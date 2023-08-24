import java.util.Scanner;
public class Bareum {
    static TaskList taskList = new TaskList();

    static void reply(String reply) {
        String botName = "Bareum: ";
        String fullReply = botName + reply;
        System.out.println(fullReply);
    }

    public static void main(String[] args) {
        String introduction = "Hello! I'm Bareum! What can I do for you? ^^";
        String goodbye = "Bye! Hope to see you again soon ^^";
        String line = "________________________";

        reply(introduction);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(line);
            String input = sc.next();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                reply("Here are your current tasks!");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i).toString());
                }
            } else if (input.equals("mark")) {
                int index = sc.nextInt() - 1;
                taskList.markAsDone(index);
                reply("Well done! I've marked this task as done:\n" + taskList.get(index).toString());
            } else if (input.equals("unmark")) {
                int index = sc.nextInt() - 1;
                taskList.markAsUndone(index);
                reply("Okay, I've marked this task as not done yet:\n" + taskList.get(index).toString());
            } else {
                String task = input + sc.nextLine();
                taskList.addTask(task);
                String added = "I have added " + "'" + task + "'" + " to your list!";
                reply(added);
            }
        }

        reply(goodbye);
    }
}
