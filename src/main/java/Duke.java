import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "    ______________________________________________";
        System.out.println(line + "\n    Hello, I'm ChadGPT :)\n    What can I do for you?\n" + line);
        String command = scanner.nextLine();
        while (!command.equals("bye"))
        {
            System.out.println(line + "\n    " + command + "\n" + line);
            command = scanner.nextLine();
        }
        System.out.println(line + "\n    Bye. Hope to see you again soon!\n" + line);
    }
}
