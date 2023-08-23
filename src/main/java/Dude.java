import java.util.Scanner;

public class Dude {

    static Task[] taskList = new Task[100];
    static int nTasks = 0;

    public static void addTask(String task) {
        Task newTask = new Task(task);
        taskList[nTasks] = newTask;
        nTasks += 1;
        System.out.printf("added %s \n", newTask.getDescription());
    }

    public static void list() {
        String doneStatus = "[ ]";
        for (int i = 0; i < nTasks; i++) {
            Task task = taskList[i];
            if (task.isDone()) {
                doneStatus = "[x]";
            }
            System.out.printf("%d. %s %s \n", i + 1, doneStatus, task.getDescription());
            doneStatus = "[ ]";
        }
    }

    public static void bye() {
        String greeting = "Bye. Hope to see you again soon!";
        System.out.println(greeting);
    }

    public static void main(String[] args) {
        String greeting = "Hello, I'm Dude! \n" +
                "What can I do for you?";
        System.out.println(greeting);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] words = input.split(" ");

            if (words[0].equals("list")) {
                list();
            } else if (words[0].equals("bye")) {
                bye();
                break;
            } else {
                addTask(input);
            }


        }
    }
}