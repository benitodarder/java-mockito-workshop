package local.tin.tests.mockito.workshop.operations;

/**
 *
 * @author benitodarder
 */
public enum Operator {
    
    SUM("+"),
    SUBSTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    NOOP("NoOp");
    
    private final String operatorString;

    Operator(String operator) {
        this.operatorString = operator;
    }

    public String getOperatorString() {
        return operatorString;
    }  
 
    
}
