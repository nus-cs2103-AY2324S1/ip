import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Duke {


    public static void printHorizontalLine() {

        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        if (command.equals("bye")) {
            return;
        }
        printHorizontalLine();
        System.out.println(command);
        printHorizontalLine();
        echo();
    }

    public static void introduction() {
        String name = "Donk";
        printHorizontalLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void conclusion() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you soon again soon!");
        printHorizontalLine();
    }


    public static void toDo() {
        ArrayList<Task> list = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        Pattern listRegex = Pattern.compile("^list", Pattern.CASE_INSENSITIVE);
        Pattern markRegex = Pattern.compile("^mark ", Pattern.CASE_INSENSITIVE);
        Pattern unmarkRegex = Pattern.compile("^unmark ", Pattern.CASE_INSENSITIVE);
        Pattern deadlineRegex = Pattern.compile("^deadline ", Pattern.CASE_INSENSITIVE);
        Pattern todoRegex = Pattern.compile("^todo ", Pattern.CASE_INSENSITIVE);
        Pattern eventRegex = Pattern.compile("^event", Pattern.CASE_INSENSITIVE);
        while(true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                return;
            } else if (listRegex.matcher(command).find()) {
                printHorizontalLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    int printIndex = i + 1;
                    System.out.println(printIndex + "." + list.get(i).toString());
                }
                printHorizontalLine();
                continue;
            } else if (markRegex.matcher(command).find()) {
                int curr = Integer.parseInt(command.substring(5)) - 1;
                Task currTask = list.get(curr);
                currTask.markDone();
                continue;
            } else if (unmarkRegex.matcher(command).find()) {
                int curr = Integer.parseInt(command.substring(7)) - 1;
                Task currTask = list.get(curr);
                currTask.markUndone();
                continue;
            } else if (deadlineRegex.matcher(command).find()) {
                printHorizontalLine();
                System.out.println("Got it, I've added this deadline.");
                String currStr = command.substring(9);
                int dateIndex = currStr.indexOf("/");
                String date = currStr.substring(dateIndex + 4);
                String description = currStr.substring(0, dateIndex);
                Task deadlineTask = new Deadline(description, date);
                list.add(deadlineTask);
                System.out.println(deadlineTask.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list." );
                printHorizontalLine();
                continue;
            } else if (todoRegex.matcher(command).find()) {
                printHorizontalLine();
                System.out.println("Got it, I've added this task.");
                String description = command.substring(5);
                Task todoTask = new Todo(description);
                list.add(todoTask);
                System.out.println(todoTask.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list." );
                printHorizontalLine();
                continue;
            } else if (eventRegex.matcher(command).find()) {
                printHorizontalLine();
                System.out.println("Got it, I've added this event.");
                String currStr = command.substring(6);
                int fromIndex = currStr.indexOf("/");
                String description = currStr.substring(0, fromIndex);
                currStr = currStr.substring(fromIndex + 6);
                int toIndex = currStr.indexOf("/");
                String from = currStr.substring(0, toIndex);
                String to = currStr.substring(toIndex + 4);
                Task eventTask = new Event(description, from, to);
                list.add(eventTask);
                System.out.println(eventTask.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list." );
                printHorizontalLine();
                continue;
            }


            list.add(new Task(command));
            printHorizontalLine();
            System.out.println("added: " + command);
            printHorizontalLine();
        }
    }




    public static void main(String[] args) {
        introduction();

        toDo();

        conclusion();


    }
}
