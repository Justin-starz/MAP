//File: Model.Statements.PrintStatement.java
package Model.Statements;
import Exceptions.StatementException;
import Exceptions.ExpressionException;
import Model.Adt.MyIDictionary ;
import Model.Adt.MyIList ;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Values.IValue;

public class PrintStatement implements IStatement {
    private IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    public String toString() {
        return "(Print(" + expression.toString() + "))";
    }

    public ProgramState execute(ProgramState currentState) throws StatementException, ExpressionException {
        MyIList<IValue> out = currentState.getOut();
        MyIDictionary<String, IValue> symbolTable = currentState.getSymbolTable();
        // Evaluate the expression and add the result to the output list
        IValue value = this.expression.evaluate(symbolTable);
        out.add(value);
        return currentState;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }
}
