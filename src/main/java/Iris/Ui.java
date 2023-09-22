package iris;

/**
 * The class responsible for user interface interactions in the Iris application.
 */
public class Ui {
    public String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String respond(String message) {
        return message;
    }

    public String getTasksMessage(TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList.toString();
    }


    public String markTaskMessage(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    public String unmarkTaskMessage(Task task) {
        return "Okay, I've marked this task as not done yet: \n" + task;
    }

    public String getKeywordTasksMessage(TaskList keywordTaskList) {
        return "Here are the matching tasks in your list: \n" + keywordTaskList.toString();
    }
    public String getNoKeywordTasksFoundMessage() {
        return "No matching tasks found.";
    }

    public String getAddTaskMessage(TaskList taskList, Task task) {
        return String.format("      Got it. I've added this task:\n          " +
                "%s\n      Now you have " +
                "%d tasks in the list", task, taskList.getSize());
    }

    public String getDeleteTaskMessage(TaskList taskList, Task task) {
        return String.format("Noted. I've removed this task:\n          " +
                "%s\n      Now you have" +
                " %d tasks in the list", task, taskList.getSize());
    }

    public String getPostponeTaskMessage(Task postponedTask) {
        return "OK, I have postponed this task:\n" + postponedTask;
    }

    public String getUnableToPostponeTaskMessage() {
        return "Task selected is not time-sensitive! Please select carefully!";
    }

    public static void printLength(TaskList taskList) {
        int listSize = taskList.getSize();
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }
}
