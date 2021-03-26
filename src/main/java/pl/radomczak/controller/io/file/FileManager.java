package pl.radomczak.controller.io.file;

import pl.radomczak.model.Wheel;
import pl.radomczak.model.exception.DataImportException;

public interface FileManager {
    Wheel importWheel() throws DataImportException;
    void exportWheel(Wheel wheel);
}
