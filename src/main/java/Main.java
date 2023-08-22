import java.util.HashMap;

public class Main {
    private static Main INSTANCE;
    private String name = "your girlfriend";
    private HashMap<String,ICommandHandler> commands;

    public Main(){
        this.commands = new HashMap<String,ICommandHandler>();
        this.commands.put("intro", new IntroHandler());
        this.commands.put("exit", new ExitHandler());
    }

    public static void main(String[] args) {
        initialize();
        INSTANCE.executeCommand("intro");
        INSTANCE.executeCommand("exit");
    }

    private static void initialize(){
        INSTANCE = new Main();
    }
    private void executeCommand(String command){
        if(!this.commands.containsKey(command)){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        this.commands.get(command).execute();
    }

    public static Main getInstance(){
        return INSTANCE;
    }

    public String getName(){
        return this.name;
    }
}
