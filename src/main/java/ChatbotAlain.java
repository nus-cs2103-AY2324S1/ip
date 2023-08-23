import java.util.Scanner;

public class ChatbotAlain {
    public static void main(String[] args) {
        String renameAndGreeting = "____________________________________________________________\n"
                + " Hello! I'm Alain\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n";
        System.out.println(renameAndGreeting);
        while (true) {
            Scanner s = new Scanner(System.in);
            String text = new String();
            text = s.nextLine();
            if (text.equals("bye")) {
                break;
            } else {
                String output = "____________________________________________________________\n"
                        + " " + text + "\n"
                        + "____________________________________________________________\n";
                System.out.println(output);
            }
        }
        String endingLine = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(endingLine);
    }
}
