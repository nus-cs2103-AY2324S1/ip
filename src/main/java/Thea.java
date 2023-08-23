import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Thea {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks =  new ArrayList<>();
        greet();
        String userInput = input.nextLine();
        while (true) {
            String[] commandWords = userInput.split(" ");
            String command = commandWords[0];
            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                printList(tasks);
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(commandWords[1]) - 1;
                tasks.get(index).markAsDone();
                System.out.printf("Great job! I've marked this task as done:\n  %s\n", tasks.get(index));
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(commandWords[1]) - 1;
                tasks.get(index).unmarkAsDone();
                System.out.printf("Okay, I've marked this task as not done yet:\n  %s\n", tasks.get(index));
            }
            else {
                add(new Task(userInput), tasks);
            }
            userInput = input.nextLine();
        }
    }
    public static void greet() {
        System.out.println("Hello! I'm Thea •ᴗ•\nHow can I help you?\n");
    }
    public static void exit() {
        System.out.println("I hope I made your day easier with my service. See you again! >ᴗ<");
    }
    public static void add(Task task, ArrayList<Task> tasks) {
        tasks.add(task);
        System.out.println("added: " + task.getTaskName());
    }
    public static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }
}
