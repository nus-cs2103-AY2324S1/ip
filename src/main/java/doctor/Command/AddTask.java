package java.doctor.Command;

import java.doctor.Builder.Builder;
import java.doctor.Task.*;
import java.doctor.store.*;


public class AddTask implements Command {
    protected Builder<Task> taskBuilder;
    public AddTask (Builder<Task> taskBuilder) {
        this.taskBuilder = taskBuilder;
    }

    @Override
    public void execute(String input) {
        store s = store.getInstance();
        Task task = taskBuilder.buildFromString(input);
        if (task == null) {
            System.out.println("Invalid input");
            return;
        }
        if(!s.addTask(task)) {
            System.out.println("Task list is full");
            return;
        }
        System.out.println("Got it. I’ve added this task:");
        System.out.println(task);
        System.out.println("Now you have " + s.getTaskCount() + " tasks in the list.");
    }
    
}


