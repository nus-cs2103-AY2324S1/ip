package duke.storage;

import duke.tasks.Tasks;
import duke.ui.Ui;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH = "./data/duke.txt";
    private Ui ui = new Ui();

    public Tasks load() {
        Tasks tasks = new Tasks();
        File myObj = new File(this.FILE_PATH);
        try {
            Scanner myReader = new Scanner(myObj);
            ui.showSuccessLoadingStorage(this.FILE_PATH);
            while (myReader.hasNextLine()) {
                String text = myReader.nextLine();
                Command c = Parser.parse(text, true);
                if (c == null) {
                    continue;
                }
                c.execute(tasks, ui, new Storage(), true);
            }

        } catch (FileNotFoundException ex) {
            ui.showErrorFileNotFound();
            try {
                myObj.createNewFile();
            } catch (IOException e) {
                ui.showErrorLoadingFile();
                System.exit(1);
            }
        } catch (DukeException ex) {
            ui.showErrorLoadingFile();
            ui.showError(ex.getMessage());
            System.exit(1);
        }
        return tasks;
    }

    public void save(Tasks tasks) {
        // Delete everything in
        try {
            PrintWriter writer = new PrintWriter(this.FILE_PATH);
            writer.print("");
            writer.close();
        } catch (IOException ex) {
            System.out.println("    Error saving file... exiting");
            System.exit(1);
        }

        // Rewrite everything
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).save(this.FILE_PATH);
        }
    }
}
