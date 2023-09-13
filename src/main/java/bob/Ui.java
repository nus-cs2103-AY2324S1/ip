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
        return "Bob: " + content;
    }

    public String greet() {
        return stringFormat(new String[]{
            "Hi there! I'm Bob", "How can I help?"
        });
    }

    public String exit() {
        return stringFormat(new String[]{"See you soon!"});
    }

    public void echo(String input) {
        stringFormat(new String[]{input});
    }

    public String showLoadingError() {
        return stringFormat(new String[]{
            "Unable to load tasks. New list created!"
        });
    }
}
