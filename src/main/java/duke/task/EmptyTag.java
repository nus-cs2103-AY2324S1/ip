package duke.task;

import java.util.ArrayList;
import java.util.HashMap;

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
