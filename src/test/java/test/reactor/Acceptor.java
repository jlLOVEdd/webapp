package test.reactor;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 9:08 2018/8/14
 */
public class Acceptor implements Runnable {
    private Server_reactor reactor;
    public Acceptor(Server_reactor reactor){
        this.reactor=reactor;
    }
    @Override
    public void run() {
        try {
            SocketChannel socketChannel=reactor.serverSocketChannel.accept();
            if(socketChannel!=null)//调用Handler来处理channel
                new SocketReadHandler(reactor.selector, socketChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
