package math;

import io.FileIO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayOperationsTest {


    @Test
    public void testFindPrimesInFileWithEmptyFile() throws IllegalArgumentException{
        String emptyFilePath = "src/test/resources/empty_file.txt";
        FileIO fileIO = new FileIO();
        MyMath myMath = new MyMath();
        ArrayOperations arrayOps = new ArrayOperations();

        try {
            int[] actualResult = arrayOps.findPrimesInFile(fileIO, emptyFilePath, myMath);
            assertArrayEquals(new int[]{}, actualResult);
        } catch (IllegalArgumentException e) {
            assertEquals("Given file is empty", e.getMessage());
        }
    }



    @Test
    public void testFindPrimesInFileWithInvalidEntries() {
        String invalidFilePath = "src/test/resources/InvalidPrime.txt";

        FileIO fileIO = new FileIO();
        MyMath myMath = new MyMath();
        ArrayOperations arrayOps = new ArrayOperations();

        // Read primes from file and filter out numbers below 2
        int[] actualResult = arrayOps.findPrimesInFile(fileIO, invalidFilePath, myMath);

        assertArrayEquals(new int[]{}, actualResult);
    }

    @Test
    public void testFindPrimesInFileWithValidPrimes() {
        String validFilePath = "src/test/resources/prime.txt";

        FileIO fileIO = new FileIO();
        MyMath myMath = new MyMath();
        ArrayOperations arrayOps = new ArrayOperations();

        // Read primes from file and filter out numbers below 2
        int[] actualResult = arrayOps.findPrimesInFile(fileIO, validFilePath, myMath);

        int[] expectedResult = {3, 2, 3}; // Considering only valid prime numbers

        assertArrayEquals(expectedResult, actualResult);
    }

}
