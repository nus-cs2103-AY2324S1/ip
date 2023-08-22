import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static Main INSTANCE;
    private String name = "your girlfriend";
    private HashMap<String,ICommandHandler> commands;
    private boolean isRunning;

    public Main(){
        this.commands = new HashMap<String,ICommandHandler>();
        this.commands.put("intro", new IntroHandler());
        this.commands.put("list", new ListHandler());
        this.commands.put("bye", new ExitHandler());
    }

    public static void main(String[] args) {
        initialize();
        INSTANCE.run();
    }

    private void run(){
        this.executeCommand("intro");
        this.isRunning = true;
        Scanner sc = new Scanner(System.in);
        while(this.isRunning){
            String input = sc.nextLine();
            this.executeCommand(input);
        }
        sc.close();
        return;
    }

    private static void initialize(){
        INSTANCE = new Main();
    }
    private void executeCommand(String command){
        if(!this.commands.containsKey(command)){
            this.commands.get("list").execute(command);
            return;
        }
        this.commands.get(command).execute(command);
    }

    public static Main getInstance(){
        return INSTANCE;
    }

    public String getName(){
        return this.name;
    }

    public void exit(){
        this.isRunning = false;
    }

    public void say(String content){
        this.say(content, true,true);
    }
    public void say(String content, boolean outputUpperLine, boolean outputLowerLine){
        if(outputUpperLine){
            System.out.println("    ____________________________________________________________");
        }
        System.out.println("    " + content);
        if(outputLowerLine){
            System.out.println("    ____________________________________________________________");
        }

    }
}
