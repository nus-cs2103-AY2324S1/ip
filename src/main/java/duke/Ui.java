package duke;

import duke.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public void printException(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }

    public void listMessage(TaskList lst) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            System.out.println(String.valueOf(i + 1) + "." + task.toString());
        }
    }
    public String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }
    public void closeScanner() {
        this.sc.close();
    }
    public void uncompletedMessage(int index, TaskList lst) {
        Task tsk = lst.get(index);
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(tsk.toString());
    }
    public void completedMessage(int index, TaskList lst) {
        Task tsk = lst.get(index);
        System.out.printf("Nice! I've marked this task as done: \n");
        System.out.println(tsk.toString());
    }
    public void deletedMessage(int index, TaskList lst) {
        Task tsk = lst.get(index);
        System.out.println(tsk.removed());
    }
    public void addedMessage(Task tsk, TaskList lst) {
        System.out.println(tsk.confirmation(lst.size()));
    }
    public String getCommand() {
        return this.sc.nextLine();
    }
    public String byeGreetings() {
        return "Bye. Hope to see you again soon!";
    }

    public String name = " ____    __        __       \n"
            + "|  _ \\   | |  ____ | | __\n"
            + "| | | |  | | |     | |/ /\n"
            + "| |_| |  | | |     |   < \n"
            + "|____/   ___  ____ |_|\\_\\\n";
    public String greet() {
        return "Hello from \n " + this.name + "What can I do for you? ";
    }

}
