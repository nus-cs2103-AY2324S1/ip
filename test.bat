@ECHO OFF



@REM Clear any previous data files, remake data\
del /S /Q .\data\ > nul
rmdir /S /Q .\data\
mkdir .\data\

@REM Gradle default clean & build
call gradlew
if errorlevel 1 (
    echo:
    echo ^>^>^> Gradle build failed
    exit /B 1
)

@REM Clear previous output
del .\tests\ACTUAL.txt

@REM Test using JAR, generate output
java -jar .\build\libs\cloud.jar < .\tests\input.txt > .\tests\ACTUAL.txt

@REM Compare output with expected
FC .\tests\ACTUAL.txt .\tests\EXPECTED.txt
