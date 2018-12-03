package test;



import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:48 2018/6/22
 */
public class LogTest {

   public static  Logger logger = ((Logger) LoggerFactory.getLogger(LogTest.class));

    public static void main(String[] args) {

       /* InnerLog.info("write info log");
        InnerLog.debug("write debug log");
        InnerLog.error("write error log");
        InnerLog.warn("write warn log");
*/
        logger.info("sssss=======ssss");
    }
}
