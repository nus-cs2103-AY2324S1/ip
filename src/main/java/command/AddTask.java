package command;

import data.*;
import exception.DukeException;


public class AddTask implements Command {
    protected Builder<Task> taskBuilder;
    public AddTask (Builder<Task> taskBuilder) {
        this.taskBuilder = taskBuilder;
    }

    @Override
    public void execute(String input) throws DukeException{
        Store s = Store.getInstance();
        Task task = taskBuilder.buildFromString(input);
        if (task == null) {
            System.out.println("Invalid input");
            return;
        }
        s.addTask(task);
        System.out.println("Got it. I’ve added this task:");
        System.out.println(task);
        System.out.println("Now you have " + s.getTaskCount() + " tasks in the list.");
    }
    
}


