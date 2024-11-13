//File: Model.Type.IType.java
package Model.Type;
import Model.Values.IValue;

/**
 * IType Interface
 *
 * Lab5 Task 2.2 Requirement;
 * Modify the Type interface to include a defaultValue () method that provides
 * a default value for each implementing type
 *
 * Method
 * - boolean equals(IType other) : Checks equality between types.
 * - String toString(): Returns a string representation of the type.
 * - IValue defaultValue(): Returns the default value for the type.
 * - IType deepCopy() : Creates a copy of the type.
 */
public interface IType {
    boolean equals(IType other);
    String toString();
    IValue defaultValue();
    IType deepCopy();
}


