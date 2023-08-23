import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String linebreak = "____________________________________________________________";
        System.out.println(linebreak);
        String greeting = "Hello! I'm chatbot\nWhat can I do for you?";
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(greeting);
        System.out.println(linebreak);
        Scanner sc = new Scanner(System.in);
        do {
            String echo = sc.next();
            if (echo.equals("bye")) {
                break;
            } else {
                System.out.println(linebreak);
                System.out.println(echo);
                System.out.println(linebreak);
            }
        } while (true);
        System.out.println(linebreak);
        System.out.println(bye);
        System.out.println(linebreak);

    }
}
