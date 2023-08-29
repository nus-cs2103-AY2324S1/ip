package command;

import data.storage.Store;

public class List implements Command{
    @Override
    public void execute(String input) {
        Store s = Store.getInstance();
        System.out.print("Here are the tasks in your list:" + "\n" + s);    
    } 
}
