import Errors.DukeException;
import Errors.InvalidTaskException;
import Errors.InvalidTaskInput;
import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;

import java.util.ArrayList;

public class Commands {
    static String name = "Nichbot";

    static int count = 0;

    // task list to store tasks
    static ArrayList<Task> tasks = new ArrayList<>();

    // Level-0, Function to say introduce the chatbot
    public static void sayHello() {
        String greet = String.format(

                        "____________________________________________________________\n" +
                         "Hello! I'm %s, a task planning bot that will help you record your tasks.\n" +
                         "If you require help on the commands you can key in, type \"help\"\n" +
                                 "____________________________________________________________\n"
                         , name);
        System.out.println(greet);
    }

    public static void commandList() {
        String commands = (
                "____________________________________________________________\n" +
                "I currently can record 3 types of tasks. tasks to do, tasks with deadlines and events \n" +
                "Here is how you can record down your events.\n" +
                "To record tasks to do, simply begin your command with \"todo\" followed by a space and the task you need to do.\n" +
                "To record tasks with deadlines, simply begin your command with \"deadlines\" followed by the task and a / then the due date.\n" +
                "To record events, simply begin your command with \"events\" followed by the event, / and start time , then / with the end time" +
                "To view your list of events, type list. \n" + "To mark your events as done or undone, type in mark / unmark , followed by the index of the task \n"+
                "To delete tasks from the list, type delete task number, such as delete 2 \n" +
                "Lastly, to exit the chatbot, type \"bye\" \n" + "____________________________________________________________\n"
        );
        System.out.println(commands);
    }

