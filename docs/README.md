# User Guide for Linus

## Features

### Record Your Tasks

Users can add 3 types of tasks,
ToDo tasks that contain a description,
Deadline tasks that contain a description and a deadline,
Event tasks that contain a description, an event start date, and an event end date.

### Manage Your Tasks
Users can also mark the tasks as done, or unmark done tasks. They can also delete unneeded tasks.

### Summarise Your Tasks
Users can list all tasks by the order in which they are added. They can search the tasks by their descriptions. They can also query for the statistics of tasks.

## Usage

### `list` \- List out all your tasks\.

Linus will list out all your tasks that are being tracked by it.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] Do CS2103T IP
2.[D][ ] Submit CS2106 Lab (by: Sep 9 2023)
3.[E][X] Attend Fintech Workshop (from: Nov 10 2023 to: Nov 12 2023)
```

### `find` \- Find a task\.

Linus will find a task using the given description and wil return the list of tasks that has matched or partially matched the description.
`find <description to be searched>`

Example of usage:

`find attend`

Expected outcome:

```
Here are the matching tasks in your list:
2.[E][X] Attend hackathon (from: Aug 20 2023 to: Aug 30 2023)
5.[E][ ] Learn AWS (from: Feb 01 2023 to: Aug 18 2023)
```

### `stats` \- Summarise task statistics\.

Given a duration of n days, Linus will respond with the number of tasks every day for the last n days, filtered by the task type and whether the task is marked as done.
`stats /duration <number of days> /task <taskType> /done`

`/task <taskType>` and `/done` indicate the task type (ToDo / Deadline / Event) and whether the task is done respectively. Both are optional.

Example of usage:

`stats /duration 7 /task todo /done`

Expected outcome:

```
Your tasks for the past 7 days:
1. 2023-09-14: 1
2. 2023-09-15: 3
3. 2023-09-16: 2
4. 2023-09-17: 9
5. 2023-09-18: 6
6. 2023-09-19: 5
7. 2023-09-20: 4
```
### `mark` \- Mark your task as done\.

Linus will mark your task specified by its index(1-indexed) as done.
`mark <index of task to be marked>`

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [T][X] Play Minecraft
```

### `unmark` \- Mark your task as done\.

Linus will mark your task specified by its index(1-indexed) as done.
`unmark <index of task to be unmarked>`

Example of usage:

`unmark 3`

Expected outcome:

```
OK, I've marked this task as not done yet:
    [E][ ] Join hackathon (from: Aug 25 2023 to: Aug 25 2023)
```

### `todo` \- Add a ToDo task\.

Linus will add a ToDo task with the given description.
`todo <description of your TODO task>`

Example of usage:

`todo This is an example ToDo`

Expected outcome:

```
Got it. I've added this task:
    [T][ ] I am adding a TODO
Now you have 4 tasks in the list.
```

### `deadline` \- Add a Deadline task\.

Linus will add a DEADLINE task with the given description and deadline.
`deadline <description> /by <deadline in the format yyyy-mm-dd>`

Example of usage:

`deadline submission /by 2022-06-05`

Expected outcome:

```
Got it. I've added this task:
    [D][ ] Interview (by: May 5 2023)
Now you have 5 tasks in the list.
```

### `event` \- Add an Event task\.

Linus will add an EVENT task with the given description, event start date, and event end date.
`event <description> /from <start date in the format yyyy-mm-dd> /to <end date in the format yyyy-mm-dd>`

Example of usage:

`event attend carnival /from 2023-05-06 /to 2023-05-08`

Expected outcome:

```
Got it. I've added this task:
    [E][ ] Attend gathering (from: May 6 2023 to: May 8 2023)
Now you have 6 tasks in the list.
```

### `delete` \- Delete a task\.

Linus will delete the task specified by its index(1-indexed).
`delete <index of task to be deleted>`

Example of usage:

`delete 2`

Expected outcome:

```
Noted! I've removed this task:
    [D][ ] Submit assignment (by: Aug 1 2023)
Now you have 5 tasks in the list.
```

### `bye` \- Quits the application\.

Linus will quit the application and close the window.

Example of usage:

`bye`

Expected outcome:

Linus will respond with a goodbye message. After 1.5 seconds, the application window will be closed.