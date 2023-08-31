package duke.task;
public class Task {
    private String text;
    private String type = "";
    private boolean checked;
    public final static String horiLine = "____________________________________________________________";

    /**
     * Creates a new Task object with the given text and sets checked status to false.
     *
     * @param text The text description of the task.
     */
    public Task(String text){
        this.text = text;
        this.checked = false;
    }

    /**
     * Creates a new Task object with the given text and checked status.
     * Mainly used for registering input from local file
     *
     * @param text The text description of the task.
     * @param checked The status of whether the task is checked (completed) or not.
     */
    public Task(String text,boolean checked){
        this.text = text;
        this.checked = checked;
    }

    /**
     * Gets the text description of the task.
     *
     * @return The text description of the task.
     */
    public String getText(){

        return text;
    }

    /**
     * Gets a string representation of the checked status.
     *
     * @return A string representing the checked status of the task.
     */
    public String getChecked(){
        if(checked){
            return "[/]";
        }else{
            return "[]";
        }
    }
    /**
     * Gets the task's status text including its checked status and text description.
     *
     * @return The task's status text.
     */
    public String getStatusText(){
        String result = getChecked() + " " + getText();
        return result;
    }

    /**
     * Gets the type of the task enclosed in brackets.
     *
     * @return The type of the task.
     */
    public String getType(){
        String result = "[" + type +"]";
        return result;
    }

    /**
     * Gets the formatted text including type, checked status, and text description.
     *
     * @return The formatted text of the task.
     */
    public String getTypeCheckedText(){
        String result = getType() + getChecked() + " " + getText();
        return result;
    }

    /**
     * Marks the task as completed.
     */
    public void mark(){
        checked = true;
        System.out.println(horiLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(getStatusText());
        System.out.println(horiLine);
    }

    /**
     * Unmarks the task as completed.
     */
    public void unmark(){
        checked = false;
        System.out.println(horiLine);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(getStatusText());
        System.out.println(horiLine);

    }

    /**
     * Sets the type of the task.
     *
     * @param text The type to set for the task.
     */
    public void setType(String text){
        type = text;
    }

    /**
     * Gets the task's data in a parsed format to be imputed into our local file
     *
     * @return The parsed data of the task.
     */
    public String getParsed(){
        String result = this.type + ";" + this.text + ";" + this.checked;
        return result;
    }



}
