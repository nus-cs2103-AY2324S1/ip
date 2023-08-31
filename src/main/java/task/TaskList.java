package task;

import command.CommandException;
import main.Main;
import util.Storage;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class TaskList {

    private ArrayList<Task> tasks;


    /**
     * The default constructor for the Task class
     */
    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Finds a task in the list by name and returns the task instance
     *
     * @param name The name of the task that you want to find
     * @return The task instance found by the given name, or "null" if no task in the list has the given name.
     */
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

    /**
     * Adds a new task into the list and then send a message to the user.
     * The new task must have a unique name from the names of all other tasks in the list.
     *
     * @param newTask the instance of the new task to add
     * @throws CommandException when there is already a task in the list that has the same name as the new task
     */
    public void addTaskAndSay(Task newTask) throws CommandException {
        if(this.findTaskByName(newTask.getName()) != null){
            throw new CommandException("Error: A task with name '" + newTask.getName() + "' already exists.");
        }
        this.tasks.add(newTask);
        Main.getInstance().getUi().say("Got it. I've added this task:", true, false);
        Main.getInstance().getUi().say("  " + newTask.toString(), false, false);
        Main.getInstance().getUi().say("Now you have " + Main.getInstance().getTaskList().getCount() +" tasks in the list.", false, true);
        Storage.saveTasksToFile();
    }

    /**
     * Simply adds a new task into the list.
     *
     * @param newTask the instance of the new task to add
     */
    public void addTask(Task newTask){
        this.tasks.add(newTask);
    }

    /**
     * Removes a task from the list
     *
     * @param index the index of the task instance that will be removed in the list
     * @return The removed task instance
     */
    public Task removeTask(int index){
        Task removedTask = this.tasks.remove(index);
        Storage.saveTasksToFile();
        return removedTask;
    }

    /**
     * Iterates all tasks by a given consumer method
     *
     * @param consumer a BiConsumer that takes two parameters to consume:
     * the first parameter is the index of the current task in the list,
     * the second parameter is the current task instance.
     */
    public void iterate(BiConsumer<Integer, Task> consumer){
        int index = 0;
        for(Task task : this.tasks){
            consumer.accept(index, task);
            index++;
        }
    }

    /**
     * Get the number of tasks in the list
     *
     * @return the number of tasks in the list
     */
    public int getCount(){
        return this.tasks.size();
    }
}
