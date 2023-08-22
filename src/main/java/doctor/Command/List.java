package java.doctor.Command;

import java.doctor.store.store;
public class List implements Command{
    @Override
    public void execute(String input) {
        store s = store.getInstance();
        System.out.print("Here are the tasks in your list:" + "\n" + s);    
    } 
}
