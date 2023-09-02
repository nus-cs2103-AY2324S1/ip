import exceptions.InvalidInputException;
import task.TaskList;

import java.io.File;
import java.util.Scanner;

public class Nexus {
    private TaskList list;
    private Storage storage;
    private Ui ui;
    private String path;

    public Nexus() {
        this.path = "src" + File.separator + "main" + File.separator + "data" + File.separator + "nexus.txt";
        this.storage = new Storage(path);
        this.ui = new Ui();
    }

    public void run() {
        System.out.println("Hello! I'm NEXUS");
        System.out.println("What can I do for you?");
        this.list = new TaskList(storage.loadTasks());
        // Show current tasks
        ui.list(this.list);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            try {
                String input = scanner.nextLine();
                exit = Parser.parseInput(storage, this.list, input);
                scanner.reset();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

    public static void main(String[] args) {
        Nexus nexus = new Nexus();
        nexus.run();
    }
}

