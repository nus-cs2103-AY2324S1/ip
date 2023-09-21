# Dialogix

## Features
1. Add tasks such as todos, events, or deadlines to Dialogix.
2. View list of added tasks.
3. Mark tasks as completed/incomplete.
4. Delete a task.
5. Find tasks that have given keyword.

**Notes about the command format**:
- Words in `UPPER_CASE` are the parameters to be supplied by the user. For example, in `todo DESCRIPTION`, `DESCRIPTION` is a parameter that can be used as `todo READBOOK`.

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
