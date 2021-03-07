package local.tin.tests.fractions.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
