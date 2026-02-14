package bruno;

import java.util.List;

import bruno.task.Task;
/**
 * The UI class is responsible for the display of messages, i.e, it controls the user-interface of the task
 * management system.
 */
public class UI {

    /**
     * Displays the divider lines before and after each command.
     */
    public void displayLines() {
        for (int i = 0; i < 20; i++) {
            System.out.print((i == 0 ? "-" : " -"));
        }
        System.out.println();
    }

    /**
     * Displays the greeting at the start of the program.
     */
    public String displayGreeting() {
        String name = "Bruno";
        return "Woof Woof! I'm " + name + "\nHow can I help you?";
    }

    /**
     * Displays the "bye" message when the command "bye" is entered.
     */
    public String displayBye() {
        return "Bye Bye! Hope to see you again soon!";
    }

    /**
     * Displays message when a task is added.
     *
     * @param taskList List of tasks to which task is added.
     * @return The message being displayed.
     */
    public String displayAddMessage(TaskList taskList) {
        List<Task> tasks = taskList.getList();
        String taskString = tasks.get(tasks.size() - 1).getString();
        return "Woof. I have added this task:\n" + taskString;
    }

    /**
     * Displays message when a task is marked.
     *
     * @param taskList List of tasks of which a task is marked.
     * @param markVal The index of the task that is marked.
     * @return The message being displayed.
     */
    public String displayMarkMessage(TaskList taskList, int markVal) {
        List<Task> tasks = taskList.getList();
        String taskString = tasks.get(markVal - 1).getString();
        return "Woof Woof! I have marked the task as done.\n" + taskString;
    }

    /**
     * Displays message when a task is unmarked.
     *
     * @param taskList List of tasks of which a task is unmarked.
     * @param unmarkVal The index of the task that is unmarked.
     * @return The message being displayed.
     */
    public String displayUnmarkMessage(TaskList taskList, int unmarkVal) {
        List<Task> tasks = taskList.getList();
        String taskString = tasks.get(unmarkVal - 1).getString();
        return "OK, I have marked the task as not done yet.\n" + taskString;
    }

    /**
     * Displays message when a task is deleted.
     *
     * @param taskString The task that is to be deleted.
     * @return The message being displayed.
     */
    public String displayDeleteMessage(String taskString) {
        return "I have removed this task from your tasks:\n" + taskString;
    }

    /**
     * Displays the schedule when the command is input.
     *
     * @param taskInfo String containing schedule for the day.
     * @param counter Number of tasks in the schedule.
     * @return The message containing schedule.
     */
    public String displaySchedule(String taskInfo, int counter) {
        if (counter == 0) {
            return "You have no deadlines or events on this date.";
        } else {
            return "Here is the schedule for the given date:\n" + taskInfo;
        }
    }

    /**
     * Displays the search items when the command is input.
     *
     * @param taskInfo String containing the search items.
     * @param counter Number of items in the search.
     * @return The message containing search items.
     */
    public String displaySearch(String taskInfo, int counter) {
        if (counter == 0) {
            return "There are no items matching your search.";
        } else {
            return "Here are the tasks matching your search:\n" + taskInfo;
        }
    }

    /**
     * Displays a message when a note is added to a task.
     *
     * @param taskList List of tasks to which a note is added.
     * @param noteVal Index of the task to which note is added.
     * @return The message to be displayed.
     */
    public String displayNoteMessage(TaskList taskList, int noteVal) {
        List<Task> tasks = taskList.getList();
        String taskString = tasks.get(noteVal - 1).getString();
        return "I have added note to the task:\n" + taskString;
    }
}
