package tasks;

import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> inputs = new ArrayList<>();

    public TaskList(ArrayList<Task> inputs){
        this.inputs = inputs;
    }

    public void addTask(Task task){
        this.inputs.add(task);
    }

    public void removeTask(int num){
        if(num < 0){
            System.out.println("The value cannot be negative. Please try again.");
        } else if (num >= this.inputs.size()){
            System.out.println("The input value is wrong. Please try again.");
        } else {
            this.inputs.remove(num);
        }
    }

    public void markTask(int num){
        if(num < 0){
            System.out.println("The value cannot be negative. Please try again.");
        } else if (num >= this.inputs.size()){
            System.out.println("The input value is wrong. Please try again.");
        } else {
            this.inputs.get(num).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + inputs.get(num));
        }
    }

    public void unMarkTask(int num){
        if(num < 0){
            System.out.println("The value cannot be negative. Please try again.");
        } else if (num >= this.inputs.size()){
            System.out.println("The input value is wrong. Please try again.");
        } else {
            this.inputs.get(num).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + inputs.get(num));
        }
    }

    public int len(){
        return this.inputs.size();
    }
    public ArrayList<Task> retrieveList(){
        return this.inputs;
    }
}
