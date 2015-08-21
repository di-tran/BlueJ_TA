--JUnitRunner README--

This is an complex prototype version of a JUnit runner. This version supports the running of
a test without locking the calling thread as well as supports the stopping of test mid run.
This is done via a new process to execute the code with and a dedicated thread to watch over
the process. This version also has a custom made TestListener class for the easy notification 
of test results when complete. 


The main classes to use in your proggram are in the JUnitRunner folder:
	TestListener, JUnitRunner

To use:
Create a class that implements the TestListener class and then create a new JUnitRunner and
add the TestListener to the JUnitRunner via JUnitRunner.addTestListener(..).
then simply call run with your test and itll return either a running msg or the compile error.
When the test is completed the class that implemented the TestListener will be called by an internal 
thread with the results via TestListener.testCompleted(..) to deliver the results.

For an example of this in action see the JUnitRunnerDemo class.

To run the JUnitRunnerDemo call the run method of the build file using ant. 
