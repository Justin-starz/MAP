//File: Model.Expressions.ValueExpression.java
package Model.Expressions;

import Exceptions.ExpressionException;
import Model.Values.IValue;
import Model.Adt.MyIDictionary;

public class ValueExpression implements IExpression {
    private IValue value;
    public ValueExpression(IValue value) {
        this.value = value;
    }
    public IValue evaluate(MyIDictionary<String, IValue> symbolTable) throws ExpressionException{
        return this.value;
    }
    @Override
    public IExpression deepCopy() {
        return new ValueExpression(this.value.deepCopy());
    }
    public String toString () {
        return this.value.toString();
    }
}

