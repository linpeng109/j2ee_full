package com.cn.quartz;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用以测试quartz的bean
 *
 * @author linp
 */
public class QuartzSample {
    public static Logger logger = Logger.getLogger(QuartzSample.class);

    public void diaplayCurrentTime() throws IOException, SAXException {
        Date current = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        logger.debug("CurrentTime is " + format.format(current));
    }
}
