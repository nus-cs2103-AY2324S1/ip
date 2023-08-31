import org.w3c.dom.bootstrap.DOMImplementationRegistry;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.IOException;


public class Corubi {
    private static Ui userUi;
    private static Storage store;
    private static TaskList tasks;
    private static Parser parser;

    private static final String DIRECTORY = "./src/main/java/OUTPUT.txt";

    private Corubi(String dir) {
        this.userUi = new Ui();
        this.tasks = new TaskList();
        this.parser = new Parser();
        this.store = new Storage(dir, tasks);
    }

    private static void run() throws IOException {
        userUi.takeCommands(store, tasks, parser);
    }
    public static void main(String[] args) throws IOException {
        new Corubi(DIRECTORY).run();
    }
}
