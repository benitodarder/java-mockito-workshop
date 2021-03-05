package local.tin.tests.fractions.api;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author benitodarder
 */
public class StreamUtils {

    public static final int FRACTION_NUMERIC_COMPONENTS = 2;
    public static final String FRACTION_BAR = "/";
    public static final int EOF = -1;
    public static final int ASCII_CODE_TAB = 9;
    public static final int ASCII_CODE_SPACE = 32;
    public static final int ASCII_CODE_SLASH = 47;
    public static final int ASCII_CODE_CERO = 48;
    public static final int ASCII_CODE_NINE = 57;

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
     */
    public Fraction getFraction(InputStream in) throws FractionException {
        int numerator = 0;
        int denominator = 0;
        try {
            String string = getStringFromInputStream(in);
            String[] stringSplit = string.split(FRACTION_BAR);
            if (stringSplit.length != FRACTION_NUMERIC_COMPONENTS) {
                throw new FractionException("Expression does not match <integer> / <integer>");
            }
            numerator = Integer.parseInt(stringSplit[0].trim());
            denominator = Integer.parseInt(stringSplit[1].trim());
        } catch (IOException | NumberFormatException ioe) {
            throw new FractionException(ioe);
        }
        return new Fraction(numerator, denominator);
    }

    private String getStringFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int currentChar = inputStream.read();
        while (currentChar != EOF) {
            stringBuilder.append((char) currentChar);
            currentChar = inputStream.read();
        }
        return stringBuilder.toString();
    }
}
