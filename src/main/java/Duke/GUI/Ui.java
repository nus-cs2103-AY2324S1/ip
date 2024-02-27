package Duke.GUI;

import Duke.Tasks.TaskList;

public class Ui {

    public Ui() {

    }

    private final String horizontalLineTop = "_______________ \n\n";
    private final String horizontalLineBottom = "_______________ \n";

    public static String getWelcomeMessage() {
        return "_______________ \n\n"
                + "Eh, I'm Scarlet \n"
                + "No, I don't really want to know who you are \n\n"
                + "_______________ \n";
    }

    public static String getGoodbyeMessage() {
        return "_______________ \n\n"
                + "finally.  \n"
                + "_______________ \n";
    }

    public String getMatchingTasksMessage(TaskList tasks, String description) {
        return horizontalLineTop
                + "Let's see what you've got, shall we? \n"
                + String.format("%s \n%s", tasks.filterTasksByDescription(description.trim()), horizontalLineBottom);
    }

    public String getTaskListMessage(TaskList tasks) {
        return horizontalLineTop
                + "What a terrible day to be alive. \n"
                + String.format("%s \n%s", tasks, horizontalLineBottom);
    }

    public String getMarkedTaskMessage(TaskList tasks, int index) {
        return horizontalLineTop
                + "A proud moment of your life I am sure... \n  "
                + tasks.getTask(index)
                + " \n"
                + horizontalLineBottom;
    }

    public String getUnmarkedTaskMessage(TaskList tasks, int index) {
        return horizontalLineTop
                + "I'm not judging at all... \n"
                + tasks.getTask(index) +
                " \n"
                + horizontalLineBottom;
    }

    public String getDeleteTaskMessage(TaskList tasks, int index) {
        return horizontalLineTop
                + "Not another mistake I hope... \n"
                + tasks.getTask(index)
                + " \n\n"
                + horizontalLineBottom;
    }

    public String getTaskWithoutDescriptionMessage() {
        return horizontalLineTop
                + "Come on now... don't be shy, go on \n"
                + horizontalLineBottom;
    }

    public String getUnreadableInputMessage() {
        return horizontalLineTop
                + "Someone should have paid attention in school... try again \n"
                + horizontalLineBottom;
    }

    public String getDuplicateInputMessage() {
        return horizontalLineTop
                + "Is the schizophrenia finally starting to kick in? It's duplicated, but if you do it again I'll allow it. \n"
                + horizontalLineBottom;
    }

    public String getAddedToListMessage(TaskList tasks) {
        return horizontalLineTop
                + "I'm totally not judging... \n"
                + tasks.getTask(tasks.getNumOfItems())
                + " \n"
                + " ... added to the list \n"
                + "I wonder how you'll mess up this... "
                + tasks.getNumOfItems() + "\n"
                + horizontalLineBottom;
    }

    public String getWrongIndexMessage() {
        return horizontalLineTop
                + "1 mama and 1 papa made 2 dummies \n"
                + horizontalLineBottom;
    }
}
