# Jelly User Guide

## Features
- Add a todo task: `todo`
- Add a deadline task: `deadline`
- Add an event task: `event`
- List all tasks: `list`
- Find a task using a keyword: `find`
- Mark a task as done: `mark`
- Mark a task as not done: `unmark`
- Delete a task: `delete`
- Change the priority of a task: `priority`
- Exit the chat bot: `bye`

## Usage

### Add a todo task: `todo`

Adds a todo task to the list.

Format: `todo {DESCRIPTION}`

Example of usage:
`todo math homework`

Expected outcome:
```
Ok! I've added this task:
[T] [ ] math homework (HIGH)
Now you have {x} tasks in the list.
```

### Add a deadline task: `deadline`

Adds a deadline task to the list.

Format: `deadline {DESCRIPTION} /by dd/MM/yyyy HHmm`

Example of usage:
`deadline Finish homework /by 24/09/2023 1900`

Expected outcome:
```
Ok! I've added this task:
[D] [ ] Finish homework (by: Sep 24 2023 19:00) (HIGH)
Now you have {x} tasks in the list.
```

### Add an event task: `event`

Adds an event task to the list.

Format: `event {DESCRIPTION} /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm`

Example of usage:
`event Party /from 24/09/2023 1900 /to 24/09/2023 2300`

Expected outcome:
```
Ok! I've added this task:
[E] [ ] Party (from: Sep 24 2023 19:00 to: Sep 24 2023 23:00) (HIGH)
Now you have {x} tasks in the list.
```

### List all tasks: `list`

Displays all tasks in the list.

Format: `list`

Example of usage:
`list`

Expected outcome:
```
Here are the tasks in your list:
1.[T] [ ] math homework (HIGH)
2.[D] [ ] Finish homework (by: Sep 24 2023 19:00) (HIGH)
3.[D] [ ] Finish homework (by: Sep 24 2023 19:00) (HIGH)
```

### Find a task using a keyword: `find`

Displays all tasks in the list.

Format: `find {keyword}`

Example of usage:
`find math`

Expected outcome:
```
Here are the tasks in your list:
1.[T] [ ] math homework (HIGH)
````

### Mark a task as done: `mark`

Marks a task in the list as done.

Format: `mark {index}`

Example of usage:
`mark 1`

Expected outcome:
```
Good job! I've marked this task as done :)
```

### Mark a task as not done: `unmark`

Marks a task in the list as not done.

Format: `unmark {index}`

Example of usage:
`unmark 1`

Expected outcome:
```
Bad job! I've marked this task as not done :(
```

### Delete a task: `delete`

Deletes the specified task from the list.

Format: `delete {index}`

Example of usage:
`delete 1`

Expected outcome:
```
Okay, I've removed this task:
[T] [ ] math homework (HIGH)
Now you have 2 tasks in the list.
```

### Change the priority of a task: `priority`

Changes the priority of a task to high(1), medium(2), or low(3).

Note: Priority of a task is (HIGH) as default.

Format: `priority {index} {priority}`

Example of usage:
`priority 1 3`

Expected outcome:
```
I've changed the priority of this task to: 3
```

### Exit the chat bot: `bye`

Saves the current list and exits out of the chat bot.

Format: `bye`

Example of usage:
`bye`
