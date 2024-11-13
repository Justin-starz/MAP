package Model.Statements;

import Exceptions.DataStructureExceptions;
import Exceptions.StatementException;
import Model.Adt.MyIFileTable;
import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement {
    private final IExpression exp;

    public CloseRFile(IExpression exp) {
        this.exp = exp;
    }

    /**
     * Executes the closeRFile statement by closing and removing the file from the FileTable.
     *
     * @param currentState the current program state
     * @return the updated program state
     * @throws StatementException if the expression does not evaluate to a StringType or the file cannot be closed
     * @throws DataStructureExceptions if the file is not found in the FileTable
     */
    @Override
    public ProgramState execute(ProgramState currentState) throws StatementException, DataStructureExceptions {
        MyIFileTable fileTable = currentState.getFileTable();

        // Evaluate exp and ensure it is a StringType
        IValue evaluatedExp = exp.evaluate(currentState.getSymbolTable());
        if (!evaluatedExp.getType().equals(new StringType())) {
            throw new StatementException("Expression is not of type StringType");
        }

        StringValue fileNameValue = (StringValue) evaluatedExp;

        // Retrieve the BufferedReader for the file
        BufferedReader fileReader = fileTable.getFile(fileNameValue);
        if (fileReader == null) {
            throw new DataStructureExceptions("File not found in FileTable: " + fileNameValue.getVal());
        }

        // Attempt to close the file and remove it from the FileTable
        try {
            fileReader.close();
            fileTable.removeFile(fileNameValue); // Remove from FileTable after closing
        } catch (IOException e) {
            throw new StatementException("Error closing file: " + fileNameValue.getVal());
        }

        return currentState;
    }

    @Override
    public IStatement deepCopy() {
        return new CloseRFile(exp.deepCopy());
    }

    public String toString() {
        return "closeRFile(" + exp.toString() + ")";
    }
}
