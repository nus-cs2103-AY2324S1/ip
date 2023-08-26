package penguin;

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
    private Storage memory;
    private Parser parser;
    /**
     * Constructor for Penguin chatbot.
     */
    public Penguin() {
        this.ui = new UI();
        this.taskList = new TaskList();
        this.memory = new Storage("data/memory.txt");
        this.parser = new Parser();
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
     * Starts the main logic of Penguin chatbot; parses commands and handles errors.
     */
    public void run() {
        ui.out(GREETING);
        boolean running = true;
        this.taskList = memory.load();

        while (running) {
            try {
                String command = ui.in();
                String[] spl = null;
                int taskNo;

                switch (parser.parse(command)) {
                    case "bye":
                        ui.out(GOODBYE);
                        running = false;
                        break;
                    case "list":
                        ui.out(taskList.printList());
                        break;
                    case "mark":
                        spl = command.split(" ", 2);
                        taskNo = Integer.parseInt(spl[1]);
                        taskList.list.get(taskNo - 1).done = true;
                        ui.out(MARK + taskList.list.get(taskNo - 1).getDisplay());
                        break;
                    case "unmark":
                        spl = command.split(" ", 2);
                        taskNo = Integer.parseInt(spl[1]);
                        taskList.list.get(taskNo - 1).done = false;
                        ui.out(UNMARK + taskList.list.get(taskNo - 1).getDisplay());
                        break;
                    case "todo":
                        spl = command.split("todo ");
                        ToDo newToDo = new ToDo(spl[1]);
                        taskList.addTask(newToDo);
                        ui.out(TODO + newToDo.getDisplay());
                        break;
                    case "deadline":
                        spl = command.split("deadline | /by ");
                        Deadline newDeadline = new Deadline(spl[1], spl[2]);
                        taskList.addTask(newDeadline);
                        ui.out(DEADLINE + newDeadline.getDisplay());
                        break;
                    case "event":
                        spl = command.split("event | /from | /to ");
                        Event newEvent = new Event(spl[1], spl[2], spl[3]);
                        taskList.addTask(newEvent);
                        ui.out(EVENT + newEvent.getDisplay());
                        break;
                    case "delete":
                        spl = command.split(" ");
                        taskNo = Integer.parseInt(spl[1]);
                        ui.out(DELETE + taskList.list.remove(taskNo - 1).getDisplay());
                        break;
                    case "find":
                        spl = command.split(" ");
                        String match = spl[1];
                        ui.out(taskList.findInList(match));
                        break;
                    case "unknown":
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
