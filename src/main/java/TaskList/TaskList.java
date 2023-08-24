package TaskList;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public String addToDo(String name) {
        ToDo newToDo = new ToDo(name);
        this.taskList.add(newToDo);
        return "Got it. I've added this task: \n\t\t" +
                newToDo +
                "\n\tNow you have " + this.taskList.size() + " tasks in the list.";
    }

    public String addEvent(String name, String startTime, String endTime) {
        Event newEvent = new Event(name, startTime, endTime);
        this.taskList.add(newEvent);
        return "Got it. I've added this task: \n\t\t" +
                newEvent +
                "\n\tNow you have " + this.taskList.size() + " tasks in the list.";
    }

    public String addDeadline(String name, String deadline) {
        Deadline newDeadline = new Deadline(name, deadline);
        this.taskList.add(newDeadline);
        return "Got it. I've added this task: \n\t\t" +
                newDeadline +
                "\n\tNow you have " + this.taskList.size() + " tasks in the list.";
    }

    public String list() {
        StringBuilder listOutput = new StringBuilder();
        listOutput.append("Here are the tasks in your list: ");

        for (int i = 0; i < this.taskList.size(); i++) {
            listOutput.append("\n\t").
                    append((i + 1)).
                    append(".").
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

    public String delete(int deleteIndex) {
        Task deletedTask = this.taskList.get(deleteIndex - 1);
        this.taskList.remove(deleteIndex - 1);
        return "Noted. I've removed this task: \n\t\t" +
                deletedTask +
                "\n\tNow you have " + this.taskList.size() + " tasks in the list.";
    }

    @Override
    public String toString() {
        System.out.println(this.taskList);
        return this.taskList.toString();
    }
}
