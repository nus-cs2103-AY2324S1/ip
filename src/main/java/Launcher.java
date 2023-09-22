import duke.Main;
import duke.TaskList;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;

public class Launcher {

    /**
     * A launcher class to workaround classpath issues.
     */
        public static void main(String[] args) {
            try {
                String filename = System.getProperty("user.dir") + "/duke.txt";
                System.out.println(filename);
                File file = new File(filename);
                file.createNewFile();
                assert file.exists();
                if (!file.canRead() || !file.canWrite()) {
                    System.out.println("There is an error initialising Nya");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }


            Application.launch(Main.class, args);
        }
}
