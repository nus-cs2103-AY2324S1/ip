# Bob User Guide

## Features 

### 1. Add task

Tasks of 3 types:
- Todo (T)
- Deadline (D)
- Event (E)

can be added to your tasklist, and classified by priority

### 2. View tasks

You can display all your tasks, or select based on priority.

### 3. Mark done/ undone

Once a task is complete, you can mark it as done.
Conversely, you can also mark a previous completed task as undone.

### 4. Find

You can find specific tasks based on keyword.

### 5. Delete task

Tasks can be deleted.



## Usage

### `todo` - Add task

Adds task of type Todo into tasklist.
#### Arguments:
- priority - `p/high`, `p/mid`, `p/low`
- task name

Example of usage: 

`todo p/mid read book`

Expected outcome:

```
You have added a new task: 
    [T][][Mid] read book
Now, you have 1 task!
```

### `deadline` - Add task

Adds task of type Deadline into tasklist.
#### Arguments:
- priority - `p/high`, `p/mid`, `p/low`
- task name
- due date - `/by YYYY-MM-DD`

Example of usage: 

`deadline p/high assignment /by 2023-01-01`

Expected outcome:

```
You have added a new task: 
    [D][][High] assignment (by: Jan 01 2023)
Now, you have 2 tasks!
```

### `event` - Add task

Adds task of type Event into tasklist.
#### Arguments:
- priority - `p/high`, `p/mid`, `p/low`
- task name
- start date - `/from YYYY-MM-DD`
- end date - `/by YYYY-MM-DD`

Example of usage: 

`event p/low camp /from 2023-01-01 /to 2023-01-03`

Expected outcome:

```
You have added a new task: 
    [E][][Low] camp (from: Jan 01 2023 to: Jan 03 2023)
Now, you have 3 tasks!
```

### `list` - Display tasks

Displays your tasks
#### Optional arguments:
- priority - `p/high`, `p/mid`, `p/low`

Examples of usage: 

`list`

Expected outcome:

```
Certainly. Here are all your tasks. Consider filtering by priority (e.g. list p/high)
1. [T][][Mid] read book
2. [D][][High] assignment (by: Jan 01 2023)
3. [E][][Low] camp (from: Jan 01 2023 to: Jan 03 2023)
```

`list p/high`

Expected outcome:

```
Certainly. Here are your tasks of high priority!
2. [D][][High] assignment (by: Jan 01 2023)
```

### `mark` - Mark done

Marks specified task as done.
#### Argument:
- index (integer)

Example of usage: 

`mark 1`

Expected outcome:

```
Splendid! You completed a task!
    [T][X][Mid] read book
```

### `unmark` - Mark undone

Marks specified task as undone.
#### Argument:
- index (integer)

Example of usage: 

`unmark 1`

Expected outcome:

```
This is now undone. Let that not happen again.
    [T][][Mid] read book
```

### `find` - Find task

Find all matching tasks based on keyword
#### Argument:
- keyword (String)

Example of usage: 

`find m`

Expected outcome:

```
Certainly. Here are the matching tasks: 
1. [D][][High] assignment (by: Jan 01 2023)
2. [E][][Low] camp (from: Jan 01 2023 to: Jan 03 2023)
```

### `delete` - Delete task

Deletes specified task.
#### Argument:
- index (integer)

Example of usage: 

`delete 2`

Expected outcome:

```
The selected task is removed from list: 
    [D][][High] assignment (by: Jan 01 2023)
You now have 2 tasks!
```
