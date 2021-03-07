package local.tin.tests.fractions.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author benitodarder
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Fraction.class, StreamUtils.class})
public class StreamUtilsTest {

    private static final int ASCII_CODE_CERO = 48;
    private static final int ONE = 1;
    private static final int ASCII_CODE_ONE = ASCII_CODE_CERO + ONE;
    private static final int TWO = 2;
    private static final int ASCII_CODE_TWO = ASCII_CODE_CERO + TWO;
    private static final int THREE = 3;
    private static final int ASCCI_CODE_THREE = ASCII_CODE_CERO + THREE;
    private static final int ASCII_CODE_A = 65;

    private InputStream mockedInputStream;

    @Test
    public void readFractions_returns_expected_list_with_one_fraction_fraction() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);

        List<Fraction> result = StreamUtils.getInstance().readFractions(mockedInputStream);

        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getNumerator(), equalTo(ONE));
        assertThat(result.get(0).getDenominator(), equalTo(TWO));
    }

    @Test(expected = FractionException.class)
    public void readFractions_throws_exception_when_non_digit_before_slash() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(ASCII_CODE_A).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);

        List<Fraction> result = StreamUtils.getInstance().readFractions(mockedInputStream);

    }

    @Test(expected = FractionException.class)
    public void readFractions_throws_exception_when_non_digit_after_slash() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(ASCII_CODE_A).thenReturn(StreamUtils.EOF);

        List<Fraction> result = StreamUtils.getInstance().readFractions(mockedInputStream);

    }

    @Test
    public void readFractions_skips_tabs_before_first_digit() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(StreamUtils.ASCII_CODE_TAB).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);

        List<Fraction> result = StreamUtils.getInstance().readFractions(mockedInputStream);

        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getNumerator(), equalTo(ONE));
        assertThat(result.get(0).getDenominator(), equalTo(TWO));
    }

    @Test
    public void readFractions_skips_space_before_first_digit() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(StreamUtils.ASCII_CODE_SPACE).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);

        List<Fraction> result = StreamUtils.getInstance().readFractions(mockedInputStream);

        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getNumerator(), equalTo(ONE));
        assertThat(result.get(0).getDenominator(), equalTo(TWO));
    }

    @Test
    public void readFractions_skips_tabs_before_slash() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_TAB).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);

        List<Fraction> result = StreamUtils.getInstance().readFractions(mockedInputStream);

        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getNumerator(), equalTo(ONE));
        assertThat(result.get(0).getDenominator(), equalTo(TWO));
    }

    @Test
    public void readFractions_skips_spaces_before_slash() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SPACE).thenReturn(StreamUtils.ASCII_CODE_SPACE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);

        List<Fraction> result = StreamUtils.getInstance().readFractions(mockedInputStream);

        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getNumerator(), equalTo(ONE));
        assertThat(result.get(0).getDenominator(), equalTo(TWO));
    }

    @Test(expected = FractionException.class)
    public void readFractions_throws_exception_when_ioexception_from_stream() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenThrow(new IOException());

        List<Fraction> result = StreamUtils.getInstance().readFractions(mockedInputStream);

    }

    @Test
    public void readFractions_creates_a_new_fraction_with_expected_values() throws Exception {
        Fraction mockedFraction = mock(Fraction.class);
        PowerMockito.whenNew(Fraction.class).withArguments(ONE, TWO).thenReturn(mockedFraction);
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);

        List<Fraction> result = StreamUtils.getInstance().readFractions(mockedInputStream);

        assertThat(result.size(), equalTo(1));
        assertThat(result.contains(mockedFraction), equalTo(true));
    }
    
    @Test
    public void readFractions_returns_expected_fraction_when_finds_line_feed() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.ASCII_CODE_LINE_FEED);

        List<Fraction> result = StreamUtils.getInstance().readFractions(mockedInputStream);

        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getNumerator(), equalTo(ONE));
        assertThat(result.get(0).getDenominator(), equalTo(TWO));
    }    
    
    @Test
    public void readFractions_returns_expected_list_with_two_fraction_fraction() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).
                thenReturn(ASCII_CODE_ONE).
                thenReturn(StreamUtils.ASCII_CODE_SLASH).
                thenReturn(ASCII_CODE_TWO).
                thenReturn(StreamUtils.ASCII_CODE_LINE_FEED).
                thenReturn(ASCII_CODE_TWO).
                thenReturn(StreamUtils.ASCII_CODE_SLASH).
                thenReturn(ASCCI_CODE_THREE).
                thenReturn(StreamUtils.EOF);

        List<Fraction> result = StreamUtils.getInstance().readFractions(mockedInputStream);

        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0).getNumerator(), equalTo(ONE));
        assertThat(result.get(0).getDenominator(), equalTo(TWO));
        assertThat(result.get(1).getNumerator(), equalTo(TWO));
        assertThat(result.get(1).getDenominator(), equalTo(THREE));        
    }
}
