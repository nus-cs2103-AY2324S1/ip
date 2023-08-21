import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Bongo!\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                String goodbyeMessage = "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n";
                System.out.println(goodbyeMessage);
                break;
            }
            String echoMessage = "____________________________________________________________\n" +
                    String.format(" %s\n", input) +
                    "____________________________________________________________\n";
            System.out.println(echoMessage);
        }
    }
}

