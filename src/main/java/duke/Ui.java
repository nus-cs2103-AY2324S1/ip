package duke;

import duke.task.Task;
import java.util.List;

public class Ui {

    private final static String LINE = "____________________________________________________________";

    public void printLine(){
        System.out.println(LINE);
    }
    public void showGreeting(){
        printLine();
        System.out.println("Hello! I'm MYBOT:)");
        System.out.println("What can I do for you?");
        System.out.println("(if you are entering a deadline/event time please enter in the format date,day,time)");
        printLine();
    }

    public void closeGreeting(){
        System.out.println(LINE + "\nBye. Hope to see you again soon!\n" + LINE);
    }

    public void printException(MYBotExceptions e){
        System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
    }

    public void printTaskList(List<Task> tasks, int taskCount){
        System.out.println(LINE + "\nHere are the tasks in your list:");
        for (int i = 0; i < taskCount; i++){
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
        printLine();
    }

    public void printAddTask(Task task, int taskCount){
        System.out.println(LINE + "\nI've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    public void printMarkTask(Task task){
        System.out.println(LINE + "\nGood job completing! I've marked these task as done:):");
        System.out.println(task.toString() + "\n" + LINE);
    }

    public void printUnmarkTask(Task task){
        System.out.println(LINE + "\nOK, I've marked this task as not done yet:");
        System.out.println(task.toString() + "\n" + LINE);
    }

    public void printRemoveTasks(Task task, TaskList task_List) {
        printLine();
        System.out.println("Noted. I've removed this task:\n  " + task.toString());
        System.out.println("Now you have " + task_List.getTask_Count() + " tasks in the list.");
        printLine();
    }

    public void printFileError() {
        System.out.println("MYBOT has created a file MYBOT.txt to record your tasks");
    }

    public void printNoTasks() {
        System.out.println("You have no task at the moment!");
    }
}
