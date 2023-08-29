package duke;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Duke bot = new Duke("chatBot", "ip", new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
        bot.run();
    }

}