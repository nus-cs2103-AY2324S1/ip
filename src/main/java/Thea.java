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
            String[] commandWords = userInput.split(" ", 2);
            String command = commandWords[0];
            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                printList(tasks);
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(commandWords[1]) - 1;
                tasks.get(index).markAsDone();
                System.out.printf("Great job! ˊᗜˋ I've marked this task as done:\n  %s\n", tasks.get(index));
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(commandWords[1]) - 1;
                tasks.get(index).unmarkAsDone();
                System.out.printf("Okay, I've marked this task as not done yet:\n  %s\n", tasks.get(index));
            }
            else {
                if (command.equals("todo")) {
                    add(new ToDo(commandWords[1]), tasks);
                }
                else if (command.equals("deadline")) {
                    String relevantData = commandWords[1];
                    String[] nameAndTime = relevantData.split(" /by ");
                    add(new Deadline(nameAndTime[0], nameAndTime[1]), tasks);
                }
                else if (command.equals("event")) {
                    String relevantData = commandWords[1];
                    String[] nameAndTime = relevantData.split(" /from | /to ");
                    add(new Event(nameAndTime[0], nameAndTime[1], nameAndTime[2]), tasks);
                }
            }
            userInput = input.nextLine();
        }
    }
    public static void greet() {
        System.out.println("Hello! I'm Thea •ᴗ•\nHow can I help you?");
    }
    public static void exit() {
        System.out.println("I hope I made your day easier with my service. See you again! >ᴗ<");
    }
    public static void add(Task task, ArrayList<Task> tasks) {
        tasks.add(task);
        System.out.println("I have added the following task to your list:\n  "
                + task.toString() + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list. You can do this! •̀ ᗜ •́ ");
    }
    public static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }
}
