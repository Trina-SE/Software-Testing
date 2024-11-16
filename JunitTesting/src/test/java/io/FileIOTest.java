package io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOTest {

    @Test
    public void testReadFileFileDoesNotExist() {
        FileIO fileIO = new FileIO();
        String filepath = "src/test/resources/empty_file.txt";
        assertThrows(IllegalArgumentException.class, () -> fileIO.readFile(filepath));
    }

    @Test
    public void testReadFileEmptyFile()  {
        FileIO fileIO = new FileIO();
        String filepath = "src/test/resources/empty_file.txt";
        assertThrows(IllegalArgumentException.class, () -> fileIO.readFile(filepath));

    }


    @Test
    public void testReadFileValidInput()  {
        FileIO fileIO = new FileIO();
        String filepath = "src/test/resources/grades_valid.txt";
        int[] actualResult = fileIO.readFile(filepath);
        int[] expectedResult = {3,9,0,2,10,9,3,8,0,3};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testReadFileInvalidInput() throws NumberFormatException {
        FileIO fileIO = new FileIO();
        String filepath = "src/test/resources/grades_invalid.txt";
        int[] actualResult = fileIO.readFile(filepath);
        int[] expectedResult = {3,9,2,10,8,0,3};
        assertArrayEquals(expectedResult, actualResult);
    }


}
