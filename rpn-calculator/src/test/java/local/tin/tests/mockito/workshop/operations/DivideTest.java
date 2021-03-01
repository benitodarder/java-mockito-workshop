package local.tin.tests.mockito.workshop.operations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 * This test class shows basic testing of protected methods.
 * 
 * @author benitodarder
 */
public class DivideTest {

    private static final double DOUBLE_OPERAND_01 = 1d;
    private static final int INTEGER_OPERAND_02 = 2;
    private static final int INTEGER_OPERAND_01 = 1;
    private static final double DOUBLE_OPERAND_02 = 2d;    
    private Divide operation;

    @Before
    public void setUp() {
        operation = new Divide();
    }

    @Test
    public void divides_two_integers() {

        Double result = operation.operate(INTEGER_OPERAND_01, INTEGER_OPERAND_02);

        assertThat((int) result.longValue(), equalTo(INTEGER_OPERAND_01 / INTEGER_OPERAND_02));
    }


    @Test
    public void divides_first_double_second_integer() {

        Double result = operation.operate(DOUBLE_OPERAND_01, INTEGER_OPERAND_02);

        assertThat(result, equalTo((DOUBLE_OPERAND_01 / INTEGER_OPERAND_02)));
    }

    
    @Test
    public void divides_first_integer_second_double() {

        Double result = operation.operate(INTEGER_OPERAND_01, DOUBLE_OPERAND_02);

        assertThat(result, equalTo((INTEGER_OPERAND_01 / DOUBLE_OPERAND_02)));
    }
 
    
    @Test
    public void divides_two_double() {

        Double result = operation.operate(DOUBLE_OPERAND_01, DOUBLE_OPERAND_02);

        assertThat(result, equalTo((DOUBLE_OPERAND_01 / DOUBLE_OPERAND_02)));
    }

    
}
