package tasks;

import exceptions.JamesBondException;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class TaskList {
    ArrayList<Task> toDos;

    public TaskList() {
        toDos = new ArrayList<>(100);
    }

    //keep taking in userInput
    //add userInput to the list
   /* public void addInput(String textInput) {
        UserInput userInput = new UserInput(textInput);
            this.toDos.add(userInput);
            System.out.println("added:" + userInput.text);
    } */

    public void add(Task task) {
        toDos.add(task);
    }

    public void delete(int index) {
        toDos.remove(index);
    }

    public int len() {
        return this.toDos.size();
    }

    public Task get(int index) {
        return toDos.get(index);
    }

    public ArrayList<Task> getToDos() {
        return toDos;
    }

    public int findTask(String task) {
        for (int i = 0; i < toDos.size(); i++) {
            if (task.equalsIgnoreCase(toDos.get(i).text)) {
                return i;
            }
        }
        return -1;
    }

}
