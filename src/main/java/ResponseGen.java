public class ResponseGen {
    protected static String H_LINE = "____________________________________________________________\n";
    public ResponseGen(){}

    public String bye() {
        return (H_LINE
                +  "Bye. Hope to see you again soon!\n"
                + H_LINE);
    }

    public String list(String taskList) {
        return (H_LINE
                + "Here are the tasks in your list:\n"
                + taskList
                + H_LINE);
    }

    public String unmarkTask(Task task) {
        return (H_LINE
                + "OK, I've marked this task as not done yet:\n"
                + task + "\n"
                + H_LINE);
    }

    public String markTask(Task task) {
        return (H_LINE
                + "Nice! I've marked this task as done:\n"
                + task + "\n"
                + H_LINE);
    }

    public String toDoAdded(ToDo toDoTask, TaskManager taskManager) {
        return (H_LINE
                + "Got it. I've added this task:\n"
                + toDoTask + "\n"
                + "Now you have " + taskManager.getSize() + ((taskManager.getSize() > 1) ? " tasks " : " task ") + "in the list." + "\n"
                + H_LINE);
    }

    public String toDoMissingContent() {
        return (H_LINE
                + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                + H_LINE);
    }

    public String deadlineAdded(Deadline deadlineTask, TaskManager taskManager) {
        return (H_LINE
                + "Got it. I've added this task:\n"
                + deadlineTask + "\n"
                + "Now you have " + taskManager.getSize() + ((taskManager.getSize() > 1) ? " tasks " : " task ") + "in the list." + "\n"
                + H_LINE);
    }

    public String deadlineMissingContent() {
        return (H_LINE
                + "☹ OOPS!!! The deadline needs to have a task description and /by .\n"
                + H_LINE);
    }

    public String eventAdded(Event eventTask, TaskManager taskManager) {
        return (H_LINE
                + "Got it. I've added this task:\n"
                + eventTask + "\n"
                + "Now you have " + taskManager.getSize() + ((taskManager.getSize() > 1) ? " tasks " : " task ") + "in the list." + "\n"
                + H_LINE);
    }

    public String eventMissingContent(){
        return (H_LINE
                + "☹ OOPS!!! The event needs to have a task description, /from and /to.\n"
                + H_LINE);
    }

    public String taskDeleted(Task deletedTask, TaskManager taskManager){
        return (H_LINE
                + "Noted. I've removed this task:\n"
                + deletedTask + "\n"
                + "Now you have " + taskManager.getSize() + ((taskManager.getSize() > 1) ? " tasks " : " task ") + "in the list." + "\n"
                + H_LINE);
    }
}
