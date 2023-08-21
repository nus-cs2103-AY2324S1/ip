import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting =
                "____________________________________________________________\n" +
                " Hello! I'm Pixel\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
