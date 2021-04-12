package local.tin.tests.fractions.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import local.tin.tests.fractions.api.Fraction;
import local.tin.tests.fractions.api.FractionException;
import local.tin.tests.fractions.api.StreamUtils;

/**
 *
 * @author benito.darder
 */
public class FileUtils {

    private static FileUtils instance;

    private FileUtils() {
    }

    public static FileUtils getInstance() {
        if (instance == null) {
            instance = new FileUtils();
        }
        return instance;
    }

    public List<Fraction> readFromFile(String filePath) throws FractionException {
        try {
            InputStream inputStream = new FileInputStream(filePath);
            return StreamUtils.getInstance().readFractions(inputStream);
            
        } catch (FileNotFoundException ex) {
            throw new FractionException(ex);
        }

    }
}
