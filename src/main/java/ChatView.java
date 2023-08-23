import java.util.regex.Pattern;
import java.util.regex.Matcher;
import task.*;

public class ChatView {
    static String LINE_BREAK = "____________________________";
    static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]");
    private ChatRecord chatRecord;
    public ChatView() {
        chatRecord = new ChatRecord();
        System.out.println(String.format("%s\nKnowledgeYuan, at your service!\nWhat can I do for you today?\n%s", LINE_BREAK, LINE_BREAK));
    }

    /**
     * Processes the input command.
     * @param command The input received.
     */
    public void commandInput(String command) {

        if (command.toLowerCase().contains("unmark")) {
            int n = getInt(command);
            chatRecord.setUnmark(n);
            System.out.println(beautifyString(String.format("I have marked this task as undone!\n%s", chatRecord.getTask(n))));
            return;
        }

        if (command.toLowerCase().contains("mark")) {
            int n = getInt(command);
            chatRecord.setMark(n);
            System.out.println(beautifyString(String.format("I have marked this task as done!\n%s", chatRecord.getTask(n))));
            return;
        }

        String[] commandSplit = command.split(" ", 2);
        String commandType = commandSplit[0].toLowerCase();
        Task out;

        switch(commandType) {
            case "bye":
                System.out.println(String.format("%s\nAccess Terminated! Hope to see you again soon!\n%s", LINE_BREAK, LINE_BREAK));
                System.exit(0);
                break;
            case "list":
                System.out.println(beautifyString(String.format("These are the items in your list!\n%s", chatRecord.listMessage())));
                break;
            case "todo":
                out = chatRecord.addTask(commandSplit[1], TaskTypes.TODO);
                System.out.println(beautifyString(String.format("Recorded to database: %s\nYou now have %d tasks in the list", out.toString(), chatRecord.getCount())));
                break;
            case "deadline":
                String[] ddlSplit = commandSplit[1].split(" /by ");
                out = chatRecord.addTask(ddlSplit[0], TaskTypes.DEADLINE, ddlSplit[1]);
                System.out.println(beautifyString(String.format("Recorded to database: %s\nYou now have %d tasks in the list", out.toString(), chatRecord.getCount())));
                break;
            case "event":
                String[] evSplit = commandSplit[1].split(" /from ");
                String[] args = evSplit[1].split(" /to ");
                out = chatRecord.addTask(evSplit[0], TaskTypes.EVENT, args);
                System.out.println(beautifyString(String.format("Recorded to database: %s\nYou now have %d tasks in the list", out.toString(), chatRecord.getCount())));
                break;
            default:
                System.out.println(beautifyString(command));
                break;
        }
    }

    private int getInt(String str) {
        Matcher matcher = NUMBER_PATTERN.matcher(str);
        StringBuilder m = new StringBuilder();
        while(matcher.find()) {
            m.append(matcher.group());
        }
        return Integer.parseInt(m.toString());
    }

    private String beautifyString(String str) {
        return String.format("\t%s\n%s\n%s", LINE_BREAK, str, LINE_BREAK).replace("\n", "\n\t");
    }
}
