import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tSup bro! I'm Brobot");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            String taskType = words[0];

            if (input.equals("bye")) {
                // exit program
                break;
            } else if (input.equals("list")) {
                // display current list
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + "." + list.get(i));
                }
                System.out.println("\t-----------------------------------------------");

            } else if (words[0].equals("mark")) {
                // mark task as done
                int index = Integer.parseInt(words[1]) - 1;

                if (index < 0 || index >= list.size()) {
                    // handle invalid input
                    System.out.println("Invalid number ");
                } else {
                    list.get(index).markAsDone();
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t" + list.get(index));
                    System.out.println("\t-----------------------------------------------");
                }


            } else if (words[0].equals("unmark")) {
                // mark task as undone
                int index = Integer.parseInt(words[1]) - 1;

                if (index < 0 || index >= list.size()) {
                    // handle invalid input
                    System.out.println("Invalid number ");
                } else {
                    list.get(index).unMark();
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tNice! I've marked this task as not done yet:");
                    System.out.println("\t" + list.get(index));
                    System.out.println("\t-----------------------------------------------");
                }

            } else if (taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event")) {
                // add task
                String description;
                Task task;
                if (taskType.equals("todo")) {
                    // Define regular expressions for pattern matching for todo
                    Pattern todoPattern = Pattern.compile("todo\\s+(.*?)$");

                    // Match the input string with the pattern
                    Matcher matcher = todoPattern.matcher(input);

                    // Check if the input string matches the pattern
                    if (matcher.matches()) {
                        description = matcher.group(1); // Extract task name
                        task = new Todo(description);
                    } else {
                        System.out.println("\t-----------------------------------------------");
                        System.out.println("\tInput doesn't match the expected format.");
                        System.out.println("\t-----------------------------------------------");
                        continue;
                    }

                } else if (taskType.equals("deadline")) {
                    // Define regular expressions for pattern matching for deadline
                    Pattern deadlinePattern = Pattern.compile("deadline\\s+(.*?)\\s+/by\\s+(.*?)$");

                    // Match the input string with the pattern
                    Matcher matcher = deadlinePattern.matcher(input);
                    // Check if the input string matches the pattern
                    if (matcher.matches()) {
                        description = matcher.group(1); // Extract task name
                        String dueDate = matcher.group(2);  // Extract due date
                        task = new Deadline(dueDate, description);
                    } else {
                        System.out.println("\t-----------------------------------------------");
                        System.out.println("\tInput doesn't match the expected format.");
                        System.out.println("\t-----------------------------------------------");
                        continue;
                    }

                } else {
                    // Define regular expressions for pattern matching for event
                    Pattern eventPattern = Pattern.compile("event\\s+(.*?)\\s+/from\\s+(.*?)\\s+/to\\s+(.*?)$");
                    Matcher matcher = eventPattern.matcher(input);
                    if (matcher.matches()) {
                        description = matcher.group(1); // Extract event name
                        String startTime = matcher.group(2); // Extract start time
                        String endTime = matcher.group(3);   // Extract end time
                        task = new Event(startTime, endTime, description);
                    } else {
                        System.out.println("\t-----------------------------------------------");
                        System.out.println("\tInput doesn't match the expected format.");
                        System.out.println("\t-----------------------------------------------");
                        continue;
                    }
                }
                list.add(task);
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tGot it bro! I've added this task:\n\t\t" + task);
                System.out.println("\tNow you have " + list.size() + " tasks in the list");
                System.out.println("\t-----------------------------------------------");
            } else {
                // invalid input
                System.out.println("\t-----------------------------------------------");
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t-----------------------------------------------");
            }
        }

        System.out.println("\t-----------------------------------------------");
        System.out.println("\tBye. Hope to see you again soon bro!");
        System.out.println("\t-----------------------------------------------");
    }
}
