import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Victor {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner reader = new Scanner(System.in);
        List list = new ArrayList(100);
        System.out.println("Hello! I'm Victor\n" +
                "What can I do for you?\n----------\n");

        while (true) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, list.get(i));
                }
                System.out.println("----------\n");
            } else {
                list.add(input);
                System.out.printf("added: %s\n----------\n", input);
            }
        }
    }
}
