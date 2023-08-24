import java.util.Scanner;

public class Duke {

    public static void talk(String str) {
        String line = "_".repeat(50);
        System.out.println(line);
        System.out.println(str);
        System.out.println(line);
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm JED, your personal chat-bot!\nWhat can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        Task[] items = new Task[100];
        int count = 0;

        Scanner sc = new Scanner(System.in);

        talk(greeting);

        while (true) {
            String input = sc.nextLine();
            String keyword = input.split(" ", 2)[0];
            if (keyword.equals("bye")) {
                talk(goodbye);
                break;
            }
            if (keyword.equals("list")) {
                String list = "";
                for (int i = 0; i < count; i++) {
                    list += "  " + (i + 1) + ". " + items[i] + "\n";
                }
                talk(list);
                continue;
            }

            String command = input.split(" ", 2)[1];
            switch (keyword) {
                case "mark":
                    int indexMark = Integer.parseInt(command) - 1;
                    items[indexMark].markDone();
                    talk("Nice! I've marked this task as done:\n  " + items[indexMark]);
                    break;
                case "unmark":
                    int indexUnmark = Integer.parseInt(command) - 1;
                    items[indexUnmark].markUnDone();
                    talk("OK, I've marked this task as not done yet:\n  " + items[indexUnmark]);
                    break;
                case "todo":
                    items[count] = new ToDo(command);
                    talk("Got it. I've added this task:\n  " + items[count] + "\n Now you have " + (count + 1) + " tasks in your list.");
                    count++;
                    break;
                case "deadline":
                    String deadlineName = "";
                    String deadlineBy = "";
                    String[] deadlineDescription = command.split("/");
                    for (String str : deadlineDescription) {
                        if (str.startsWith("by")) {
                            deadlineBy = str.split(" ", 2)[1].trim();
                        } else {
                            deadlineName = str.trim();
                        }
                    }
                    items[count] = new Deadline(deadlineName, deadlineBy);
                    talk("Got it. I've added this task:\n  " + items[count] + "\n Now you have " + (count + 1) + " tasks in your list.");
                    count++;
                    break;
                case "event":
                    String eventName = "";
                    String eventFrom = "";
                    String eventTo = "";
                    String[] eventDescription = command.split("/");
                    for (String str : eventDescription) {
                        if (str.startsWith("from")) {
                            eventFrom = str.split(" ", 2)[1].trim();
                        } else if (str.startsWith("to")) {
                            eventTo = str.split(" ", 2)[1].trim();
                        } else {
                            eventName = str.trim();
                        }
                    }
                    items[count] = new Event(eventName, eventFrom, eventTo);
                    talk("Got it. I've added this task:\n  " + items[count] + "\n Now you have " + (count + 1) + " tasks in your list.");
                    count++;
                    break;
            }
        }
    }
}
