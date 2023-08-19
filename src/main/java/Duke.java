import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<Task> taskList = new ArrayList<>();
        //Create buffered reader for user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //try catch to handle possible IO exceptions
        try {
            System.out.println(logo + divider + "\nHello! I'm MACHO-CATTO! Your personal chat-bot to make your \nday macho!"
                    + "\nWhat can I do for you today?" + divider);
            String[] input = br.readLine().split(" ", 2);
            String command = input != null ? input[0] : null;
            String taskDesc = input.length == 1 ? null : input[1];
            //exits the echo commands when input contains 'bye'
            while (!command.equalsIgnoreCase("bye")) {
                System.out.println(divider);
                if (command.equalsIgnoreCase("list")) {
                    if(taskList.isEmpty()) {
                        System.out.println("No tasks recorded, macho!");
                    }
                    for (int i = 0; i < taskList.size(); i++) {
                        int index = i + 1;
                        Task task = taskList.get(i);
                        System.out.println(index + ".[" + task.getStatus() + "] " + task.getTaskDesc());
                    }

                } else if (command.contains("add")) {
                    taskList.add(new Task(taskDesc, false));
                    System.out.println("Added: " + taskDesc);

                } else if (command.contains("mark")) {
                    //Check marking of tasks
                    int index = Integer.parseInt(taskDesc) - 1;
                    if (index < 0 || index >= taskList.size()) {
                        System.out.println("No tasks available at the specified index, please try again macho!");
                    } else {
                        Task task = taskList.get(index);
                        String reply = "I have marked this task as %s yet, per your request, macho!";
                        if (command.contains("unmark")) {
                            task.markedAsUndone();
                            System.out.println(String.format(reply, "not done"));
                        } else {
                            task.markedAsDone();
                            System.out.println(String.format(reply, "done"));
                        }
                        System.out.println(index + 1 + ".[" + task.getStatus() + "] " + task.getTaskDesc());
                    }

                }

                System.out.println(divider);
                input = br.readLine().split(" ", 2);
                command = input != null ? input[0] : null;
                taskDesc = input.length == 1 ? null : input[1];


            }
            System.out.println(divider + "\nBye! Hope to see you again soon, macho!" + divider + "\n" + endLogo);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
