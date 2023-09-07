package ui;

public class UiMessage {
    private String[] message;

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
        String formatMsg = "";
        for (String line : message) {
            formatMsg += line + "\n";
        }
        return formatMsg.strip();
    }
}
