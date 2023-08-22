import java.util.Scanner;
import java.util.ArrayList;

public class Hong {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String line = "---------------------------------------------------------";
    public static void main(String[] args) {
        sayHello();
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                myObj.close();
                break;
            } else if (userInput.equals("list")) {
                printTasks();
            } else if (userInput.startsWith("mark")) {
                handleMark(userInput);
            } else {
                Task currentTask = new Task(userInput);
                String currentMessage = line + "\n" + "added: " + userInput + "\n" + line;
                System.out.println(currentMessage);
                tasks.add(currentTask);
            }
        }
        sayBye();
    }
    private static void sayHello() {
        String firstMessage = line + "\nHello! I'm Hong\nWhat can I do for you?\n" + line;
        System.out.println(firstMessage);
    }

    private static void sayBye() {
        String exitMessage = line + "\n" + "Bye. Hope to see you again soon!\n" + line;
        System.out.println(exitMessage);
    }

    private static void printTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String currentItem = (i + 1) + "." + currentTask.checkDone() + " " + currentTask.getDescription();
            System.out.println(currentItem);
        }
        System.out.println(line);
    }

    private static void handleMark(String userInput) {
        String[] arrInput = userInput.split(" ");
        Task currentTask = tasks.get(Integer.valueOf(arrInput[1]) - 1);
        currentTask.markDone();
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        String currentItem = currentTask.checkDone() + " " + currentTask.getDescription();
        System.out.println(currentItem);
        System.out.println(line);
    }
}
