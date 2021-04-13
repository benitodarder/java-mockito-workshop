package local.tin.tests.fractions;


import local.tin.tests.fractions.api.Fraction;
import local.tin.tests.fractions.api.FractionException;
import local.tin.tests.fractions.utils.OperateAndLog;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);
    
    /**
     * @param args the command line arguments
     * @throws local.tin.tests.fractions.api.FractionException
     */
    public static void main(String[] args) throws FractionException {
        LOGGER.info("There we go...");
        LOGGER.info(OperateAndLog.getInstance().add(new Fraction(1, 2), new Fraction(1, 2)).toString());
        LOGGER.info("That's all folks!");
    }
    
}
