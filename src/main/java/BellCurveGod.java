import java.io.IOException;

public class BellCurveGod {
    private static final String DATA_FILE_PATH = "./data/tasks.txt";
    public static void main(String[] args) {
        try {
            Action.loadTasks(DATA_FILE_PATH);
        } catch (IOException e) {
            System.out.println(e);
        }

        Action.greet();
        Action.respond();
    }
}
