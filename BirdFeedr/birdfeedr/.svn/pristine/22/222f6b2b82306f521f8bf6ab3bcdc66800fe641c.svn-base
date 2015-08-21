rem This file will install the JAR in Windows.
rem Assuming the classes have been compiled in BlueJ.

rem Construct class path with the JARs.
set CP=".
for /R ./+libs %%a in (*.jar) do call :build %%~na
set CP=%CP%"
echo %CP%

pushd C:\Program Files\Java\jdk1.7*
set JDKBIN=%CD%\bin
set PATH=%JDKBIN%;%PATH%
popd

javac -cp %cp% *.java Runner\*.java
IF %ERRORLEVEL% NEQ 0 GOTO :EOF



rem Set local variables.
set outJar=%CD%\CodingPractice.jar
set tempMFFile=Manifest.mf
set mainClass=Main.class
set libPath=+libs
set extPath=%USERPROFILE%\bluej\extensions

rem Create manifest.
echo Main-Class: Main> %tempMFFile%
set CP=
for /R ./+libs %%a in (*.jar) do call :build2 %%~na
echo Class-Path: %CP%>> %tempMFFile%

rem Make jar
set exFileList=exercises.lst
del "%exFileList%"
for /R ..\Exercises\ %%a in (*.xml) do echo /Exercises/%%~nxa>> "%exFileList%"
jar -cfm  "%outJar%" %tempMFFile% *.class *.fxml Runner\*.class "%exFileList%"
pushd ..\
rem xcopy /D /E /C /R /I /K /Y  BlueJCodingBat_Resources "%USERPROFILE%\bluej\BlueJCodingBat_Resources"
jar -uf  "%outJar%" Exercises\*
popd

rem Remove manifest.
del %tempMFFile%
rem del "%exFileList%"

rem Copy the jars into the extensions folder.
if not exist "%extPath%" mkdir "%extPath%"
copy "%outJar%" "%extPath%"
rem XXX don't copy bluejext.jar
for /R ./+libs %%a in (*.jar) do copy "%%a" "%extPath%"


"C:\Program Files (x86)\BlueJ\BlueJ.exe"

rem Exit.
goto :EOF

rem Concatenates the path.
:build
set CP=%CP%;+libs\%*.jar
goto :EOF
:build2
set CP=%CP% %*.jar
goto :EOF
:build2
set CP=%CP% %*.jar
goto :EOF