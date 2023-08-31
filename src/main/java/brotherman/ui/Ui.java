package brotherman.ui;

import java.util.ArrayList;
import java.util.Scanner;
import brotherman.tasks.*;

public class Ui {

    private Scanner sc;
    private final String line = "___________________________________________________________\n";


    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println(
                line
                + "Hello! I'm Brotherman\n"
                + "What can I do for you?\n"
                + "Do remember to add your date and time in DD/MM/YYYY\n");
    }

    public void showGoodbyMessage() {
        System.out.println("Bye, see you again Brotherman!\n");
    }

    public void showLine() {
        System.out.println(line);
    }

    public String readCommand() {
        String userCommand = sc.nextLine();
        return userCommand;
    }

    public void readWrongValue() {
        System.out.println("Brotherman the value you put in wrong.  Try again.");
    }

    public void showTaskAdded(TaskList taskList) {
        System.out.println("The task has been added Brotherman \n");
        System.out.println(taskList.list().get(taskList.size() - 1).toString());
        System.out.println("Brotherman you have " + taskList.size() + " tasks in the list!");
//        System.out.println(line);

    }

    public void showTaskList(TaskList taskList) {
        System.out.println("Hey Brotherman, these are the tasks on your list!");
        ArrayList<Task> list = taskList.list();
        int start = 1;
        for (Task listItems : list) {
            System.out.println(start + ". " + listItems.toString());
            start++;
        }
    }

    public void showFind(TaskList taskList, String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        ArrayList<Task> list = taskList.getTasksByKeyword(keyword);

        if (list.size() == 0) {
            System.out.println("Brotherman, there are no tasks with that keyword");
        }

        for (Task listItems : list) {
            System.out.println(listItems.toString());
        }
    }


}
