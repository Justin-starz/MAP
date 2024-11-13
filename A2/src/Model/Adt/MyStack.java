//File: Model.Adt.MyStack.java
package Model.Adt;
import Exceptions.DataStructureExceptions;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;
    public MyStack() {
        this.stack = new Stack<>();
    }
    public MyStack(Stack<T> stack) {
        this.stack = stack;
    }
    public Stack<T> getStack() {
        return this.stack;
    }

    public T pop() throws DataStructureExceptions {
        if (this.stack.isEmpty()) {
            throw new DataStructureExceptions("Stack is empty");
        }
        return this.stack.pop();
    }
    public void push (T element) {
        this.stack.push(element);
    }
    public boolean empty() {
        return this.stack.empty();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        List<T> reversedElements = this.stack.subList(0, this.stack.size());
        result.append("Execution Stacks[");
        for (int i = reversedElements.size() - 1; i >= 0; i--) {
            result.append(reversedElements.get(i).toString()).append(", ");
        }
        if (result.toString().endsWith(", ")) result.delete(result.length() -2, result.length());
        result.append("]");
        return result.toString();
    }
}
