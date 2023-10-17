# Fong Task Manager User Guide

>**_An easy to use and reliable task manager bot!_**

- [Features](#features)
  - [Add New Tasks](#1-adding-new-tasks)
  - [View All Tasks](#2-viewing-all-tasks)
  - [Manage Tasks](#3-managing-your-tasks)
  - [Find Tasks](#4-finding-specific-tasks)
- [Command Summary](#command-summary)
- [Get Started](#feel-free-to-use-fong-task-manager-)

## Features 

### 1. Adding New Tasks
```
todo read book
deadline return book /by 2023-06-06
event project meeting /from Aug 6th 2pm /to 4pm
recur run /every week 
```

### 2. Viewing All Tasks
```
list
```

### 3. Managing Your Tasks
```
mark 3   // To mark task 3 as completed
unmark 3 // To unmark previously marked task 3
delete 3 // To delete task 3
```

### 4. Finding Specific Tasks
```
find <keyword> // To find all tasks with the specified keyword
```

## Command Summary

| Command  | Description                     | Format                                 |
|----------|---------------------------------|----------------------------------------|
| todo     | Add a new todo task             | `todo <task>`                          |
| deadline | Add a new deadline task         | `deadline <task> /by <deadline>`       |
| event    | Add a new event task            | `event <task> /from <start> /to <end>` |
| recur    | Add a new recurring task        | `recur <task> /every <recurrence>`     |
| list     | View all tasks                  | `list`                                 |
| mark     | Mark a task                     | `mark <index>`                         |
| unmark   | Unmark a previously marked task | `unmark <index>`                       |
| delete   | Delete a task                   | `delete <index>`                       |
| find     | Find tasks by keyword           | `find <keyword>`                       |
| bye      | Exit the program                | `bye`                                  |

## Feel free to use Fong Task Manager!
1. Download the jar executable file in the release
2. Run the command: `java -jar duke.jar`
