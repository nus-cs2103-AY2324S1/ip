import java.util.Scanner;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.parser.CommandParser;
import duke.records.ChatRecord;
import duke.ui.ChatView;

public class Duke {
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
}
