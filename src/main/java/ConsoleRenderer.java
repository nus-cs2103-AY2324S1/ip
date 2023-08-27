public class ConsoleRenderer {
    private static final int LINE_LENGTH = 100;
    public void printMessage(String message) {
        renderLine();
        System.out.println(message);
        renderLine();
    }

    public void renderLine() {
        System.out.println("_".repeat(LINE_LENGTH));
    }
}
