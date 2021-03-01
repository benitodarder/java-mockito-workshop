package local.tin.tests.mockito.workshop.operations;

/**
 *
 * @author benitodarder
 */
public abstract class Operation implements IOperation {

    public static final String FIRST_OPERAND_IS_NOT_A_DOUBLE = "First operand is not a double";
    public static final String FIRST_OPERAND_IS_NOT_AN_INTEGER = "First operand is not an integer";
    public static final String FIRST_OPERAND_IS_NOT_A_NUMBER = "First operand is not a number";
    public static final String SECOND_OPERAND_IS_NOT_A_DOUBLE = "Second operand is not a double";
    public static final String SECOND_OPERAND_IS_NOT_AN_INTEGER = "Second operand is not an integer";
    public static final String SECOND_OPERAND_IS_NOT_A_NUMBER = "Second operand is not a number";

    @Override
    public String operate(String firstOperand, String secondOperand)  {
        String result = null;        
        if (!isInteger(firstOperand) && !isDouble(firstOperand)) {
            result = FIRST_OPERAND_IS_NOT_A_NUMBER;
        } else if (!isInteger(secondOperand) && !isDouble(secondOperand)) {
            result = SECOND_OPERAND_IS_NOT_A_NUMBER;
        }  else if (isInteger(firstOperand) && isInteger(secondOperand)) {
            result = String.valueOf(operate(Integer.parseInt(firstOperand), Integer.parseInt(secondOperand)));
        } else if (isDouble(firstOperand) && isInteger(secondOperand)) {
            result = String.valueOf(operate(Double.parseDouble(firstOperand), Integer.parseInt(secondOperand)));
        } else if (isInteger(firstOperand) && isDouble(secondOperand)) {
            result = String.valueOf(operate(Integer.parseInt(firstOperand), Double.parseDouble(secondOperand)));
        } else  {
            result = String.valueOf(operate(Double.parseDouble(firstOperand), Double.parseDouble(secondOperand)));
        }
        return result;
    }

    /**
     * Returns the result of two Integers operation
     *
     * @param firstOperand Integer
     * @param secondOperand Integer
     * @return Double
     * @throws ArithmeticException
     */
    protected abstract Double operate(Integer firstOperand, Integer secondOperand);

    /**
     * Returns the result of one Double and one Integer operation
     *
     * @param firstOperand Double
     * @param secondOperand Integer
     * @return Double
     * @throws ArithmeticException
     */
    protected abstract Double operate(Double firstOperand, Integer secondOperand);

    /**
     * Returns the result of one Integer and one Double operation
     *
     * @param firstOperand Integer
     * @param secondOperand Double
     * @return Double
     * @throws ArithmeticException
     */
    protected abstract Double operate(Integer firstOperand, Double secondOperand);

    /**
     * Returns the result of two Doubles operation
     *
     * @param firstOperand Double
     * @param secondOperand Double
     * @return Double
     * @throws ArithmeticException
     */
    protected abstract Double operate(Double firstOperand, Double secondOperand);

    protected boolean isInteger(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof String) {
            try {
                Integer.parseInt((String) object);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
        return object instanceof Integer;
    }

    protected boolean isDouble(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof String) {
            try {
                Double.parseDouble((String) object);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }            
        }
        return object instanceof Double;
    }
}
