public class ChatView {
    static String lineBreak = "____________________________";
    private ChatRecord chatRecord;
    public ChatView() {
        chatRecord = new ChatRecord();
        System.out.println(String.format("%s\nKnowledgeYuan, at your service!\nWhat can I do for you today?\n%s", lineBreak, lineBreak));
    }

    /**
     * Processes the input command.
     * @param command The input received.
     */
    public void commandInput(String command) {
        switch(command.toLowerCase()) {
            case "bye":
                System.out.println(String.format("%s\nAccess Terminated! Hope to see you again soon!\n%s", lineBreak, lineBreak));
                System.exit(0);
                break;
            case "list":
                System.out.println(String.format("\t%s\n\tThese are the items in your list!\n%s\t%s", lineBreak, chatRecord.ListMessage(), lineBreak));
                break;
            default:
                chatRecord.AddMessage(command);
                System.out.println(String.format("\t%s\n\tRecorded to database: %s\n\t%s", lineBreak, command, lineBreak));
                break;
        }
    }
}
