# Roo TaskMaster User Guide

## Features

### Adding task
You can add 3 types of tasks: todo tasks, tasks with deadline, or events.

#### `todo` - Add a todo task
Format: `todo TASK_DETAILS [#TAG]`

Example:

`todo solve leetcode #COMPUTING`

`todo exercise`

Expected outcome:
```
"[T][ ] solve leetcode tags:[#COMPUTING]" added :)
Now got 2 tasks liao T_T
```

#### `deadline` - Add a task with deadline
Format: `deadline TASK_DETAILS /by DD-MM-YYYY hh:mm [#TAG]`

Example:

`deadline Quiz /by 20-09-2023 23:59 #CS2103`

`deadline SEP application /by 24-09-2023 23:59`

Expected outcome:
```
"[D][ ] SEP applcation by: 24 Sep 2023 11:59 PM" added :)
Now got 3 tasks liao T_T
```
 
#### `event` - Add an event
Format: `event TASK_DETAILS /from DD-MM-YYYY hh:mm /to DD-MM-YYYY hh:mm [#TAG]`

Example:

`event SLF /from 17-08-2023 10:00 /to 18-08-2023 17:00 #FREESTUFF`

`event Recess week /from 25-09-2023 00:00 /to 01-10-2023 23:59`

Expected outcome:
```
"[E][ ] Recess week from: 25 Sep 2023 12:00 AM to: 01 Oct 2023 11:59 PM" added :)
Now got 4 tasks liao T_T
```

### Delete task

You can delete tasks one-by-one or delete all tasks in one go.

#### `delete` - delete a task
Format: `delete INDEX`

Expected outcome:
```
Okay!! Task "TASK_DETAILS_FOR_TASK_AT_INDEX" removed :)
You still have AMOUNT_OF_TASKS tasks in the list
```

#### `clear` - delete all tasks
Format: `clear`

Expected outcome:
```
All tasks cleared
```

### Find task

You can find a task using a keyword.

#### `find` - find a task
Format: `find KEYWORD`

- The search is case-sensitive.
- Partial words can be matched e.g. boo can match book 
- All tasks matching the keyword will be returned

Example:

`find SEP`

Expected outcome:
```
Nah, your matching tasks:
1. [D][ ] SEP applcation by: 24 Sep 2023 11:59 PM
```

### List task

You can list all your tasks.

#### `list` - list all tasks
Format: `list`

Expected outcome:
```
Although I dunwan to list... But here is your list:
1. [T][ ] solve leetcode tags:[#COMPUTING]
2. [D][ ] SEP applcation by: 24 Sep 2023 11:59 PM
```

### Mark or Unmark a task

You can mark your task as done or unmark your tasks as not done.

#### `mark` - mark a task as done
Format: `mark INDEX`

Example:
`mark 1`

Expected outcome:
```
Yay! "[T][ ] solve leetcode tags:[#COMPUTING]" done liao!!
```

#### `unmark` - unmark a task as not done
Format: `unmark INDEX`

Example:
`unmark 1`

Expected outcome:
```
Hmm... Why just now simply mark "[T][ ] solve leetcode tags:[#COMPUTING]" as done??
```

### Tag or Untag a task

You can add tags to your task or untag your task.

#### `tag` - add tags to a task
Format: `tag INDEX #TAG [#MORE_TAGS]`

Example:
`tag 1 #DAILY #MUSTDO` 

Expected outcome:
```
Okay, tag added to [T][ ] solve leetcode tags:[#COMPUTING #DAILY #MUSTDO]
```

#### `untag` - untag a task
Format: `untag INDEX`

- All tags for the task will be removed

Example:
`untag 1`

Expected outcome:
```
[T][ ] solve leetcode untagged
```
