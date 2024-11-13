//File: Model.Adt.MyIStack.java
package Model.Adt;
import Exceptions.DataStructureExceptions;
public interface MyIStack <T> {
    T pop() throws DataStructureExceptions;
    void push(T element) ;
    boolean empty();
    String toString();
}
