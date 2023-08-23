import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String text1 = " Hello! I'm Novo\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        String text2 = " Bye. Hope to see you again soon!";
        System.out.println("____________________________________________________________\n"
                + text1);

        Scanner sc = new Scanner(System.in);
        String user_text = sc.nextLine();

        while (!user_text.isEmpty()) {
            if (user_text.equals("bye")) {
                System.out.println("____________________________________________________________\n"
                        + text2 + "\n"
                        + "____________________________________________________________\n");
                break;
            } else {
                System.out.println("____________________________________________________________\n"
                        + user_text + "\n"
                        + "____________________________________________________________\n");
            }
            user_text = sc.nextLine();
        }
    }
}
