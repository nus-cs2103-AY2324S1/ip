import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class MattBot {
    private static final String NAME = "MattBot";
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<Task>();
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                printTop();
                System.out.println("Bye, Hope to see you soon!");
                printBottom();
                break;
            } else if (userInput.equals("list")) {
                printTop();
                for (int i = 0; i < taskList.size(); i++) {
                    Task t = taskList.get(i);
                    System.out.println(String.valueOf(i+1) + ". [" + t.showStatus() + "] " + t.showName() );
                }
                printBottom();
            } else if (userInput.split(" ",2)[0].equals("mark")) {
                printTop();
                int taskId = Integer.parseInt(userInput.split(" ",2)[1]) - 1;
                Task t = taskList.get(taskId);
                t.markAsDone();
                taskList.set(taskId, t);
                System.out.println("Great job! You have completed the task " + t.showName());
                printBottom();
            } else if (userInput.split(" ",2)[0].equals("unmark")) {
                printTop();
                int taskId = Integer.parseInt(userInput.split(" ",2)[1]) - 1;
                Task t = taskList.get(taskId);
                t.markAsNotDone();
                taskList.set(taskId, t);
                System.out.println("Oh no, you have uncompleted " + t.showName());
                printBottom();
            } else {
                printTop();
                Task t = new Task(userInput);
                taskList.add(t);
                System.out.println("added: " + userInput);
                printBottom();
            }
        }
    }
    public static void printTop() {
        System.out.println("____________________________________________________________");
    }
    public static void printBottom() {
        System.out.println("____________________________________________________________");
    }
}
