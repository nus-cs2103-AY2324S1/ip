import java.util.Scanner;
import java.util.ArrayList;

public class ChatBot {

    //Name of the user's ChatBot.
    private String name;

    //Scanner used to see user input
    private static final Scanner sc = new Scanner(System.in);

    //Check if the chat has ended.
    private boolean hasEnded = false;

    //Array of Tasks to store in the list.
    private ArrayList<Task> list = new ArrayList<Task>();

    private static final String BORDER = "____________________________________________________________\n";


    //Constructor that allows for the naming of your own bot.
    public ChatBot(String name) {
        this.name = name;
    }

    /* 
     * Command to introduce the bot. Outputs an introduction with the bot's name.
     * 
     * @return void
     */
    public void intro() {
        System.out.println(BORDER);
        System.out.println("Hello! I am " + this.name + ".\n");
        System.out.println("What can I do for you today?\n"); 
        System.out.println(BORDER);
    }

    /* 
     * To exit chat and end the session.
     * 
     * @return void
     */
    public void exitChat() {
        System.out.println(BORDER);
        System.out.println("Bye. Have a bad day you doofus.\n");
        System.out.println(BORDER);
        hasEnded = true;
    }

    /* 
     * To exit chat and end the session.
     * 
     * @return boolean The hasEnded encapsulated in the chatbot.
     */
    public boolean isEnded() {
        return this.hasEnded;
    }

    /* 
     * Adds the user input into a list.
     * @param String message User input, parsed in readInput.
     * @return void
     */
    public void addToList(String item) {
        list.add(new Task(item));
        System.out.println(BORDER);
        System.out.println("Added: " + item + "\n");
        System.out.println(BORDER);
    }

    /*
     * Prints the list that has been built so far.
     * 
     * @return void 
     */
    public void displayList() {

        System.out.println(BORDER);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }

        System.out.println(BORDER);
    }

    public void mark(int listNum) {
        int index = listNum - 1;

        Task task = list.get(index);
        task.done();
        System.out.println(BORDER);
        System.out.println("Took you long enough. I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(BORDER);
    }

    public void unmark(int listNum) {
        int index = listNum - 1;

        Task task = list.get(index);
        task.undone();

        System.out.println(BORDER);
        System.out.println("You incompetent child. I've unmarked the task. Please get it together.");
        System.out.println(task.toString());
        System.out.println(BORDER);
        
    }

    /* Reads the input of the user, and executes the commands accordingly.
     * 
     * @param String message User input.
     * @return void
     */
    public void readInput(String input) {
        //Split the input so that we can read integers.
        String[] inputStrings = input.split(" ");

        switch(inputStrings[0]) {

            case("bye"):
                this.exitChat();
                break;

            case("list"):
                this.displayList();
                break;

            case("mark"):
                mark(Integer.parseInt(inputStrings[1]));
                break;
            case("unmark"):
                unmark(Integer.parseInt(inputStrings[1]));
                break;
            default:
                this.addToList(input);
        }
    }

    private class Task {
        private String label;
        private boolean done;

        Task(String label) {
            this.label = label;
            this.done = false;
        }

        public void done() {
            done = true;
        }

        public void undone() {
            done = false;
        }

        @Override
        public String toString() {
            if (done) {
                return "[X] " + label;
            }
            return "[ ] " + label;
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
