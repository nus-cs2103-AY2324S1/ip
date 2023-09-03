package Duke;

import Duke.Tasks.*;
import Duke.Exceptions.*;

public class Ui {

    public Ui() {

    }

    private final String upper = "_______________ \n\n";
    private final String lower = "_______________ \n";

    public static void printWelcome() {
        String logo = "_______________ \n\n"
                + "Eh, I'm Scarlet \n"
                + "No, I don't really want to know who you are \n\n"
                + "_______________ \n";
        System.out.println(logo);
    }

    public static void printBye() {
        String logo = "_______________ \n\n"
                + "finally.  \n"
                + "_______________ \n";
        System.out.println(logo);
    }

    public void printList(TaskList tasks) {
        String condemn = upper
                + "What a terrible day to be alive. \n"
                + String.format("%s \n_______________ \n", tasks);
        System.out.println(condemn);
    }

    public void printMarked(TaskList tasks, int index) {
        System.out.println( upper
                + "A proud moment of your life I am sure... \n  "
                + tasks.getTask(index) + " \n" + lower);
    }

    public void printUnmarked(TaskList tasks, int index) {
        System.out.println(upper
                + "I'm not juding at all... \n"
                + tasks.getTask(index) + " \n" + lower);
    }

    public void printDelete(TaskList tasks, int index) {
        System.out.println( upper
                + "Not another mistake I hope... \n"
                + tasks.getTask(index) + " \n\n" + lower);
    }

    public void printTaskWithoutDescription() {
        System.out.println(upper +
                "Come on now... don't be shy, go on \n" +
                lower);
    }

    public void printNonsense() {
        System.out.println(upper +
                "Someone should have paid attention in school... try again \n" +
                lower);
    }

    public void printAddedToList(TaskList tasks) {
        System.out.println(upper
                + "I'm totally not judging...  "
                + tasks.getTask(tasks.numOfItems())
                + " ... added to the list \n"
                + "I wonder how you'll mess up this... " + tasks.numOfItems() + "\n"
                + lower);
    }

    public void printWrongIndex() {
        System.out.println(upper
                +  "1 mama and 1 papa made 2 dummies \n"
                + lower);

    }
}


