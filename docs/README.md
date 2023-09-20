# User Guide

## SeeWhyAre Bot - The reason why you are seeing.
SeeWhyAre is a personal assistant chatbot that assists you to **keep track of your tasks**. SeeWhyAre currently supports three types of tasks: 
1. Todo
2. Deadline
3. Event

You can manage your tasks seamlessly through **Command Line Interface(CLI)**, such as *Add* a task, *Delete* a task, *Find* a task with a given keyword, *View* your tasks at a given date, and so on.

## Features 

### Todo

Adds a todo to your list of tasks.
You have to provide a description of your `todo` task.
SeeWhyAre will complain to you if you do not provide a task description.

#### Usage

### `todo <taskDescription>` - Adds a todo task.

Adds a todo task to your list of tasks with the given task description.

Example of usage: 

`todo Buy 2 dozens of eggs and 0.5kg of Red Bass fish for dinner.`

Expected outcome:

SeeWhyAre will show you the task it has successfully added. 
It will also tell you your current number of tasks in the list.

```
Got it. I've added this task:
 [T] Buy 2 dozens of eggs and 0.5kg of Red Bass fish for dinner.
Now you have 1 task(s) in the list.
```


### `deadline`

Adds a deadline to your list of tasks.
You have to provide a description of your `deadline` task.
Also, you have to provide a deadline date in `YYYY-MM-DD` format.

SeeWhyAre will complain to you if:
- You do not provide a deadline task description.
- You do not provide a deadline date
- You provided the wrong date format

#### Usage

### `deadline <taskDescription> /by <deadlineDate>` - Adds a deadline task.

Adds a deadline task to your list of tasks.

Example of usage:

`deadline CS2103T ASSIGNMENT /by 2023-09-22`

Expected outcome:

SeeWhyAre will show you the task it has successfully added.
It will also tell you your current number of tasks in the list.

```
Got it. I've added this task:
 [D] CS2103T ASSIGNMENT (by: Sep 22 2023)
Now you have 2 task(s) in the list.
```


### `event`

Adds an event to your list of tasks.
You have to provide a description of your `event` task.
Also, you have to provide a start date and end date, both in `YYYY-MM-DD` format.

SeeWhyAre will complain to you if:
- You do not provide an event task description.
- You do not provide an event start and end date
- You provided the wrong date format

#### Usage

### `event <taskDescription> /from <startDate> /to <endDate>` - Adds an event task.

Adds an event task to your list of tasks.

Example of usage:

`event NUS Chinese Orchestra Open House /from 2023-11-01 /to 2023-11-03`

Expected outcome:

SeeWhyAre will show you the task it has successfully added.
It will also tell you your current number of tasks in the list.

```
Got it. I've added this task:
 [E] NUS Chinese Orchestra Open House (from: Nov 01 2023 to: Nov 03 2023)
Now you have 3 task(s) in the list.
```


### `list`
Shows your list of saved tasks.

#### Usage

### `list` - Lists down all saved tasks.

Lists down all saved tasks, including the tasks added when the program is running and the tasks previously saved.

Example of usage (Assuming you added the three example tasks sequentially):
`list`

Expected outcome:
```
You have 3 tasks now. Here is your task list:
    1. [T] Buy 2 dozens of eggs and 0.5kg of Red Bass fish for dinner.
    2. [D] CS2103T ASSIGNMENT (by: Sep 22 2023)
    3. [E] NUS Chinese Orchestra Open House (from: Nov 01 2023 to: Nov 03 2023)
```

If you do not have any tasks at the point of calling this `list` command, SeeWhyAre simply responds by saying there is no tasks now.

Example usage (No tasks):
`list`

Expected outcome:
```
No tasks for now!
```


### `mark`
When given a valid task index, marks the corresponding task as done.

#### Usage

### `mark <taskIndex>` - marks the task as done.

When given a task index, mark that task as done.
SeeWhyAre will complain to you if you provide an index that is out of the range of your current list of tasks.

Example of usage (Assuming you added the three example tasks sequentially):
`mark 1`

Expected outcome:
```
Nice! I've marked this Task as done:
    [T] Buy 2 dozens of eggs and 0.5kg of Red Bass fish for dinner.
```

Example of usage (Invalid task index, Note: This example is given with a list of 3 tasks):
`mark 99999`

Expected outcome:
```
Invalid Index of Task. You currently have 3 Task(s)
```


### `unmark`
When given a valid task index, marks the corresponding task as NOT done yet.

#### Usage

### `unmark <taskIndex>` - marks the task as NOT done yet.

When given a task index, mark that task as NOT done yet.
SeeWhyAre will complain to you if you provide an index that is out of the range of your current list of tasks.

Example of usage (Assuming you added the three example tasks sequentially):
`unmark 1`

