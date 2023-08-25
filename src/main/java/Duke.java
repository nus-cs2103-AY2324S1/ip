import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Husky\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        while (true) {
            String echo = io.nextLine();
            if (echo.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;
            } else if (echo.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, list.get(i)));
                }
                System.out.println("____________________________________________________________");
            } else {
                list.add(echo);
                System.out.println("____________________________________________________________\n" + "added: " + echo + "\n" +
                        "____________________________________________________________");
            }
        }
    }
}

