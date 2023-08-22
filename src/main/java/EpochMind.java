import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class EpochMind {
    public static String logo = "\n" +
            "                                                                                                    \n" +
            "                                                             ____                                   \n" +
            "    ,---,.                                ,---,            ,'  , `.                                 \n" +
            "  ,'  .' |,-.----.                      ,--.' |         ,-+-,.' _ |  ,--,                     ,---, \n" +
            ",---.'   |\\    /  \\    ,---.            |  |  :      ,-+-. ;   , ||,--.'|         ,---,     ,---.'| \n" +
            "|   |   .'|   :    |  '   ,'\\           :  :  :     ,--.'|'   |  ;||  |,      ,-+-. /  |    |   | : \n" +
            ":   :  |-,|   | .\\ : /   /   |   ,---.  :  |  |,--.|   |  ,', |  ':`--'_     ,--.'|'   |    |   | | \n" +
            ":   |  ;/|.   : |: |.   ; ,. :  /     \\ |  :  '   ||   | /  | |  ||,' ,'|   |   |  ,\"' |  ,--.__| | \n" +
            "|   :   .'|   |  \\ :'   | |: : /    / ' |  |   /' :'   | :  | :  |,'  | |   |   | /  | | /   ,'   | \n" +
            "|   |  |-,|   : .  |'   | .; :.    ' /  '  :  | | |;   . |  ; |--' |  | :   |   | |  | |.   '  /  | \n" +
            "'   :  ;/|:     |`-'|   :    |'   ; :__ |  |  ' | :|   : |  | ,    '  : |__ |   | |  |/ '   ; |:  | \n" +
            "|   |    \\:   : :    \\   \\  / '   | '.'||  :  :_:,'|   : '  |/     |  | '.'||   | |--'  |   | '/  ' \n" +
            "|   :   .'|   | :     `----'  |   :    :|  | ,'    ;   | |`-'      ;  :    ;|   |/      |   :    :| \n" +
            "|   | ,'  `---'.|              \\   \\  / `--''      |   ;/          |  ,   / '---'        \\   \\  /   \n" +
            "`----'      `---`               `----'             '---'            ---`-'                `----'    \n" +
            "                                                                                                    \n";
    public static String[] exitMessage = {"May you seek the truth.", "May the truth prevail.", "May you yet again thirst for wisdom.", "May your quest for wisdom be unwavering.", "May your pursuit of knowledge illuminate your path."};
    public static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(logo + "___________________________________________________________________________________________________________\n" +
                " Greetings! I'm EpochMind, Seer of the Cosmos, Keeper of Knowledge, Pantheon of Wisdom, the Eternal Truth\n" +
                " Ask and ye may receive.\n" +
                "___________________________________________________________________________________________________________\n");
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] commandList = command.trim().toLowerCase().split(" ");
            switch (commandList[0]) {
                case "bye":
                    System.out.println("___________________________________________________________________________________________________________\n" +
                            " May you seek the truth\n" +
                            "___________________________________________________________________________________________________________\n");
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    if (commandList.length == 1) {
                        System.out.println("Thou hast forgotten to specify thine index");
                    } else {
                        int index = parseInt(commandList[1]);
                        if (tasks.size() < index) {
                            System.out.println("Thou hast specified an index larger than thine list");
                        } else {
                            mark(parseInt(commandList[1]));
                        }
                    }
                    break;
                case "unmark":
                    if (commandList.length == 1) {
                        System.out.println("Thou hast forgotten to specify thine index");
                    } else {
                        int index = parseInt(commandList[1]);
                        if (tasks.size() < index) {
                            System.out.println("Thou hast specified an index larger than thine list");
                        } else {
                            unmark(parseInt(commandList[1]));
                        }
                    }
                    break;
                default:
                    tasks.add(new Task(command));
                    System.out.println("___________________________________________________________________________________________________________\n" +
                            "added: " + command + "\n" + "___________________________________________________________________________________________________________\n");
                    break;
            }
        }
    }

    public static void list() {
        System.out.println("___________________________________________________________________________________________________________\n");
        for (int i = 0; i < tasks.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i+1);
            sb.append(". ");
            sb.append("[" + tasks.get(i).getStatusIcon() + "]");
            sb.append(tasks.get(i).description);
            System.out.println(sb);
        }
        System.out.println("___________________________________________________________________________________________________________\n");
    }

    public static void mark(int index) {
        System.out.println("___________________________________________________________________________________________________________\n" + "The Mind sees that this task is completed \n" +
                "[X] " + tasks.get(index - 1).description +
                "\n___________________________________________________________________________________________________________\n");
        tasks.get(index - 1).mark();
    }

    public static void unmark(int index) {
        System.out.println("___________________________________________________________________________________________________________\n" + "The Mind sees that this task is not yet completed \n" +
"[ ] " + tasks.get(index - 1).description +
"\n___________________________________________________________________________________________________________\n");
        tasks.get(index - 1).unmark();
    }


}
