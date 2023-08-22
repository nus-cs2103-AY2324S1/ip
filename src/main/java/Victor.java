import java.util.Scanner;

public class Victor {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner reader = new Scanner(System.in);
        System.out.println("Hello! I'm Victor\n" +
                "What can I do for you?\n----------\n");

        while (true) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.printf("%s\n----------\n", input);
            }
        }
    }
}
