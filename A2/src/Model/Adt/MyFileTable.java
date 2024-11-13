package Model.Adt;
import Exceptions.DataStructureExceptions;
import Model.Values.StringValue;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class MyFileTable implements MyIFileTable{
    private final Map<StringValue, BufferedReader> fileTable;

    public MyFileTable() {
        fileTable = new HashMap<>();
    }

    public void addFile(StringValue fileName, BufferedReader fileDescriptor) throws DataStructureExceptions {
        if (fileTable.containsKey(fileName)) {
            throw new DataStructureExceptions("File is already open: " + fileName);
        }
        fileTable.put(fileName, fileDescriptor);
    }

    public BufferedReader getFile(StringValue fileName) throws DataStructureExceptions {
        BufferedReader fileDescriptor = fileTable.get(fileName);
        if (fileDescriptor == null) {
            throw new DataStructureExceptions("File not found in FileTable " + fileName);
        }
        return fileDescriptor;
    }

    public void removeFile(StringValue fileName) throws DataStructureExceptions{
        BufferedReader fileDescriptor = fileTable.get(fileName) ;
        if (fileDescriptor == null) {
            throw new DataStructureExceptions("File not found in FileTable: " + fileName);
        }
        try {
            fileDescriptor.close(); // Close the file before removing it
        } catch (IOException e ) {
            throw new DataStructureExceptions("Error closing file: " + e.getMessage());
        }
        fileTable.remove(fileName) ;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("FileTable:\n");
        for (Map.Entry<StringValue, BufferedReader> entry : fileTable.entrySet()){
            result.append("Filename: ").append(entry.getKey().toString()).append("\n");
        }
        return result.toString();
    }
}
