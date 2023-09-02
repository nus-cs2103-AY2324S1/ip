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



}