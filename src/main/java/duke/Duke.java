package duke;

import java.util.Scanner;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.parser.CommandParser;
import duke.records.ChatRecord;
import duke.ui.ChatView;

/**
 * @author Toh Li Yuan (A0255811H)
 */
public class Duke {

    private CommandParser cp;
    private ChatRecord chatRecord;

    public static boolean IS_END = false;

    public static void main(String[] args) {
        ChatView chatView = new ChatView();
        Scanner sc = new Scanner(System.in);
        CommandParser cp = new CommandParser();
        ChatRecord chatRecord = new ChatRecord();
        chatView.displayBasic(chatRecord.loadData());
        chatView.startMessage();
        Command cmd;
        do {
            String command = sc.nextLine();
            cmd = cp.parseCommand(command);
            cmd.init(chatRecord);
            String out = cmd.execute();
            chatView.displayOutput(out);
        } while (!ByeCommand.isBye(cmd));
    }

    public String init() {
        cp = new CommandParser();
        chatRecord = new ChatRecord();
        chatRecord.loadData();
        return "KnowledgeYuan, at your service!\nWhat can I do for you today?";
    }

    public String getResponse(String input) {
        Command cmd = cp.parseCommand((input));
        if (ByeCommand.isBye(cmd)) {
            IS_END = true;
        }
        cmd.init(chatRecord);
        String out = cmd.execute();
        return out;
    }

}
