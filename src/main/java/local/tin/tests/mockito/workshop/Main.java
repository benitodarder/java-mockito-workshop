package local.tin.tests.mockito.workshop;

import local.tin.tests.mockito.workshop.utils.CalculatorUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class Main {

    public static final String USAGE = Main.class.getSimpleName() + " <Operand> <Operation> <Operand>";
    public static final int REQUIRED_ARGUMENTS = 3;
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length >= REQUIRED_ARGUMENTS) {
            LOGGER.info(CalculatorUtils.getInstance().getResult(args));
        } else {
            LOGGER.info(USAGE);
        }
    }
    

}
