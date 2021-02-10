package local.tin.tests.mockito.workshop;

import java.util.ArrayDeque;
import local.tin.tests.mockito.workshop.utils.CalculatorUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *  In this test classe We find:
 * 
 * <ol>
 * <li>This class shows how PreparingForTest the test class excludes the tests
 * from the Jacoco coverage report: 
 * Execution data for class local/tin/tests/mockito/workshop/Main does not match.  
 * More information at https://github.com/powermock/powermock/wiki/Code-coverage-with-JaCoCo
 *  * The reason the class needs to be prepared for test is the PowerMockito.whenNew</li>
 * <li>How to mock several calls to the same mock chaining the thenReturn()</li>
 * </ol>
 * 
 * @author benitodarder
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Logger.class, Main.class, ArrayDeque.class, CalculatorUtils.class})
public class MainTest {

    private static CalculatorUtils mockedCalculatorUtils;
    private static Logger mockedLogger;

    @BeforeClass
    public static void setUpClass() {
        mockedLogger = mock(Logger.class);
        mockedCalculatorUtils = mock(CalculatorUtils.class);
    }

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Logger.class);
        when(Logger.getLogger(Main.class)).thenReturn(mockedLogger);
        PowerMockito.mockStatic(CalculatorUtils.class);
        when(CalculatorUtils.getInstance()).thenReturn(mockedCalculatorUtils);        
    }

    @Test
    public void not_meeting_required_arguments_shows_usage_message() {
        String[] args = {"a", "b"};

        Main.main(args);

        verify(mockedLogger).info(Main.USAGE);
    }

    
    @Test
    public void main_get_results_from_calculatorutils() {
        String[] args = {"2", "1", "+"};
        when(mockedCalculatorUtils.getResult(args)).thenReturn("3.0");
        
        Main.main(args);
        
        verify(mockedLogger).info("3.0");
    }
    
}
