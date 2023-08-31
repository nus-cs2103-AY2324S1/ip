public class Ui {
    /** Line separators for the console between paragraphs. **/
    private static final String FORMAT_LINE =
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public void showLine() {
        System.out.println(FORMAT_LINE);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