Expected outcome:
```
Ok. I've marked this Task as NOT done yet:
    [T] Buy 2 dozens of eggs and 0.5kg of Red Bass fish for dinner.
```

Example of usage (Invalid task index, Note: This example is given with a list of 3 tasks):
`mark 99999`

Expected outcome:
```
Invalid Index of Task. You currently have 3 Task(s)
```


### `delete`
Deletes a task, when given a valid task index.

#### Usage

### `delete <taskIndex>` - delete the task at that task index.

When given a task index, delete that task.

> [!WARNING]
> SeeWhyAre will complain to you if:
> - You did not provide a task index.
> - You provide an index that is out of the range of your current list of tasks.

> [!NOTE] 
> The list of tasks will update their indices accordingly. 
For example, if there are 10 tasks originally, and `delete 7` is executed:
then task 8, 9, 10 will become task 7, 8, 9 in the updated list of tasks.

Example of usage (Assuming you added the three example tasks sequentially):

`delete 1`

Expected outcome:
```
Noted. I've removed this task:
    [T] Buy 2 dozens of eggs and 0.5kg of Red Bass fish for dinner.
Now you have 2 task(s) in the list.
```

Example of usage (Invalid task index, Note: This example is given with a list of 3 tasks):

`delete 99999`

Expected outcome:
```
OOPS!!! The task index is invalid.
You currently have 3 task(s).
```

Example of usage (Invalid input after `delete`):

`delete wrongInput`

Expected outcome:
```
OOPS!!! Please enter the index after 'delete' command.
For example: delete 5
This will remove Task 5 from your Task List, assuming you have at least 5 tasks.
```


### `find`
Finds the task(s) with the given keyword.

#### Usage

### `find <keyword>` - finds tasks.

Lists out all the tasks whose description contains the specified keyword.
If there are no tasks in your list, SeeWhyAre simply tells you that.

Example usage:
```
find NUS
```

Expected outcome:
```
Here are your tasks that contains 'NUS':
    [E] NUS Chinese Orchestra Open House (from: Nov 01 2023 to: Nov 03 2023)
```

Example of usage (list does not contain given keyword):
```
find asdfghj
```

Expected outcome:
```
Hm there are no matching tasks with 'asdfghj'. Try with another keyword.
```

Example usage (No tasks in yout list):
```
find noTasksYet
```

Expected outcome:
SeeWhyAre will tell you there is no tasks to begin with.
```
You currently have no tasks so I can't find any matching tasks :/.
```


### `view`
Shows you the list of tasks that is occurring on a particular day, including event tasks ranging across multiple days.
Essentially, this lets you view your schedule.

#### Usage

### `view <dateRequested>` - views tasks on the date requested.

Lists out all the tasks that happens on the specified date.
This includes:
- deadline tasks that has deadline date set on `dateRequested`
- event tasks that has the range of dates where `dateRequested` falls in it.

Example usage:
```
view 2023-09-22
```

Expected outcome:
```
Here are your scheduled tasks happening on Sep 22 2023:
    [D] CS2103T ASSIGNMENT (by: Sep 22 2023)
```

Example Usage (dateRequested falls in between the range of dates for events):
```
view 2023-11-02
```
Expected Outcome:
```
Here are your scheduled tasks happening on Nov 02 2023:
    [E] NUS Chinese Orchestra Open House (from: Nov 01 2023 to: Nov 03 2023)
```


### `bye`
Shuts down the application.

#### Usage

### `bye` - Showing farewell message.

Shuts down the application.
All the tasks and their completion status are still saved.
There will be a 2-second delay before SeeWhyAre Application automatically closes.

Example of usage:
`bye`

Expected outcome:
```
____________________________________________________________
You are closing the SeeWhyAre chat bot.
Bye bye. Please use me again soon!
____________________________________________________________
```

## Command Summary 

- `todo` `<taskDescription>` Creates a todo task with the given task description
- `deadline` `<taskDescription>` `/by` `<deadlineDate` Creates a deadline task with the given task description and deadline date.
- `event` `<taskDescription>` `/from` `<startDate>` `/to` `<endDate>` Creates an event task with the diven task description, start date, and end date.
- `list` Lists all tasks from your current list of tasks.
- `find` `<keyword>` Finds the list of tasks that contains the keyword.
- `view` `<dateRequested>` Finds the list of tasks that are occuring on the date requested.
- `delete` `<taskIndex>` Deletes the corresponding task from a given index.
- `mark` `<taskIndex>` Marks the corresponding task as done.
- `unmark` `<taskIndex>` Marks the corresponding task as NOT done yet.
- `bye` Closes SeeWhyAre application.

> [!NOTE]
> Commands are not case-sensitive: keying in 'bye', 'bYE', 'ByE' are all calling the same `bye` command.
