package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    public static enum Color {
        RESET("\u001B[0m"),
        GREEN("\033[0;32m"),
        YELLOW("\033[0;33m"),
        BLUE("\033[0;34m"),
        PURPLE("\u001B[35m"),
        RED("\033[0;31m");

        public final String value;

        private Color(String value) {
            this.value = value;
        }
    }

    private static BufferedReader reader = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static String cTxt(String text, Color color) {
        return color.value + text + Color.RESET.value;
    }

    public void displayIntro() {
        displayMsg(new String[] {
            "Hi. I'm " + Ui.cTxt("Bryan", Ui.Color.PURPLE),
            "What can I do for you?"
        });
    }

    public void displayGoodbye() {
        displayMsg("Bye~ Come back soon :)");
    }

    public void displayInputStart() {
        System.out.print("> ");
    }

    public String readInput() throws IOException {
        return reader.readLine();
    }

    public void displayMsg(String text) {
        String msg = String.format("\n    %s", text);
        System.out.println(msg + "\n");
    }

    public void displayMsg(String[] text) {
        String msg = "\n";
        for (String stub : text) {
            msg += String.format("    %s\n", stub);
        }
        System.out.println(msg);
    }

    public void displayError(String text) {
        String msg = String.format(
            "\n    %s\n    %s\n",
            cTxt("Erm... error :(", Color.RED),
            text
        );
        System.out.println(msg);
    }

    public void displayError(String[] text) {
        String msg = String.format(
            "\n    %s\n",
            cTxt("Erm... error :(", Color.RED)
        );
        for (String stub : text) {
            msg += String.format("    %s\n", stub);
        }
        System.out.println(msg);
    }
}
