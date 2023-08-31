import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * A chat bot that can be renamed, and responds to inputs from users
 * 
 * @author Owen Yeo
 * Version Level-3
 */
public class ChatBot {

    //Name of the user's ChatBot.
    private String name;

    //Scanner used to see user input
    private static final Scanner sc = new Scanner(System.in);

    //Check if the chat has ended.
    private boolean hasEnded = false;

    //Array of Tasks to store in the list.
    private ArrayList<Task> list = new ArrayList<>();

    //String representing a border.
    private static final String BORDER = "____________________________________________________________\n";

    //String representing the file directory.
    private static final String DATA_SAVE_PATH = "src/main/java/data/chatBot.txt"; 

    //Enum Command to make code cleaner and allow for the use of 
    //switch case statements.
    private static enum Command {
        BYE("bye"),
        DISPLAY_LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        ADD_TODO("todo"),
        ADD_DEADLINE("deadline"),
        ADD_EVENT("event"),
        DELETE("delete");

        private final String input;

        private Command(String input) {
            this.input = input;
        }

        public static Command parseInput(String input) {
            for(Command command: Command.values()) {
                if (command.input.equals(input)) {
                    return command;
                }
            }

            return null;
        }
    }

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
     * Adds the user input into a list, depending on the command.
     * If description is wrong, throws an exception.
     * 
     * @param String message User input, parsed in readInput.
     * @return void
     */
    public void addToList(String taskString, Command command) 
        throws InvalidDescriptionException {
        switch(command) {
            case ADD_TODO:
                if (taskString.equals(" ")) {
                    throw new InvalidDescriptionException("What? Where's your label? Stop this.");
                }
                list.add(new ToDo(taskString));
                saveTasks();
                break;

            case ADD_DEADLINE:
                try {
                    String[] deadlineParts = taskString.split("/by");
                    list.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
                    saveTasks();
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidDescriptionException("Are you stupid? Can you follow instructions?");
                }
                break;

            case ADD_EVENT:
                try {
                    String[] eventParts = taskString.split("/from");
                    String eventLabel = eventParts[0];
                    String[] eventParts2 = eventParts[1].split("/to");
                    list.add(new Event(eventLabel.trim(), eventParts2[0].trim(), eventParts2[1].trim()));
                    saveTasks();
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidDescriptionException("Are you stupid? Can you follow instructions?");
                }
                break;
                
        }
        System.out.println(BORDER);
        System.out.println("What? You ain't finishing it. Added: \n");
        System.out.println(list.get(list.size() - 1) + "\n");
        System.out.println("Now you have an overwhelming " + list.size() + " things to do.\n");
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

    /*
     * To mark tasks as done.
     * 
     * @param int listNum the item on the list to mark.
     * @return void
     */
    public void mark(int listNum) {
        int index = listNum - 1;

        Task task = list.get(index);
        task.done();
        System.out.println(BORDER);
        System.out.println("Took you long enough. I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(BORDER);

        saveTasks();
    }

     /**
      * To unmark a list item as undone.
      *
      * @param listNum Index of the item on the list to unmark.
      */
    public void unmark(int listNum) {
        int index = listNum - 1;

        Task task = list.get(index);
        task.undone();

        System.out.println(BORDER);
        System.out.println("You incompetent child. I've unmarked the task. Please get it together.");
        System.out.println(task.toString());
        System.out.println(BORDER);

        saveTasks();
        
    }

    /**
     *  Deletes the item off the list and prints it out with a message.
     * 
     * @param listNum Index of the item of the list to delete.
     */
    public void delete(int listNum) {
        int index = listNum - 1;

        Task task = list.get(index);

        System.out.println(BORDER);
        System.out.println("I knew you couldn't finish it. Or maybe you did. I don't care. Deleted:\n");
        System.out.println(task.toString());
        System.out.println("Now you have an overwhelming " + (list.size() - 1) + " things to do.\n");
        System.out.println(BORDER);

        list.remove(index);
        saveTasks();
    }

    private void loadTasks() {
        try {
            File file = new File(DATA_SAVE_PATH);
            
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private void saveTasks() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter
            (DATA_SAVE_PATH, false));

            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i).toString());
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Error writing file:" + e.getMessage());
        }
    }

     /**
      * Reads the input of the user, and executes the commands accordingly.
      * If a command is unknown, throws an exception.
      *
      * @param input
      * @throws InvalidDescriptionException
      * @throws InvalidCommandException
      * @throws InvalidIndexException
      */
    public void readInput(String input) throws 
        InvalidDescriptionException, InvalidCommandException, InvalidIndexException {
        //Split the input so that we can read integers.
        String[] inputStrings = input.split(" ", 2);
        Command command = Command.parseInput(inputStrings[0]);
        if (command == null) {
            throw new InvalidCommandException("What are you saying? Try again.");
        }

        switch(command) {
            case BYE:
                this.exitChat();
                break;

            case DISPLAY_LIST:
                this.displayList();
                break;

            case MARK:
                try {
                    mark(Integer.parseInt(inputStrings[1]));
                } catch (NumberFormatException e) {
                    throw new InvalidIndexException("Are you stupid? That's not a number.");
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidIndexException("That's not even a number on the list, idiot.");
                }
                break;
            case UNMARK:
                try {
                    unmark(Integer.parseInt(inputStrings[1]));
                } catch (NumberFormatException e) {
                    throw new InvalidIndexException("Are you stupid? That's not a number.");
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidIndexException("That's not even a number on the list, idiot.");
                }
                break;

            case ADD_TODO:
                addToList(inputStrings[1], command);
                break;

            case ADD_DEADLINE:
                addToList(inputStrings[1], command);
                break;
            
            case ADD_EVENT:
                addToList(inputStrings[1], command);
                break;

            case DELETE:
                try {
                    delete(Integer.parseInt(inputStrings[1]));
                } catch (NumberFormatException e) {
                    throw new InvalidIndexException("Are you stupid? That's not a number.");
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidIndexException("That's not even a number on the list, idiot.");
                }
                break;

            default:
                throw new InvalidCommandException("Don't be stupid, speak english.");
        }
    }

    public static void main(String[] args) {

        //Test chatbot named "Bobby Wasabi".
        ChatBot chatbot = new ChatBot("Bobby Wasabi");
        chatbot.intro();

        //While chat has not ended, keep reading input.
        while(!chatbot.isEnded()) {
            String input = sc.nextLine();
            chatbot.readInput(input);
        }
    }
}
