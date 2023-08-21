package pkg.command;
import pkg.store.*;

public abstract class dataModifier extends decorator {
    public dataModifier(command command) {
        super(command);
    }

    @Override
    public void execute(String input) {
        store s = store.getInstance();
        int id = 
        s.addTask(task);
        System.out.println("Got it. I’ve added this task:");
        System.out.println(task);
        System.out.println("Now you have " + s.getTaskCount() + " tasks in the list.");
        super.execute(input);  
    }
    
}
