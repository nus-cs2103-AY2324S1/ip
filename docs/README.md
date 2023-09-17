# Cheems
Your all-in-one task manager!
## Highlights
### Better Search with Fuzzy Matching
- When you input a keyword, we will find all tasks with description in high similarity to your input, ranked by relevance. Don't worry if you input slightly wrongly!
  
Example Usage
```shell
# All of the commands below find tasks with "project" in their description
find project
find prrjeco
find projecst
```
- If you have more than one keyword to find, simply input them consecutively, separated by a space. We will find you all tasks you want to find!
  
Example Usage
```shell
# The command below finds tasks with "icecream", "project", and "hackers" in their description
find icecream project hackers
```
## Usage

### Listing all tasks: `list`
Shows a list of all current tasks stored.

Format: `list`

Example:
![img_1.png](img_1.png)

### Find tasks using keywords: `find`
Find all tasks with a corresponding keyword.

Format: `find [keywords separated by one space]`

Example:
![img.png](img.png)
![img_2.png](img_2.png)

### Add todo task: `todo`
Add task of type todo.

Format: `todo [task description]`

Example:
![img_3.png](img_3.png)

### Add deadline task: `deadline`
Add task of type deadline.

Format: `deadline [task description] /by [yyyy-mm-dd]`

Example:
![img_4.png](img_4.png)

### Add event task: `event`
Add task of type event.

Format: `event [task description] /from [yyyy-mm-dd] /to [yyyy-mm-dd]`

Example:
![img_5.png](img_5.png)

### Delete a task: `delete`
Delete the task with corresponding index shown in the list.

Format: `delete [index]`

Example:
![img_6.png](img_6.png)