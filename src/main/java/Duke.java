import java.util.Scanner;
public class Duke {
    private static String GREETINGS = "Hello! I'm AChatBot\n" +
            "What can I do for you?";
    private static String FAREWELL = "Bye. Hope to see you again soon!";
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println(GREETINGS);
        while (true) {
            String input = s.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
        }
        System.out.println(FAREWELL);
        s.close();
    }
}
