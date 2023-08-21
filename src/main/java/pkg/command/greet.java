package pkg.command;

public class greet implements command{
    @Override
    public void execute(String input) {
        System.out.println("Hello! I'm Doctor101");
        System.out.println("What can I do for you?");
    }
    
}
