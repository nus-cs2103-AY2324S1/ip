import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Sidtacphi is the main class for the Sidtacphi bot.
 */
public class Sidtacphi {
    private static ArrayList<Task> taskList = new ArrayList<>();
    enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }
    
    /**
     * The main method for the Sidtacphi class.
     * 
     * @param args
     */
    public static void main(String[] args) {
        startBot();
    }

    /**
     * This method starts the Sidtacphi bot.
     */
    private static void startBot() {
        String logo = " _______  ___   ______   _______  _______  _______  _______  __   __  ___  \n"
                + "|       ||   | |      | |       ||   _   ||       ||       ||  | |  ||   | \n"
                + "|  _____||   | |  _    ||_     _||  |_|  ||       ||    _  ||  |_|  ||   | \n"
                + "| |_____ |   | | | |   |  |   |  |       ||       ||   |_| ||       ||   | \n"
                + "|_____  ||   | | |_|   |  |   |  |       ||      _||    ___||       ||   | \n"
                + " _____| ||   | |       |  |   |  |   _   ||     |_ |   |    |   _   ||   | \n"
                + "|_______||___| |______|   |___|  |__| |__||_______||___|    |__| |__||___| \n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        readInputs();
    }

    /**
     * This method stops the Sidtacphi bot.
     */
    private static void stopBot() {
        System.out.println("\nSidtacphi: Goodbye non-Euclidean life form.");
        System.out.println("\n____________________________________________________________");
    }

    /**
     * The method for reading inputs for the bot.
     */
    private static void readInputs() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nYou: ");
        String input = "";
        while (true) { 
            input = scan.nextLine().trim();
            if (Objects.equals(input, "bye")) {
                stopBot();
                break;
            } else if (Objects.equals(input, "list")) {
                showTaskList();
            } else if (input.startsWith("mark")) {
                try {
                    markTaskAs(true, input);
                } catch (SidException e) {
                    System.out.print("\n");
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("unmark")) {
                try {;
                    markTaskAs(false, input);
                } catch (SidException e) {
                    System.out.print("\n");
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("todo")) {
                try {
                    addTask(TaskType.TODO, input);
                } catch (SidException e) {
                    System.out.print("\n");
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("event")) {
                try {
                    addTask(TaskType.EVENT, input);
                } catch (SidException e) {
                    System.out.print("\n");
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("deadline")) {
                try {
                    addTask(TaskType.DEADLINE, input);
                } catch (SidException e) {
                    System.out.print("\n");
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.print("\nSidtacphi: \"" + input + "\" is not a valid command. \n");
            }
            System.out.print("\nYou: ");
        }
        scan.close();
    }

    /**
     * Adds the input to the task_list kept track of by the bot.
     * 
     * @param taskType type of task to add
     * @param input input to add to the task_list kept by the bot
     */
    private static void addTask(TaskType taskType, String input) throws SidException {
        String[] inputArgs;
        switch (taskType) {
            case TODO:
                if (input.length() < 5) {
                    throw new SidInvalidFormatException("Please input a name for your Todo task.");
                } else if (input.charAt(4) == ' ') {
                    taskList.add(new Todo(input.substring(5)));
                } else {
                    throw new SidException("\"" + input + "\" is not a valid command.");
                }
                break;
            case EVENT:
                if (input.length() < 6) {
                    throw new SidInvalidFormatException("Please input a name for your Event" 
                    + "task, along with a start and end time.");
                } else if (input.charAt(5) == ' ') {
                    inputArgs = input.substring(6).split("\\s*/from\\s*");
                    if (inputArgs.length == 2) { 
                        String[] startAndEnd = inputArgs[1].split("\\s*/to\\s*");
                        if (startAndEnd.length == 2) {
                            taskList.add(new Event(inputArgs[0], startAndEnd[0], startAndEnd[1]));
                        } else {
                            throw new SidInvalidFormatException("Please put in the starting and ending time " 
                            + "using \"/from <time>\" followed by \"/to <time>\" for Event tasks.");
                        }
                    } else {
                        throw new SidInvalidFormatException("Please put in the starting and ending time " 
                        + "using \"/from <time>\" followed by \"/to <time>\" for Event tasks.");
                    }
                } else {
                    throw new SidException("\"" + input + "\" is not a valid command.");
                }
                break;
            case DEADLINE:
                if (input.length() < 9) {
                    throw new SidInvalidFormatException("Please input a name for your Event" 
                    + "task, along with a start and end time.");
                } else if (input.charAt(8) == ' ') {
                    inputArgs = input.substring(9).split("\\s*/by\\s*");
                    if (inputArgs.length == 2) { 
                        taskList.add(new Deadline(inputArgs[0], inputArgs[1]));
                    } else if (inputArgs.length == 1) {
                        throw new SidInvalidFormatException("Please write in the deadline" 
                        + "using \"/by <time>\" for Deadline tasks. ");
                    } else {
                        throw new SidInvalidFormatException("Please do not write in more than 1 deadline. ");
                    }
                } else {
                    throw new SidException("\"" + input + "\" is not a valid command.");
                }
                break;
        }
        
        System.out.println("\nSidtacphi: I have added \"" + taskList.get(taskList.size() - 1) + "\".");
        
        if (taskList.size() == 1) {
            System.out.println("Sidtacphi: You now have 1 task in your list.");
        } else {
            System.out.println("Sidtacphi: You now have " + taskList.size() + " tasks in your list.");
        }
    }

    /**
     * Marks/Unmarks the task given.
     * 
     * @param toMark to mark the task as done when true, and to unmark when false
     * @param task the task to mark
     */
    private static void markTaskAs(boolean toMark, String input) throws SidException {
        
        if (!toMark) {
            if (input.length() < 7) {
                throw new SidInvalidFormatException("Please input a name for your Event" 
                + "task, along with a start and end time.");
            } else if (input.charAt(6) != ' ') {
                throw new SidException("\"" + input + "\" is not a valid command.");
            } 

            Integer taskId = Integer.parseInt(input.substring(7));
            if (Objects.isNull(taskId)) {
                throw new SidInvalidIndexException("Invalid task ID.");
            }
            
            Task task = taskList.get(taskId - 1);
            if (!task.isCompleted()) {
                throw new SidInvalidIndexException("\"" + task + "\" is already unmarked!");
            } else {
                task.unmark();
                System.out.println("\nSidtacphi: Unmarked \"" + task + "\".");
            }
        } else {
            if (input.length() < 5) {
                throw new SidInvalidFormatException("Please input a name for your Event" 
                + "task, along with a start and end time.");
            } else if (input.charAt(4) != ' ') {
                throw new SidException("\"" + input + "\" is not a valid command.");
            } 

            Integer taskId = Integer.parseInt(input.substring(5));
            if (Objects.isNull(taskId)) {
                throw new SidInvalidIndexException("Invalid task ID.");
            }
            
            Task task = taskList.get(taskId - 1);
            if (task.isCompleted()) {
                throw new SidInvalidIndexException("\"" + task + "\" is already marked!");
            } else {
                task.mark();
                System.out.println("\nSidtacphi: Marked \"" + task + "\".");
            }
        }
    }

    /**
     * Prints the task list.
     */
    private static void showTaskList() {
        if (taskList.size() == 0) {
            System.out.println("\nSidtacphi: There are no tasks in your list.");
            return;
        }

        System.out.println("\nSidtacphi: These are the tasks in your list.");
        for (int i = 0; i < taskList.size() ; i++) {
            System.out.println("" + (i + 1) + ". " + taskList.get(i));
        }
    }
}
