package local.tin.tests.mockito.workshop.utils;

import java.util.ArrayDeque;
import local.tin.tests.mockito.workshop.operations.OperationFactory;
import local.tin.tests.mockito.workshop.operations.Operator;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class CalculatorUtils {

    private static final Logger LOGGER = Logger.getLogger(CalculatorUtils.class);

    private CalculatorUtils() {
    }

    public static CalculatorUtils getInstance() {
        return CalculatorUtilsHolder.INSTANCE;
    }

    private static class CalculatorUtilsHolder {

        private CalculatorUtilsHolder() {
        }

        private static final CalculatorUtils INSTANCE = new CalculatorUtils();
    }

    public Operator getOperator(String text) {
        Operator result = null;
        for (Operator b : Operator.values()) {
            if (b.getOperatorString().equalsIgnoreCase(text)) {
                result = b;
                break;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException(" No enum constant local.tin.tests.mockito.workshop.operations.Operator." + text);
        }
        return result;
    }

    public String getResult(String[] arguments) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (int i = (arguments.length - 1); i >= 0; i--) {
            stack.push(arguments[i]);
        }
        LOGGER.debug("Stack: " + stack);
        while (stack.size() > 1) {
            stack.push(getResult(stack));
        }
        return stack.pop();
    }

    private boolean isOperator(String string) {
        try {
            getOperator(string);
            return true;
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }

    private String getResult(ArrayDeque<String> stack) {
        String operand01 = stack.pop();
        String operand02 = stack.pop();
        String operator = stack.pop();
        LOGGER.debug("Stack: " + stack + ", operand 01: " + operand01 + ", operand 02; " + operand02 + ", operator: " + operator);
        if (!isOperator(operator)) {
            stack.push(operator);
            stack.push(operand02);
            operand02 = getResult(stack);
            operator = stack.pop();
            if (!isOperator(operator)) {
                stack.push(operator);
                stack.push(operand02);
                operand02 = getResult(stack);
                operator = stack.pop();
            }
        }
        return OperationFactory.getInstance().getOperation(operator).operate(operand01, operand02);
    }
}
