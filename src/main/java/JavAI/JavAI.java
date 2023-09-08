package javai;

import javafx.application.Platform;

/**
 * JavAI is a simple chatbot that allows users to add, mark, unmark, delete,
 * and list tasks.
 */
public class JavAi {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;


    /**
     * Constructs a JavAI chatbot instance.
     *
     * @param filePath The file path where tasks are stored.
     */
    public JavAi(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public JavAi() {
        this("./src/main/txtFolder/JavAI.txt");
    }

    String getResponse(String input) {

        if (input.equals("bye")) {
            storage.taskListWriter(tasks.getTasksAsArrayList());
            Platform.exit();
            return ui.exit();
        } else {
            try {
                return parser.parse(input, tasks, ui);
            } catch (JavAiException e) {
                return ui.showLoadingError(e);
            }
        }


    }


}
