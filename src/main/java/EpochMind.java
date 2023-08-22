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
            } else {
                System.out.println(command);
            }
        }
    }

}
