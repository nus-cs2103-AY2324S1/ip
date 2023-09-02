package duke.dukeexceptions;

import java.lang.Exception;
public class CorruptDataException extends Exception{
    private final String corruptLine;
    public CorruptDataException(String corruptLine) {
        super("Data is corrupt");
        this.corruptLine = corruptLine;
    }

    public String getCorruptLine() {
        return this.corruptLine;
    }
}
