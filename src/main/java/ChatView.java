public class ChatView {
    static String lineBreak = "____________________________";
    public ChatView() {
        System.out.println(String.format("%s\nKnowledgeYuan, at your service!\nWhat can I do for you today?\n%s", lineBreak, lineBreak));
    }

    /**
     * Processes the input command.
     * @param command The input received.
     */
    public void commandInput(String command) {
        if (command.toLowerCase().equals("bye")) {
            System.out.println(String.format("%s\nAccess Terminated! Hope to see you again soon!\n%s", lineBreak, lineBreak));
            System.exit(0);
        } else {
            System.out.println(String.format("\t%s\n\t%s\n\t%s", lineBreak, command, lineBreak));
        }
    }
}
