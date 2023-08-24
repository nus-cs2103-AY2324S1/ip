public class TextFormat {
    private static String hLine = "    ____________________________________________________________\n";
    private static String indent = "     ";

    public static String indentMessage(String message) {
        String[] lines = message.split("\n");
        StringBuilder indentedMessage = new StringBuilder();
        for (String line : lines) {
            indentedMessage.append(indent).append(line).append("\n");
        }
        return indentedMessage.toString();
    }
    public static String botReply(String message) {
        StringBuilder reply = new StringBuilder();
        return reply.append(hLine).append(indentMessage(message))
                .append(hLine).toString();
    }

    public static String indentLineBy(String message, int indents) {
        StringBuilder indentedLine = new StringBuilder();
        for (int i = 0; i < indents; i++) {
            indentedLine.append(" ");
        }
        return indentedLine.append(message).toString();
    }
}
