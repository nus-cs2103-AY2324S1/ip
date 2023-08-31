package emiya.task;

import emiya.emiyaexception.ListEmptyException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    public Task get(int pos) {
        return this.taskArrayList.get(pos);
    }

    public void remove(Task task) {
        this.taskArrayList.remove(task);
    }

    public int size() {
        return this.taskArrayList.size();
    }

    public void add(Task task) {
        this.taskArrayList.add(task);
    }

    public void list() throws ListEmptyException{
        int listPointer = 1;
        StringBuilder listString = new StringBuilder("-----------------------------------------\n" +
                "Lots of things to do! Get to it!:\n");
        for (Task task : taskArrayList) {
            if (task == null) {
                if (listPointer == 1) {
                    // throw new EmiyaException("List is empty! Please add items to list before trying to display list contents!");
                    throw new ListEmptyException();
                }
                break;
            }
            String listItem = listPointer + "." + task + "\n";
            listPointer++;
            listString.append(listItem);
        }
        listString.append("-----------------------------------------\n");
        System.out.println(listString);
    }

}
