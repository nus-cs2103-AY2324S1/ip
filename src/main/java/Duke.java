import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;



public class Duke {

    static String termination_word = "BYE";

    public static void main(String[] args) {
        String action;
        ArrayList<String> history = new ArrayList<>();
        Duke.sayHi();
        Scanner reader = new Scanner(System.in);
        action = reader.next().toString();
        while (!action.equals(termination_word)) {
            history.add(action);
            Duke.actions(action, history);
            action = reader.next().toString();
        }
        Duke.sayBye();

    }

    private static void sayHi() {
        String logo = "YONG";
        System.out.println("Hello I'm " + logo + "\n" +
                "What can I do for you? \n" +
                "------------------------------------- \n"
        );
    }

    private static void sayBye() {
        System.out.println("Thank you and have a good day!");
    }



    private static void actions(String action, ArrayList history) {
        action = action.toUpperCase();
        switch (action) {
            case "BYE":
                System.out.println();
                break;
            case "HI":
                System.out.println("How are you doing today!");
                break;
            case "LIST":
                for (int i = 0; i < history.size(); i++) {
                    System.out.println(i+1 + ": " + history.get(i));
                }
                break;
            default:
                action = action.substring(0,1).toUpperCase() + action.substring(1).toLowerCase();
                System.out.println(action);
        }
    }


}
