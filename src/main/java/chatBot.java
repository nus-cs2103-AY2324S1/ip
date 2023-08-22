import java.util.Scanner;
public class chatBot {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Desolute\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        String next = sc.next();

        while (!next.equals("bye")) {
            nextCommand(next);
            next = sc.next();
        }

        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    private static void nextCommand(String str) {
        switch(str) {
            case "list":
                System.out.println("____________________________________________________________\n" +
                        " list\n" +
                        "____________________________________________________________\n");
                break;
            case "blah":
                System.out.println("____________________________________________________________\n" +
                        " blah\n" +
                        "____________________________________________________________\n");
                break;
            default:
                System.out.println("____________________________________________________________\n" +
                        " No such command! Please try again!\n" +
                        "____________________________________________________________\n");
        }
    }
}
