package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.parser.CommandParser;
import duke.records.ChatRecord;

/**
 * @author Toh Li Yuan (A0255811H)
 */
public class Duke {

    private boolean isEnd = false;

    private CommandParser cp;
    private ChatRecord chatRecord;

//    public static void main(String[] args) {
//        ChatView chatView = new ChatView();
//        Scanner sc = new Scanner(System.in);
//        CommandParser cp = new CommandParser();
//        ChatRecord chatRecord = new ChatRecord();
//        chatView.displayBasic(chatRecord.loadData());
//        chatView.startMessage();
//        Command cmd;
//        do {
//            String command = sc.nextLine();
//            cmd = cp.parseCommand(command);
//            cmd.init(chatRecord);
//            String out = cmd.execute();
//            chatView.displayOutput(out);
//        } while (!ByeCommand.isBye(cmd));
//    }

    /**
     * Initialises the Duke program.
     *
     * @return the greeting string.
     */
    public String init() {
        cp = new CommandParser();
        chatRecord = new ChatRecord();
        chatRecord.loadData();
        return "KnowledgeYuan, at your service!\nWhat can I do for you today?";
    }

    /**
     * Returns a response from Duke from a given input.
     *
     * @param input the user input.
     * @return the response from duke.
     */
    public String getResponse(String input) {
        Command cmd = cp.parseCommand((input));
        if (ByeCommand.isBye(cmd)) {
            isEnd = true;
        }
        cmd.init(chatRecord);
        String out = cmd.execute();
        assert out != null;
        return out;
    }

    public boolean isTerminate() {
        return isEnd;
    }

}
