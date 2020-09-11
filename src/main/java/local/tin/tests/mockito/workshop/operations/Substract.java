package local.tin.tests.mockito.workshop.operations;

/**
 *
 * @author benitodarder
 */
public class Substract extends Operation {

    @Override
    protected Double operate(Integer firstOperand, Integer secondOperand) {
        return (double) firstOperand - secondOperand;
    }

    @Override
    protected Double operate(Double firstOperand, Integer secondOperand) {
        return firstOperand - secondOperand;
    }

    @Override
    protected Double operate(Integer firstOperand, Double secondOperand) {
        return firstOperand - secondOperand;
    }
    

    @Override
    protected Double operate(Double firstOperand, Double secondOperand) {
        return firstOperand - secondOperand;
    }
    
    
    
}
