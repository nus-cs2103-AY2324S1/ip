import Exceptions.InvalidCommandException;
import Helpers.Parser;
import Tasks.Task;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        final boolean isDebug = true;
        final String divider = "\n____________________________________________________________";
        final String logo = isDebug ? "" :
                "      ＼ﾌﾌ 　　      ム｀ヽ\n" +
                        "    / ノ)　  A　 A 　  ）  ヽ\n" +
                        "   / ｜　　( ´・ω・`)ノ⌒ ゝ_ノ\n" +
                        "  /　ﾉ⌒ 7  ⌒ヽーく　 ＼　／\n" +
                        "  丶＿ ノ ｡　　  ノ､　　|/\n" +
                        "　　  `ヽ   `ー-'_人`ーﾉ\n" +
                        "　　　   丶   ￣ _人'彡ﾉ\n";

        final String endLogo = isDebug ? "" :
                "               ＿   ★★EVERYDAY★★\n" +
                        "           ／     j     ★★ IS A  ★★\n" +
                        "        ／     /ｰ'          ★★ MACHO  ★★\n" +
                        "     〈       ヽ               ★★ DAY!!!  ★★\n" +
                        "           ､       ヽ ﾍ⌒ ヽﾌ\n" +
                        "             〉       ´ ･ω )        ,-､、\n" +
                        "           / ノ         ￣⌒ヽ　「　   〉\n" +
                        "          ﾉ       ' L          `ヽ.／   /\n" +
                        "     ／    , '           .ノ＼    ´    /\n" +
                        "    (                ∠_       ヽ､＿,.\n" +
                        "     ＼   (            ヽ ";

        //Create buffered reader for user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println(logo + divider + "\nHello! I'm MACHO-CATTO! Your personal chat-bot to make your \nday macho!"
                    + "\nWhat can I do for you today?" + divider);

            boolean isRunning = true;

            //exits the echo commands when input contains 'bye'
            while (isRunning) {
                String input = br.readLine();
                String cmd = input.split(" ")[0].toUpperCase();
                Parser commands = new Parser(cmd, input, "tasks.txt");
                commands.execute();
                if (cmd.equalsIgnoreCase("bye")) {
                    System.out.println(endLogo);
                    isRunning = false;
                }

            }
        } catch (IOException | InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println(divider);
        }
    }
}
