import java.util.Scanner;

public class Duke {

    private static Scanner prompt = new Scanner(System.in);
    private static List taskList = new List();
    public static void main(String[] args) {
        greet();
    }

    public static void greet() {
        System.out.println("Hello! I'm Oranges.");
        System.out.println("What can I do for you?");
        echo();
    }

    public static void echo() {
        String promptText = prompt.nextLine();
        if (promptText.equals("bye")) {
            exit();
        }
        else if (promptText.equals("list")) {
            list();
        }
        else if (promptText.startsWith("todo") || promptText.startsWith("deadline") || promptText.startsWith("event")) {
            createTask(promptText);
        }
        else if (promptText.startsWith("mark") || promptText.startsWith("unmark")){
            markTask(promptText);
        }
        else {
            System.out.println("sorry, i don't recognise that command.");
        }
        echo();
    }

    public static void createTask(String promptText) {
        if (promptText.startsWith("todo")) {
            Task todo = new Todo(promptText.substring(5));
            taskList.add(todo);
        }
        else if (promptText.startsWith("deadline")) {
            String[] parts = promptText.split("/");
            Task deadline = new Deadline(parts[0].substring(9), parts[1].substring(2));
            taskList.add(deadline);
        }
        else {
            String[] parts = promptText.split("/");
            Task event = new Event(parts[0].substring(6), parts[1].substring(4), parts[2].substring(2));
            taskList.add(event);
        }
    }
    public static void markTask(String promptText) {
        int i = Integer.parseInt(promptText.substring(promptText.length() - 1));
        Task t = taskList.get(i-1);
        if (promptText.startsWith("unmark")) {
            t.unmark();
        }
        else {
            t.mark();
        }
    }



    public static void list() {
        taskList.list();
        echo();
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
