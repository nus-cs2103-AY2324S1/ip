package duke.Utils;

import duke.Utils.Command.Type;

public class InvalidArgumentException extends DukeException {
    protected InvalidArgumentException(String arg, Type type) {
        super(String.format(
            "I'm sorry, but you have keyed in an invalid argument for the argType /%s. Try again with /%s [%s]", 
            arg,
            arg,
            type
          ));
    }
}
