package duke.storage;

import duke.command.Command;
import duke.exception.DukeException;
import duke.object.TaskList;
import duke.parser.Parser;
import duke.ui.SilentUi;
import duke.ui.Ui;
import duke.ui.VerboseUi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Storage {

    private static final List<String> storagePath = List.of("data");
    private static final String storageFileName = "data.txt";

    private List<String> dirPath;
    private File dataFile;

    public Storage(String projectName, File mainFile) {
        this.dirPath = new ArrayList<>(Arrays.asList(mainFile.getPath().split(Pattern.quote(File.separator))));
        while (!this.dirPath.get(this.dirPath.size() - 1).equals(projectName)) {
            this.dirPath.remove(this.dirPath.size() - 1);
        }
        this.dataFile = makeFile(this.dirPath, storagePath, storageFileName);
    }

    private File makeFile(List<String> projectDir, List<String> filePath, String fileName) {
        String path = String.join(File.separator, projectDir) + File.separator + String.join(File.separator, filePath);
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File opFile = new File(path + File.separator + fileName);
        try {
            opFile.createNewFile();
        } catch (IOException e) {}
        return opFile;
    }

    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        Ui ui;
        try {
            ui = new SilentUi(new Scanner(dataFile));
        } catch (FileNotFoundException e) {
            ui = new SilentUi(new Scanner(System.in));
        }
        try {
            while (ui.hasNext()) {
                String saved = ui.readCommand();
                if (saved.equals("")) {
                    continue;
                }
                Command cmd = Parser.parse(saved);
                cmd.execute(tasks, ui, this);
            }
        } catch (DukeException e) {
            (new VerboseUi("")).print("☹ OOPS!!! Data file is corrupted. Starting from a clear state...");
            tasks.clear();
        }
        ui.close();
        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(dataFile, false);
            for (int i = 1; i <= tasks.size(); i++) {
                writer.append(tasks.get(i).toCommand(i));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {}
    }

}
