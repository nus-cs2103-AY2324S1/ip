package DukeUIClasses;

import DukeTasks.Task;

import java.util.ArrayList;

/**
 * Encapsulates a class which will print out normal UI operations.
 *
 * @author Tan Kerway
 *
 */
public class DukeUI {
    /**
     * When called, the chatbot will greet the user.
     *
     * @author Tan Kerway
     * @param tasks the list of tasks to print out.
     */
    public void greet(ArrayList<Task> tasks) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Hello! I'm nyancatbot!\nWhat can I do for nyan?");
        System.out.println("------------------------------------------------------------------------");
        // list all the tasks
        listAllTasks(tasks);
    }

    /**
     * Prints the current list of tasks.
     *
     * @author Tan Kerway
     * @param tasks the list of tasks to print out.
     */
    public void listAllTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) { return; }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Here are the tasks in your list :3");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Echos the task that was added to the task list to
     * the console.
     *
     * @author Tan Kerway
     * @param task the task to be echoed to the console
     */
    public void echoTaskAdded(Task task, int tasksCount) {
        if (task == null) { return; }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:\n    " + task);
        System.out.println("Nyan you have " + tasksCount + " tasks in the list.");
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Echos that the task has been marked.
     *
     * @author Tan Kerway
     * @param currentTask the marked task to be echoed
     */
    public void echoTaskMarked(Task currentTask) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as nyan:");
        System.out.println("    " + currentTask);
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Echos that the task has been deleted.
     *
     * @author Tan Kerway
     * @param removedTask the index of the deleted task
     * @param tasksCount the number of tasks currently
     */
    public void echoTaskDeleted(Task removedTask, int tasksCount) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + removedTask);
        System.out.println("Now you have " + tasksCount + " tasks in the list.");
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Echos that the task has been marked.
     *
     * @author Tan Kerway
     * @param currentTask the marked task to be echoed
     */
    public void echoTaskUnmarked(Task currentTask) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("OK, I've marked this task as not nyan yet:");
        System.out.println("    " + currentTask);
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * When called, will bid the user farewell.
     *
     * @author Tan Kerway
     */
    public void sayGoodBye() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you a-nyan soon!");
        System.out.println("------------------------------------------------------------------------");
    }
}
