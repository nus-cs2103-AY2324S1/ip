import java.util.Scanner;

public class TehO {
    static int taskCounter = 0;
    static String[] taskList = new String[100];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm TehO \nWhat can I do for you?");

        while (true) {
            String userCommand = sc.nextLine();

            if (userCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userCommand.equals("list")) {
                listTask(taskList);
            } else {
                addTask(taskList, userCommand);
            }
        }
    }

    public static void addTask(String[] taskList, String task) {
        taskList[taskCounter] = task;
        taskCounter++;
        System.out.println("added: " + task);
    }

    public static void listTask(String[] taskList) {
        for (int i = 0; i < taskCounter; i++) {
            System.out.println((i+1) + ". " + taskList[i]);
        }
    }
}
