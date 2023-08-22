import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class Duke {


    public static void printHorizontalLine() {

        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
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
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Pattern listRegex = Pattern.compile("^list", Pattern.CASE_INSENSITIVE);
        Pattern markRegex = Pattern.compile("^mark", Pattern.CASE_INSENSITIVE);
        Pattern unmarkRegex = Pattern.compile("^unmark", Pattern.CASE_INSENSITIVE);
        Pattern deadlineRegex = Pattern.compile("^deadline", Pattern.CASE_INSENSITIVE);
        Pattern todoRegex = Pattern.compile("^todo", Pattern.CASE_INSENSITIVE);
        Pattern eventRegex = Pattern.compile("^event", Pattern.CASE_INSENSITIVE);
        Pattern deleteRegex = Pattern.compile("^delete", Pattern.CASE_INSENSITIVE);
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
            } else if (markRegex.matcher(command).find()) {
                int curr = Integer.parseInt(command.substring(5)) - 1;
                Task currTask = list.get(curr);
                currTask.markDone();
            } else if (unmarkRegex.matcher(command).find()) {
                int curr = Integer.parseInt(command.substring(7)) - 1;
                Task currTask = list.get(curr);
                currTask.markUndone();
            } else if (deadlineRegex.matcher(command).find()) {
                try {
                    if (command.length() < 9) {
                        throw new DukeException("Deadline Argument Empty");
                    }
                }
                catch (DukeException e) {
                    printHorizontalLine();
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    printHorizontalLine();
                    continue;
                }
                printHorizontalLine();
                System.out.println("Got it, I've added this deadline.");
                String currStr = command.substring(9);
                int dateIndex = currStr.indexOf("/");
                String date = currStr.substring(dateIndex + 4);
                String description = currStr.substring(0, dateIndex);
                Task deadlineTask = new Deadline(description, date);
                list.add(deadlineTask);
                System.out.println(deadlineTask);
                System.out.println("Now you have " + list.size() + " tasks in the list." );
                printHorizontalLine();
            } else if (todoRegex.matcher(command).find()) {
                try {
                    if (command.length() < 5) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                }
                catch (DukeException e) {
                    printHorizontalLine();
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    printHorizontalLine();
                    continue;
                }
                printHorizontalLine();
                System.out.println("Got it, I've added this task.");
                String description = command.substring(5);
                Task todoTask = new Todo(description);
                list.add(todoTask);
                System.out.println(todoTask);
                System.out.println("Now you have " + list.size() + " tasks in the list." );
                printHorizontalLine();
            } else if (eventRegex.matcher(command).find()) {
                try {
                    if (command.length() < 6) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                }
                catch (DukeException e) {
                    printHorizontalLine();
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    printHorizontalLine();
                    continue;
                }
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
                System.out.println(eventTask);
                System.out.println("Now you have " + list.size() + " tasks in the list." );
                printHorizontalLine();
            } else if (deleteRegex.matcher(command).find()) {
                printHorizontalLine();
                System.out.println("Noted. I've removed this task:");
                int indexToRemove = Integer.parseInt(command.substring(7)) - 1;
                Task taskToRemove = list.get(indexToRemove);
                System.out.println(taskToRemove);
                list.remove(indexToRemove);
                System.out.println("Now you have " + list.size() + " tasks in the list." );
                printHorizontalLine();

            } else {
                try {
                    throw new DukeException("Invalid Response");
                } catch (DukeException e) {
                    printHorizontalLine();
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    printHorizontalLine();
                }
            }
        }
    }




    public static void main(String[] args) {
        introduction();

        toDo();

        conclusion();



    }
}
