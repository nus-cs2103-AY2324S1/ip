import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // Array storing the tasks
    static ArrayList<Task> taskArr = new ArrayList<Task>();

    // Function that encapsulates message into a message Card template
    private static String messageCard(String message) {
        String horizontalLine = "\t____________________________________________________________\n";
        return horizontalLine + "\t " + message + "\n" + horizontalLine;
    }

    // Displays list of items
    private static String displayList(ArrayList<? extends Task> list) {
        String str = "Here are the tasks in your list:\n\t ";
        int count = 0;
        for (Task task : taskArr) {
            str += count + 1 + "." + task.toString() + "\n\t ";
            count++;
        }
        return messageCard(str.substring(0, str.length() - 3));
    }

    private static void markTaskAsDone(int index) {
        taskArr.get(index).markAsDone();
        System.out.println(messageCard("Nice! I've marked this task as done:\n\t\t" + taskArr.get(index)));
    }

    private static void markTaskAsUndone(int index) {
        taskArr.get(index).markAsUndone();
        System.out.println(messageCard("OK, I've marked this task as not done yet:\n\t\t" + taskArr.get(index)));
    }

    private static void addToDo(String description) {
        Todo todo = new Todo(description);
        taskArr.add(todo);
        System.out.println(messageCard("Got it. I've added this task:\n\t\t"
                + todo
                + "\n\tNow you have " + taskArr.size() + " tasks in the list."));
    }

    private static void addDeadline(String description) {
        Deadline deadline = new Deadline(description.substring(0, description.indexOf("/by")),
                description.substring(description.indexOf("/by") + 4));
        taskArr.add(deadline);
        System.out.println(messageCard("Got it. I've added this task:\n\t\t"
                + deadline
                + "\n\tNow you have " + taskArr.size() + " tasks in the list."));
    }

    private static void addEvent(String description) {

        int indexFrom = description.indexOf("/from");
        int indexTo = description.indexOf("/to");

        String event = description.substring(0, indexFrom).trim();
        String startTime = description.substring(indexFrom + "/from".length(), indexTo).trim();
        String endTime = description.substring(indexTo + "/to".length()).trim();

        Event eventTask = new Event(event, startTime, endTime);
        taskArr.add(eventTask);
        System.out.println(messageCard("Got it. I've added this task:\n\t\t"
                + eventTask
                + "\n\tNow you have " + taskArr.size() + " tasks in the list."));
    }

    private static void deleteTask(int index) {
        Task task = taskArr.get(index);
        taskArr.remove(task);
        System.out.println(messageCard("Noted. I've removed this task:\n\t\t"
                + task
                + "\n\tNow you have " + taskArr.size() + " tasks in the list."));
    }


    // Main function
    public static void main(String[] args) {
        String CHATBOTNAME = "Carl";
        System.out.println(messageCard("Hello! I'm " + CHATBOTNAME
            + "\n\t What can I do for you?"));
        Scanner SC = new Scanner(System.in);

        while (true) {
            String userInput = SC.nextLine();
            try {
                if (userInput.equals("bye")) {
                    System.out.println(messageCard("Bye. Hope to see you again soon!"));
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println(displayList(taskArr));
                } else if (userInput.contains("mark") && userInput.substring(0, 4).equals("mark")) {
                    if (!userInput.equals("mark")) {
                        int index = Integer.parseInt(userInput.substring(5)) - 1;
                        if (index >= taskArr.size() || index < 0) {
                            System.out.println(messageCard("Invalid mark task"));
                        } else {
                            markTaskAsDone(index);
                        }
                    } else {
                        System.out.println(messageCard("Invalid mark task"));
                    }
                } else if (userInput.contains("unmark") && userInput.substring(0, 6).equals("unmark")) {
                    if (!userInput.equals("unmark")) {
                        int index = Integer.parseInt(userInput.substring(7)) - 1;
                        if (index >= taskArr.size() || index < 0) {
                            System.out.println(messageCard("Invalid unmark task"));
                        } else {
                            markTaskAsUndone(index);
                        }
                    } else {
                        System.out.println(messageCard("Invalid unmark task"));
                    }
                } else if (userInput.contains("delete") && userInput.substring(0, 6).equals("delete")) {
                    if (!userInput.equals("delete")) {
                        int index = Integer.parseInt(userInput.substring(7)) - 1;
                        if (index >= taskArr.size() || index < 0) {
                            System.out.println(messageCard("Invalid task index to be deleted"));
                        } else {
                            deleteTask(index);
                        }
                    } else {
                        System.out.println(messageCard("Invalid task index to be deleted"));
                    }
                } else {
                    if (userInput.contains("todo") && userInput.substring(0, 4).equals("todo")) {
                        // Add a task
                        if (userInput.equals("todo")) { // checks if description is empty
                            throw new DukeException("todo");
                        } else {
                            addToDo(userInput.substring(5));
                        }
                    } else if (userInput.contains("deadline") && userInput.substring(0, 8).equals("deadline")) {
                        // Add a deadline
                        if (userInput.equals("deadline") || !userInput.contains("/by")) { // checks if description is invalid
                            throw new DukeException("deadline");
                        } else {
                            addDeadline(userInput.substring(9));
                        }

                    } else if (userInput.contains("event") && userInput.substring(0, 5).equals("event")) {
                        // Add an event
                        if (userInput.equals("event") || !userInput.contains("/from") || !userInput.contains("/to")) { // checks if description is invalid
                            throw new DukeException("event");
                        } else {
                            addEvent(userInput.substring(6));
                        }
                    } else {
                        System.out.println(messageCard("OOPS!!! I'm sorry, but I don't know what that means :-("));
                    }
                }
            } catch (DukeException e) {
                System.out.println(messageCard(e.getMessage()));
            }
        }
        SC.close();
    }
}