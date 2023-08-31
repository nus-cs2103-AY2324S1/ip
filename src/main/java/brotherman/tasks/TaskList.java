package brotherman.tasks;

import java.util.ArrayList;
public class TaskList {

    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList taskList) {this.taskList = taskList;}

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void delete(int num) {
        if (num < 0 || num >= taskList.size()) {
            System.out.println("Brotherman the value you put in wrong.  Try again.");
        } else {
            System.out.println(
                            "The task is now deleted Brotherman \n"
                            + taskList.get(num).toString()
            );
            taskList.remove(num);
        }
    }


    public ArrayList list() {
        return this.taskList;
    }

    public int size() {return this.taskList.size();}

    public void markDone(int num) {
        if (num < 0 || num >= taskList.size()) {
            System.out.println("Brotherman the value you put in wrong.  Try again.");
        } else {
            taskList.get(num).markAsDone();
            System.out.println(
                    "The task is done Brotherman \n"
                    + taskList.get(num).toString()
            );
        }
    }

    public void markUndone(int num) {
        if (num < 0 || num >= taskList.size()) {
            System.out.println("Brotherman the value you put in wrong.  Try again.");
        } else {
            taskList.get(num).unmarkAsDone();
            System.out.println(
                    "The task is now undone Brotherman \n"
                    + taskList.get(num).toString()
            );
        }
    }

    public ArrayList<Task> getTasksByKeyword(String keyword) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task listItems : taskList) {
            if (listItems.description.contains(keyword)) {
                list.add(listItems);
            }
        }

        return list;
    }
}
