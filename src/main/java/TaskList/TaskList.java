package TaskList;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public String addList(String newList) {
        Task newTask = new Task(newList);
        this.taskList.add(newTask);
        return "added: " + newTask;
    }

    public String list() {
        StringBuilder listOutput = new StringBuilder();
        listOutput.append("Here are the tasks in your list: ");

        for (int i = 0; i < this.taskList.size(); i++) {
            listOutput.append("\n\t").
                    append((i + 1)).
                    append(". ").
                    append(this.taskList.get(i));
        }

        return listOutput.toString();
    }

    public String mark(int toDoIndex) {
        this.taskList.get(toDoIndex - 1).mark();
        return "Nice! I've marked this task as done: \n\t\t" + this.taskList.get(toDoIndex - 1);
    }

    public String unmark(int toDoIndex) {
        this.taskList.get(toDoIndex - 1).unmark();
        return "OK, I've marked this task as not done yet: \n\t\t" + this.taskList.get(toDoIndex - 1);
    }

    @Override
    public String toString() {
        System.out.println(this.taskList);
        return this.taskList.toString();
    }
}
