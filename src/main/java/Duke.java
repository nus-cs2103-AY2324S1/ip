import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Duke {
    public static void main(String[] args) {
        String divider = "\n____________________________________________________________";
        String logo = "       ／ﾌﾌ 　　　　　　ム｀ヽ\n" +
                "      / ノ)　  A　　A 　　）   ヽ\n" +
                "      / ｜　　( ´・ω・`)ノ⌒ゝ_ノ\n" +
                "      /　ﾉ⌒7⌒ヽーく　 ＼　／\n" +
                "      丶＿ ノ ｡　　 ノ､　　|/\n" +
                "    　　  `ヽ `ー-'_人`ーﾉ\n" +
                "    　　　   丶 ￣ _人'彡ﾉ\n" +
                "   　　　　   ﾉ　r'十ヽ/";

        String endLogo = "               ＿   ★★EVERYDAY★★\n" +
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

        //try catch to handle possible IO exceptions
        try {
            System.out.println(logo + divider + "\nHello! I'm MACHO-CATTO! Your personal chat-bot to make your \nday macho!"
                    + "\nWhat can I do for you today?" + divider);
            String input = br.readLine();
            //exits the echo commands when input contains 'bye'
            while (!input.equalsIgnoreCase("bye")) {
                System.out.println(divider + "\n" + input + divider);
                input = br.readLine();
            }
            System.out.println(divider + "\nBye. Hope to see you again soon!" + divider + "\n" + endLogo);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
