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
                TodoTask task = new TodoTask(description);
                taskList.addTask(task);
                String added = "I have added this task:\n" + task + "\nYou now have "
                        + taskList.size() + " task(s) in your list.";
                reply(added);
            } else if (input.equals("deadline")){
                boolean isDate = false;
                String description = "";
                String dueDateTime = "";

                while(!isDate) {
                    String word = sc.next();
                    if (word.equals("/by")) {
                        dueDateTime = sc.nextLine();
                        isDate = true;
                    } else {
                        description = description + " " + word;
                    }
                }

                DeadlineTask task = new DeadlineTask(description, dueDateTime);
                taskList.addTask(task);
                String added = "I have added this task:\n" + task + "\nYou now have "
                        + taskList.size() + " task(s) in your list.";
                reply(added);
            } else if (input.equals("event")) {
                boolean isStartDate = false;
                boolean isEndDate = false;
                String description = "";
                String startDateTime = "";
                String endDateTime = "";

                while(!isEndDate) {
                    String word = sc.next();
                    if (word.equals("/from")) {
                        isStartDate = true;
                        while (isStartDate) {
                            word = sc.next();
                            if (word.equals("/to")) {
                                isStartDate = false;
                                endDateTime = sc.nextLine();
                                isEndDate = true;
                            } else {
                                startDateTime = startDateTime + " " + word;
                            }
                        }
                    } else {
                        description = description + " " + word;
                    }
                }

                EventTask task = new EventTask(description, startDateTime, endDateTime);
                taskList.addTask(task);
                String added = "I have added this task:\n" + task + "\nYou now have "
                        + taskList.size() + " task(s) in your list.";
                reply(added);
            }
        }

        reply(goodbye);
    }
}
