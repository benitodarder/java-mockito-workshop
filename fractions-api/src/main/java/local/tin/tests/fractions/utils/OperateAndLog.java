package local.tin.tests.fractions.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import local.tin.tests.fractions.api.Fraction;
import local.tin.tests.fractions.api.FractionException;
import local.tin.tests.fractions.model.OperationLog;

/**
 *
 * @author benitodarder
 */
public class OperateAndLog {
    
    private static EntityManagerFactory entityManagerFactory;
    
    private OperateAndLog() {
    }
    
    public static OperateAndLog getInstance() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("operations");
        }
        return OperateAndLogHolder.INSTANCE;
    }
    
    private static class OperateAndLogHolder {

        private static final OperateAndLog INSTANCE = new OperateAndLog();
    }
    
    public Fraction add(Fraction a, Fraction b) throws FractionException {
        Fraction result = a.add(b);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        OperationLog operationLog = new OperationLog();
        operationLog.setFirstDenominator(a.getDenominator());
        operationLog.setFirstNumerator(a.getNumerator());
        operationLog.setOperation("+");
        operationLog.setSecondDenominator(b.getDenominator());
        operationLog.setSecondNumerator(b.getDenominator());
        operationLog.setResultDenominator(result.getDenominator());
        operationLog.setResultNumerator(result.getNumerator());
        entityManager.persist(operationLog);
        return result;
    }
}
