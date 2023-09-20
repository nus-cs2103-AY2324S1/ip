# Bongo User Guide

## Features 

### List tasks (`list`)

Lists all tasks.

### Add todo (`todo`)

Adds a todo task.

### Add deadline (`deadline`)

Adds a deadline task.

### Add event (`event`)

Adds an event task.

### Mark a task as done (`mark`)

Marks a task as done.

### Mark a task as not done (`unmark`)

Marks a task as **not** done.

### Delete a task (`delete`)

Deletes a task.

### Find a task (`find`)

Search for a specific task.

### Exit the bot (`bye`)

Exits Bongo.

## Usage

### `list` - Lists all tasks

Describe the action and its outcome.

Format:

`list`

Example of usage: 

`list`

Expected outcome:

Bongo will output all your tasks, their types and whether they're done. 
For deadlines, the deadline will be shown. For events, the to and from date/time will be shown.

```
 1. [T][X] Make coffee
 2. [D][ ] CS2105 Assignment (by: Monday, Sep 25, 2023 11:59 PM)
 3. [T][ ] Buy bread
```

### `todo` - Add todo

Adds a todo task.

Format:

`todo <todo name>`

Example of usage:

`todo Make coffee`

Expected outcome:

```
 Got it. I've added this task:
  [T][] Make coffee
 Now you have <current number of tasks> in the list.
```

### `deadline` - Add deadline

Adds a deadline task.

Format:

`deadline <deadline name> /by <deadline date>` (date in `dd/MM/yyyy [HHmm]`)

Example of usage:

`deadline CS2105 Assignment /by 25/09/2023 2359`

Expected outcome:

```
 Got it. I've added this task:
  [D][] CS2105 Assignment (by: Monday, Sep 25, 2023 11:59 PM)
 Now you have <current number of tasks> in the list.
```

### `event` - Add event

Adds a event task.

Format:

`event <event name> /from <event start> /to <event end>` (date in `dd/MM/yyyy [HHmm]`)

Example of usage:

`event Sleep /from 25/09/2023 2300 /to 26/09/2023 0700`

Expected outcome:

```
 Got it. I've added this task:
  [D][] CS2105 Assignment (from: Monday, Sep 25, 2023 11:00 PM 
  to: Tuesday, Sep 26, 2023 07:00 AM)
 Now you have <current number of tasks> in the list.
```

### `mark` - Marks a task as done

Marks a task as done.

Format:

`mark <index>`

Example of usage:

`mark 3`

Expected outcome:

```
 Nice! I've marked this task as done:
  [T][X] Buy bread
```

### `unmark` - Marks a task as not done

Marks a task as **not** done.

Format:

`unmark <index>`

Example of usage:

`unmark 3`

Expected outcome:

```
 OK, I've marked this task as not done yet:
  [T][] Buy bread
```

### `delete` - Deletes a task

Deletes a task.

Format:

`delete <index>`

Example of usage:

`delete 3`

Expected outcome:

```
 Noted, I've removed this task:
  [T][] Buy bread 
 Now you have <current number of tasks> in the list.
```

### `find` - Searches for a task

Search for a task with a specific name.

Format:

`find <name>`

Example of usage:

`find coffee`

Expected outcome:

```
 Here are the matching tasks in your list:
  1. [T][X] Make coffee
```

### `bye` - Exits Bongo

Exits Bongo.

Format:

`bye`

Example of usage:

`bye`

Expected outcome:

```
 Bye! Hope to see you again soon!
```

