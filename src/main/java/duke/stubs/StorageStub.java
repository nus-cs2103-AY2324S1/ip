package duke.stubs;

import duke.Storage;
import duke.Ui;
import duke.tasks.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StorageStub extends Storage {
    public StorageStub(String path, Ui ui) {
        super(path, ui);
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
