package Sidtacphi.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import Sidtacphi.Exception.SidException;
import Sidtacphi.Exception.SidInvalidFormatException;
import Sidtacphi.Exception.SidInvalidIndexException;

/**
 * TaskList is the main class for the task list stored by the Sidtacphi bot.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        super();
    }

    /**
     * Parses a string into an integer and returns a default value on failure.
     * 
     * @param text String value to be parsed to integer
     * @param defaultVal default value to be returned if text cannot be parsed
     * @return an integer value represented by the string
     */
    private static int tryParseInt(String text, int defaultVal) {
        try {
          return Integer.parseInt(text);
        } catch (NumberFormatException e) {
          return defaultVal;
        }
    }

    /**
     * Adds the input to the task_list kept track of by the bot.
     * 
     * @param taskType type of task to add
     * @param input input to add to the task_list kept by the bot
     */
    public void addTask(TaskType taskType, String input) throws SidException {
        String[] inputArgs;
        switch (taskType) {
        case TODO:
            if (input.length() < 5) {
                throw new SidInvalidFormatException("Please input a name for your Todo task.");
            } else if (input.charAt(4) == ' ') {
                this.add(new Todo(input.substring(5)));
            } else {
                throw new SidException("\"" + input + "\" is not a valid command.");
            }
            break;
        case EVENT:
            if (input.length() < 6) {
                throw new SidInvalidFormatException("Please input a name for your Event" 
                        + "task, along with a start and end date.");
            } else if (input.charAt(5) != ' ') {
                throw new SidException("\"" + input + "\" is not a valid command.");
            }

            inputArgs = input.substring(6).split("\\s*/from\\s*");
            if (inputArgs.length != 2) { 
                throw new SidInvalidFormatException("Please put in the starting and ending date " 
                        + "using \"/from <date>\" followed by \"/to <date>\" for Event tasks.");
            }

            String[] startAndEnd = inputArgs[1].split("\\s*/to\\s*");
            if (startAndEnd.length == 2) {
                LocalDate start;
                LocalDate end;
                try {
                    start = LocalDate.parse(startAndEnd[0]);
                    end = LocalDate.parse(startAndEnd[1]);
                } catch (DateTimeParseException e) {
                    throw new SidInvalidFormatException("Please put in your dates in YYYY-MM-DD format.");
                }

                if (end.isBefore(start)) {
                    throw new SidInvalidFormatException("Please make sure your starting date is before your ending date.");
                }

                this.add(new Event(inputArgs[0], start, end));
            } else {
                throw new SidInvalidFormatException("Please put in the starting and ending date " 
                        + "using \"/from <date>\" followed by \"/to <date>\" for Event tasks.");
            }

            break;
        case DEADLINE:
            if (input.length() < 9) {
                throw new SidInvalidFormatException("Please input a name for your Event" 
                        + "task, along with a start and end date.");
            } else if (input.charAt(8) != ' ') {
                throw new SidException("\"" + input + "\" is not a valid command.");
            }

            inputArgs = input.substring(9).split("\\s*/by\\s*");
            if (inputArgs.length == 2) {
                LocalDate deadline;
                try {
                    deadline = LocalDate.parse(inputArgs[1]);
                } catch (DateTimeParseException e) {
                    throw new SidInvalidFormatException("Please put in your dates in YYYY-MM-DD format.");
                }
                this.add(new Deadline(inputArgs[0], deadline));
            } else if (inputArgs.length == 1) {
                throw new SidInvalidFormatException("Please write in the deadline" 
                        + "using \"/by <date>\" for Deadline tasks.");
            } else {
                throw new SidInvalidFormatException("Please do not write in more than 1 deadline.");
            }
            break;
        }
        
        System.out.println("\nSidtacphi: I have added \"" + this.get(this.size() - 1) + "\".");
        
        if (this.size() == 1) {
            System.out.println("Sidtacphi: You now have 1 task in your list.");
        } else {
            System.out.println("Sidtacphi: You now have " + this.size() + " tasks in your list.");
        }
    }

    /**
     * Marks/Unmarks the task given.
     * 
     * @param isToMark to mark the task as done when true, and to unmark when false
     * @param input 
     */
    public void markTaskAs(boolean isToMark, String input) throws SidException {
        if (!isToMark) {
            if (input.length() < 7) {
                throw new SidInvalidFormatException("Please input the task ID number to unmark.");
            } else if (input.charAt(6) != ' ') {
                throw new SidException("\"" + input + "\" is not a valid command.");
            } 

            int taskId = tryParseInt(input.substring(7), -1);
            if (taskId > this.size() || taskId < 1) {
                throw new SidInvalidIndexException("Invalid task ID.");
            }
            
            Task task = this.get(taskId - 1);
            if (!task.isCompleted()) {
                throw new SidInvalidIndexException("\"" + task + "\" is already unmarked!");
            } else {
                task.unmark();
                System.out.println("\nSidtacphi: Unmarked \"" + task + "\".");
            }
        } else {
            if (input.length() < 5) {
                throw new SidInvalidFormatException("Please input the task ID number to mark.");
            } else if (input.charAt(4) != ' ') {
                throw new SidException("\"" + input + "\" is not a valid command.");
            } 

            int taskId = tryParseInt(input.substring(5), -1);
            if (taskId > this.size() || taskId < 1) {
                throw new SidInvalidIndexException("Invalid task ID.");
            }
            
            Task task = this.get(taskId - 1);
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
    public void showTaskList() {
        if (this.size() == 0) {
            System.out.println("\nSidtacphi: There are no tasks in your list.");
            return;
        }

        System.out.println("\nSidtacphi: These are the tasks in your list.");
        for (int i = 0; i < this.size() ; i++) {
            System.out.println("" + (i + 1) + ". " + this.get(i));
        }
    }

    /**
     * Deletes the task list.
     * 
     * @param input
     */
    public void deleteTask(String input) throws SidException {
        if (input.length() < 7) {
            throw new SidInvalidFormatException("Please input the task ID number to delete.");
        } else if (input.charAt(6) != ' ') {
            throw new SidException("\"" + input + "\" is not a valid command.");
        } 

        int taskId = tryParseInt(input.substring(7), -1);
        if (taskId > this.size() || taskId < 1) {
            throw new SidInvalidIndexException("Invalid task ID.");
        }

        Task task = this.get(taskId - 1);
        this.remove(taskId - 1);
        System.out.println("\nSidtacphi: Removed \"" + task + "\".");
        System.out.println("Sidtacphi: You now have " + this.size() + " tasks in your list.");
    }

    /**
     * Checks if obj is equal to to the TaskList object.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TaskList)) {
            System.out.println("hi1");
            return false;
        }
        TaskList taskList = (TaskList) obj;

        if (taskList.size() != this.size()) {
            System.out.println("hi2");
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            System.out.println("hi3");
            if (!this.get(i).equals(taskList.get(i))) {
                System.out.println("task1: " + this.get(i));
                System.out.println("task2: " + taskList.get(i));
                return false;
            }
        }

        return true;
    }
}
