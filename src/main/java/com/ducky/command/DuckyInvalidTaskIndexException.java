package com.ducky.command;

import com.ducky.logic.DuckyException;

/**
 * An exception thrown when the user specifies an index that is negative,
 * or greater than Ducky's task list size.
 */
public class DuckyInvalidTaskIndexException extends DuckyException {

    /**
     * Constructs an invalid index exception with an error message detailing
     * the invalid index given and the number of tasks currently in the list.
     * @param index Invalid index given by user.
     * @param size Size of the task list.
     */
    public DuckyInvalidTaskIndexException(int index, int size) {
        super(String.format("Could not update task %d! There are %d tasks available.", index, size));
    }
}
