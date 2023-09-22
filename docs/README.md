# Dialogix

# User Guide

Dialogix is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Dialogix can get your contact management tasks done faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `dialogix.jar` from [here](https://github.com/longnguyentan/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Dialogix.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar dialogix.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will display all the current tasks/events/deadlines.<br>
   Some example commands you can try:

    * `list` : Lists all the saved tasks, events, and deadlines.
    * `todo Homework` : Adds `todo` task named `Homework`.
    * `delete 3` : Deletes the 3rd task in the list.
    * `bye` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Features
1. Add tasks such as todos, events, or deadlines to Dialogix.
2. View list of added tasks.
3. Mark tasks as completed/incomplete.
4. Delete a task.
5. Find tasks that have given keyword.

**Notes about the command format**:

- Words in `UPPER_CASE` are the parameters to be supplied by the user. For example, in `todo DESCRIPTION`, `DESCRIPTION` is a parameter that can be used as `todo READBOOK`.

--------------------------------------------------------------------------------------------------------------------

## Usage


### Adding a task: `todo`
Adds a Todo to Dialogix.

**Format**: `todo DESCRIPTION`

Example:

```
todo Read CS2101 lecture note
```


### Adding an event: `event`
Adds an event at the date provided to Dialogix.

**Format**: `event DESCRIPTION /at DATE`

Example:

```
event N-House Connect /at 21/09/2023
```

### Adding a deadline: `deadline`
Adds a deadline by the date to Dialogix.

**Format**: `deadline DESCRIPTION /by DATE`

Example:

```
deadline Quiz of CS2103T /by 23/09/2019 2359
```

### Listing all tasks: `list`
Lists all tasks in Dialogix.

**Format**: `list`

Example:

```
list
```

### Marking a task as done: `done`
Marks the task with the current task number as done.

**Format**: `done TASK_NUMBER`

Example:

```
done 3
```

### Finding a task: `find`
Finds a task with a keyword.

**Format**: `find KEYWORD`

Example:

```
find homework
```

### Deleting a task: `delete`
Deletes a task in the list with its task number.

**Format**: `delete TASK_NUMBER`

Example:

```
delete 3
```
--------------------------------------------------------------------------------------------------------------------
## Command summary

| Action           | Format, Examples                                                                          |
|------------------|-------------------------------------------------------------------------------------------|
| **List**         | `list`                                                                                    |
| **Add Todo**     | `todo DESCRIPTION` <br> e.g., `todo Homework`                                             |
| **Add Event**    | `event DESCRIPTION /at DATETIME` <br> e.g., `event U-Town Orientation /at 5PM 23/09/2023` |
| **Add Deadline** | `deadline DESCRIPTION /by DATETIME` <br> e.g., `deadline Quiz 1 /by 19:00 21/09/2023`     |
| **Delete**       | `delete TASK_NUMBER`<br> e.g., `delete 3`                                                 |
| **Mark as done** | `done TASK_NUMBER`<br> e.g.,`done 3`                                                      |
| **Find**         | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find assignment`                                |
