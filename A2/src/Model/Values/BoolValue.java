//File: Model.Values.BoolValue.java
package Model.Values;
import Model.Type.BoolType;
import Model.Type.IType;

public class BoolValue implements IValue{
    private boolean value;

    public BoolValue() {
       this.value = false;
     }

    public IValue defaultValue(){
        return new BoolValue(false);
    }
    public boolean getValue() {
        return value;
    }

    public BoolValue(boolean value) {
        this.value = value;
    }
    public IType getType() {
        return new BoolType();
    }
//    public boolean equals(IValue other){
//        IType valueType = other.getType();
//        if(!valueType.equals(this.getType()))
//            return false;
//        return ((BoolValue) other).getValue() == value;
//    }

    /**
     * @ Overrides equals to compare BoolValue object .
     *
     * @param other the object to compare with
     * @return true if a BoolValue and Values are equal, false otherwise.
     */
    @Override
    public boolean equals(IValue other) {
        return other instanceof BoolValue &&
                ((BoolValue) other).getValue() == this.value;
    }


    public String toString() {
        return "Boolean: " + value;
    }

    @Override
    public IValue deepCopy() {
        return new BoolValue(value);
    }

}