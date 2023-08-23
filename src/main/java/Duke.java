import java.util.Scanner;

public class Duke {

    Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.start();

    }
    public void line() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public void start() {
        line();
        System.out.println(" Hello! I'm JARVIS");
        System.out.println("What can I do for you?");
        line();

        while (true) {
            String output = userInput.nextLine();
            if (output.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                line();
                break;
            } else {
                System.out.println(output);
                line();
            }
        }

    }

}
