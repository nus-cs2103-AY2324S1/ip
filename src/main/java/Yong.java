import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;




/**
 * Chatbot named YONG that responds to user input using CLI
 */
public class Yong {

    static String termination_word = "BYE";
    static ArrayList<Task> history = new ArrayList<>();

    static final String FILE_PATH = "./data/dataFile";

    public static void main(String[] args) {
        Yong.createFile();
        Yong.readFile();
        String action;
        Yong.sayHi();
        Scanner reader = new Scanner(System.in);
        action = reader.nextLine().toString();
        String[] parts = action.split(" ");
        String check = parts[0].toUpperCase();
        while (!check.equals(termination_word)) {
            try {
                Yong.actions(action);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                action = reader.nextLine().toString();
                parts = action.split(" ");
                check = parts[0].toUpperCase();
            }

        }
        Yong.saveFile();
        Yong.sayBye();

    }

    /**
     * Method to say Hi to users
     */
    private static void sayHi() {
        String logo = "YONG";
        System.out.println("Hello I'm " + logo + "\n" +
                "What can I do for you? \n" +
                "------------------------------------- \n"
        );
    }

    /**
     * Method to say bye upon termination of program
     */
    private static void sayBye() {
        System.out.println("Thank you and have a good day!");
    }


    /**
     * Method that takes in user input and runs the suitable function
     * @param inp User input in a string format
     */
    private static void actions(String inp) {
        inp = inp.toUpperCase();
        String [] parts = inp.split(" ", 2);
        String check = parts[0];
        String numberString = parts.length > 1 ? parts[1] : "";
        switch (check) {
        case "BYE":
            System.out.println();
            break;
        case "HI":
            System.out.println("How are you doing today! \n");
            break;
        case "LIST":
            Yong.list();
            break;
        case "MARK":
            Yong.mark(numberString);
            break;
        case "UNMARK":
            Yong.unmark(numberString);
            break;
        case "DELETE":
            Yong.delete(numberString);
            break;
        case "TODO":
            ToDo toDo = Yong.toDo(inp);
            history.add(toDo);
            System.out.println("Okay! Task added \n" + toDo + "\n");
            break;
        case "EVENT":
            Event event = Yong.event(inp);
            history.add(event);
            System.out.println("Okay! Task added \n" + event + "\n");
            break;
        case "DEADLINE":
            Deadline deadLine = Yong.deadline(inp);
            history.add(deadLine);
            System.out.println("Okay! Task added \n" + deadLine + "\n");
        default:
            throw new DukeException("I do not know what you are saying.");
        }
    }

    /**
     * Method that displays the list of tasks stored
     */
    private static void list() {
        for (int i = 0; i < history.size(); i++) {
            System.out.println(i+1 + ": " + history.get(i));
        }
    }

    /**
     * Method that marks a specific task as being done
     * @param numberString User input number corresponding to the task that should be marked
     */
    private static void mark(String numberString) {
        Integer number = Integer.parseInt(numberString);
        if (number > history.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }
        Task task = history.get(number-1);
        task.markAsDone();
        System.out.println("YONG has marked this task as completed! \n" + task.toString() + "\n");
    }

    /**
     * Method that marks a specific task as being undone
     * @param numberString User input number corresponding to the task that should be marked
     */
    private static void unmark(String numberString) {
        Integer number = Integer.parseInt(numberString);
        if (number > history.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }
        Task task = history.get(number-1);
        task.unmarkAsDone();
        System.out.println("YONG has unmarked this task! \n" + task.toString() + "\n");
    }

    /**
     * Method that deletes a specific task
     * @param numberString User input number corresponding to the task that should be deleted
     */
    private static void delete(String numberString) {
        Integer number = Integer.parseInt(numberString);
        if (number > history.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }
        Task task = history.remove(number-1);
        System.out.println("YONG has deleted this task for you! \n" + task.toString() + "\n");
    }

    /**
     * Method to create a Todo task and add it to the list of tasks
     * Input format should be "todo (task)"
     *
     * @param inp User input of the entire string corresponding to the TODO task
     * @return ToDo object with the description provided
     */
    private static ToDo toDo(String inp) {
        try {
            String[] type_description = inp.split(" ", 2);
            String type = type_description[0];
            String description = type_description[1];
            return new ToDo(description);
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for a ToDo task!");
        }
    }

    /**
     * Method to create a Deadline task and add it to the list of tasks
     * Input format should be "deadline (task) /by YYYY-MM-DD"
     *
     * @param inp User input of the entire string corresponding to the Deadline task
     * @return Deadline object with the description provided
     */
    private static Deadline deadline(String inp) {
        try {
            String[] parts = inp.split("/", 2);
            String[] type_description = parts[0].split(" ", 2);
            String type = type_description[0];
            String description = type_description[1];
            return new Deadline(description, parts[1]);
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for a Deadline task!");
        }
    }

    /**
     * Method to create an Event task and add it to the list of tasks
     * Input format should be "event (task) /from YYYY-MM-DD /to YYYY-MM-DD
     *
     * @param inp User input of the entire string corresponding to the Event task
     * @return Event object with the description provided
     */
    private static Event event(String inp) {
        try {
            String[] parts = inp.split("/", 3);
            String[] type_description = parts[0].split(" ", 2);
            String type = type_description[0];
            String description = type_description[1];
            String from = parts[1].trim().split(" ")[1];
            String to = parts[2].trim().split(" ")[1];
            return new Event(description, from, to);
        } catch (Exception e) {
            System.out.println(e);
            throw new DukeException("Please give a valid description for an Event task!");
        }
    }

    /**
     * Creates file to save task list if file is not present
     */
    private static void createFile() {
        File dataFile = new File(FILE_PATH);
        try {
            boolean hasFile = dataFile.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the current history array to the file
     */
    private static void saveFile() {
        try {
            FileOutputStream dataFileStream = new FileOutputStream(FILE_PATH);
            ObjectOutputStream objectStream = new ObjectOutputStream(dataFileStream);
            objectStream.writeObject(history);
            objectStream.close();
        } catch (Exception e) {
            System.out.println("File is not found! But this shouldn't happen LOL");
        }
    }

    /**
     * Reads the file to populate the history array
     */
    private static void readFile() {
        try {
            File dataFile = new File(FILE_PATH);
            FileInputStream dataFileStream = new FileInputStream(dataFile);
            if (dataFileStream.available() > 0) {
                ObjectInputStream objectStream = new ObjectInputStream(dataFileStream);
                history = (ArrayList<Task>) objectStream.readObject();
                objectStream.close();
            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("File is not found! But this shouldn't happen LOL");
        } catch (Exception e) {
            System.out.println("There is an error occurring, " + e.getMessage());
        }
    }


}
