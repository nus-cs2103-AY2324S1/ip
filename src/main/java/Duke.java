import Exceptions.InvalidCommandException;
import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


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


        ArrayList<Task> taskList = new ArrayList<>();
        //Create buffered reader for user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //try catch to handle possible IO exceptions
        try {
            System.out.println(logo + divider + "\nHello! I'm MACHO-CATTO! Your personal chat-bot to make your \nday macho!"
                    + "\nWhat can I do for you today?" + divider);
            boolean isRunning = true;

            //exits the echo commands when input contains 'bye'
            while (isRunning) {
                String input = br.readLine();
                String cmd = input.split(" ")[0].toUpperCase();
                Commands commands = new Commands(cmd, taskList);
                taskList = commands.execute(input);
                if (cmd.equalsIgnoreCase("bye")) {
                    isRunning = false;
                }

            }
        } catch (IOException | InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println(divider);
        }
    }
}
