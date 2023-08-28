package glub;

import glub.task.TaskList;

public class Glub {
    private TaskList taskList;
    private Parser parser;

    public Glub(Storage storage) {
        try {
            this.taskList = new TaskList(storage);
        } catch (GlubException ex) {
            Ui.printError(ex.getMessage());
        }
        this.parser = new Parser(taskList, storage);
    }

    public void run() {
        parser.listen();
    }

    public static void main(String[] args) {
        Glub glub = new Glub(new Storage("tasks.txt"));
        Ui.greet();
        glub.run();
    }

}
