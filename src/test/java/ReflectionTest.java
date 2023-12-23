import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.example.Injector;
import org.example.SomeBean;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class ReflectionTest {

        @Before
        public void setupData() throws IOException, IllegalAccessException, InstantiationException {
            System.setOut(new PrintStream(outContent));
            testBean = (new Injector<SomeBean>("src/main/java/config/scratch.properties").inject(new SomeBean()));
        }

        @Test
        public void checkReflection(){
            testBean.go();
            assertEquals("BC", outContent.toString());
        }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private SomeBean testBean;
}
