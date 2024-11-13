//File: Model.Type.IntType.java
package Model.Type;
import Model.Values.IValue;
import Model.Values.IntValue;

public class IntType implements IType {
    public boolean equals(IType otherType){
        return otherType instanceof IntType;
    }
    public String toString() {return "Integer";}
    /**
     * Provides the default value for IntType, which is 0 .
     * @return a new IntValue with default value 0
     */
    public IValue defaultValue() {
        return new IntValue(0);
    }

    public IType deepCopy() {
        return new IntType();
    }

}