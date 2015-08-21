del *.class *.jar
pushd Runner
del *.class
popd
pushd ..\BlueJCodingBat_Resources\Temp\SrcTemp
del *.class
popd

pushd "%USERPROFILE%\bluej\BlueJCodingBat_Resources\Exercises"
del *.class
popd

pushd "%USERPROFILE%\bluej\extensions"
del *.jar ..\bluej-debuglog.txt
popd