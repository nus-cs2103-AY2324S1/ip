package Jelly.main;

import java.util.Scanner;
import Jelly.commands.Command;
import Jelly.exceptions.JellyException;


public class Jelly {
    private static final String FILE_PATH = "./taskData/jelly.txt";
    private final Scanner scanner = new Scanner(System.in);

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    private Jelly(String filePath) throws JellyException {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        try {
            this.taskList = new TaskList(storage.startUp());
        } catch (JellyException e) {
            this.taskList = new TaskList();
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws JellyException {
        Jelly jelly = new Jelly(FILE_PATH);
        jelly.run();
    }

    private void run() {
        ui.startUpMessage();
        boolean running = true;
        while (running) {
            try {
                String command = ui.commandMe();
                Command c = Parser.parse(command);
                c.execute(taskList, ui, storage);
                running = c.running();
            } catch (JellyException e) {
            }
        }
    }
}