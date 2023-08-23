import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains the chatbot Brobot. It allows users to add and delete different types of tasks and mark them
 * as complete or incomplete
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tSup bro! I'm Brobot");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t-----------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        while (true) {
            // keep prompting user until user enters "bye"
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            String taskType = words[0];

            if (input.equals("bye")) {
                // exit program
                break;
            } else if (input.equals("list")) {
                // displays items in current list
                System.out.println("\t-----------------------------------------------");
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + "." + list.get(i));
                }
                System.out.println("\t-----------------------------------------------");

            } else if (words[0].equals("mark")) {
                // mark task as done
                try {
                    int index = Integer.parseInt(words[1]) - 1;
                    list.get(index).markAsDone();
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t" + list.get(index));
                    System.out.println("\t-----------------------------------------------");
                } catch (IndexOutOfBoundsException e) {
                    // number input is invalid
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tInvalid number");
                    System.out.println("\t-----------------------------------------------");
                } catch (NumberFormatException e) {
                    // user did not enter a number
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tPlease key in a number");
                    System.out.println("\t-----------------------------------------------");
                }


            } else if (words[0].equals("unmark")) {
                // mark task as undone
                try {
                    int index = Integer.parseInt(words[1]) - 1;
                    list.get(index).unMark();
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tNice! I've marked this task as not done yet:");
                    System.out.println("\t" + list.get(index));
                    System.out.println("\t-----------------------------------------------");
                } catch (IndexOutOfBoundsException e) {
                    // number input is invalid
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("Invalid number");
                    System.out.println("\t-----------------------------------------------");
                } catch (NumberFormatException e) {
                    // user did not enter a number
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("Please key in a number");
                    System.out.println("\t-----------------------------------------------");
                }

            } else if (words[0].equals("delete")) {
                // delete task
                try {
                    int index = Integer.parseInt(words[1]) - 1;
                    list.get(index);
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tNoted! I've removed this task from the list:");
                    System.out.println("\t\t" + list.get(index));
                    list.remove(index);
                    System.out.println("\tNow you have " + list.size() + " tasks in the list");
                    System.out.println("\t-----------------------------------------------");
                } catch (IndexOutOfBoundsException e) {
                    // number input is invalid
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("Invalid number");
                    System.out.println("\t-----------------------------------------------");
                } catch (NumberFormatException e) {
                    // user did not enter a number
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("Please key in a number");
                    System.out.println("\t-----------------------------------------------");
                }
            } else {
                // add task
                try {
                    String description;
                    Task task;
                    if (taskType.equals("todo")) {
                        // Define regular expressions for pattern matching for todo
                        Pattern todoPattern = Pattern.compile("todo\\s+(.*?)$");

                        // Match the input string with the pattern
                        Matcher matcher = todoPattern.matcher(input);

                        // Check if the input string matches the pattern
                        if (matcher.matches()) {
                            description = matcher.group(1); // Extract task description
                            task = new Todo(description);
                        } else {
                            // Todo description is empty
                            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty."
                                    + "\n\ttodo ...");
                        }

                    } else if (taskType.equals("deadline")) {
                        // Define regular expressions for pattern matching for deadline
                        Pattern deadlinePattern = Pattern.compile("deadline\\s+(.*?)\\s+/by\\s+(.*?)$");

                        // Match the input string with the pattern
                        Matcher matcher = deadlinePattern.matcher(input);

                        // Check if the input string matches the pattern
                        if (matcher.matches()) {
                            description = matcher.group(1); // Extract task description
                            String dueDate = matcher.group(2);  // Extract due date
                            task = new Deadline(dueDate, description);
                        } else {
                            // User did not follow deadline format
                            throw new DukeException("\tInput for deadline doesn't match the expected format."
                                    + "\n\tdeadline ... /by ...");
                        }

                    } else if (taskType.equals("event")){
                        // Define regular expressions for pattern matching for event
                        Pattern eventPattern = Pattern.compile("event\\s+(.*?)\\s+/from" +
                                "\\s+(.*?)\\s+/to\\s+(.*?)$");

                        // Match the input string with the pattern
                        Matcher matcher = eventPattern.matcher(input);

                        // Check if the input string matches the pattern
                        if (matcher.matches()) {
                            description = matcher.group(1); // Extract event name
                            String startTime = matcher.group(2); // Extract start time
                            String endTime = matcher.group(3);   // Extract end time
                            task = new Event(startTime, endTime, description);
                        } else {
                            // User did not follow event format
                            throw new DukeException("\tInput for event doesn't match the expected format."
                                    + "\n\tevent ... /from ... /to ...");
                        }
                    } else {
                        // invalid input
                        throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    list.add(task);
                    System.out.println("\t-----------------------------------------------");
                    System.out.println("\tGot it bro! I've added this task:\n\t\t" + task);
                    System.out.println("\tNow you have " + list.size() + " tasks in the list");
                    System.out.println("\t-----------------------------------------------");

                } catch (DukeException e) {
                    System.out.println("\t-----------------------------------------------");
                    System.out.println(e.getMessage());
                    System.out.println("\t-----------------------------------------------");
                }

            }
        }

        System.out.println("\t-----------------------------------------------");
        System.out.println("\tBye. Hope to see you again soon bro!");
        System.out.println("\t-----------------------------------------------");
    }
}
