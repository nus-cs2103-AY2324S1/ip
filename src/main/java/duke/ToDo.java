package duke;

//CHECKSTYLE.OFF: MissingJavadocMethodCheck
//CHECKSTYLE.OFF: MissingJavadocType
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Return the string representation of the task
     *
     * @return string the description of task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Return the string representation of the task to be saved
     *
     * @return the right format of description of task to save into the file
     */
    public String save() {
        return "T|" + super.status();
    }

}
