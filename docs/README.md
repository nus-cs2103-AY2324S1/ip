# Cupid Bot

Cupid Bot serves to track all your todos, deadlines and events all in one application. It's named after the god of love and affection _Cupid_. Given below are instructions on how to use it.

## Sample UI

![Screenshot of sample UI](https://github.com/nicrandomlee/ip/blob/master/docs/Ui.png?raw=true)

## Features

### Help

Shows a help message outlining the possible functions.

Format to execute function:

> help

Sample execution:

```
help
```

### ToDo

Adds a todo to your Task List.

- Do note that the function is **not** case sensitive.

Format to execute function:

> todo {description}

Sample execution:

```
todo wash bedsheets
```

### Deadline

Adds a Deadline to your Task List.

- Do note that the function is **not** case sensitive.
- Do note that the datetime should consist of year, month, date, hour and minute. Eg: 2023-10-25 18:00

Format to execute function:

> deadline {description} /by {Datetime}

Sample execution:

```
deadline cs2103 iP /by 2023-09-22 23:59
```

### Event

Adds a Event to your Task List.

- Do note that the function is **not** case sensitive.
- Do note that the datetime should consist of year, month, date, hour and minute. Eg: 2023-10-25 18:00.

Format to execute function:

> event {description} /from {Datetime} /to {Datetime}

Sample execution:

```
event Halloween Horror Night /from 2023-10-13 19:00 /to 2023-10-13 23:00
```

### List

Lists all tasks and the index of each task in your Task List.

- Do note that task index starts from 1.

Format to execute function:

> list

Sample execution:

```
list
```

### Delete

Deletes a task from your Task List.

- Do note that task index starts from 1.

Format to execute function:

> delete {task index}

Sample execution:

```
delete 3
```

### Find

Finds a task from your Task List that has a description that matches your input.

- Do note that find looks for phrases that are being contained in the description, and need not be an exact word. Eg: finding "eat" may return "heat up lunch" or "create google drive".

Format to execute function:

> find {keyword}

Sample execution:

```
find eat
```

### Mark

Marks a task as complete from your Task List.

- Do note that task index starts from 1.
- If a task is already marked, running this will not have any consequences

Format to execute function:

> mark {task index}

Sample execution:

```
mark 4
```

### Unmark

Marks a task as incomplete from your Task List.

- Do note that task index starts from 1.
- If a task is already unmarked, running this will not have any consequences

Format to execute function:

> unmark {task index}

Sample execution:

```
unmark 1
```

## FAQ

_Q_: How do I transfer my data to another computer?
_A_: Search for "cupid.txt" in your main folder and upload a copy to an online cloud storage or your local thumbdrive. Follow the steps at the top of this document to set up cupid bot on the other computer and copy "cupid.txt" to the main folder.

## Known Issues

The following warning issued by Java runtime can be ignored. This warning appears when you use a later JavaFX version (e.g., 17) with a JDK version that doesn't support the modules feature yet (e.g., Java 11).

> WARNING: Unsupported JavaFX configuration: classes were loaded from 'unnamed module @...
