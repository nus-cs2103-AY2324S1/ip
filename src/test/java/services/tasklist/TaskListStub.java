package services.tasklist;

import command.CommandType;

import java.util.Arrays;

public class TaskListStub implements ITaskList {

    public TaskListStub() {
    }

    @Override
    public String add(String description, CommandType taskType, String... args) {
        return "add method called with description: " + description
                + " and taskType: " + taskType + " and args: " + Arrays.toString(args) + ".";
    }

    @Override
    public String delete(int taskNumber) {
        return "delete method called with taskNumber: " + taskNumber + ".";
    }

    @Override
    public String find(String keyword) {
        return "find method called with keyword: " + keyword + ".";
    }

    @Override
    public String markDone(int taskNumber) {
        return "markDone method called with taskNumber: " + taskNumber + ".";
    }

    @Override
    public String markUndone(int taskNumber) {
        return "markUndone method called with taskNumber: " + taskNumber + ".";
    }

    @Override
    public String show() {
        return "show method called";
    }
}
