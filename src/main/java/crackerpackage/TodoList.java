package crackerpackage;

import java.io.FileWriter;
import java.util.ArrayList;

import crackerpackage.tasks.*;


public class TodoList {



    FileWriter writer;
    ArrayList<Task> list;

    TodoList(){
        this.list = new ArrayList<>();
    }
    public void store(Task s){
        list.add(s);
    }


    public void markDone(int index){
        list.get(index).markDone();
    }
    public void markUndone(int index){
        list.get(index).markUndone();
    }
    public void deleteTask(int index) {list.remove(index);}

    public String getTaskString(int index){
        return list.get(index).toString();
    }
    public int size(){ return list.size();}
    public Task getTask(int i){
        return list.get(i);
    }

    /**
     * Filters all the tasks that contain a keyword.
     * @param keyword the word that must be in the task description
     * @return a TodoList that contains all the tasks that contain the keyword
     */
    public TodoList filter(String keyword){
        TodoList filteredList = new TodoList();

        for (int i = 0; i < list.size(); i++){
            Task task = list.get(i);
            if (task.getDesc().contains(keyword)) {
                filteredList.store(task);
            }
        }
        return filteredList;
    }



}