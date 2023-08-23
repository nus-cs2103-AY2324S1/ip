import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ChatbotAlain{
    public static void main(String[] args) throws AlainException {
        try {
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
                    if (mission.length() == 0) {
                        throw new AlainException(" ☹ OOPS!!! The description of a Todo cannot be empty.");
                    }
                    list[pos] = new ToDos(mission);
                    pos++;
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
                    if (mission.length() == 0) {
                        throw new AlainException(" ☹ OOPS!!! The description of a Deadline cannot be empty.");
                    }
                    String[] parts = mission.split("/");
                    if (parts.length != 2) {
                        throw new AlainException(" ☹ OOPS!!! The description of a Deadline is invalid");
                    }
                    list[pos] = new Deadlines(parts[0], parts[1]);
                    pos++;
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
                    if (mission.length() == 0) {
                        throw new AlainException(" ☹ OOPS!!! The description of a Event cannot be empty.");
                    }
                    String[] parts = mission.split("/");
                    if (parts.length != 3) {
                        throw new AlainException(" ☹ OOPS!!! The description of a Event is invalid");
                    }
                    list[pos] = new Events(parts[0], parts[1], parts[2]);
                    pos++;
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
                    list[Integer.parseInt(numericPart) - 1].markAsDone();
                    String output = "____________________________________________________________\n"
                            + " Nice! I've marked this task as done:\n"
                            + "   " + list[Integer.parseInt(numericPart) - 1] + "\n"
                            + "____________________________________________________________\n";
                    System.out.println(output);
                } else if (isMatchUnmark) {
                    String numericPart = text.substring(6);
                    list[Integer.parseInt(numericPart) - 1].markAsUndone();
                    String output = "____________________________________________________________\n"
                            + " OK, I've marked this task as not done yet:\n"
                            + "   " + list[Integer.parseInt(numericPart) - 1] + "\n"
                            + "____________________________________________________________\n";
                    System.out.println(output);
                } else if (text.equals("list")) {
                    String output = "";
                    output += "____________________________________________________________\n"
                            + "Here are the tasks in your list:\n";
                    for (int i = 0; i < pos; i++) {
                        output += " " + (i + 1) + ". " + list[i] + "\n";
                    }
                    output += "____________________________________________________________\n";
                    System.out.println(output);

                }
                throw new AlainException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            String endingLine = "____________________________________________________________\n"
                    + " Bye. Hope to see you again soon!\n"
                    + "____________________________________________________________\n";
            System.out.println(endingLine);
        } catch (AlainException e) {
            System.out.println("____________________________________________________________\n");
            System.out.println(e.getMessage());
            System.out.println("____________________________________________________________\n");
        }
    }
}
