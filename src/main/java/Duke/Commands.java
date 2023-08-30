package Duke;

import Duke.Tasks.Deadlines;
import Duke.Tasks.Events;
import Duke.Tasks.Task;
import Duke.Tasks.ToDos;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Commands {
    String name = "Jose Mourinho Bot";

    // task list to store tasks
    ArrayList<Task> tasks;

    public Commands(ArrayList tasks) {
        this.tasks = tasks;
    }

    // Level-0, Function to say introduce the chatbot
    public void sayHello() {
        String greet = String.format(

                        "____________________________________________________________\n" +
                         "Hello! I'm %s, a task planning bot that will record your tasks.\n" +
                         "If you require help, type \"help\"\n" +
                                 "____________________________________________________________\n"
                         , name);
        System.out.println(greet);
    }

    public void commandList() {
        String commands = (
                "____________________________________________________________\n" +
                "I currently can record 3 types of tasks. tasks to do, tasks with deadlines and events \n" +
                "Here is how you can record down your events.\n" +
                "To record tasks to do, simply begin your command with \"todo\" followed by a space and the task you need to do.\n" +
                "To record tasks with deadlines, simply begin your command with \"deadlines\" followed by the task and a / then the due date.\n" +
                "To record events, simply begin your command with \"events\" followed by the event, \"/from\" and start time , " +
                        "then \"/tp\" with the end time" +
                "To view your list of events, type list. \n" + "To mark your events as done or undone, type in mark / unmark , " +
                        "followed by the index of the task \n"+
                "To delete tasks from the list, type delete task number, such as delete 2 \n" +
                "Lastly, to exit the chatbot, type \"bye\" \n" + "____________________________________________________________\n"
        );
        System.out.println(commands);
    }

    // Level-0, Function to say goodbye
    public void sayGoodBye() {
        String bye =  (
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
        System.out.println(bye);
    }

    //  Level-1, Echo user input
    public void echoUserInput(Task task) {
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    public static String getDescription(String task, String input) throws DukeException {
        if (task.equalsIgnoreCase("todo")) {

            // check that string contains task to prevent indexOutOfBoundsException
            if (input.length() < 5) {
                throw new DukeException("You are missing the task to do. This is unacceptable.");
            }
            String description = input.substring(5);
            return description;
        } else if (task.equalsIgnoreCase("deadline")) {
            int endIndex = input.indexOf("/by");
            if (input.length() < 9 || endIndex == -1) {
                throw new DukeException("You are missing the deadline. This is unacceptable.");
            }
            String description = input.substring(9, endIndex);
            return description;
        }else if (task.equalsIgnoreCase("event")) {
            int endIndex = input.indexOf("/from");
            if (input.length() < 6 || endIndex == -1) {
                throw new DukeException("You are missing the event. This is unacceptable.");
            }
            String description = input.substring(6, endIndex);
            return description;
        } else {
            throw new DukeException("You are missing the task. This is unacceptable.");
        }
    }
    public static String getStartDate(String input) throws DukeException {
        String startWord = "/from";
        String endWord = "/to";
        int startIndex = input.indexOf(startWord) + startWord.length() + 1;
        int endIndex = input.indexOf(endWord);
                
        if (startIndex == input.length()) {
            throw new DukeException("You are missing the keyword " + startWord + ". This is unacceptable.");
        } else if (startIndex > input.length()) {
            throw new DukeException("You are missing the start date. This is unacceptable.");
        } else if (endIndex == -1) {
            throw new DukeException("You are missing the keyword " + endWord + ". This is unacceptable.");
        } else if (startIndex > endIndex) {
            throw new DukeException("You are missing the end date. This is unacceptable.");
        }
        return input.substring(startIndex, endIndex);
    }

    public static String getEndDate(String task, String input) throws DukeException {
        String startWord = task.equalsIgnoreCase("deadline") ? "/by" : "/to";
        int startIndex = input.indexOf(startWord) + startWord.length() + 1;
                
        if (startIndex > input.length()) {
            throw new DukeException("You are missing the end date. This is unacceptable.");
        }
        return input.substring(startIndex);
    }


    //    Level-2, Add, list
    public void addList(String input) throws DukeException {
        Task newTask = null;

        // check if input begins with todo, deadline or event to categorise task type
        String[] wordSeparator = input.split(" ");
        String firstWord = wordSeparator[0];

        try {
            if (firstWord.equalsIgnoreCase("todo")) {
                newTask = new ToDos(getDescription("todo", input));
            } else if (firstWord.equalsIgnoreCase("deadline")) {
                String task = "deadline";
                newTask = new Deadlines(getDescription(task, input), getEndDate(task, input));
            } else if (firstWord.equalsIgnoreCase("event")) {
                String task = "event";
                newTask = new Events(getDescription(task, input), getStartDate(input), getEndDate(task, input));
            }

            if (newTask == null) throw new DukeException("You are missing the task. This is unacceptable.");
            tasks.add(newTask);
            System.out.println("____________________________________________________________\n" +
                    "This task has been added to your list\n" +
                    String.format("[%s]", newTask.getTaskType()) + "[ ]" + newTask.getDescription() + "\n" +
                    String.format("You now have %d tasks in your list.", tasks.size()));
            System.out.println("____________________________________________________________\n");

        } catch (DukeException inputException) {
            System.out.println("____________________________________________________________\n");
            System.out.println(inputException);
            System.out.println("____________________________________________________________\n");
        } catch (DateTimeParseException e) {
            System.out.println("____________________________________________________________\n");
            System.out.println("Your Date and time does not follow the format. This is not acceptable");
            System.out.println("____________________________________________________________\n");
        }
    }

    public void printList() {
        System.out.println("____________________________________________________________\n");
        for (int i = 0; i < tasks.size(); i++) {
            // for each task in task[], prints out a simple line describing the task
            Character taskType = tasks.get(i).getTaskType();
            String current = String.format("%d: [%c][%s] %s ", i+1, taskType, tasks.get(i).getStatusIcon(),tasks.get(i));

            if (tasks.get(i) instanceof Deadlines) {

                current += (String.format("(by: %s)", ((Deadlines) tasks.get(i)).getDeadlineInWords()));
            } else if (tasks.get(i) instanceof Events) {
                current += (String.format("(from: %s to: %s)", ((Events) tasks.get(i)).getStartDateInWords(),
                        ((Events) tasks.get(i)).getEndDateInWords()));
            }

            System.out.println(current);
        }
        System.out.print("____________________________________________________________\n");
    }

    // function to mark task as done or undone
    public void markDoneOrUndone(int task, boolean done) {
        if (task < 1 || task > tasks.size()) {
            System.out.print("____________________________________________________________\n");
            System.out.println("Invalid Input written.");
            System.out.print("____________________________________________________________\n");
            return;
        }
        if (task > tasks.size() + 1) {
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
    public void deleteTasks(int taskNumber) throws DukeException {
        try {
            if (tasks.size() <= taskNumber) throw new DukeException("oops. You entered an invalid task number.");
            Task removed = tasks.remove(taskNumber - 1);
            System.out.println("____________________________________________________________\n");
            System.out.println("Noted, I have deleted this task:");
            System.out.println(String.format("  [%c][%s] %s ", removed.getTaskType(), removed.getStatusIcon(), removed));
            System.out.println(String.format("You now have %d tasks in your list.", taskNumber));
            System.out.println("____________________________________________________________");
        } catch (DukeException message) {
            System.out.println("____________________________________________________________\n");
            System.out.println(message);
            System.out.println("____________________________________________________________\n");
        }
    }

    // overall input handler which determines which function to run
    public void handleInput(String userInput) throws DukeException {
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
