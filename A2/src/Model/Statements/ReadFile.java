package Model.Statements;
import Exceptions.DataStructureExceptions;
import Exceptions.StatementException;
import Exceptions.ExpressionException;
import Model.Adt.MyIFileTable;
import Model.Adt.MyIDictionary;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

import  java.io.BufferedReader;
import java.io.IOException;


public class ReadFile implements IStatement {
    private final IExpression exp;
    private final String varName;

    public ReadFile (IExpression exp, String varName) {
        this.exp = exp ;
        this.varName = varName;
    }
    /**
     *  Executes the readFile statement by reading an integer from the file and updating
     *  the variable in SymTable.
     *
     * @param currentState the current program state
     * @return the updated program state.
     * @throws StatementException if the variable is not of type IntType or the file cannot
     * be read
     * @throws DataStructureExceptions if the file does not exist in the FileTable
     */
    @Override
    public ProgramState execute (ProgramState currentState) throws StatementException, DataStructureExceptions, ExpressionException{
        MyIDictionary<String, IValue> symbolTable = currentState.getSymbolTable();
        MyIFileTable fileTable = currentState.getFileTable() ;

        // Check that varName is defined in SymTable and is of type IntType
        if (!symbolTable.isDefined(varName)){
            throw new StatementException("Variable not defined in symbol table: " + varName);
        }
        if (!symbolTable.lookup(varName).getType().equals(new IntType())) {
            throw new StatementException("Variable is not of type IntType: "+ varName);
        }
        // Evaluate exp and ensure it is a StringType
        IValue evaluateExp= exp.evaluate(symbolTable);
        if(!evaluateExp.getType().equals(new StringType())) {
            throw new StatementException("Expression is not of type StringType ");
        }
        StringValue fileNameValue = (StringValue) evaluateExp;

        // Retrieve the BufferedReader from the FileTable
        BufferedReader fileReader = fileTable.getFile(fileNameValue);
        if(fileReader== null) {
            throw new DataStructureExceptions("File not found in FileTable: " + fileNameValue) ;
        }
        try {
            String line = fileReader.readLine();
            int intValue = (line == null) ? 0 : Integer.parseInt(line.trim());
            symbolTable.update(varName, new IntValue(intValue));// Update varName with the read value
        } catch (IOException e ) {
            throw new StatementException("Error reading file:" + fileNameValue.getVal());
        } catch (NumberFormatException e) {
            throw new StatementException("File contains non-integer value: " + fileNameValue.getVal());
        }
        return currentState;
    }
    @Override
    public IStatement deepCopy(){
        return new ReadFile(exp.deepCopy(), varName);
    }

    public String toString () {
        return "readFile(" + exp.toString()+ " , " + varName + ")";
    }
}
