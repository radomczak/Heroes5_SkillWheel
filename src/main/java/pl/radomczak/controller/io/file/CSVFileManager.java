package pl.radomczak.controller.io.file;

import pl.radomczak.model.Wheel;
import pl.radomczak.model.exception.DataImportException;

public class CSVFileManager implements FileManager{

    @Override
    public Wheel importWheel() throws DataImportException {
        throw new DataImportException("Not ready yet");
    }

    @Override
    public void exportWheel(Wheel wheel) {

    }
}
