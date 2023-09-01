package duke.storage;

import duke.tasks.Task;
import duke.tasks.Tasks;
import duke.ui.Ui;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a Storage function for the specified filepath.
 */
public class Storage {
    private final String filepath;
    private final Ui ui = new Ui();

    /**
     * Public constructor for the Storage class.
     *
     * @param filepath the filepath to the file to be tracked
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the data from the given filepath into a Tasks object.
     *
     * @return the Tasks object containing data from the data file
     */
    public Tasks load() {
        Tasks tasks = new Tasks();
        File myObj = new File(this.filepath);
        try {
            Scanner myReader = new Scanner(myObj);
            ui.showSuccessLoadingStorage(this.filepath);
            while (myReader.hasNextLine()) {
                String text = myReader.nextLine();
                Command c = Parser.parse(text, true);
                if (c == null) {
                    continue;
                }
                c.execute(tasks, ui, new Storage(this.filepath), true);
            }

        } catch (FileNotFoundException ex) {
            ui.showErrorFileNotFound();
            try {
                String[] filepathSplit = this.filepath.split("/");
                String fileLocation = String.join("/", Arrays.copyOfRange(filepathSplit, 0, filepathSplit.length - 1));
                System.out.println(fileLocation);
                File dir = new File(fileLocation);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                myObj.createNewFile();
            } catch (IOException e) {
                ui.showErrorLoadingFile();
                ui.showError(e.getMessage());
                System.exit(1);
            }
        } catch (DukeException ex) {
            ui.showErrorLoadingFile();
            ui.showError(ex.getMessage());
            System.exit(1);
        }
        return tasks;
    }

    /**
     * Save tasks into the specified filepath.
     *
     * @param tasks the Tasks to be saved
     */
    public void save(Tasks tasks) {
        // Delete everything in
        try {
            PrintWriter writer = new PrintWriter(this.filepath);
            writer.print("");
            writer.close();
        } catch (IOException ex) {
            System.out.println("    Error saving file... exiting");
            System.exit(1);
        }

        // Rewrite everything
        for (int i = 0; i < tasks.size(); i++) {
            this.saveTask(tasks.get(i));
        }
    }

    /**
     * Save a task into the specified filepath.
     *
     * @param task the Task to be saved
     */
    private void saveTask(Task task) {
        try {
            FileWriter myWriter = new FileWriter(this.filepath, true);
            myWriter.write(String.format("%s%s\n", task.getOriginalMessage(), task.getMarked() ? "1" : "0"));
            myWriter.close();
        } catch (IOException ex) {
            System.out.println("    Error saving to file");
            System.exit(1);
        }
    }
}
