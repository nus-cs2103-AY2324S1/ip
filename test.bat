@ECHO OFF



@REM Clear any previous build files, remake bin\
del /S /Q .\bin\ > nul
rmdir /S /Q .\bin\
mkdir .\bin\

@REM Clear any previous data files, remake data\
del /S /Q .\data\ > nul
rmdir /S /Q .\data\
mkdir .\data\

@REM Compile
javac -cp .\src\main\java\;.\lib\* -Xlint:none -d .\bin\ .\src\main\java\com\cloud\chatbot\*.java
IF ERRORLEVEL 1 (
    echo:
    echo ^>^>^> javac failed
    exit /B 1
)

@REM Clear previous output
del .\tests\ACTUAL.txt

@REM Test main class, generate output
java -cp .\bin\;.\lib\* com.cloud.chatbot.Cloud < .\tests\input.txt > .\tests\ACTUAL.txt

@REM Compare output with expected
FC .\tests\ACTUAL.txt .\tests\EXPECTED.txt
