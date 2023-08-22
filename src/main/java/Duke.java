import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm froggie! ");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        boolean notEnd = true;

        while (notEnd) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                notEnd = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(input);
            }
        }

        scanner.close();
    }
}
