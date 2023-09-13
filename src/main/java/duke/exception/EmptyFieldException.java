package duke.exception;

import duke.helper.FormatHelper;

/** An exception for when a field that is required is empty */
public class EmptyFieldException extends DukeException {
    /**
     * Returns an EmptyFieldException
     *
     * @param object name of object
     * @param field name of field in object
     * @return an EmptyFieldException
     */
    public EmptyFieldException(String object, String field) {
        super(String.format("The %s of %s %s cannot be empty", field, FormatHelper.getArticle(object), object));
    }
}
