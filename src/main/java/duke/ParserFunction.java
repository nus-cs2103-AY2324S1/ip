package duke;

import command.Executable;
import dukeexception.InvalidVarException;

@FunctionalInterface
public interface ParserFunction {
    Executable apply(String paramString) throws InvalidVarException;
}
