package jarvis;

import command.Parser;

import services.TextUi;
import services.bizerrors.JarvisException;
import services.tasklist.Storage;
import services.tasklist.TaskList;

import java.util.Scanner;

public class Jarvis {
    public final static String JARVIS_LOGO = "                                                                 \n" +
            "                          .::^^^^^^::.                           \n" +
            "                     .^!??????777??????7~:                       \n" +
            "                  .~?J?7!~^:::::::::^^~!?JJ7:                    \n" +
            "                :7YJ!~^:..:::::7???7!~:::^~7Y?^                  \n" +
            "              .!Y?~^^..:^^:.. :?::^~!?J?!::^^7Y?:                \n" +
            "             .?Y~^^..^^.     :YP?.    .^?Y7::^^?Y~               \n" +
            "            .JY^^: ^~.      :5PP5:       :JY~.~:75~              \n" +
            "            ?5^^: ~^       ^5PP5:~J.       75! ~.?Y:             \n" +
            "           ^5?:~ :~       ^5PP5:~PPJ.       ?Y::~^57             \n" +
            "           ~Y~:: ^.      ~PPP5: !PPPY.      ~5! ^.J?             \n" +
            "           .! .  .      :JYYJ:   ~PPPY:     ^57 . ^^             \n" +
            "            ! ^^ ^^               ~5PPY:    !5~.~ ~:             \n" +
            "            ~^ ! .!.  !JJJJJJJJJJJ?5PPP5:  :YJ.^: !              \n" +
            "             !.:~ .~.!5PPPPPPPPPPPP5555PY:^YJ.:~ ~:              \n" +
            "             .!.:~..~!^................:~JY7.^^ ~^               \n" +
            "              .~^.^^..^^:.          .:!?J7::^:.~:                \n" +
            "                :~::^^:.::^^:::!777?J?7~::^::^^.                 \n" +
            "                  :^^::::::::::~~~~^^::::::^^.                   \n" +
            "                    .:^^:::::::::::::::^^^:                      \n" +
            "                        .:::::.  :::::..                         \n" +
            "                                                                 \n" +
            "                                                                 \n" +
            "          ^!.    ~7^    :77777!:  !!     ~7..!~  .~77777:        \n" +
            "          Y&^   7&B#^   :!!!!!Y&! 7&Y   7&Y :&5  G#?!7!!:        \n" +
            "          J&^  !&J.G#:  ^JJJJJPB~  7&? ~&Y  :#5  JBYJJY?:        \n" +
            "      :::^G#: !&Y  .BB: !&J^~G&~    ?&Y#5   :&5  .^~~~J&J        \n" +
            "     .YYYYJ: :5Y.   :5? ^P~  .YY:    ?PY.   .5?  7YYYYYJ:        \n" +
            "                                                                 ";

    private final TextUi textUi;
    private TaskList taskList;
    private Parser parser;

    public static void main(String[] args) {
        String dataFilePath = "data/jarvislist.txt";
        new Jarvis(dataFilePath).run();
    }

    public Jarvis(String dataFilePath) {
        textUi = new TextUi();
        try {
            taskList = new TaskList(new Storage(dataFilePath), textUi);
            parser = new Parser(taskList);
        } catch (JarvisException e) {
            textUi.print(e.toString());
        }
    }

    public String respond(String userInput) {
        try {
            return parser.parseAndExecute(userInput);
        } catch (JarvisException e) {
            return e.toString();
        }
    }

    public String greet() {
        return "At your service, sir.";
    }

    // Only used for text-ui.
    public void run() {
        System.out.println(JARVIS_LOGO);
        textUi.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("exit")) {
            try {
                textUi.print(parser.parseAndExecute(userInput));
            } catch (JarvisException e) {
                textUi.print(e.toString());
            }
            userInput = scanner.nextLine();
        }

        scanner.close();
        textUi.exit();
    }

}
