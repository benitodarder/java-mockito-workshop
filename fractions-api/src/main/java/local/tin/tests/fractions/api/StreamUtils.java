package local.tin.tests.fractions.api;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author benitodarder
 */
public class StreamUtils {

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
            int inputChar = in.read();
            while (inputChar == ASCII_CODE_TAB || inputChar == ASCII_CODE_SPACE) {
                inputChar = in.read();
            }
            while (inputChar != ASCII_CODE_SLASH && inputChar != ASCII_CODE_TAB && inputChar != ASCII_CODE_SPACE) {
                if (inputChar >= ASCII_CODE_CERO && inputChar <= ASCII_CODE_NINE) {
                    numerator = numerator * 10 + (inputChar - ASCII_CODE_CERO);
                    inputChar = in.read();
                } else {
                    throw new FractionException("Unexpected character in numerator: " + (char) inputChar);
                }
            }
            while (inputChar != ASCII_CODE_SLASH && (inputChar == ASCII_CODE_TAB || inputChar == ASCII_CODE_SPACE)) {
                inputChar = in.read();
            }
            inputChar = in.read();
            while (inputChar != EOF) {
                if (inputChar >= ASCII_CODE_CERO && inputChar <= ASCII_CODE_NINE) {
                    denominator = denominator * 10 + (inputChar - ASCII_CODE_CERO);
                    inputChar = in.read();
                } else {
                    throw new FractionException("Unexpected character in denominator: " + (char) inputChar);
                }
            }

        } catch (IOException ioe) {
            throw new FractionException(ioe);
        }
        return new Fraction(numerator, denominator);
    }

}
