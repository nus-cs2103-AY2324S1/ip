import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private String name = "Lakinta";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> tasks = new ArrayList<>();

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
        int count = 1;
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
        myBot.addTask();
    }
}
