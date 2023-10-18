package ui;

public class UiMessage {
    private final String[] message;

    public UiMessage(String[] message) {
        this.message = message;
    }

    public UiMessage() {
        this.message = new String[]{};
    }

    public String[] getRawStringArr() {
        return message;
    }

    @Override
    public String toString() {
        StringBuilder formatMsg = new StringBuilder();
        for (String line : message) {
            formatMsg.append(line).append("\n");
        }
        return formatMsg.toString().strip();
    }
}
