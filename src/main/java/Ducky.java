import java.util.Scanner;

public class Ducky {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage store = new Storage();
        CommandParser parser = new CommandParser();
        UserInterface ui = new UserInterface(sc, store, parser);
        ui.start();
    }
}
