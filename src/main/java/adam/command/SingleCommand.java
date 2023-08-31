package adam.command;

import adam.Storage;
import adam.Ui;
import adam.TaskList;
import adam.exception.OneWordException;
public class SingleCommand implements Command {
    private String[] tokens;
    private String input;
    public SingleCommand(String[] tokens, String input) {
        this.tokens = tokens;
        this.input = input;
    }
    public void execute(TaskList tasks, Storage storage, Ui ui){
        if (tokens.length > 1) {
            throw new OneWordException();
        }
        switch (input) {
        case "bye":
            tasks.bye();
            break;
        case "list":
            tasks.list();
            break;
        default:
            System.out.println("Wrong input");
        }
    }
}
