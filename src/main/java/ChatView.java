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
        String[] commandSplit = command.split(" ", 2);
        String commandType = commandSplit[0].toLowerCase();
        Task out;

        switch(commandType) {
            case "unmark":
                int n = getInt(command);
                chatRecord.setUnmark(n);
                System.out.println(beautifyString(String.format("I have marked this task as undone!\n%s", chatRecord.getTask(n))));
                break;
            case "mark":
                n = getInt(command);
                chatRecord.setMark(n);
                System.out.println(beautifyString(String.format("I have marked this task as done!\n%s", chatRecord.getTask(n))));
                break;
            case "delete":
                n = getInt(command);
                Task del = chatRecord.deleteTask(n);
                System.out.println(beautifyString(String.format("The following task has been deleted: %s\nYou now have %d tasks in the list", del, chatRecord.getCount())));
                break;
            case "bye":
                System.out.println(String.format("%s\nAccess Terminated! Hope to see you again soon!\n%s", LINE_BREAK, LINE_BREAK));
                System.exit(0);
                break;
            case "list":
                System.out.println(beautifyString(String.format("These are the items in your list!\n%s", chatRecord.listMessage())));
                break;
            case "todo":
                try {
                    out = chatRecord.addTask(commandSplit[1], TaskTypes.TODO);
                    System.out.println(beautifyString(String.format("Recorded to database: %s\nYou now have %d tasks in the list", out.toString(), chatRecord.getCount())));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(beautifyString("ERROR!! The description of Todo cannot be empty!"));
                } catch (Exception e) {
                    System.out.println(beautifyString(String.format("CRITICAL ERROR!!! An unknown error has occurred. Please report the bug to the developers: %s", e.getMessage())));
                }
                break;
            case "deadline":
                try {
                    String[] ddlSplit = commandSplit[1].split("/by");
                    if (ddlSplit[0].equals("")){
                        System.out.println(beautifyString("ERROR!! The name of Deadline cannot be empty!"));
                        break;
                    }
                    try {
                        out = chatRecord.addTask(ddlSplit[0].trim(), TaskTypes.DEADLINE, ddlSplit[1].trim());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(beautifyString("ERROR!! The date of Deadline cannot be empty!"));
                        break;
                    }
                    System.out.println(beautifyString(String.format("Recorded to database: %s\nYou now have %d tasks in the list", out.toString(), chatRecord.getCount())));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(beautifyString("ERROR!! The description of Deadline cannot be empty!"));
                } catch (Exception e) {
                    System.out.println(beautifyString(String.format("CRITICAL ERROR!!! An unknown error has occurred. Please report the bug to the developers: %s", e.getMessage())));
                }
                break;
            case "event":
                try {
                    String[] evSplit = commandSplit[1].split("/from");
                    if (evSplit[0].equals("")) {
                        System.out.println(beautifyString("ERROR!! The name of Event cannot be empty!"));
                        break;
                    }
                    String[] args;
                    try {
                        args = evSplit[1].split("/to");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("ERROR!! The time period of event cannot be empty!");
                        break;
                    }
                    out = chatRecord.addTask(evSplit[0].trim(), TaskTypes.EVENT, args);
                    System.out.println(beautifyString(String.format("Recorded to database: %s\nYou now have %d tasks in the list", out.toString(), chatRecord.getCount())));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(beautifyString("ERROR!! The description of Event cannot be empty!"));
                } catch (Exception e) {
                    System.out.println(beautifyString(String.format("CRITICAL ERROR!!! An unknown error has occurred. Please report the bug to the developers: %s", e.getMessage())));
                }
                break;
            default:
                System.out.println(beautifyString("CRITICAL ERROR!!! Command not found! Updates are still ongoing..."));
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
