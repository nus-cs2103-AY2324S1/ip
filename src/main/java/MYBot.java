public class MYBot {

    private String bot_Name;

    public MYBot(String bot_Name){
        this.bot_Name = bot_Name;
    }

    public void openGreeting(){
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + bot_Name + ":)");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void closeGreeting(){
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
