import services.Basics;
import services.tasklist.List;
import services.tasklist.Todo;

import java.util.Scanner;

public class Jarvis {

    public final static Scanner scanner = new Scanner(System.in);
    public final static String jarvisLogo = "                                                                 \n" +
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

    public static void main(String[] args) {
        System.out.println(jarvisLogo);
        Basics.greet();

        String userInput = scanner.nextLine();

        while (!userInput.equals("exit")) {
            String command = userInput.split(" ")[0];
            String arguments = userInput.replaceFirst(command, "").strip();
            // condition on the first word of the user input.
            switch (command) {
                case "list": {
                    List.show();
                    break;
                }
                case "mark": {
                    int taskNumber = Integer.parseInt(arguments);
                    List.markDone(taskNumber);
                    break;
                }
                case "unmark": {
                    int taskNumber =Integer.parseInt(arguments);
                    List.markUndone(taskNumber);
                    break;
                }
                case "todo": {
                    List.add(arguments, "todo");
                    break;
                }
                case "deadline": {
                    String[] varargs = arguments.split("\\s+/by\\s+");
                    String description = varargs[0], by = varargs[1];
                    List.add(description, "deadline", by);
                    break;
                }
                case "event": {
                    String[] varargs = arguments.split("\\s+/from\\s+|\\s+/to\\s+");
                    String description = varargs[0], from = varargs[1], to = varargs[2];
                    List.add(description, "event", from, to);
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + userInput);
            }
            userInput = scanner.nextLine();
        }

        Basics.exit();
    }


}