    // Level-0, Function to say goodbye
    public static void sayGoodBye() {
        String bye =  (
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
        System.out.println(bye);
    }

    //  Level-1, Echo user input
    public static void echoUserInput(Task task) {
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    //    Level-2, Add, list
    public static void addList(String input) throws InvalidTaskInput, InvalidTaskException {
        Task newTask = null;

        // check if input begins with todo, deadline or event to categorise task type
        String[] wordSeparator = input.split(" ");
        String firstWord = wordSeparator[0];

        try {
            if (firstWord.equalsIgnoreCase("todo")) {
                String taskToDo = input.substring(5);

                // check that string is not empty after substring to remove todo;
                if (taskToDo.isEmpty()) {
                    throw new InvalidTaskInput();
                }
                newTask = new ToDos(taskToDo);

            }
            if (firstWord.equalsIgnoreCase("deadline")) {
                String[] parts = input.split("/");

                /*
                split input array into description and deadline.
                "deadline return book /by Sunday" -> parts[0] = deadline return book  and parts[1] = by Sunday
                using substring, deadline return book -> return book
                throws exception if / not used properly, leading to error in either access in values or
                fixing the deadline
                */
                if (parts.length != 2) {
                    throw new InvalidTaskInput();
                }

                String description = parts[0].substring(9);
                // check that string is not empty after substring to remove todo;
                if (description.isEmpty()) {
                    throw new InvalidTaskInput();
                }

                String deadline = parts[1];
                newTask = new Deadlines(description, deadline);
            }

            if (firstWord.equalsIgnoreCase("event")) {
                String[] parts = input.split("/");
                /*
                similar logic as above. additionally, splits input into start and end time.
                Throws exception if / is not used properly, leading to error in start / end time.
                 */

                if (parts.length != 3) {
                    throw new InvalidTaskInput();
                }

                String description = parts[0].substring(6);
                String startTime = parts[1];
                String endTime = parts[2];

                // Ensure that start, end and description are all filled in
                if (startTime.isEmpty() || endTime.isEmpty() || description.isEmpty()) {
                    throw new InvalidTaskInput();
                }

                newTask = new Events(description, startTime, endTime);
            }

            if (newTask == null) throw new InvalidTaskException();
            tasks.add(count++, newTask);
            System.out.println("____________________________________________________________\n" +
                    "I have added this task to your list\n" +
                    String.format("[%s]", newTask.getTaskType()) + "[ ]" + newTask.getDescription() + "\n" +
                    String.format("You now have %d tasks in your list.", count));
            System.out.println("____________________________________________________________\n");

        } catch (InvalidTaskInput inputException) {
            System.out.println("____________________________________________________________\n");
            System.out.println(inputException);
            System.out.println("____________________________________________________________\n");

        } catch (InvalidTaskException taskException) {
            System.out.println("____________________________________________________________\n");
            System.out.println(taskException);
            System.out.println("____________________________________________________________\n");
        }
    }

    public static void printList() {
        System.out.println("____________________________________________________________\n");
        for (int i = 0; i < count; i++) {
            // for each task in task[], prints out a simple line describing the task
            Character taskType = tasks.get(i).getTaskType();
            String current = String.format("%d: [%c][%s] %s ", i+1, taskType, tasks.get(i).getStatusIcon(),tasks.get(i));

            if (tasks.get(i) instanceof Deadlines) {
                current += (String.format("(%s)", ((Deadlines) tasks.get(i)).getDeadline()));
            } else if (tasks.get(i) instanceof Events) {
                current += (String.format("(%s, %s)", ((Events) tasks.get(i)).getStartTime(), ((Events) tasks.get(i)).getEndTime()));
            }

            System.out.println(current);
        }
        System.out.print("____________________________________________________________\n");
    }

    // function to mark task as done or undone
    public static void markDoneOrUndone(int task, boolean done) {
        if (task < 1 || task > tasks.size()) {
            System.out.print("____________________________________________________________\n");
            System.out.println("Invalid Input written.");
            System.out.print("____________________________________________________________\n");
            return;
        }
        if (task > count + 1) {
            System.out.print("____________________________________________________________\n");
            System.out.printf("Task %d has not been recorded%n",task);
            System.out.print("____________________________________________________________\n");
            return;
        }
        Task current = tasks.get(task - 1);
        if (done) {
            current.setDone();
            System.out.println("____________________________________________________________\n");
            System.out.printf("Nice! I've marked %s as completed%n", current);
            System.out.println("____________________________________________________________\n");
        } else {
            current.setNotDone();
            System.out.println("____________________________________________________________\n");
            System.out.printf("Ok, I'll mark %s as not done%n", current);
            System.out.print("____________________________________________________________\n");
        }
    }

    //level 6. Delete tasks
    public static void deleteTasks(int taskNumber) throws InvalidTaskInput {
        try {
            if (count <= taskNumber) throw new InvalidTaskInput();
            Task removed = tasks.remove(taskNumber - 1);
            count--;
            System.out.println("____________________________________________________________\n");
            System.out.println("Noted, I have deleted this task:");
            System.out.println(String.format("  [%c][%s] %s ", removed.getTaskType(), removed.getStatusIcon(), removed));
            System.out.println(String.format("You now have %d tasks in your list.", taskNumber));
            System.out.println("____________________________________________________________");
        } catch (InvalidTaskInput message) {
            System.out.println("____________________________________________________________\n");
            System.out.println(message);
            System.out.println("____________________________________________________________\n");
        }
    }

    // overall input handler which determines which function to run
    public static void handleInput(String userInput) throws InvalidTaskInput, DukeException, InvalidTaskException {
            if (userInput.equalsIgnoreCase("bye")) return;
            if (userInput.equalsIgnoreCase("list")) {
                printList();
            } else if (userInput.equalsIgnoreCase("help")) {
                commandList();
            } else if (userInput.length() > 7 && userInput.substring(0,6).equalsIgnoreCase("delete")) {
                // delete takes up first 5 indexes, index 6 takes up space, so 7 onwards should be numbers
                String index = userInput.substring(7);
                while (index.charAt(0) == ' ') index = index.substring(1);
                while (index.charAt(index.length() - 1) == ' ') index = index.substring(0, index.length()-1);
                Integer value = null;
                try {
                    value = Integer.parseInt(index);
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid input added");
                    return;
                }
                if (value != null ) deleteTasks(value);
            } else if (userInput.length() > 5 && userInput.substring(0,4).equalsIgnoreCase("mark")) {
                markDoneOrUndone(Integer.parseInt(userInput.substring(5)), true);
            } else if (userInput.length() > 7 && userInput.substring(0,6).equalsIgnoreCase("unmark")) {
                markDoneOrUndone(Integer.parseInt(userInput.substring(7)), false);
            } else {
                addList(userInput);
            }
    }
}
