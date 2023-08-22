import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        String line = "    ______________________________________________";
        System.out.println(line + "\n    Hello, I'm ChadGPT :)\n    What can I do for you?\n" + line);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("    %d. " + list.get(i), i+1));
                }
                System.out.println(line);
            } else {
                System.out.println(line + "\n    added: " + command + "\n" + line);
                list.add(command);
            }
            command = scanner.nextLine();
        }
        System.out.println(line + "\n    Bye. Hope to see you again soon!\n" + line);
    }
}
