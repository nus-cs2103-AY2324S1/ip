package pippi.ui;

public class Ui {

    /**
     * Wraps responses in upper and lower lines
     */
    public static String wrapText(String content) {
        String line = "";
        String output = line + content + "\n" + line;
        System.out.println(output);
        return output;
    }

    public static String helloMessage() {
        return "Hello trainer, I'm Pippi!\nWhat can I do for you?";
    }
}
