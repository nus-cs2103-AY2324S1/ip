package duke.storage;

import java.util.ArrayList;

import duke.helper.Ui;
import duke.tasks.Task;

public class TaskList {
    private static ArrayList<Task> taskList;

    /**
    * constuct the TaskList by initializing the taskList
    */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
    * constuct the TaskList by initializing the taskList
    * with a given arraylist
    *
    * @param tasks the given arraylist
    */
    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    /**
    * execute the clear command
    */
    public void clear() {
        taskList.clear();
        Storage.save(taskList);
        Ui.print("Okay, I have cleared all tasks.");
    }

    /**
    * execute the delete command
    *
    * @param num the index of the task the user want to delete
    */
    public void delete(int num) {
        Ui.print("I've removed this task:");
        taskList.get(num - 1).getStatus();
        taskList.remove(num - 1);
        Storage.save(taskList);
        Ui.print("Current # of " + plural(taskList.size(), "task") + ": " + taskList.size());
    }

    /**
    * execute the mar command
    *
    * @param num the index of the task the user want to mark
    * @param isDone a boolean value that indicated whether the task is done or not
    */
    public void mark(int num, boolean isDone) {
        taskList.get(num - 1).markItem(isDone);
        Storage.save(taskList);
    }

    /**
    * execute print command by printing all tasks
    */
    public void print() {
        int index = 1;
        for (Task task: taskList) {
            System.out.println(index + ". " + task.getStatus());
            index ++;
        }
        Ui.print("Current # of " + plural(taskList.size(), "task") + ": " + taskList.size());
    }

    /**
    * execute the print command by printing task with given time
    *
    * @param time the time of tasks the user want to see
    */
    public void print(String time) {
        int index = 1;
        for (Task task: taskList) {
            if(task.getTime() != null && task.getTime().contains(time)) {
                System.out.println(index + ". " + task.getStatus());
                index ++;
            }
        }
        index -= 1;
        Ui.print("Current # of " + plural(index, "task") + ": " + index);
    }

    /**
    * execute the add command
    *
    * @param input the task to add to the taskList
    */
    public void add(Task input) {
        taskList.add(input);
        Storage.save(taskList);
        String[] messageList = {("Got it! This task has been added: ")
        , (input.getStatus())
        , ("Current # of " + plural(taskList.size(), "task") + ": " + taskList.size())};
        Ui.print(messageList);
    }

    public void addTest(Task input) {
        taskList.add(input);
        String[] messageList = {("Got it! This task has been added: ")
                , (input.getStatus())
                , ("Current # of " + plural(taskList.size(), "task") + ": " + taskList.size())};
        Ui.print(messageList);
    }

    /**
    * returns the size ot the taskList
    *
    * @return the number of tasks in the taskList
    */
    public int size() {
        return taskList.size();
    }

    /**
    * return the task by the given index if it exists
    *
    * @param num the index of the task
    * @return the task by the given index
    */
    public Task get(int num) {
        if (num >= 0 && num < taskList.size()) {
            return taskList.get(num);
        }
        return null;
    }

    private static String plural(int count, String word) {
        return (count <= 1) ? word : (word + "s");
    }
}
