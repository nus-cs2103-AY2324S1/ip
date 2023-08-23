import java.util.Scanner;

public class Dude {

    static String[] taskList = new String[100];
    static int nTasks = 0;

    public static void addTask(String task) {
        taskList[nTasks] = task;
        nTasks += 1;
        System.out.printf("added %s \n", task);
    }

    public static void list() {
        for (int i = 0; i < nTasks; i++) {
            System.out.printf("%d. %s \n", i + 1, taskList[i]);
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

            if (input.equals("list")) {
                list();
            } else if (input.equals("bye")) {
                bye();
                break;
            } else {
                addTask(input);
            }


        }
    }
}