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
            boolean isDeadline = Pattern.matches("deadline .+", text);
            boolean isToDo = Pattern.matches("todo .+", text);
            boolean isEvent = Pattern.matches("event .+", text);
            if (isToDo) {
                String mission = text.substring(4);
                list[pos] = new ToDos(mission);
                pos ++;
                String output = "____________________________________________________________\n"
                        + "  Got it. I've added this task:\n"
                        + "   " + list[pos - 1] + "\n"
                        + "Now you have " + pos + " tasks in the list.\n"
                        + "____________________________________________________________\n";
                System.out.println(output);
                continue;
            }
            if (isDeadline) {
                String mission = text.substring(8);
                String[] parts = mission.split("/");
                list[pos] = new Deadlines(parts[0], parts[1]);
                pos ++;
                String output = "____________________________________________________________\n"
                        + "  Got it. I've added this task:\n"
                        + "   " + list[pos - 1] + "\n"
                        + "Now you have " + pos + " tasks in the list.\n"
                        + "____________________________________________________________\n";
                System.out.println(output);
                continue;
            }
            if (isEvent) {
                String mission = text.substring(5);
                String[] parts = mission.split("/");
                list[pos] = new Events(parts[0], parts[1], parts[2]);
                pos ++;
                String output = "____________________________________________________________\n"
                        + "  Got it. I've added this task:\n"
                        + "   " + list[pos - 1] + "\n"
                        + "Now you have " + pos + " tasks in the list.\n"
                        + "____________________________________________________________\n";
                System.out.println(output);
                continue;
            }
            if (text.equals("bye")) {
                break;
            } else if (isMatchMark) {
                String numericPart = text.substring(4);
                list[Integer.parseInt(numericPart) -1].markAsDone();
                String output = "____________________________________________________________\n"
                        + " Nice! I've marked this task as done:\n"
                        + "   " + list[Integer.parseInt(numericPart) - 1] + "\n"
                        + "____________________________________________________________\n";
                System.out.println(output);
            }else if (isMatchUnmark) {
                String numericPart = text.substring(6);
                list[Integer.parseInt(numericPart) - 1].markAsUndone();
                String output = "____________________________________________________________\n"
                        + " OK, I've marked this task as not done yet:\n"
                        + "   " + list[Integer.parseInt(numericPart) - 1] + "\n"
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
                    output += " " + (i + 1) + ". " + list[i] + "\n";
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
