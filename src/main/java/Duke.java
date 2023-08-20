public class Duke {

    Ui ui = new Ui();

    public void run () {
        ui.showWelcome();
        boolean isExit = false;
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
