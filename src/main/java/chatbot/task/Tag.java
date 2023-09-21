package chatbot.task;

public class Tag {
    /**
     * The description of the tag of the task.
     */
    private String desc;

    /**
     * Constructor for the tag.
     */
    public Tag() {
        this.desc = "";
    }

    /**
     * toString method for Tag.
     *
     * @return String representation of tag.
     */
    @Override
    public String toString() {
        return this.desc;
    }

    /**
     * Sets the description of the tag.
     *
     * @param desc The description of the tag.
     */
    public void setDesc(String desc) {
        this.desc = " #" + desc;
    }
}
