package Model.Adt;
import  Exceptions.DataStructureExceptions;
import Model.Values.StringValue;

import java.io.BufferedReader;

public interface MyIFileTable {
    /**
     * Adds a new file entry with the given file name and BufferedReader.
     *
     * @param fileName the name of the file as a StringValue
     * @param fileDescriptor the BufferedReader associated with the file
     * @throws  DataStructureExceptions if the file name is already in the table
     */
    void addFile(StringValue fileName, BufferedReader fileDescriptor) throws DataStructureExceptions;
    /**
     * Retrieves the fileName the name of the file as a StringValue
     *
     * @param fileName the name of the file as a StringValue
     * @return the BufferedReader for the file
     * @throws DataStructureExceptions if the file is not found in the table
     */
    BufferedReader getFile(StringValue fileName) throws DataStructureExceptions;

    /**
     * Removes the entry for the specified file from the table and closes the BufferedReader
     *
     * @param filename the name of the file as a StringValue
     * @throws DataStructureExceptions if the file is not found in the table.
     */
    void removeFile(StringValue filename) throws DataStructureExceptions;
    /**
     * Returns the contents of the FileTable as a string.
     *
     * @return the FileTable contents in string format
     */
    String toString();
}
