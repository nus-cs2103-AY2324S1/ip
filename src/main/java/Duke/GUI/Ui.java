package Duke.GUI;

import Duke.Tasks.TaskList;

public class Ui {

    public Ui() {

    }

    private final String upper = "_______________ \n\n";
    private final String lower = "_______________ \n";

    public static String printWelcome() {
        String logo = "_______________ \n\n"
                + "Eh, I'm Scarlet \n"
                + "No, I don't really want to know who you are \n\n"
                + "_______________ \n";
        return logo;
    }

    public static String printBye() {
        String logo = "_______________ \n\n"
                + "finally.  \n"
                + "_______________ \n";
        return logo;
    }

    public String printMatchingTasks(TaskList tasks, String desc) {
        String condemn = upper
                + "Let's say what you've cooked, if you could. \n"
                + String.format("%s \n_______________ \n", tasks.filterTaskList(desc.trim()));
        return condemn;
    }

    public String printList(TaskList tasks) {
        String condemn = upper
                + "What a terrible day to be alive. \n"
                + String.format("%s \n_______________ \n", tasks);
        return condemn;
    }

    public String printMarked(TaskList tasks, int index) {
        return upper
                + "A proud moment of your life I am sure... \n  "
                + tasks.getTask(index)
                + " \n"
                + lower;
    }

    public String printUnmarked(TaskList tasks, int index) {
        return upper
                + "I'm not juding at all... \n"
                + tasks.getTask(index) +
                " \n"
                + lower;
    }

    public String printDelete(TaskList tasks, int index) {
        return upper
                + "Not another mistake I hope... \n"
                + tasks.getTask(index)
                + " \n\n"
                + lower;
    }

    public String printTaskWithoutDescription() {
        return upper
                + "Come on now... don't be shy, go on \n"
                + lower;
    }

    public String printNonsense() {
        return upper
                + "Someone should have paid attention in school... try again \n"
                + lower;
    }

    public String printAddedToList(TaskList tasks) {
        return upper
                + "I'm totally not judging... \n"
                + tasks.getTask(tasks.numOfItems())
                + " \n"
                + " ... added to the list \n"
                + "I wonder how you'll mess up this... "
                + tasks.numOfItems() + "\n"
                + lower;
    }

    public String printWrongIndex() {
        return upper
                +  "1 mama and 1 papa made 2 dummies \n"
                + lower;

    }
}


