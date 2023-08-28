package duke.userio;

import duke.task.*;

public class Ui {
    protected static String H_LINE = "____________________________________________________________\n";
    public Ui(){}

    public void greetings(){
        System.out.println(H_LINE
                + "Hello! I'm ChadBob.\n"
                + "What can I do for you?\n"
                + H_LINE);
    }
    public void bye() {
        System.out.println(H_LINE
                +  "Bye. Hope to see you again soon!\n"
                + H_LINE);
    }

    public void list(String taskList) {
        System.out.println(H_LINE
                + "Here are the tasks in your list:\n"
                + taskList
                + H_LINE);
    }

    public void unmarkTask(Task task) {
        System.out.println(H_LINE
                + "OK, I've marked this task as not done yet:\n"
                + task + "\n"
                + H_LINE);
    }

    public void markTask(Task task) {
        System.out.println(H_LINE
                + "Nice! I've marked this task as done:\n"
                + task + "\n"
                + H_LINE);
    }

    public void toDoAdded(ToDo toDoTask, TaskList taskList) {
        System.out.println(H_LINE
                + "Got it. I've added this task:\n"
                + toDoTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ") + "in the list." + "\n"
                + H_LINE);
    }

    public void toDoMissingContent() {
        System.out.println(H_LINE
                + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                + H_LINE);
    }

    public void deadlineAdded(Deadline deadlineTask, TaskList taskList) {
        System.out.println(H_LINE
                + "Got it. I've added this task:\n"
                + deadlineTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ") + "in the list." + "\n"
                + H_LINE);
    }

    public void deadlineMissingContent() {
        System.out.println(H_LINE
                + "☹ OOPS!!! The deadline needs to have a task description and /by .\n"
                + H_LINE);
    }

    public void eventAdded(Event eventTask, TaskList taskList) {
        System.out.println(H_LINE
                + "Got it. I've added this task:\n"
                + eventTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ") + "in the list." + "\n"
                + H_LINE);
    }

    public void eventMissingContent(){
        System.out.println(H_LINE
                + "☹ OOPS!!! The event needs to have a task description, /from and /to.\n"
                + H_LINE);
    }

    public void taskDeleted(Task deletedTask, TaskList taskList){
        System.out.println(H_LINE
                + "Noted. I've removed this task:\n"
                + deletedTask + "\n"
                + "Now you have " + taskList.getSize() + ((taskList.getSize() > 1) ? " tasks " : " task ") + "in the list." + "\n"
                + H_LINE);
    }

    public void findResponse(String tasks){
        System.out.println(H_LINE
                + "Here are the matching tasks in your list:\n"
                + tasks
                + H_LINE);
    }

    public void invalidInputRes(){
        System.out.println(H_LINE
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + H_LINE);
    }
}
