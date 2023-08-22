import java.util.regex.Pattern;
public class Unmark implements Command{
    @Override
    public void execute(String input) {
        Pattern pattern = Pattern.compile("unmark \\d+");
        if (!pattern.matcher(input).matches()) {
            System.out.println("Please enter a valid command");
            return;
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        Store s = Store.getInstance();
        if(!s.unmark(index)){
            System.out.println("Invalid index");
            return;
        }
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(s.getTask(index));
    }
    
}
