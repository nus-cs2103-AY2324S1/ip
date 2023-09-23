# NEXUS User Guide

## Features

### List tasks (`list`)

### Add todo (`todo`)

### Add deadline (`deadline`)

### Add event (`event`)

### Mark a task as done (`mark`)

### Mark a task as not done (`unmark`)

### Find a task (`find`)

### Delete a task (`delete`)

## Usage

### `list` - Displays all tasks

Example of usage:

`list`

Expected outcome:

Show all tasks in local storage.

```
Here are the tasks in your list:
1.[T][X] Buy groceries
2.[D][ ] Assignment 2 (by: 9 Sep 2023 2359)
```

### `todo` - Add a todo task to the list

Format:

`todo <name>` or `t <name>`

Example of usage:

`todo Buy groceries` or `t Buy groceries`

Expected outcome:

```
Noted. I've added this task:
[T][ ] Buy groceries
Now you have 4 tasks in the list.
```

### `deadline` - Add a deadline to the list

Format:

`deadline <name> /by [D/M/YYYY] HHMM` or `d <name> /by [D/M/YYYY] HHMM`

Example of usage:

`deadline Assignment 1 /by 22/9/2023 2359` or `d Assignment 1 /by 22/9/2023 2359`

Expected outcome:

```
Noted. I've added this task:
[D][ ] Assignment 2 (by: 9 Sep 2023 2359)
Now you have 2 tasks in the list.
```

### `event` - Add an event to the list

Format:

`event <name> /from [D/M/YYYY] HHMM /to [D/M/YYYY] HHMM` or `e <name> /from [D/M/YYYY] HHMM /to [D/M/YYYY] HHMM`

Example of usage:

`event RH Bash /from 22/9/2023 1200 /to 23/9/2023 0300`
or `e RH Bash /from 22/9/2023 1200 /to 23/9/2023 0300`


Expected outcome:

```
Noted. I've added this task:
[E][ ] RH Bash (from: 22 Sep 2023 1200 to: 23 Sep 2023 0300)
Now you have 3 tasks in the list.
```

### `mark` - Marks a task as done

Format:

`mark <task number>` or `m <task number>`

Example of usage:

Given the list
```
Here are the tasks in your list:
1.[T][X] Buy groceries
2.[D][ ] Assignment 2 (by: 9 Sep 2023 2359)
```

Enter `mark 2` or `m 2`

Expected outcome:

```
Nice! I've marked this task as done:
[D][X] Assignment 2 (by: 9 Sep 2023 2359)
```

### `unmark` - Marks a task as not done yet

Format:

`unmark <task number>` or `um <task number>`

Example of usage:

Given the list
```
Here are the tasks in your list:
1.[T][X] Buy groceries
2.[D][ ] Assignment 2 (by: 9 Sep 2023 2359)
```

Enter `unmark 2` or `um 2`

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][ ] Buy groceries
```

### `find` - Find tasks with matching keyword

Format:

`find <keyword>` or `f <keyword>`

Example of usage:

Given the list
```
Here are the tasks in your list:
1.[T][X] Buy groceries
2.[D][ ] Assignment 2 (by: 9 Sep 2023 2359)
```

Enter `find groceries` or `f groceries`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][X] Buy groceries
```

### `delete` - Remove a task from the list

Format:

`delete <task number>` or `d <task number>`

Example of usage:

Given the list
```
Here are the tasks in your list:
1.[T][X] Buy groceries
2.[D][ ] Assignment 2 (by: 9 Sep 2023 2359)
```

Enter `delete 1` or `d 1`

Expected outcome:

```
Noted. I've removed this task:
[T][X] Buy groceries
Now you have 1 tasks in the list.
```
