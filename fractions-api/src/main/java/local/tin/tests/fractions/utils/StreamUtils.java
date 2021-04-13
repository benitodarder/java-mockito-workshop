package local.tin.tests.fractions.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import local.tin.tests.fractions.api.Fraction;
import local.tin.tests.fractions.api.FractionException;

/**
 *
 * @author benitodarder
 */
public class StreamUtils {

    public static final int FRACTION_NUMERIC_COMPONENTS = 2;
    public static final String FRACTION_BAR_OBLIQUE = "/";
    public static final String FRACTION_BAR_HORIRZONTAL = "-";
    public static final int EOF = -1;
    public static final int ASCII_CODE_TAB = 9;
    public static final int ASCII_CODE_SPACE = 32;
    public static final int ASCII_CODE_SLASH = 47;
    public static final int ASCII_CODE_CERO = 48;
    public static final int ASCII_CODE_NINE = 57;
    public static final int ASCII_CODE_LINE_FEED = 10;

    private StreamUtils() {
    }

    public static StreamUtils getInstance() {
        return StreamUtilsHolder.INSTANCE;
    }

    private static class StreamUtilsHolder {

        private static final StreamUtils INSTANCE = new StreamUtils();
    }

    /**
     * Returns the corresponding fraction from given InputStream:
     *
     * <ul>
     * <li>numerator/denominator
     * </ul>
     *
     * Where numerator and denominator are integers.
     *
     * @param in as InputStream
     * @return Fraction
     * @throws local.tin.tests.fractions.api.FractionException
     */
    public List<Fraction> readFractions(InputStream in) throws FractionException {
        List<Fraction> fractions = new ArrayList<>();
        try {
            String string = getStringFromInputStream(in);
            while (!string.isEmpty()) {
                String[] stringSplit = string.split(FRACTION_BAR_OBLIQUE);
                if (stringSplit.length != FRACTION_NUMERIC_COMPONENTS) {
                    throw new FractionException("Expression does not match <integer> / <integer>.");
                }
                fractions.add(new Fraction(Integer.parseInt(stringSplit[0].trim()), Integer.parseInt(stringSplit[1].trim())));
                string = getStringFromInputStream(in);
            }
        } catch (IOException | NumberFormatException ioe) {
            throw new FractionException(ioe);
        }
        return fractions;
    }

    private String getStringFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int currentChar = inputStream.read();
        while (currentChar != EOF && currentChar != ASCII_CODE_LINE_FEED) {
            stringBuilder.append((char) currentChar);
            currentChar = inputStream.read();
        }
        return stringBuilder.toString();
    }
}
