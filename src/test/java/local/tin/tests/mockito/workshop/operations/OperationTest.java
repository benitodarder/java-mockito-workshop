package local.tin.tests.mockito.workshop.operations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 * This test class shows how to test an abstract class:
 * 
 * <ol>
 * <li>Use a wrapper to implement the abstract class</li>
 * <li>Provide default values for abstract mehtods</li>
 * <li>Test base implementation with default values from point 2</li>
 * </ol>
 * 
 * 
 * @author benitodarder
 */
public class OperationTest {

    protected static final double TWO_INTEGERS = 1d;
    protected static final double INTEGER_AND_DOUBLE = 2d;
    protected static final double DOUBLE_AND_INTEGER = 3d;
    protected static final double TWO_DOUBLES = 4d;
    private static final int INTEGER_VALUE = 666;
    private static final double DOUBLE_VALUE = 666d;
    private static final String DOUBLE_STRING_01 = "1d";
    private static final String DOUBLE_STRING_02 = "6d";
    private static final String INTEGER_STRING_01 = "1";
    private static final String INTEGER_STRING_02 = "2";
    private Operation operation;

    @Before
    public void setUp() {
        operation = new OperationWrapper();
    }

    @Test
    public void isInteger_returns_false_when_null() {

        boolean result = operation.isInteger(null);

        assertThat(result, equalTo(false));
    }

    @Test
    public void isInteger_returns_true_when_integer() {

        boolean result = operation.isInteger(INTEGER_VALUE);

        assertThat(result, equalTo(true));
    }

    @Test
    public void isInteger_returns_false_when_double() {

        boolean result = operation.isInteger(DOUBLE_VALUE);

        assertThat(result, equalTo(false));
    }

    @Test
    public void isDouble_returns_false_when_null() {

        boolean result = operation.isDouble(null);

        assertThat(result, equalTo(false));
    }

    @Test
    public void isDouble_returns_true_when_double() {

        boolean result = operation.isDouble(DOUBLE_VALUE);

        assertThat(result, equalTo(true));
    }

    @Test
    public void isDouble_returns_false_when_integer() {

        boolean result = operation.isDouble(INTEGER_VALUE);

        assertThat(result, equalTo(false));
    }

    @Test
    public void two_integers_returs_expected_result() {

        String result = operation.operate(INTEGER_STRING_01, INTEGER_STRING_02);

        assertThat(Double.valueOf(result), equalTo(TWO_INTEGERS));
    }

    @Test
    public void null_and_integer_returs_expected_result() {

        String result = operation.operate((String) null, INTEGER_STRING_02);

        assertThat(result, equalTo(Operation.FIRST_OPERAND_IS_NOT_A_NUMBER));
    }

    @Test
    public void integer_and_null_returs_expected_result() {

        String result = operation.operate(INTEGER_STRING_01, (String) null);

        assertThat(result, equalTo(Operation.SECOND_OPERAND_IS_NOT_A_NUMBER));
    }

    @Test
    public void double_and_integer_returs_expected_result() {

        String result = operation.operate(DOUBLE_STRING_01, INTEGER_STRING_02);

        assertThat(Double.valueOf(result), equalTo(DOUBLE_AND_INTEGER));
    }

    @Test
    public void isInteger_returns_true_when_string_integer() {

        boolean result = operation.isInteger(INTEGER_STRING_01);

        assertThat(result, equalTo(true));
    }

    @Test
    public void isDouble_returns_true_when_string_double() {

        boolean result = operation.isDouble(DOUBLE_STRING_01);

        assertThat(result, equalTo(true));
    }

    @Test
    public void double_and_null_returs_expected_result() {

        String result = operation.operate(DOUBLE_STRING_01, (String) null);

        assertThat(result, equalTo(Operation.SECOND_OPERAND_IS_NOT_A_NUMBER));
    }

    @Test
    public void integer_and_double_returs_expected_result() {

        String result = operation.operate(INTEGER_STRING_01, DOUBLE_STRING_02);

        assertThat(Double.valueOf(result), equalTo(INTEGER_AND_DOUBLE));
    }

    @Test
    public void null_and_double_returs_expected_result() {

        String result = operation.operate((String) null, DOUBLE_STRING_02);

        assertThat(result, equalTo(Operation.FIRST_OPERAND_IS_NOT_A_NUMBER));
    }    
    
    @Test
    public void double_and_double_returs_expected_result() {

        String result = operation.operate(DOUBLE_STRING_01, DOUBLE_STRING_02);

        assertThat(Double.valueOf(result), equalTo(TWO_DOUBLES));
    }    
}

class OperationWrapper extends Operation {

    @Override
    public Double operate(Integer firstOperand, Integer secondOperand) throws ArithmeticException {
        return OperationTest.TWO_INTEGERS;
    }

    @Override
    public Double operate(Double firstOperand, Integer secondOperand) throws ArithmeticException {
        return OperationTest.DOUBLE_AND_INTEGER;
    }

    @Override
    public Double operate(Integer firstOperand, Double secondOperand) throws ArithmeticException {
        return OperationTest.INTEGER_AND_DOUBLE;
    }

    @Override
    public Double operate(Double firstOperand, Double secondOperand) throws ArithmeticException {
        return OperationTest.TWO_DOUBLES;
    }

}
