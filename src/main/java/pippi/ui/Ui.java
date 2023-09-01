package pippi.ui;

public class Ui {

    /**
     * Wraps responses in upper and lower lines
     */
    public static void wrapText(String content) {
        String line = "_____________________________________\n";
        System.out.println(line + content + "\n" + line);
    }
}
