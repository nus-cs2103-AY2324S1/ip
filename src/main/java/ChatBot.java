import java.util.Scanner;

public class ChatBot {
    private String name;
    private static final Scanner sc = new Scanner(System.in);
    private boolean hasEnded = false;


    public ChatBot(String name) {
        this.name = name;
    }
    public void intro() {
        System.out.println("____________________________________________________________\n" + 
        "Hello! I am " + this.name + ".\n" + 
        "What can I do for you today?\n" +
        "____________________________________________________________\n");
    }

    public void exitChat() {
        System.out.println("Bye. Have a bad day you doofus.\n" + 
        "____________________________________________________________\n");
        hasEnded = true;
    }

    public boolean isEnded() {
        return this.hasEnded;
    }

    public void echo(String message) {
        System.out.println(message +"!\n" +
        "____________________________________________________________\n");
    }

    public void readInput(String input) {
        switch(input) {
            case("bye"):
                this.exitChat();
                break;
            default:
                this.echo(input);
        }
    }

    public static void main(String[] args) {
        ChatBot chatbot = new ChatBot("Bobby Wasabi");
        chatbot.intro();

        while(!chatbot.isEnded()) {
            String input = sc.nextLine();
            chatbot.readInput(input);
        }
    }
}
