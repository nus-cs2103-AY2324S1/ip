# Duke

## Quick start

1. Ensure that you have Java 11 or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/zhyuhan/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open a command terminal, `cd` into the folder you put the `.jar` file in, and run `java -jar duke.jar`
5. Type a command in the chat box and press Enter to execute it.

## Usage

### `list`

Lists all tasks in the task list.

Format: `list`

Example usage:

```text
list
```

Expected outcome:

All tasks in the task list are shown.

```text
Here are the tasks in your list:
1. [T][X] buy milk
2. [D][ ] assignment (by 2023-09-20T23:59)
3. [E][ ] meeting (from 2023-09-20T14:00 to 2023-09-20T16:00)
You have 3 tasks in your list.
```

### `todo`

Adds a new Todo to the task list.

Format: `todo <DESCRIPTION>`

Example usage:

```text
todo buy milk
```

Expected outcome:

A new Todo is added to the task list.

```text
Got it. I've added this task:
[T][ ] buy milk
Now you have 1 task in your list.
```

### `deadline`

Adds a new deadline to the task list.

Format: `deadline <DESCRIPTION> /by <DUE_DATE>`

Example usage:

```text
deadline assignment /by 2023-09-20T23:59
```

Expected outcome:

A new Deadline is added to the task list.

```text
Got it. I've added this task:
[D][ ] assignment (by 2023-09-20T23:59)
Now you have 2 tasks in your list.
```

### `event`

Adds a new event to the task list.

Format: `event <DESCRIPTION> /from <START_TIME> /by <END_TIME>`

Example usage:

```text
event meeting /from 2023-09-20T14:00 /to 2023-09-20T16:00
```

Expected outcome:

A new Event is added to the task list.

```text
Got it. I've added this task:
[E][ ] meeting (from 2023-09-20T14:00 to 2023-09-20T16:00)
Now you have 3 tasks in your list.
```

### `find`

Finds a task based on a keyword.

Format: `find <KEYWORD>`

Example usage:

```text
find milk
```

Expected outcome:

All tasks whose description contains the keyword are shown.

```text
Here are the matching tasks in your list:
1. [T][ ] buy milk
```

### `delete`

Deletes a task.

Format: `delete <TASK_NUMBER>`

Example usage:

```text
delete 1
```

Expected outcome:

The task with the corresponding task number is deleted.

```text
Noted. I've removed this task:
[T][ ] buy milk
You have 3 tasks in your list.
```

### `mark`

Marks a task as done

Format: `mark <TASK_NUMBER>`

Example usage:

```
mark 1
```

Expected outcome:

The task with the corresponding task number is marked as done.

```text
Nice! I've marked this this task as done:
[D][X] meeting (from 2023-09-20T14:00 to 2023-09-20T16:00)
```

### `unmark`

Marks a task as not done.

Format: `unmark <TASK_NUMBER>`

Example usage:

```text
unmark 1
```

Expected outcome:

The task with the corresponding task number is marked as not done.

```text
OK, I've marked this this task as not done yet:
[D][ ] meeting (from 2023-09-20T14:00 to 2023-09-20T16:00)
```

### `bye`

Terminates the program.

Format: `bye`

Example usage:

```
bye
```

Expected outcome:

The program terminates.

## Command summary

| Command    | Usage                                                   |
|------------|---------------------------------------------------------|
| `list`     | `list`                                                  |
| `todo`     | `todo <DESCRIPTION>`                                    |
| `deadline` | `deadline <DESCRIPTION> /by <DUE_DATE>`                 |
| `event`    | `event <DESCRIPTION> /from <START_TIME> /by <END_TIME>` |
| `find`     | `find <KEYWORD>`                                        |
| `delete`   | `delete <TASK_NUMBER>`                                  |
| `mark`     | `mark <TASK_NUMBER>`                                    |
| `unmark`   | `unmark <TASK_NUMBER>`                                  |
| `bye`      | `bye`                                                   |
