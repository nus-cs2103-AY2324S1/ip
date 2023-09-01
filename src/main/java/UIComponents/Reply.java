package UIComponents;

import CrackerPackage.TodoList;
import tasks.Task;

import java.util.ArrayList;
public class Reply {
    private String line = "____________________________________________________________";
    private ArrayList<String> lines;

    public Reply(){
        this.lines = new ArrayList<>();
    }



    public void echo(String content){
        System.out.println(line);
        System.out.println(content);
        System.out.println(line);
    }

    private void echo(){
        System.out.println(line);
        for(int i = 0;i< lines.size();i++){
            System.out.println(lines.get(i));
        }
        System.out.println(line);
        lines.removeAll(lines);
    }
    private void add(String s){
        lines.add(s);
    }

    public void iterate(TodoList list){

        this.add("Here are the tasks in your list:");
        for(int i = 0 ; i< list.size(); i++){
            this.add((i+1) + ". " + list.getTaskString(i));
        }
        echo();
    }

    public void storeTaskReply(Task t,int size){

        this.add("Got it. I've added this task:");
        this.add(t.toString());
        this.add("Now you have " + size + " task(s) in the list.");

        echo();
    }

    public void deleteTaskReply(Task t, int size){

        this.add("Got it. I've removed this task:");
        this.add(t.toString());
        this.add("Now you have " + size + " task(s) in the list.");

        echo();
    }

    public void modifyTaskReply(Task t){

        this.add("Operation done. This is the current state of your task:");
        this.add(t.toString());

        echo();
    }
}