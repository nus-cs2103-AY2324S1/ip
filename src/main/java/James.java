import java.util.Scanner;

public class James {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line + "\n" + "Hello! I'm James\n" + "What can I do for you?\n" + line);

        // User Input
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            System.out.println(line + "\n" + input + "\n" + line);
            input = in.nextLine();
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);

    }
}
