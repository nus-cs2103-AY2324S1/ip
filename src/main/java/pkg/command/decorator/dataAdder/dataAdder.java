package pkg.command.decorator.dataAdder;


import pkg.task.*;
import pkg.command.command;
import pkg.command.decorator;
import pkg.command.taskBuilder;
import pkg.store.*;

public abstract class dataAdder extends decorator{
    private taskBuilder taskBuilder;
    public dataAdder(command command, taskBuilder taskBuilder) {
        super(command);
        this.taskBuilder = taskBuilder;
    }

    @Override
    public void execute(String input) {
        store s = store.getInstance();
        Task task = taskBuilder.build(input);
        s.addTask(task);
        System.out.println("Got it. I’ve added this task:");
        System.out.println(task);
        System.out.println("Now you have " + s.getTaskCount() + " tasks in the list.");
        super.execute(input);  
    }
}
