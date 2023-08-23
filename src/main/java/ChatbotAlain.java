import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ChatbotAlain {
    public static void main(String[] args) {
        Task[] list = new Task[200];
        int pos = 0;
        String renameAndGreeting = "____________________________________________________________\n"
                + " Hello! I'm Alain\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(renameAndGreeting);
        while (true) {
            Scanner s = new Scanner(System.in);
            String text = new String();
            text = s.nextLine();
            boolean isMatchMark = Pattern.matches("mark\\d+", text);
            boolean isMatchUnmark = Pattern.matches("unmark\\d+", text);
            if (text.equals("bye")) {
                break;
            } else if (isMatchMark) {
                String numericPart = text.substring(4);
                list[Integer.parseInt(numericPart) -1].markAsDone();
                String output = "____________________________________________________________\n"
                        + " Nice! I've marked this task as done:\n"
                        + "   [X]" +  list[Integer.parseInt(numericPart) - 1].getName() + "\n"
                        + "____________________________________________________________\n";
                System.out.println(output);
            }else if (isMatchUnmark) {
                String numericPart = text.substring(6);
                list[Integer.parseInt(numericPart) - 1].markAsUndone();
                String output = "____________________________________________________________\n"
                        + " OK, I've marked this task as not done yet:\n"
                        + "   [ ]" +  list[Integer.parseInt(numericPart) - 1].getName() + "\n"
                        + "____________________________________________________________\n";
                System.out.println(output);
            }else if (! text.equals("list")){
                list[pos] = new Task(text);
                pos ++;
                String output = "____________________________________________________________\n"
                        + " " + text + "\n"
                        + "____________________________________________________________\n";
                System.out.println(output);

            } else {
                String output = "";
                output += "____________________________________________________________\n"
                 + "Here are the tasks in your list:\n";
                for (int i = 0; i < pos; i++ ) {
                    output += " " + (i + 1) + ". " + list[i].getStatusIcon() + list[i].getName() + "\n";
                }
                output += "____________________________________________________________\n";
                System.out.println(output);
            }
        }
        String endingLine = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";

        System.out.println(endingLine);
    }
}
