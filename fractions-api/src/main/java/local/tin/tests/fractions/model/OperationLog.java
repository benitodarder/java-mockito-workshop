package local.tin.tests.fractions.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author benitodarder
 */
@Entity
@Table(name = "OPERATIONLOG")
public class OperationLog implements Serializable {
    
    @Id
    private int id;
    @Column(name = "timestamp")
    private Date timestamp;
    @Column(name = "firstNumerator")
    private int firstNumerator;
    @Column(name = "firstDenominator")
    private int firstDenominator;
    @Column(name = "secondNumerator")
    private int secondNumerator;
    @Column(name = "secondDenominator")
    private int secondDenominator;
    @Column(name = "resultNumerator")
    private int resultNumerator;
    @Column(name = "resultDenominator")
    private int resultDenominator;    
    @Column(name = "operation")
    private String operation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getFirstNumerator() {
        return firstNumerator;
    }

    public void setFirstNumerator(int firstNumerator) {
        this.firstNumerator = firstNumerator;
    }

    public int getFirstDenominator() {
        return firstDenominator;
    }

    public void setFirstDenominator(int firstDenominator) {
        this.firstDenominator = firstDenominator;
    }

    public int getSecondNumerator() {
        return secondNumerator;
    }

    public void setSecondNumerator(int secondNumerator) {
        this.secondNumerator = secondNumerator;
    }

    public int getSecondDenominator() {
        return secondDenominator;
    }

    public void setSecondDenominator(int secondDenominator) {
        this.secondDenominator = secondDenominator;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getResultNumerator() {
        return resultNumerator;
    }

    public void setResultNumerator(int resultNumerator) {
        this.resultNumerator = resultNumerator;
    }

    public int getResultDenominator() {
        return resultDenominator;
    }

    public void setResultDenominator(int resultDenominator) {
        this.resultDenominator = resultDenominator;
    }
    
    
    
}
