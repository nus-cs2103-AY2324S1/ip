import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
            System.out.println(String.format("\t%s\n\tI have marked this task as undone!\n\t%s", LINE_BREAK, chatRecord.getTask(n)));
            return;
        }

        if (command.toLowerCase().contains("mark")) {
            int n = getInt(command);
            chatRecord.setMark(n);
            System.out.println(String.format("\t%s\n\tI have marked this task as done!\n\t%s", LINE_BREAK, chatRecord.getTask(n)));
            return;
        }

        switch(command.toLowerCase()) {
            case "bye":
                System.out.println(String.format("%s\nAccess Terminated! Hope to see you again soon!\n%s", LINE_BREAK, LINE_BREAK));
                System.exit(0);
                break;
            case "list":
                System.out.println(String.format("\t%s\n\tThese are the items in your list!\n%s\t%s", LINE_BREAK, chatRecord.listMessage(), LINE_BREAK));
                break;
            default:
                chatRecord.addMessage(command);
                System.out.println(String.format("\t%s\n\tRecorded to database: %s\n\t%s", LINE_BREAK, command, LINE_BREAK));
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
}
