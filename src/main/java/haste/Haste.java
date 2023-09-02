package haste;

import haste.commands.Command;
import haste.commands.Parser;
import haste.data.Storage;
import haste.data.TaskList;
import haste.exceptions.HasteException;
import haste.ui.Ui;

import java.util.Scanner;

public class Haste {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Haste(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
    }

    public static void main(String[] args) {
        Haste haste = new Haste("./Data.txt");
        // greet and check for storage files
        haste.load();
        haste.run();
        haste.end();

    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (ui.running) {
            String cmd = sc.nextLine();
            //System.out.println(cmd);
            Command c = Parser.handleCommand(cmd, ui, tasks, storage);
            try {
                c.execute(tasks, ui);
            } catch (HasteException e) {
                e.printStackTrace();
            }
        }

    }
    public void load() {
        this.ui.greet();
        this.storage.read(this.ui, tasks);
    }

    public void end() {
        this.storage.delete();
        this.storage.save(tasks);
    }

}
