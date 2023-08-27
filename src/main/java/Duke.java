import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A chatbot that helps to record tasks.
 */
public class Duke {
    ArrayList<Task> tasks;
    private enum FirstWord {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID
    }

    Scanner sc;
    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds horizontal line for replies by chatbot.
     * @param text
     */
    private void line(String text) {
        System.out.println(text);
        System.out.println("------------------------------------------");
    }

    /**
     * Greets by chatbot.
     */
    private void greet() {
        System.out.println("------------------------------------------");
        this.line("  Hello! I'm Jokey :) \n  What can I do for you?");
    }

    /**
     * Exits chatbot.
     */
    private void exit() {

        this.line("  Bye~ Hope to see you again soon! >w<");
    }

    /**
     * Lists out all tasks.
     */
    private void listOutTasks() {
        String tasksList = "";
        for(int i = 0; i < tasks.size(); i++) {
            tasksList += String.format("%d. %s\n", i + 1, tasks.get(i).toString().replace("  ", ""));
        }
        this.line(tasksList);
    }

    /**
     * Marks task as done.
     * @param index
     */
    private void mark(int index) {
        Task task = tasks.get(index);
        task.mark();
        this.line(String.format("  Nice! I've marked this task as done:\n    %s", task.toString()));
    }

    /**
     * Marks task as not done.
     * @param index
     */
    private void unmark(int index) {
        Task task = tasks.get(index);
        task.unmark();
        this.line(String.format("  Ok, I've marked this task as not done yet:\n    %s", task.toString()));
    }

    /**
     * Adds a To Do task.
     * @param reply
     * @throws DukeException
     */
    private void addToDo(String reply) throws DukeException {
        if (reply.length() == "todo".length()) {
            throw new DukeEmptyToDoException();
        }
        
        Task task = new ToDo(reply);
        addTask(task);
    }

    /**
     * Adds a Deadline task.
     * @param reply
     * @throws DukeException
     */
    private void addDeadline(String reply) throws DukeException {

        Task task = new Deadline(reply);

        addTask(task);
    }

    /**
     * Adds an Event task.
     * @param reply
     * @throws DukeException
     */
    private void addEvent(String reply) throws DukeException {
        Task task = new Event(reply);

        addTask(task);
    }

    /**
     * Deletes a task.
     * @param index
     */
    private void delete(int index) {
        Task removedTask = tasks.remove(index);
        System.out.println(String.format("  Noted. I've removed this task:"));
        System.out.println(removedTask.toString());
        this.line(String.format("  Now you have %d task(s) in the list.", tasks.size()));
        this.save();
    }

    /**
     * Constructs message for adding task.
     * @param task
     */
    private void addTask(Task task) {
        System.out.println(String.format("  Got it. I've added this task:"));
        tasks.add(task);
        System.out.println(String.format("  %s", task.toString()));
        this.line(String.format("  Now you have %d task(s) in the list.", tasks.size()));
        this.save();
    }

    private void save() {
        String dataPath = Paths.get("data", "duke.txt").toString();
        try {
            File dataFile = new File(dataPath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String line = task.saveToFileLine();
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {

        }

    }

    private void load() {
        try {
            String dataPath = Paths.get("data", "duke.txt").toString();
            File dataFile = new File(dataPath);
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            for (String line; (line = reader.readLine()) != null;) {
                tasks.add(fileToTask(line));
            }
        } catch (IOException ex) {

        }

    }

    private Task fileToTask(String line) {
        String[] savedToFileLine = line.split(" \\| ");
        String type = savedToFileLine[0];

        String status = savedToFileLine[1];
        String description = savedToFileLine[2];
        switch (type) {
            case "T":
                return ToDo.create(status, description);
            case "D":
                String due = "";
                return Deadline.create(status, description, due);
            case "E":
                String from = "";
                String to = "";
                return Event.create(status, description, from, to);
            default:
//                TODO
                return new Task("");
        }
    }

    /**
     * Triggers respective actions.
     */
    private void interact() {
        while(true) {
            String reply = sc.nextLine();
            FirstWord firstWord;
            try {
                firstWord = FirstWord.valueOf(reply.split(" ")[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                firstWord = FirstWord.INVALID;
            }
            try {
                switch (firstWord) {
                    case BYE:
                        exit();
                        break;
                    case LIST:
                        listOutTasks();
                        break;
                    case MARK:
                        mark(Character.getNumericValue(reply.charAt(5) - 1));
                        break;
                    case UNMARK:
                        unmark(Character.getNumericValue(reply.charAt(7) - 1));
                        break;
                    case TODO:
                        addToDo(reply);
                        break;
                    case DEADLINE:
                        addDeadline(reply);
                        break;
                    case EVENT:
                        addEvent(reply);
                        break;
                    case DELETE:
                        delete(Character.getNumericValue(reply.charAt(7) - 1));
                        break;
                    default:
                        throw new DukeUnknownCommandException();
                }
            } catch (DukeException e) {
                this.line(e.toString());
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.load();
        duke.greet();
        duke.interact();

    }
}
