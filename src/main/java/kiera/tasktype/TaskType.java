package kiera.tasktype;

import kiera.exception.KieraException;

/**
 * Enumeration representing different types of tasks.
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    /**
     * @inheritDoc
     */
    @Override
    public String toString() throws KieraException {
        String res;
        switch (this) {
        case TODO:
            res = "todo";
            break;
        case DEADLINE:
            res = "deadline";
            break;
        case EVENT:
            res = "event";
            break;
        default:
            throw new KieraException("no such task type exist! ");
        }
        return res;
    }

}
