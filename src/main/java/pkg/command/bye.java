package pkg.command;

public class bye implements command{
    @Override
    public void execute(String input) {
        System.out.println("Bye. Hope to see you again soon!");
    }
    
}
