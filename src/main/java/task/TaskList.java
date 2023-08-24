package task;

import command.CommandException;
import main.Main;

import java.util.function.BiConsumer;

public class TaskList {

    // I think here along the array, also using a HashMap<String, task.Task> to store the task could be better because it can more easily get a task by name
    // But since the question requires me to use the array to store tasks, I have to do so
    private Task[] list;
    private int count;

    public TaskList(){
        this.list = new Task[100];
        this.count = 0;
    }

    public Task findTaskByName(String name){
        for(int i = 0; i < this.count; i++){
            if(this.list[i].getName().equals(name)){
                return this.list[i];
            }
        }
        return null;
    }

    public void addTask(Task newTask) throws CommandException {
        if(this.count >= 100){
            throw new CommandException("Error: task list is full.");
        }
        if(this.findTaskByName(newTask.getName()) != null){
            throw new CommandException("Error: A task with name '" + newTask.getName() + "' already exists.");
        }
        this.list[this.count] = newTask;
        this.count++;
        Main.getInstance().say("Got it. I've added this task:", true, false);
        Main.getInstance().say("  " + newTask.toString(), false, false);
        Main.getInstance().say("Now you have " + Main.getInstance().getTaskList().getCount() +" tasks in the list.", false, true);
    }

    public void iterate(BiConsumer<Integer, Task> consumer){
        for(int i = 0;i < this.count; i++){
            consumer.accept(i, this.list[i]);
        }
    }

    public int getCount(){
        return this.count;
    }
}
