package duke.task;

public class EmptyTag extends Tag{
    public EmptyTag() {
        super(null);
    }


    public String getTagName() {
        return "";
    }
    public String getParsedTagName() {
        return "null";
    }
    public String getHashedTagName() {
        return "";
    }

}
