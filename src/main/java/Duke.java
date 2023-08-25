import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        // Initialising objects
        UI ui = new UI();
        Storage s = new Storage("storage.txt");

        // Opening Dialogue
        ui.line();
        System.out.println("Hello, I'm Prawn");
        System.out.println("What would you like me to do sire?");
        ui.line();

        // Main Loop
        try {
            ArrayList tasks = s.load();
            Parser p = new Parser(tasks);
            p.run();
        } catch (FileNotFoundException e) {
            try {
                File f = new File("storage.txt");
                if (f.createNewFile()) {
                    Parser p = new Parser(new ArrayList());
                    p.run();
                }
            } catch (IOException Ioe) {
                System.out.println(Ioe);
            }
        }

        // Ending Dialogue
        System.out.println("Bye. Hope to see you again soon!");
        ui.line();
    }
}
