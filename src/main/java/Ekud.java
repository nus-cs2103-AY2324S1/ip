import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import cli.Program;
import state.State;

public class Ekud {
    public static void main(String[] args) {
        State state = null;

        File dataFile;
        try {
            File dataDir = Paths.get("data").toFile();
            dataDir.mkdir();
            dataFile = Paths.get("data", "ekud.txt").toFile();
            if (!dataFile.createNewFile()) {
                FileInputStream dataFileInput = new FileInputStream(dataFile);
                Program program = new Program(dataFileInput);
                state = program.run();
                dataFileInput.close();
            }
        } catch (IOException error) {
            System.out.println("☹ OOPS!!! Failed to create save file.");
            return;
        } catch (SecurityException error) {
            System.out.println("☹ OOPS!!! Failed to create save file.");
            return;
        }

        FileOutputStream dataFileOut;
        try {
            dataFileOut = new FileOutputStream(dataFile, true);
        } catch (IOException error) {
            System.out.println("☹ OOPS!!! Failed to open save file.");
            return;
        }

        Program program = new Program(System.in, System.out, dataFileOut);
        if (state != null) {
            program.run(state);
        } else {
            program.run();
        }
    }
}
