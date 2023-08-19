import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greeting = "-------------------------------\n"
                + "Hello! I'm Skog.\n"
                + "What can I do for you?\n"
                + "-------------------------------\n";

        String exit = "-------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "-------------------------------\n";

        System.out.println(greeting);

        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (!str.equals("bye")) {
                System.out.println("-------------------------------\n"
                + str
                + "\n-------------------------------\n");
            } else {
                System.out.println(exit);
                break;
            }
        }
    }
}
