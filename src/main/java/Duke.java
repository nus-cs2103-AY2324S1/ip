import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    Scanner userInput = new Scanner(System.in);
    ArrayList<String> taskList = new ArrayList<String>();
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
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                line();
                break;
            } else if(input.equals("list")) {
                line();
                for (int i = 0; i < taskList.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + taskList.get(i));
                }
            } else {
                taskList.add(input);
                System.out.println("added: " + input);
                line();
            }
        }
    }
}
