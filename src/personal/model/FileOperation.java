package personal.model;

import java.util.List;

public interface FileOperation {
    List<String> readAllLines();

    void saveAllLines(List<String> lines);
    void saveCSV(List<String> lines);
}
