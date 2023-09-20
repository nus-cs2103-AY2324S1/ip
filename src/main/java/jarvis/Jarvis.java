package jarvis;

import command.Parser;

import services.bizerrors.JarvisException;
import services.tasklist.Storage;
import services.tasklist.TaskList;

public class Jarvis {
    private TaskList taskList;
    private Parser parser;

    public Jarvis(String dataFilePath) {
        try {
            taskList = new TaskList(new Storage(dataFilePath));
            parser = new Parser(taskList);
        } catch (JarvisException e) {
            e.printStackTrace();
        }
    }

    public String respond(String userInput) {
        try {
            return parser.parseAndExecute(userInput);
        } catch (JarvisException e) {
            return e.toString();
        }
    }

    public String greet() {
        return "At your service, sir.";
    }
}
