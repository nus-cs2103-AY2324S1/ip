import commands.Parser;
import data.Storage;
import ui.Ui;

import java.util.Scanner;

public class Haste {
    private Storage storage;
    private Ui ui;

    public Haste(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
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
            Parser.handleCommand(cmd, ui);
        }

    }
    public void load() {
        this.ui.greet();
        this.storage.read(this.ui);
    }

    public void end() {
        this.storage.delete();
        this.storage.save();
    }

}
