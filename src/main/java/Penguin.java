public class Penguin {
    private static final String GREETING = "Honk! I'm Penguin! What can I do for you?";
    private static final String GOODBYE = "Honk! Hope to see you again soon!";

    private UI ui;
    /**
     * Constructor for Penguin chatbot.
     */
    public Penguin() {
        this.ui = new UI();
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
                } else {
                    ui.out(command);
                }
        }
    }
}
