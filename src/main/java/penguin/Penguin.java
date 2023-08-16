package penguin;

public class Penguin {
    private static final String GREETING = "Honk! I'm Penguin! What can I do for you?";
    private static final String GOODBYE = "Honk! Hope to see you again soon!";
    private static final String MARK = "Honk honk! You did task ";
    private static final String UNMARK = "Fishes! You didn't do task ";


    private UI ui;
    private TaskList taskList;
    /**
     * Constructor for Penguin chatbot.
     */
    public Penguin() {
        this.ui = new UI();
        this.taskList = new TaskList();
    }

    /**
     * Runs the Penguin chatbot.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        new Penguin().run();
    }

    public void run() {
        ui.out(GREETING);
        boolean running = true;
        while (running) {

                String command = ui.in();
                if (command.equals("bye")) {
                    ui.out(GOODBYE);
                    running = false;
                } else if (command.equals("list")) {
                    ui.out(taskList.printList());
                } else if (command.startsWith("mark")) {
                    String[] spl = command.split(" ", 2);
                    int taskNo = Integer.parseInt(spl[1]);
                    taskList.list.get(taskNo-1).done = true;
                    ui.out(MARK + taskNo);
                } else if (command.startsWith("unmark")) {
                    String[] spl = command.split(" ", 2);
                    int taskNo = Integer.parseInt(spl[1]);
                    taskList.list.get(taskNo-1).done = false;
                    ui.out(UNMARK + taskNo);
                } else { // add to list
                    Task newTask = new Task(command);
                    taskList.addTask(newTask);
                    ui.out("remembered: " + command);
                }
        }
    }
}
