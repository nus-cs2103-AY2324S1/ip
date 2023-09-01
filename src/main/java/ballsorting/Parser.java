package ballsorting;
import java.time.LocalDateTime;
import java.util.Scanner;
public class Parser {
    private Scanner sc;
    public Parser(Scanner sc) {
        this.sc = sc;
    }
    public void parseUserInput(String input, TaskList taskList) {
        if (input.equals("list")) {

            System.out.println("Here are the tasks in your list:");
            taskList.printList();
            System.out.println(Ballsorter.LINE);

        } else if (input.startsWith("mark")) {

            int target = Integer.parseInt(input.substring(5)) - 1;
            taskList.markTask(target);

        } else if (input.startsWith("unmark")) {

            int target = Integer.parseInt(input.substring(7)) - 1;
            taskList.unmarkTask(target);

        } else if (input.startsWith("delete")) {

            int target = Integer.parseInt(input.substring(7)) - 1;
            taskList.deleteTask(target);

        } else {

            Task curr;
            StringBuilder description = new StringBuilder();
            StringBuilder start = new StringBuilder();

            if (input.startsWith("todo")) {

                String des = input.substring(4).trim();
                if (des.equals("")) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(Ballsorter.LINE);
                    curr = null;
                } else {
                    curr = new Todo(des);
                }

            } else if (input.startsWith("deadline")) {

                int i = 9;
                while (i < input.length() && input.charAt(i) != '/') {
                    description.append(input.charAt(i));
                    i++;
                }
                i += 4;
                if (description.toString().equals("")) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(Ballsorter.LINE);
                    curr = null;
                } else if (i >= input.length() || input.substring(i).equals("")) {
                    System.out.println("☹ OOPS!!! The deadline of a deadline cannot be empty.");
                    System.out.println(Ballsorter.LINE);
                    curr = null;
                } else {
                    LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
                    curr = new Deadline(description.toString(), endDateTime);
                }


            } else if (input.startsWith("event")) {

                int i = 6;
                while (i < input.length() && input.charAt(i) != '/') {
                    description.append(input.charAt(i));
                    i++;
                }
                i += 6;
                while (i < input.length() && input.charAt(i) != '/') {
                    start.append(input.charAt(i));
                    i++;
                }
                i += 4;
                if (description.toString().equals("")) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    System.out.println(Ballsorter.LINE);
                    curr = null;
                } else if (start.toString().equals("")) {
                    System.out.println("☹ OOPS!!! The start time of an event cannot be empty.");
                    System.out.println(Ballsorter.LINE);
                    curr = null;
                } else if (i >= input.length() || input.substring(i).equals("")) {
                    System.out.println("☹ OOPS!!! The end time of an event cannot be empty.");
                    System.out.println(Ballsorter.LINE);
                    curr = null;
                } else {
                    LocalDateTime startDateTime = LocalDateTime.parse(start.toString(), Ballsorter.inputFormatter);
                    LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
                    curr = new Event(description.toString(), startDateTime, endDateTime);
                }

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(Ballsorter.LINE);
                curr = null;
            }

            if (curr != null) taskList.addTask(curr);
        }
    }
}
