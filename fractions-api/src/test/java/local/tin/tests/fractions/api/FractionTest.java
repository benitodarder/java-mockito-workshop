package local.tin.tests.fractions.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

/**
 *
 * @author benitodarder
 */
public class FractionTest {

    private static final int A = 1;
    private static final int B = 3;
    private static final int C = 5;
    private static final int D = 8;
    private static final int K = 2;

    private Fraction fraction;
    private Fraction anotherFraction;

    @Test
    public void getNumerator_returns_expected_value_when_irreducible() throws FractionException {
        fraction = new Fraction(A, B);

        int result = fraction.getNumerator();

        assertThat(result, equalTo(A));
    }

    @Test
    public void getDenominator_returns_expected_value_when_irreducible() throws FractionException {
        fraction = new Fraction(A, B);

        int result = fraction.getDenominator();

        assertThat(result, equalTo(B));
    }

    @Test
    public void getDenominator_returns_1_when_not_provided() throws FractionException {
        fraction = new Fraction(A);

        int result = fraction.getDenominator();

        assertThat(result, equalTo(A));
    }

    @Test(expected = FractionException.class)
    public void expected_exception_with_constuctor_with_denominator_zero() throws FractionException {

        fraction = new Fraction(A, 0);

    }

    @Test
    public void equal_returns_true_for_same_fraction() throws FractionException {
        fraction = new Fraction(A, B);

        boolean result = fraction.equals(fraction);

        assertThat(result, equalTo(true));
    }

    @Test
    public void equal_returns_for_true_fraction_with_same_values() throws FractionException {
        fraction = new Fraction(A, B);
        Fraction anotherFraction = new Fraction(A, B);

        boolean result = fraction.equals(anotherFraction);

        assertThat(result, equalTo(true));
    }

    @Test
    public void equal_returns_for_true_for_reducible_fraction() throws FractionException {
        fraction = new Fraction(A, B);
        anotherFraction = new Fraction(K * A, K * B);

        boolean result = fraction.equals(anotherFraction);

        assertThat(result, equalTo(true));
    }

    @Test
    public void add_complies_with_expression() throws FractionException {
        fraction = new Fraction(A, B);
        anotherFraction = new Fraction(C, D);

        Fraction result = fraction.add(anotherFraction);
        System.out.println(fraction + " + " + anotherFraction + " = " + result);
        
        assertThat(result.getNumerator(), equalTo(A * D + C * B));
        assertThat(result.getDenominator(), equalTo(B * D));
        assertThat(result, equalTo(new Fraction(A * D + C * B, D * B)));
    }    
    
  
   @Test
    public void subtract_complies_with_expression() throws FractionException {
        fraction = new Fraction(A, B);
        anotherFraction = new Fraction(C, D);

        Fraction result = fraction.subtract(anotherFraction);
        System.out.println(fraction + " - " + anotherFraction + " = " + result);
        
        assertThat(result.getNumerator(), equalTo(A * D - C * B));
        assertThat(result.getDenominator(), equalTo(B * D));
        assertThat(result, equalTo(new Fraction(A * D - C * B, D * B)));
    }     
    
   @Test
    public void multiply_complies_with_expression() throws FractionException {
        fraction = new Fraction(A, B);
        anotherFraction = new Fraction(C, D);

        Fraction result = fraction.multiply(anotherFraction);
        System.out.println(fraction + " * " + anotherFraction + " = " + result);
        
        assertThat(result.getNumerator(), equalTo(A * C));
        assertThat(result.getDenominator(), equalTo(B * D));
        assertThat(result, equalTo(new Fraction(A * C, D * B)));
    }     
    
   @Test
    public void divide_complies_with_expression() throws FractionException {
        fraction = new Fraction(A, B);
        anotherFraction = new Fraction(C, D);

        Fraction result = fraction.divide(anotherFraction);
        System.out.println(fraction + " / " + anotherFraction + " = " + result);
        
        assertThat(result.getNumerator(), equalTo(A * D));
        assertThat(result.getDenominator(), equalTo(B * C));
        assertThat(result, equalTo(new Fraction(A * D, C * B)));
    }    
    
    @Test
    public void getNumerator_returns_reduced_by_gcd() throws FractionException {
        fraction = new Fraction(A * 4, B * 2);

        int result = fraction.getNumerator();

        assertThat(result, equalTo(A * 2));
    }

    @Test
    public void getDenominator_returns_reduced_by_gcd() throws FractionException {
        fraction = new Fraction(A * 2, B * 4);

        int result = fraction.getDenominator();

        assertThat(result, equalTo(B * 2));
    }    
}
