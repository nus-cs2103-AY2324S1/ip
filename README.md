# Cheems Chatbot
## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Launcher.main()` 
5. You should be able to see a pop-up window showing the Cheems chatbot, starting chating now!

## Exciting Features to Come
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
