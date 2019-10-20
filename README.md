
#### Software Needed
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [JAVA 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [JAVA 11 FX](https://gluonhq.com/products/javafx/)

#### Setup
Install IntelliJ.    
  
Make sure to add install Java 11 correctly! Also add it to the path.  
Move Java FX to your documents or something.  
  
Now you need to set Java 11 as your compiler:  
File -> Project Structure -> Project  
Underneath Project SDK Select Java 11  

Next we will need to add JavaFX as a library to the project:  
File -> Project Structure -> Libraries 
click on the '+' then Java.  
Navigate to your Lib in Java FX  
It will open a popup window, click Ok. Now all the errors should go away.  

  
Finally we will get Java FX working.    
Run -> Edit Configurations -> VM Options:  
```git
--module-path /path/to/javafx/javafx-sdk-11.0.2/lib 
--add-modules=javafx.fxml,javafx.controls,javafx.fxml
```
