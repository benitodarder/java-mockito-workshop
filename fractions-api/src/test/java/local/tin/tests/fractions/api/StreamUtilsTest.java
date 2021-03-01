package local.tin.tests.fractions.api;

import java.io.IOException;
import java.io.InputStream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author benitodarder
 */
public class StreamUtilsTest {
    
    private static final int ASCII_CODE_ONE = 49;
    private static final int ASCII_CODE_TWO = 50;
    private static final int ASCII_CODE_A = 65;
    
    
    private InputStream mockedInputStream;
    
    @Test
    public void getFraction_returns_expected_fraction() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);
        
        Fraction result = StreamUtils.getInstance().getFraction(mockedInputStream);
        
        assertThat(result.getNumerator(), equalTo(1));
        assertThat(result.getDenominator(), equalTo(2));
    }


    @Test(expected = FractionException.class)
    public void getFraction_throws_exception_when_non_digit_before_slash() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(ASCII_CODE_A).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);
        
        Fraction result = StreamUtils.getInstance().getFraction(mockedInputStream);
        
    }    
    
    @Test(expected = FractionException.class)
    public void getFraction_throws_exception_when_non_digit_after_slash() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(ASCII_CODE_A).thenReturn(StreamUtils.EOF);
        
        Fraction result = StreamUtils.getInstance().getFraction(mockedInputStream);
        
    }  

    @Test
    public void getFraction_skips_tabs_before_first_digit() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(StreamUtils.ASCII_CODE_TAB).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);
        
        Fraction result = StreamUtils.getInstance().getFraction(mockedInputStream);
        
        assertThat(result.getNumerator(), equalTo(1));
        assertThat(result.getDenominator(), equalTo(2));
    } 
    
    @Test
    public void getFraction_skips_space_before_first_digit() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(StreamUtils.ASCII_CODE_SPACE).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);
        
        Fraction result = StreamUtils.getInstance().getFraction(mockedInputStream);
        
        assertThat(result.getNumerator(), equalTo(1));
        assertThat(result.getDenominator(), equalTo(2));
    }    
    
    @Test
    public void getFraction_skips_tabs_before_slash() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_TAB).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);
        
        Fraction result = StreamUtils.getInstance().getFraction(mockedInputStream);
        
        assertThat(result.getNumerator(), equalTo(1));
        assertThat(result.getDenominator(), equalTo(2));
    }  
    
    @Test
    public void getFraction_skips_spaces_before_slash() throws FractionException, IOException {
        mockedInputStream = mock(InputStream.class);
        when(mockedInputStream.read()).thenReturn(ASCII_CODE_ONE).thenReturn(StreamUtils.ASCII_CODE_SPACE).thenReturn(StreamUtils.ASCII_CODE_SPACE).thenReturn(StreamUtils.ASCII_CODE_SLASH).thenReturn(ASCII_CODE_TWO).thenReturn(StreamUtils.EOF);
        
        Fraction result = StreamUtils.getInstance().getFraction(mockedInputStream);
        
        assertThat(result.getNumerator(), equalTo(1));
        assertThat(result.getDenominator(), equalTo(2));
    }     
}
