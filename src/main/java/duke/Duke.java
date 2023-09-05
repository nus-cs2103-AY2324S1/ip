package duke;

import java.io.IOException;

public class Duke {
    private static StringBuilder tempData = new StringBuilder();

    public Duke(String filePath) throws Exception{
        try {
            LoadFile loadFile = new LoadFile(filePath);
            loadFile.load();
            Ui ui = new Ui();
            ui.greet();
            TaskList taskList = new TaskList();
            taskList.Answer();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        String logo = "Zenith";

        String zenithData = "/Users/william/Desktop/ip/src/main/java/data/zenith.txt";
        Duke duke = new Duke(zenithData);


    }
}
