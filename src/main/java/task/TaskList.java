package task;

import command.CommandException;
import main.Main;
import util.FileUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiConsumer;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public Task findTaskByName(String name){
        int count = this.tasks.size();
        for(int i = 0; i < count; i++){
            Task task = this.tasks.get(i);
            if(task.getName().equals(name)){
                return task;
            }
        }
        return null;
    }

    public void addTaskAndSay(Task newTask) throws CommandException {
        if(this.findTaskByName(newTask.getName()) != null){
            throw new CommandException("Error: A task with name '" + newTask.getName() + "' already exists.");
        }
        this.tasks.add(newTask);
        Main.getInstance().say("Got it. I've added this task:", true, false);
        Main.getInstance().say("  " + newTask.toString(), false, false);
        Main.getInstance().say("Now you have " + Main.getInstance().getTaskList().getCount() +" tasks in the list.", false, true);
        FileUtil.saveTasksToFile();
    }

    public void addTask(Task newTask){
        this.tasks.add(newTask);
    }

    public Task removeTask(int index){
        Task removedTask = this.tasks.remove(index);
        FileUtil.saveTasksToFile();
        return removedTask;
    }

    public void iterate(BiConsumer<Integer, Task> consumer){
        int index = 0;
        for(Task task : this.tasks){
            consumer.accept(index, task);
            index++;
        }
    }

    public int getCount(){
        return this.tasks.size();
    }
}
