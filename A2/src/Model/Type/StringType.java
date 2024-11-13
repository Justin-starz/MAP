package Model.Type;
import Model.Values.IValue;
import Model.Values.StringValue;

/**
 * StringType Class
 *
 * Lab5 Task 2.3 Requirement:
 * Implements a new type StringType following the same structure as other types.
 * Provides a default value which is an empty string.
 */

public class StringType implements IType {
    public StringType() {}

    @Override
    public boolean equals(IType other) {
        return other instanceof StringType;
    }
    @Override
    public String toString() {
        return "String";
    }
    /**
     * Provides the default value for StringType, which is an empty string.
     *
     * @return a new StringValue with an empty string
     */
    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }
    @Override
    public IType deepCopy() {
        return new StringType();
    }
}
