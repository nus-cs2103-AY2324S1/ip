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
            } else if (input.equals("todo")) {
                String description = sc.nextLine();
                try {
                    TodoTask task = new TodoTask(description);
                    taskList.addTask(task);
                    String added = "I have added this task:\n" + task + "\nYou now have "
                            + taskList.size() + " task(s) in your list.";
                    reply(added);
                } catch (IllegalArgumentException e) {
                    reply("Oops! The description of a todo cannot be empty.\n" +
                            "Correct format: todo <description>");
                }
            } else if (input.equals("deadline")){
                String details = sc.nextLine();
                try {
                    DeadlineTask task = new DeadlineTask(details);
                    taskList.addTask(task);
                    String added = "I have added this task:\n" + task + "\nYou now have "
                            + taskList.size() + " task(s) in your list.";
                    reply(added);
                } catch (IllegalArgumentException e) {
                    reply("Oops! The description of a deadline cannot be empty.\n" +
                            "Correct format: deadline <description> /by <due date>");
                }
            } else if (input.equals("event")) {
                String details = sc.nextLine();
                try {
                    EventTask task = new EventTask(details);
                    taskList.addTask(task);
                    String added = "I have added this task:\n" + task + "\nYou now have "
                            + taskList.size() + " task(s) in your list.";
                    reply(added);
                } catch (IllegalArgumentException e) {
                    reply("Oops! The description of an event cannot be empty.\n" +
                            "Correct format: event <description> /from <start time> /to <end time>");
                }
            } else if (input.equals("delete")) {
                int index = sc.nextInt() - 1;
                String deletedTask = taskList.get(index).toString();
                taskList.delete(index);
                // exception for if index doesn't exist
                reply("Okay, I've deleted this task from the list:\n" + deletedTask
                        + "\nYou now have " + taskList.size() + " tasks in your list.");
            } else {
                reply("Oops! I'm sorry but I don't know what that means :(");
            }
        }

        reply(goodbye);
    }
}
