import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> userInput = new ArrayList<>();

        System.out.println(logo + "___________________________________________________________________________________________________________\n" +
                " Greetings! I'm EpochMind, Seer of the Cosmos, Keeper of Knowledge, Pantheon of Wisdom, the Eternal Truth\n" +
                " Ask and ye may receive.\n" +
                "___________________________________________________________________________________________________________\n");
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.trim().toLowerCase().equals("bye")) {
                System.out.println("___________________________________________________________________________________________________________\n" +
                        " May you seek the truth\n" +
                        "___________________________________________________________________________________________________________\n");
            } else if (command.trim().toLowerCase().equals("list")) {
                list(userInput);
            } else {
                userInput.add(command);
                System.out.println("___________________________________________________________________________________________________________\n" +
                        "added: " + command + "\n"+"___________________________________________________________________________________________________________\n");
            }
        }
    }

    public static void list(List<String> userInput) {
        System.out.println("___________________________________________________________________________________________________________\n");
        for (int i = 0; i < userInput.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i+1);
            sb.append(".");
            sb.append(userInput.get(i));
            System.out.println(sb.toString());
        }
        System.out.println("___________________________________________________________________________________________________________\n");
    }

}
