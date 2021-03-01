package local.tin.tests.mockito.workshop.operations;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * This test class shows the use of matcher instanceOf
 * 
 * @author benitodarder
 */
public class OperationFactoryTest {
    
    @Test
    public void getOperation_returns_sum() {
        
        Operation operation = OperationFactory.getInstance().getOperation("+");
        
        assertThat(operation, instanceOf(Sum.class));
    }

    @Test
    public void getOperation_returns_divide() {
        
        Operation operation = OperationFactory.getInstance().getOperation("/");
        
        assertThat(operation, instanceOf(Divide.class));
    }    
    
    @Test
    public void getOperation_returns_multiply() {
        
        Operation operation = OperationFactory.getInstance().getOperation("*");
        
        assertThat(operation, instanceOf(Multiply.class));
    }   
    
    @Test
    public void getOperation_returns_substract() {
        
        Operation operation = OperationFactory.getInstance().getOperation("-");
        
        assertThat(operation, instanceOf(Substract.class));
    } 

    @Test(expected = IllegalArgumentException.class)
    public void getOperation_throws_illegal_argument_exception() {
        
        Operation operation = OperationFactory.getInstance().getOperation(Operator.DIVIDE.getOperatorString() + Operator.SUM.getOperatorString());
        
    }     
}
