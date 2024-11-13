// File: Controller.Controller.java
package Controller;

import Exceptions.ControllerException;
import Exceptions.DataStructureExceptions;
import Exceptions.RepositoryException;
import Exceptions.StatementException;
import Exceptions.ExpressionException;
import Model.Adt.*;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.ProgramState;
import Model.Statements.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;
import Repository.IRepository;

public class Controller {
    private IRepository repo;
    private int displayFlag;

    public Controller(IRepository repo, int displayFlag) {
        this.repo = repo;
        this.displayFlag = displayFlag;
    }

    // changed to void until the program state returned is used
    public void oneStep(ProgramState state) throws ControllerException, DataStructureExceptions, StatementException {
        MyIStack<IStatement> exeStack = state.getExeStack();
        if (exeStack.empty())
            throw new ControllerException("ExeStack is empty");
        //Execute the top statement from the stack
        IStatement statement = exeStack.pop();
        statement.execute(state);
    }

    /**
     * Executes the entire program by calling oneStep in a loop.
     * Logs the program state to a file after each step as required by Lab5 Task 1.3.
     *
     * @throws RepositoryException if an error occurs in accessing or logging the repository
     * @throws ControllerException if an error occurs in program execution
     * @throws DataStructureExceptions if a data Structure error occurs
     * @throws StatementException if an error occurs in executing a statement
     */
    public void allStep() throws RepositoryException, ControllerException, DataStructureExceptions, StatementException {
        ProgramState state = repo.getProgramState();

        // Initial logging of program state before execution begins
        repo.logProgramStateExec();

//        // Run until the execution stack is empty
//        if (displayFlag == 1) {
//            displayCurrentState();
//        }

        // Run until the execution stack is empty
        while (!state.getExeStack().empty()) {
            try {
                this.oneStep(state); // Execute one step

                // Log the program state after each step
                repo.logProgramStateExec();

                // Display state if displayFlag is set
                if (displayFlag == 1) displayCurrentState(); // Display state if needed
            } catch (StatementException | ExpressionException | DataStructureExceptions e) {
                System.out.println("Error during execution: " + e.getMessage());
                break; // Stop execution if an error occurs
            }
        }
    }

    // Display the current program state
    public void displayCurrentState() {
        if (displayFlag == 1) {
            System.out.println(this.repo.getProgramState().toString());
        }
    }

    // Example programs for testing
    public IStatement makeStatement1() {
        return new CompStatement(new VarDeclStatement("v", new IntType()),
                new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
    }

    public IStatement makeStatement2() {
        return new CompStatement(new VarDeclStatement("a", new IntType()),
                new CompStatement(new VarDeclStatement("b", new IntType()),
                        new CompStatement(new AssignStatement("a", new ArithmeticExpression('+',
                                new ValueExpression(new IntValue(2)), new ArithmeticExpression('*',
                                new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompStatement(new AssignStatement("b", new ArithmeticExpression('+',
                                        new VariableExpression("a"), new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("b"))))));
    }

    public IStatement makeStatement3() {
        return new CompStatement(new VarDeclStatement("a", new BoolType()),
                new CompStatement(new VarDeclStatement("v", new IntType()),
                        new CompStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                        new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
    }

    public IStatement makeStatement4() {
        return new CompStatement(
                new VarDeclStatement("x", new IntType()),
                new AssignStatement("x", new ValueExpression(new BoolValue(true)))
        );
    }





    public void makeProgramState(IStatement statement) {
        MyIStack<IStatement> exeStack = new MyStack<>();
        MyIDictionary<String, IValue> symbolTable = new MyDictionary<>();
        MyIList<IValue> out = new MyList<>();
        ProgramState state = new ProgramState(exeStack, symbolTable, out, statement);
        this.repo.setProgramState(state);
    }



    // Loads a program based on user input

    public void hardCodedStatements(String option) throws ControllerException {
        switch (option) {
            case "1" -> this.makeProgramState(makeStatement1());
            case "2" -> this.makeProgramState(makeStatement2());
            case "3" -> this.makeProgramState(makeStatement3());
            case "4" -> this.makeProgramState(makeStatement4());
            default -> throw new ControllerException("Invalid program option selected.");
        }
    }
}



