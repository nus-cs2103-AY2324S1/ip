import java.util.Scanner;
import java.util.ArrayList;

public class ChatBot {

    //Name of the user's ChatBot.
    private String name;

    //Scanner used to see user input
    private static final Scanner sc = new Scanner(System.in);

    //Check if the chat has ended.
    private boolean hasEnded = false;

    //Array of Strings to store in the list.
    private ArrayList<String> list = new ArrayList<String>();


    //Constructor that allows for the naming of your own bot.
    public ChatBot(String name) {
        this.name = name;
    }

    /* Command to introduce the bot. Outputs an introduction with the bot's name.
     * 
     * @return void
     */
    public void intro() {
        System.out.println("____________________________________________________________\n" + 
        "Hello! I am " + this.name + ".\n" + 
        "What can I do for you today?\n" +
        "____________________________________________________________\n");
    }

    /* To exit chat and end the session.
     * 
     * @return void
     */
    public void exitChat() {
        System.out.println("Bye. Have a bad day you doofus.\n" + 
        "____________________________________________________________\n");
        hasEnded = true;
    }

    /* To exit chat and end the session.
     * 
     * @return boolean The hasEnded encapsulated in the chatbot.
     */
    public boolean isEnded() {
        return this.hasEnded;
    }

    /* Echos the user's input
     * @param String message User input, parsed in readInput.
     * @return void
     */
    public void addToList(String item) {
        list.add(item);
        System.out.println("____________________________________________________________\n");
        System.out.println("Added: " + item + "\n" +
        "____________________________________________________________\n");
    }

    public void displayList() {

        System.out.println("____________________________________________________________\n");

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }

        System.out.println("____________________________________________________________\n");
    }

    /* Reads the input of the user, and executes the commands accordingly.
     * 
     * @param String message User input.
     * @return void
     */
    public void readInput(String input) {
        switch(input) {
            case("bye"):
                this.exitChat();
                break;
            case("list"):
                this.displayList();
                break;
            default:
                this.addToList(input);
        }
    }

    public static void main(String[] args) {

        //Test chatbot named "Bobby Wasabi".
        ChatBot chatbot = new ChatBot("Bobby Wasabi");
        chatbot.intro();

        while(!chatbot.isEnded()) {
            String input = sc.nextLine();
            chatbot.readInput(input);
        }
    }
}
