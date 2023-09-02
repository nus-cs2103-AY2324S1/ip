public class Alex {

    public void run() {
        Ui.greet();

        while (true) {
            String command = Ui.readUserInput();
            Command c = Parser.parse(command);
            c.execute();
        }
    }
    public static void main(String[] args) {
        new Alex().run();
    }
}
