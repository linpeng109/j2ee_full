import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Moon on 2015/8/1.
 */
public class TestBase extends TestCase {
    private static Logger logger = Logger.getLogger(TestBase.class);

    public void testLog() {
        ApplicationContext
                context = new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.debug("TestBase is running!");
    }
}
