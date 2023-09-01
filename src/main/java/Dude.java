import java.util.Scanner;

public class Dude {

    static Task[] taskList = new Task[100];
    static int nTasks = 0;

    public static void addTodo(String task) {
        ToDo newTask = new ToDo(task);
        taskList[nTasks] = newTask;
        System.out.printf("Got it. I've added this task:\n%s\n", newTask.toString());
        nTasks += 1;
        System.out.printf("Now you have %d tasks in the list. \n", nTasks);
    }
    public static void addDeadline(String task, String by) {
        Deadline newTask = new Deadline(task, by);
        taskList[nTasks] = newTask;
        System.out.printf("Got it. I've added this task:\n%s\n", newTask.toString());
        nTasks += 1;
        System.out.printf("Now you have %d tasks in the list. \n", nTasks);
    }
    public static void addEvent(String task, String from, String to) {
        Event newTask = new Event(task, from, to);
        taskList[nTasks] = newTask;
        System.out.printf("Got it. I've added this task:\n%s\n", newTask.toString());
        nTasks += 1;
        System.out.printf("Now you have %d tasks in the list. \n", nTasks);
    }

    public static void list() {
        for (int i = 0; i < nTasks; i++) {
            Task task = taskList[i];
            System.out.printf("%d. %s\n", i+1, task.toString());
        }
    }

    public static void mark(int n) {
        taskList[n-1].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("%d. %s\n", n, taskList[n-1].toString());
    }

    public static void unmark(int n) {
        taskList[n-1].setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("%d. %s\n", n, taskList[n-1].toString());
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
            } else if (words[0].equals("mark")) {
                mark(Integer.valueOf(words[1]));
            } else if (words[0].equals("unmark")) {
                unmark(Integer.valueOf(words[1]));
            } else if (words[0].equals("todo")) {
                if (words.length > 1) {
                    addTodo(input.substring(5));
                } else {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (words[0].equals("deadline")) {
                if (words.length == 1) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] taskWords = input.substring(9).split(" /");
                String by = taskWords[1].substring(3);
                addDeadline(taskWords[0], by);
            } else if (words[0].equals("event")) {
                if (words.length == 1) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                }
                String[] taskWords = input.substring(6).split(" /");
                String from = taskWords[1].substring(5);
                String to = taskWords[2].substring(3);
                addEvent(taskWords[0], from, to);
            } else {
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            }



        }
    }
}