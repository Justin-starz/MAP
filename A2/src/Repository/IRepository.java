//File: Repository.IRepository.java
package Repository;

import Exceptions.RepositoryException;
import Model.ProgramState;

/**
 * IRepository Interface
 *
 * Lab5 Task 1.1 Requirement:
 * Add a method logProgramStateExec() to the repository interface that logs
 * the content of the ProgramState to a text file.
 *
 * This method throws RepositoryException if an error occurs during logging.
 *
 * Methods :
 * - ProgramState getProgramState(): Retrieves the current program state.
 * - void setProgramState(ProgramState programState): Sets the program state.
 * - void logProgramStateExec(): Logs the current state of the program to a file.
 */




public interface IRepository {
    ProgramState getProgramState() throws RepositoryException;
    void setProgramState(ProgramState programState);
    void logProgramStateExec() throws  RepositoryException;

}