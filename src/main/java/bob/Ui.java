package bob;

/**
 * Handles user interaction
 */
public class Ui {

    /**
     * Formats string into nice display
     * @param strArray contains strings to be displayed on different lines
     */
    public String stringFormat(String[] strArray) {
        String content = "";
        for (String s : strArray) {
            content += s + "\n";
        }
        return "Bob:\n" + content;
    }

    public String errorFormat(String[] strArray) {
        String content = "";
        for (String s : strArray) {
            content += s + "\n";
        }
        return "E Bob:\n" + content;
    }

    public String greet() {
        return stringFormat(new String[]{
            "Good day to you! How may I be of assistance?"
        });
    }

    public String exit() {
        return stringFormat(new String[]{"Helping you is my pleasure."});
    }

    public void echo(String input) {
        stringFormat(new String[]{input});
    }

    public String showLoadingError() {
        return errorFormat(new String[]{
            "Met with difficulty when retrieving old tasks.", "New list created!"
        });
    }
}
