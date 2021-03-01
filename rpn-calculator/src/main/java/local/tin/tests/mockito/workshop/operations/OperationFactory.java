package local.tin.tests.mockito.workshop.operations;

import local.tin.tests.mockito.workshop.utils.CalculatorUtils;

/**
 *
 * @author benitodarder
 */
public class OperationFactory {

    private OperationFactory() {
    }

    public static OperationFactory getInstance() {
        return OperationFactoryHolder.INSTANCE;
    }

    private static class OperationFactoryHolder {
        
        private static final OperationFactory INSTANCE = new OperationFactory();
        
        private OperationFactoryHolder() {}
    }

    public Operation getOperation(String operator) {
        switch (CalculatorUtils.getInstance().getOperator(operator)) {
            case SUM:
                return new Sum();
            case DIVIDE:
                return new Divide();
            case MULTIPLY:
                return new Multiply();
            case SUBSTRACT:
                return new Substract();
            default:
                return new NoOp();
        }
    }
}
