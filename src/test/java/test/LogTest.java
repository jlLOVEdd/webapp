package test;

import com.wdd.test.comm.log.InnerLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:48 2018/6/22
 */
public class LogTest {

    public static void main(String[] args) {
        for ( int i = 1; i <= 24; i++ ) {
            InnerLog.info( "write log" );
            try {
                Thread.sleep( 10000L );
            } catch ( final InterruptedException e ) {
                InnerLog.error( "an error occurred", e );
            }
        }

    }
}
