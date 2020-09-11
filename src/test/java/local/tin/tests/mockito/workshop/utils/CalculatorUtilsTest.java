package local.tin.tests.mockito.workshop.utils;

import local.tin.tests.mockito.workshop.operations.Operator;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author benitodarder
 */
public class CalculatorUtilsTest {

    @Test
    public void getOperatorFrom_returns_sum() {

        Operator result = CalculatorUtils.getInstance().getOperator("+");

        assertThat(result, equalTo(Operator.SUM));
    }

    @Test
    public void getOperatorFrom_returns_substract() {

        Operator result = CalculatorUtils.getInstance().getOperator("-");

        assertThat(result, equalTo(Operator.SUBSTRACT));
    }

    @Test
    public void getOperatorFrom_returns_multiply() {

        Operator result = CalculatorUtils.getInstance().getOperator("*");

        assertThat(result, equalTo(Operator.MULTIPLY));
    }

    @Test
    public void getOperatorFrom_returns_divida() {

        Operator result = CalculatorUtils.getInstance().getOperator("/");

        assertThat(result, equalTo(Operator.DIVIDE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getOperatorFrom_throws_IllegalArgumentException_when_no_match() {

        Operator result = CalculatorUtils.getInstance().getOperator("no match");

    }

    @Test
    public void getResult_evaluates_two_operands_one_operation() {
        String[] args = {"2", "1", "+"};

        String result = CalculatorUtils.getInstance().getResult(args);

        assertThat(result, equalTo("3.0"));
    }

    @Test
    public void getResult_evaluates_three_operands_two_operation_left() {
        String[] args = {"2", "1", "+", "2", "-"};

        String result = CalculatorUtils.getInstance().getResult(args);

        assertThat(result, equalTo("1.0"));
    }

    @Test
    public void getResult_evaluates_three_operands_two_operation_right() {
        String[] args = {"5", "3", "2", "+", "-"};

        String result = CalculatorUtils.getInstance().getResult(args);

        assertThat(result, equalTo("0.0"));
    }

    @Test
    public void getResult_evaluates_five_operands_four_operators() {
        String[] args = {"2", "3", "*", "5", "+", "3", "1", "+", "-"};

        String result = CalculatorUtils.getInstance().getResult(args);

        assertThat(result, equalTo("7.0"));
    }

    
    @Test
    public void getResult_evaluates_nested_parentesis() {
        String[] args = {"3", "15", "3", "6", "+", "+", "2", "-", "-" };

        String result = CalculatorUtils.getInstance().getResult(args);

        assertThat(result, equalTo("-19.0"));
    }

    @Test
    public void getResult_evaluates_parentesis_on_the_right() {
        String[] args = {"2", "2", "2", "2", "+", "+", "+"};

        String result = CalculatorUtils.getInstance().getResult(args);

        assertThat(result, equalTo("8.0"));
    }
}
