import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private String name = "Lakinta";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> tasks = new ArrayList<>();
    private ArrayList<Task> taskArrayList = new ArrayList<>();
    private int count = 1;

    public void greeting() {
        System.out.println("Hello! I'm " + name +
                "\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void echo() {
        while (true) {
            String response = scanner.nextLine();
            if (response.equals("bye")) {
                exit();
                break;
            } else {
                System.out.println(response);
            }
        }
        scanner.close();
    }

    public void addTask() {
        while (true) {
            String response = scanner.nextLine();
            if (response.equals("bye")) {
                exit();
                break;
            } else if (response.equals("list")) {
                tasks.forEach(x -> System.out.println(x));
            } else {
                tasks.add(String.valueOf(count) + ". " + response);
                count++;
                System.out.println("added: " + response);
            }
        }
        scanner.close();
    }

    public void chat() {
        while (true) {
            String response = scanner.nextLine();

            if (response.equals("bye")) {
                exit();
                break;
            } else if (response.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                taskArrayList.forEach(x -> System.out.println(x.toString()));
            } else if (response.length() > 5 && response.startsWith("mark ")) {
                int id = Integer.valueOf(response.substring(5));
                taskArrayList.get(id - 1).markAsDone();
            } else if (response.length() > 7 && response.startsWith("unmark ")) {
                int id = Integer.valueOf(response.substring(7));
                taskArrayList.get(id - 1).markAsUndone();
            } else {
                taskArrayList.add(new Task(response, count));
                count++;
                System.out.println("added: " + response);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Duke myBot = new Duke();
        myBot.greeting();
        //myBot.echo();
        //myBot.addTask();
        myBot.chat();
    }
}
