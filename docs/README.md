# Duck User Guide

```
 ____            _
|  _ \ _   _____| | __
| | | | | | |  _| |/ /
| |_| | |_| | |_    <
|____/ \__,_|___|_|\_\  🦆
```
A simple chatbot which helps you keep track of your tasks!

## Types of Tasks
### Todo Tasks
The simplest of tasks. <br>
Usage: `todo <taskname>`


### Deadline Tasks
Tasks due by a set deadline. <br>
Usage: `deadline /by <dd/mm/yyyy>`


### Event Tasks &nbsp; 
Tasks happening across the set time period. <br>
Usage: `event /from <dd/mm/yyyy> /to <dd/mm/yyyy>`


## Features
Now that you are familiar with the different types of tasks, here's how you can interact with them.


### 📋`list` - Display all tasks
Displays all tasks along with their relevant information. <br>
Tasks are listed in the order they were added. <br>
Usage: `list`


### 🗑️`delete` - Delete a task
Deletes the selected task from the list. <br>
Run `list` to  verify the index of the task as **deletion is irreversible**. <br>
Usage: `delete <task_index>` <br>
For example: `delete 1`  deletes the first task in the list.


### ✅`mark` - Mark a task as done
Marks the selected task as complete. <br>
Usage: `mark <task_index>` <br>
For example: `mark 1`  marks the first task in the list.


### ❌`unmark` - Unmark a task to undone
Removes the mark from the selected task. <br>
Usage: `unmark <task_index>` <br>
For example: `unmark 1`  unmarks the first task in the list.


### #️⃣`tag` - Tag a task with a short text
Tags the selected task with a word. <br>
Usage: `tag <task_index>:<word>` <br>
For example: `tag 1:IMPORTANT`  tags the first task in the list.


### ❌`untag` - Remove the tag from a task
Removes the selected task's tag. <br>
Usage: `untag <task_index>` <br>
For example: `untag 1`  removes the tag of the first task in the list.


### 🔎`find` - Find matching tasks
Lists all tasks which contain the given keyword. <br>
Usage: `find <keyword>` <br>
For example: `find book`  lists all tasks which names contain "book".


  
## Other functionalities
### 💾Task storage
Task data is continuously updated to data/tasks.txt, which is automatically created within the same folder as the JAR file.