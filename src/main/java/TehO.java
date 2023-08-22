import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class TehO {
    static int taskCounter = 0;
    static Task[] taskList = new Task[100];

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
            } else if (userCommand.startsWith("mark")) {
                //note that split returns a String[]
                //parseInt returns the integer value which is represented by the argument
                int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
                Task task = taskList[taskNumber];
                task.markAsDone(taskNumber);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.toString());
            } else if (userCommand.startsWith("unmark")) {
                int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
                Task task = taskList[taskNumber];
                task.markAsNotDone(taskNumber);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task.toString());
            } else {
                Task task = new Task(userCommand);
                addTask(task);
            }
        }
    }


    public static void addTask(Task newTask) {
        taskList[taskCounter] = newTask;
        taskCounter++;
        System.out.println("added: " + newTask.description);
    }

    public static void listTask(Task[] taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++) {
            Task task = taskList[i];
            System.out.println((i+1) + ". " + task.toString());
        }
    }
}
