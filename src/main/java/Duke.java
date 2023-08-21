import java.util.Scanner;

public class Duke {

    // Array storing the tasks
    static Task[] taskArr = new Task[100];
    static int count = 0;

    // Function that encapsulates message into a message Card template
    private static String messageCard(String message) {
        String horizontalLine = "\t____________________________________________________________\n";
        return horizontalLine + "\t " + message + "\n" + horizontalLine;
    }

    // Displays list of items
    private static String displayList(Task[] list) {
        String str = "Here are the tasks in your list:\n\t ";
        int count = 0;
        while (list[count] != null) {
            Task task = list[count];
            str += count + 1 + "." + task.toString() + "\n\t ";
            count++;
        }
        return messageCard(str.substring(0, str.length() - 3));
    }

    private static void markTaskAsDone(int index) {
        taskArr[index].markAsDone();
        System.out.println(messageCard("Nice! I've marked this task as done:\n\t\t" + taskArr[index]));
    }

    private static void markTaskAsUndone(int index) {
        taskArr[index].markAsUndone();
        System.out.println(messageCard("OK, I've marked this task as not done yet:\n\t\t" + taskArr[index]));
    }

    private static void addToDo(String description) {
        taskArr[count] = new Todo(description);
        System.out.println(messageCard("Got it. I've added this task:\n\t\t"
                + taskArr[count]
                + "\n\tNow you have " + (count + 1) + " tasks in the list."));
        count++;
    }

    private static void addDeadline(String description) {
        taskArr[count] = new Deadline(description.substring(0, description.indexOf("/by")),
                description.substring(description.indexOf("/by") + 4));
        System.out.println(messageCard("Got it. I've added this task:\n\t\t"
                + taskArr[count]
                + "\n\tNow you have " + (count + 1) + " tasks in the list."));
        count++;
    }

    private static void addEvent(String description) {

        int indexFrom = description.indexOf("/from");
        int indexTo = description.indexOf("/to");

        String event = description.substring(0, indexFrom).trim();
        String startTime = description.substring(indexFrom + "/from".length(), indexTo).trim();
        String endTime = description.substring(indexTo + "/to".length()).trim();

        taskArr[count] = new Event(event, startTime, endTime);
        System.out.println(messageCard("Got it. I've added this task:\n\t\t"
                + taskArr[count]
                + "\n\tNow you have " + (count + 1) + " tasks in the list."));
        count++;
    }


    // Main function
    public static void main(String[] args) {
        String CHATBOTNAME = "Carl";
        System.out.println(messageCard("Hello! I'm " + CHATBOTNAME
            + "\n\t What can I do for you?"));

        while (true) {
            Scanner SC = new Scanner(System.in);
            String userInput = SC.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(messageCard("Bye. Hope to see you again soon!"));
                break;
            } else if (userInput.equals("list")) {
                System.out.println(displayList(taskArr));
            } else if (userInput.substring(0, 4).equals("mark")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                markTaskAsDone(index);
            } else if (userInput.substring(0, 6).equals("unmark")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                markTaskAsUndone(index);
            } else {
                if (userInput.substring(0,4).equals("todo")) {
                    // Add a task
                    addToDo(userInput.substring(5));
                } else if (userInput.substring(0, 8).equals("deadline")) {
                    // Add a deadline
                    addDeadline(userInput.substring(9));
                } else if (userInput.substring(0, 5).equals("event")) {
                    // Add a event
                    addEvent(userInput.substring(6));
                }

            }
        }
    }
}