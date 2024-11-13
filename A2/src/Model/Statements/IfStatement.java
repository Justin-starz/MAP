//File: Model.Statements.IfStatement.java
package Model.Statements;
import Exceptions.ExpressionException;
import Exceptions.StatementException;
import Model.Adt.MyIDictionary;
import Model.Adt.MyIStack;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class IfStatement implements IStatement{
    private IExpression expression;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement){
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }
    public String toString (){
        return "(If " + expression.toString() + ") Then " + thenStatement.toString() +"Else (" + this.elseStatement.toString() +")";
    }

    @Override
    public ProgramState execute(ProgramState currentState) throws StatementException , ExpressionException{
        MyIDictionary<String, IValue> symbolTable = currentState.getSymbolTable();
        MyIStack<IStatement> exeStack = currentState.getExeStack();
        IValue condition = this.expression.evaluate(symbolTable);
        IType conditionType = condition.getType() ;
        if (!conditionType.equals(new BoolType()))
            throw new StatementException("Expression is not boolean");
        else{
            if (condition.equals(new BoolValue()))
                exeStack.push(this.elseStatement);
            else
                exeStack.push(this.thenStatement);
        }
        return currentState;
    }
    @Override
    public IStatement deepCopy() {
        return new IfStatement(this.expression.deepCopy(), this.thenStatement.deepCopy(), this.elseStatement.deepCopy());

    }
}
