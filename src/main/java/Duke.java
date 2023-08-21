import java.util.Scanner;

public class Duke {

    // Array storing the tasks
    static Task[] taskArr = new Task[100];

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


    // Main function
    public static void main(String[] args) {
        String CHATBOTNAME = "Carl";
        System.out.println(messageCard("Hello! I'm " + CHATBOTNAME
            + "\n\t What can I do for you?"));

        int count = 0;
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
                System.out.println(messageCard("added: " + userInput));
                taskArr[count] = new Task(userInput);
                count++;
            }
        }
    }
}