# Duck User Guide

```
 ____            _
|  _ \ _   _____| | __
| | | | | | |  _| |/ /
| |_| | |_| | |_    <
|____/ \__,_|___|_|\_\
```

## Types of Tasks
  ### Todo Tasks &nbsp; `todo <taskname>`
  The simplest of tasks.
  ### Deadline Tasks &nbsp; `deadline /by <dd/mm/yyyy>`
  Tasks due by a set deadline.
  ### Event Tasks &nbsp; `event /from <dd/mm/yyyy> /to <dd/mm/yyyy>`
  Tasks happening across the set time period. 

## Features
Now that you are familiar with the different types of tasks, here's how you can interact with them.
### 📋`list` - Display all tasks    
All tasks are shown alongside their relevant information.
Tasks are listed in the order they were added.
Usage: `list`
### 🗑️`delete` - Delete a task
The selected task is deleted from the list.
Run `list` to  verify the index of the task as **deletion is irreversible**.
Usage: `delete <task_index>` 
For example: `delete 1`  deletes the first task in the list.
### ✅`mark` - Mark a task as done 
The selected task is marked as completed.
Usage: `mark <task_index>` 
For example: `mark 1`  marks the first task in the list.
### ❌`unmark` - Unmark a task to undone 
The selected task is no longer marked as completed.
Usage: `unmark <task_index>` 
For example: `unmark 1`  unmarks the first task in the list.
### #️⃣`tag` - Tag a task with a short text 
The selected task is no longer marked as completed.
Usage: `tag <task_index>:<tag>` 
For example: `tag 1:IMPORTANT`  tags the first task in the list.
### ❌`untag` - Remove the tag from a task 
The selected task is no longer marked as completed.
Usage: `untag <task_index>`
For example: `untag 1`  removes the tag of the first task in the list.
### 🔎`find` - Find tasks 
The selected task is no longer marked as completed.
Usage: `untag <task_index>`
For example: `untag 1`  removes the tag of the first task in the list.
  
## Other functionalities
### 💾Task storage
Task data is continuously updated to data/tasks.txt, which is automatically created within the same folder as the JAR file.