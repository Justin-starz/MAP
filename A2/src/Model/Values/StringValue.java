package Model.Values;

import Model.Type.IType;
import Model.Type.StringType;

/**
 * StringValue Class
 *
 * Lab5 Task 2.4 Requirement:
 * Implements a new value class `StringValue` for handling strings.
 * Provides a method `getVal` to retrieve the stored string and
 * an `equals` method to compare values.
 */
public class StringValue implements IValue {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    /**
     * Retrieves the stored string value.
     *
     * @return the string value
     */
    public String getVal() {
        return this.value;
    }


    public IType getType() {
        return new StringType();
    }


    public boolean equals(IValue other) {
        if (!(other instanceof StringValue)) {
            return false;
        }
        return this.value.equals(((StringValue) other).getVal());
    }


    public String toString() {
        return "String: " + this.value;
    }


    public IValue deepCopy() {
        return new StringValue(this.value);
    }
}
