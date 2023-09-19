package duke.stubs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.components.Storage;
import duke.tasks.Task;

/**
 * Stub for Storage class, created for testing purposes.
 */
public class StorageStub extends Storage {

    /**
     * Class constructor for StorageStub.
     *
     * @param path path of file where data is stored.
     */
    public StorageStub(String path) {
        super(path);
    }

    @Override
    public void updateFile(ArrayList<Task> list) {
        try {
            //check if file exists, else create
            File dataFile = new File("./src/test/testdata.txt");
            if (!dataFile.exists()) {
                dataFile = createDataFile();
            }

            //create a FileWriter object to write to file. Note that this overwrites the existing data!
            FileWriter file = new FileWriter("./src/test/testdata.txt");
            BufferedWriter writer = new BufferedWriter(file);

            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                String taskStr = task.convertTask();
                writer.write(taskStr);
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
