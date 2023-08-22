package java.doctor.Command;

import java.doctor.store.store;
import java.util.regex.Pattern;
public class Mark implements Command{
    
    @Override
    public void execute(String input) {
        Pattern pattern = Pattern.compile("mark \\d+");
        if (!pattern.matcher(input).matches()) {
            System.out.println("Please enter a valid command");
            return;
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        store s = store.getInstance();
        if(!s.mark(index)){
            System.out.println("Invalid index");
            return;
        };
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(s.getTask(index));
    }
    
}
