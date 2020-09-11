package local.tin.tests.mockito.workshop.operations;

/**
 *
 * @author benitodarder
 */
public class NoOp extends Operation {

    @Override
    protected Double operate(Integer firstOperand, Integer secondOperand) {
        return 0d;
    }

    @Override
    protected Double operate(Double firstOperand, Integer secondOperand) {
        return 0d;
    }

    @Override
    protected Double operate(Integer firstOperand, Double secondOperand) {
        return 0d;
    }
    

    @Override
    protected Double operate(Double firstOperand, Double secondOperand) {
        return 0d;
    }
    
    
    
}
