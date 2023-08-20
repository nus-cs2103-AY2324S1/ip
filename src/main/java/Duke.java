import javax.sound.midi.Soundbank;
import java.util.Locale;
import java.util.Scanner;



public class Duke {

    static String termination_word = "BYE";

    public static void main(String[] args) {
        String action;
        Duke.sayHi();
        Scanner reader = new Scanner(System.in);
        action = reader.next().toString().toUpperCase();
        while (!action.equals(termination_word)) {
            Duke.actions(action);
            action = reader.next().toString().toUpperCase();
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



    private static void actions(String action) {
        switch (action) {
            case "BYE":
                System.out.println();
            case "HI":
                System.out.println("How are you doing today!");
            default:
                action = action.substring(0,1).toUpperCase() + action.substring(1).toLowerCase();
                System.out.println(action);
        }
    }


}
