package local.tin.tests.fractions.api;

/**
 *
 * @author benitodarder
 */
public class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) throws FractionException {
        if (denominator == 0) {
            throw new FractionException("Not allowed denominator: " + denominator);
        }
        int gcd = getGCD(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    public Fraction(int numerator) throws FractionException {
        this(numerator, 1);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.numerator;
        hash = 79 * hash + this.denominator;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fraction other = (Fraction) obj;
        if (this.numerator != other.numerator) {
            return false;
        }
        return this.denominator == other.denominator;
    }
    
    private int getGCD(int a, int b) {
        if (a < 0) {
            return getGCD(a* -1 , b);
        } else if (b < 0) {
            return getGCD(a, b * -1);
        } else if (b > a) {
            return getGCD(b, a);
        } else if (b > 0) {
            return getGCD(b, a % b);
        } else {
            return a;
        }
    }
    
    /**
     * Returns a new fraction with the result of adding the given
     * action with current one.
     * 
     * @param fraction
     * @return Fraction
     * @throws FractionException 
     */
    public Fraction add(Fraction fraction) throws FractionException {
        return new Fraction(
                getNumerator() * fraction.getDenominator() + getDenominator() * fraction.getNumerator(), 
                getDenominator() * fraction.getDenominator());
    }
    
    /**
     * Returns a new fraction with the result of subtracting the given
     * action from current one.
     * 
     * @param fraction
     * @return Fraction
     * @throws FractionException 
     */
    public Fraction subtract(Fraction fraction) throws FractionException {
        return new Fraction(
                getNumerator() * fraction.getDenominator() - getDenominator() * fraction.getNumerator(), 
                getDenominator() * fraction.getDenominator());
    }    

    @Override
    public String toString() {
        return '(' + String.valueOf(getNumerator()) + '/' + String.valueOf(getDenominator()) + ')';
    }
    
    /**
     * Returns a new fraction with the result of multiplying the given
     * action with current one.
     * 
     * @param fraction
     * @return Fraction
     * @throws FractionException 
     */
    public Fraction multiply(Fraction fraction) throws FractionException {
        return new Fraction(
                getNumerator() * fraction.getNumerator(), 
                getDenominator() * fraction.getDenominator());
    }    
    
    /**
     * Returns a new fraction with the result of dividing the current one
     * with the given one.
     * 
     * @param fraction
     * @return Fraction
     * @throws FractionException 
     */
    public Fraction divide(Fraction fraction) throws FractionException {
        return new Fraction(
                getNumerator() * fraction.getDenominator(), 
                getDenominator() * fraction.getNumerator());
    }       
}
