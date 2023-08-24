import java.util.Scanner;
public class Duke {
    private String name = "Lakinta";
    private Scanner scanner = new Scanner(System.in);

    public String greeting() {
        return "Hello! I'm " + name +
                "\nWhat can I do for you?";
    }

    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    public void echo() {
        while (true) {
            String response = scanner.nextLine();
            if (response.equals("bye")) {
                System.out.println(exit());
                break;
            } else {
                System.out.println(response);
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
        System.out.println(myBot.greeting());
        myBot.echo();

    }
}
