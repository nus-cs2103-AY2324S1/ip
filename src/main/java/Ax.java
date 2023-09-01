import java.util.*;

public class Ax {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Ui.greet();
        Storage.readSave();
        while (true) {
            boolean done = Parser.getInput(scanner);
            if (done) {
                break;
            }
        }
        Ui.bye();
    }
}
