import duke.TaskMaster;
import duke.Ui;

public class Duke {
    private Ui ui;

    public Duke() {
        ui = new Ui();
    }

    public void run(String filePath) {
        this.ui.sayhi();

        TaskMaster.masterTasks(filePath);

        this.ui.saybye();
    }

    public static void main(String[] args) {
        new Duke().run("Data");
    }
}
