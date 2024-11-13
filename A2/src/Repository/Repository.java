package Repository;
import Exceptions.RepositoryException;
import Model.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository implements IRepository{
    private List<ProgramState> programStates;
    private String logFilePath; // Path to log file
    /**
     * Constructor initializes repository and prompts for log file path .
     * Reads the log file path from the console to initialize logFilePath.
     */
    public Repository() {
        programStates = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path for the log file: ");
        this.logFilePath = scanner.nextLine();
    }

    public Repository(List<ProgramState> programStates) {
        this.programStates = programStates;
    }

    public Repository(ProgramState programState) {
        programStates = new ArrayList<>();
        programStates.add(programState);
    }

    public ProgramState getProgramState() throws RepositoryException {
        if (!programStates.isEmpty()) {
            return this.programStates.getFirst();
        }
        throw new RepositoryException("Repository is empty");
    }

    public void setProgramState(ProgramState programState) {
        if (this.programStates.isEmpty()) {
            this.programStates.add(programState);
        }
        else {
            this.programStates.set(0, programState);
        }
    }
    /**
     * Logs the current Program State (ProgramState) to a text file.
     * @throws RepositoryException if an error occurs during file writing.
     */
    public void logProgramStateExec() throws RepositoryException {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            ProgramState currentState = this.getProgramState();

            // Log ExeStack
            logFile.println("Out: ");
            logFile.println(currentState.getExeStack().toString());

            // Log SymTable
            logFile.println("SymTable: ");
            logFile.println(currentState.getSymbolTable().toString());

            // Log Out
            logFile.println("Out: ");
            logFile.println(currentState.getOut().toString());

            // Placeholder for FileTable (to be implemented soon)
            logFile.println("FileTable: ");
            logFile.println("To be implemented in Task 2");

            logFile.println("---------------------------------------------------------------------");
        } catch (IOException e) {
            throw new RepositoryException("Error writing to log file, Check Again " + e.getMessage());
        }
    }
}