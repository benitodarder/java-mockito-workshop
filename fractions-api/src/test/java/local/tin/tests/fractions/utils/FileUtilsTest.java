package local.tin.tests.fractions.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import local.tin.tests.fractions.api.Fraction;
import local.tin.tests.fractions.api.FractionException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author benito.darder
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({StreamUtils.class, FileInputStream.class, FileUtils.class})
public class FileUtilsTest {

    private static final String FILE_PATH = "file name";
    private static StreamUtils mockedStreamUtils;
    private FileInputStream mockedInputStream;
    private Fraction mockedFraction01;
    private Fraction mockedFraction02;
    private List<Fraction> fractions;
    
    @BeforeClass
    public static void setUpClass() {
        mockedStreamUtils = mock(StreamUtils.class);
    }

    @Before
    public void setUp() throws FractionException, Exception {
        PowerMockito.mockStatic(StreamUtils.class);
        when(StreamUtils.getInstance()).thenReturn(mockedStreamUtils);
        mockedInputStream = mock(FileInputStream.class);
        PowerMockito.whenNew(FileInputStream.class).withArguments(FILE_PATH).thenReturn(mockedInputStream);
        mockedFraction01 = mock(Fraction.class);
        mockedFraction02 = mock(Fraction.class);
        fractions = new ArrayList<>();
        fractions.add(mockedFraction01);
        fractions.add(mockedFraction02);
        when(StreamUtils.getInstance().readFractions(mockedInputStream)).thenReturn(fractions);
    }

    @Test
    public void readFromFile_returns_one_item_list() throws Exception {

        List<Fraction> result = FileUtils.getInstance().readFromFile(FILE_PATH);

        assertThat(result, equalTo(fractions));
    }


}
