package bob;

/**
 * Handles user interaction
 */
public class Ui {

    /**
     * Formats string into nice display
     * @param strArray contains strings to be displayed on different lines
     */
    public void stringFormat(String[] strArray) {
        String content = "";
        for (String s : strArray) {
            content += "\t " + s + "\n";
        }
        String display = "\t_______________________________________________\n"
                + content
                + "\t_______________________________________________";
        System.out.println(display);
    }

    public void greet() {
        stringFormat(new String[]{
            "Hi there! I'm Bob", "How can I help?"
        });
    }

    public void exit() {
        stringFormat(new String[]{"See you soon!"});
    }

    public void echo(String input) {
        stringFormat(new String[]{input});
    }

    public void showLoadingError() {
        stringFormat(new String[]{
            "Unable to load tasks. New list created!"
        });
    }
}
