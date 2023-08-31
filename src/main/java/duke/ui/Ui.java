package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private final String HORIZONTAL_LINE = "    ____________________________________________________________\n";

    public void printFinal(String str) {
        System.out.println(this.HORIZONTAL_LINE + str + this.HORIZONTAL_LINE);
    }
    public void printGreet() {
        printFinal("     Hello! I'm Siri\n" +
                "     What can I do for you?\n");
    }

    public void printBye() {
        printFinal("     Bye. Hope to see you again soon!\n");
    }

    public void printAddTask(Task newTask, int numberOfTask) {
        printFinal("     Got it. I've added this duke.task:\n" +
                "      " + newTask + "\n" +
                "     Now you have " + numberOfTask + " tasks in the list." + "\n");
    }

    public void printDeleteTask(Task deletedTask, int numberOfTask) {
        printFinal("     Noted. I've removed this duke.task:" + "\n" +
                "       " + deletedTask + "\n" +
                "     Now you have " + numberOfTask + " tasks in the list." + "\n");
    }

    public void printTaskList(TaskList taskList) {
        String items = "";

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) == null) {
                break;
            }
            items += "     " + (i + 1) + "." + taskList.get(i).toString() + "\n";
        }

        printFinal("     Here are the tasks in your list:\n" + items);
    }

    public void printMark(Task task) {
        printFinal("     Nice! I've marked this duke.task as done:" + "\n" +
                "       " + task + "\n");

    }

    public void printUnmark(Task task) {
        printFinal("     OK, I've marked this duke.task as not done yet:" + "\n" +
                "       " + task + "\n");
    }

    public void printError(String e) {
        printFinal("       " + e + "\n");
    }

}
