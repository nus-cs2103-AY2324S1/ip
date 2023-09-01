package duke.ui;

public class ChatView {
    private static String LINE_BREAK = "____________________________";
    public ChatView() {

    }

    public void startMessage() {
        System.out.println(String.format("%s\nKnowledgeYuan, at your service!\nWhat can I do for you today?\n%s", LINE_BREAK, LINE_BREAK));
    }

    public void displayOutput(String str) {
        System.out.println(beautifyString(str));
    }

    public void displayBasic(String str) {
        System.out.println(str);
    }

    private String beautifyString(String str) {
        return String.format("\t%s\n%s\n%s", LINE_BREAK, str, LINE_BREAK).replace("\n", "\n\t");
    }

}
