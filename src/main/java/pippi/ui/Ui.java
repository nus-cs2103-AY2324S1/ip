package pippi.ui;

public class Ui {

    /**
     * Wraps responses in upper and lower lines
     */
    public static String wrapText(String content) {
        String line = "_____________________________________\n";
        String output = line + content + "\n" + line;
        System.out.println(output);
        return output;
    }
}
