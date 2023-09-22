# Duck User Guide

```
 ____            _
|  _ \ _   _____| | __
| | | | | | |  _| |/ /
| |_| | |_| | |_    <
|____/ \__,_|___|_|\_\  🦆
```
<p>A simple chatbot which helps you keep track of your tasks!</p>

## Types of Tasks
### Todo Tasks
<p>The simplest of tasks. <br>
Usage: `todo <taskname>`
</p>

### Deadline Tasks
<p>Tasks due by a set deadline. <br>
Usage: `deadline /by <dd/mm/yyyy>`
</p>

### Event Tasks &nbsp; 
<p>Tasks happening across the set time period. <br>
Usage: `event /from <dd/mm/yyyy> /to <dd/mm/yyyy>`
</p>

## Features
<p>Now that you are familiar with the different types of tasks, here's how you can interact with them.
</p>

### 📋`list` - Display all tasks
<p>Displays all tasks along with their relevant information. <br>
Tasks are listed in the order they were added. <br>
Usage: `list`
</p>

### 🗑️`delete` - Delete a task
<p>Deletes the selected task from the list. <br>
Run `list` to  verify the index of the task as **deletion is irreversible**. <br>
Usage: `delete <task_index>` <br>
For example: `delete 1`  deletes the first task in the list.
</p>

### ✅`mark` - Mark a task as done
<p>Marks the selected task as complete. <br>
Usage: `mark <task_index>` <br>
For example: `mark 1`  marks the first task in the list.
</p>

### ❌`unmark` - Unmark a task to undone
<p>Removes the mark from the selected task. <br>
Usage: `unmark <task_index>` <br>
For example: `unmark 1`  unmarks the first task in the list.
</p>

### #️⃣`tag` - Tag a task with a short text
<p>Tags the selected task with a word. <br>
Usage: `tag <task_index>:<word>` <br>
For example: `tag 1:IMPORTANT`  tags the first task in the list.
</p>

### ❌`untag` - Remove the tag from a task
<p>Removes the selected task's tag. <br>
Usage: `untag <task_index>` <br>
For example: `untag 1`  removes the tag of the first task in the list.
</p>

### 🔎`find` - Find matching tasks
<p>Lists all tasks which contain the given keyword. <br>
Usage: `find <keyword>` <br>
For example: `find book`  lists all tasks which names contain "book".
</p>

  
## Other functionalities
### 💾Task storage
Task data is continuously updated to data/tasks.txt, which is automatically created within the same folder as the JAR file.