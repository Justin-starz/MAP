// File: Model.Type.BoolType.java
package Model.Type;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class BoolType implements IType {
    public BoolType() {}
    public String toString() {return "Boolean";}

    /**
     * Provides the default value for BoolType, which is false.
     *
     * @return a new BoolValue with default value false
     */
    public IValue defaultValue() {
        return new BoolValue(false);
    }

    public IType deepCopy() {
        return new BoolType();
    }

    public boolean equals(IType other){
        return(other instanceof BoolType);
    }
}
