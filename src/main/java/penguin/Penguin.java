package penguin;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Penguin is the main logic of Penguin chatbot; its main responsibility is to parse commands and handle errors.
 */
public class Penguin {
    private static final String GREETING = "Honk! I'm Penguin! What can I do for you?";
    private static final String GOODBYE = "Honk! Hope to see you again soon!";
    private static final String MARK = "Honk honk! You did task ";
    private static final String UNMARK = "Fishes! You didn't do task ";
    private static final String TODO = "Honk! You'll have to do ";
    private static final String DEADLINE = "Flap flap flap! Must do this ";
    private static final String EVENT = "Fish party?! ";

    private static final String DELETE = "This task is thrown into the sea! ";

    private UI ui;
    private TaskList taskList;
    private Memory memory;
    /**
     * Constructor for Penguin chatbot.
     */
    public Penguin() {
        this.ui = new UI();
        this.taskList = new TaskList();
        this.memory = new Memory("data/memory.txt");

    }

    /**
     * Runs the Penguin chatbot.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {

        new Penguin().run();
    }

    /**
     * The main logic of Penguin chatbot; parses commands and handles errors.
     */
    public void run() {
        ui.out(GREETING);
        boolean running = true;
        this.taskList = memory.load();

        while (running) {
            try {
                String command = ui.in();
                if (command.equals("bye")) {
                    ui.out(GOODBYE);
                    running = false;
                } else if (command.equals("list")) {
                    ui.out(taskList.printList());
                } else if (command.startsWith("mark")) {
                    String[] spl = command.split(" ", 2);
                    int taskNo = Integer.parseInt(spl[1]);
                    taskList.list.get(taskNo - 1).done = true;
                    ui.out(MARK + taskList.list.get(taskNo - 1).getDisplay());
                } else if (command.startsWith("unmark")) {
                    String[] spl = command.split(" ", 2);
                    int taskNo = Integer.parseInt(spl[1]);
                    taskList.list.get(taskNo - 1).done = false;
                    ui.out(UNMARK + taskList.list.get(taskNo - 1).getDisplay());
                } else if (command.startsWith("todo")) {
                    String[] spl = command.split("todo ");
                    ToDo newToDo = new ToDo(spl[1]);
                    taskList.addTask(newToDo);
                    ui.out(TODO + newToDo.getDisplay());
                } else if (command.startsWith("deadline")) {
                    String[] spl = command.split("deadline | /by ");
                    Deadline newDeadline = new Deadline(spl[1], spl[2]);
                    taskList.addTask(newDeadline);
                    ui.out(DEADLINE + newDeadline.getDisplay());
                } else if (command.startsWith("event")) {
                    String[] spl = command.split("event | /from | /to ");
                    Event newEvent = new Event(spl[1], spl[2], spl[3]);
                    taskList.addTask(newEvent);
                    ui.out(EVENT + newEvent.getDisplay());
                } else if (command.startsWith("delete")) {
                    String[] spl = command.split(" ");
                    int taskNo = Integer.parseInt(spl[1]);
                    ui.out(DELETE + taskList.list.remove(taskNo - 1).getDisplay());

                } else {
                    throw new PenguinUnknownCommandException();
                }
                memory.save(taskList);
            } catch (PenguinException e) {
                ui.out("Fishes!! " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.out("No fishes? One or more fields in your command is empty or malformed.");
            } catch(DateTimeParseException e) {
                ui.out("Dates must be in penguin format yyyy-mm-dd!");
            } catch (Exception e) {
                ui.out("Flap flap flap flap!! An unexpected error occurred...");
            }
        }

    }
}
