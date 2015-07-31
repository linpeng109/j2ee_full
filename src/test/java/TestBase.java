import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by linpeng109 on 15-7-31.
 */
public class TestBase extends TestCase {
    private static Logger logger = Logger.getLogger(TestBase.class.getName());

    @Test
    public void testPrint() {
        String result = "Test is running!";
        logger.debug(result);
    }
}
