package test.nettyOne;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClientOne {
	private  final  String host;
	private final  int port;

	public NettyClientOne(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println(
			"Usage: " + NettyClientOne.class.getSimpleName() +
			" <host> <port>");
			return;
			}
			String host = args[0];
			int port = Integer.parseInt(args[1]);
			new NettyClientOne("127.0.0.1", 9080).start();
	}
	public static void start(){
		 EventLoopGroup workerGroup = new NioEventLoopGroup();  
	        try {  
	            Bootstrap b = new Bootstrap();  
	            b.group(workerGroup);  
	            b.channel(NioSocketChannel.class);  
	            b.option(ChannelOption.SO_KEEPALIVE, true);  
	            b.handler(new ChannelInitializer<SocketChannel>() {  
	                @Override  
	                public void initChannel(SocketChannel ch) throws Exception {  
	                    ch.pipeline().addLast(new NettyClientOneHandler());  
	                }  
	            });  
	            ChannelFuture f = b.bind().sync();  
	            f.channel().closeFuture().sync();  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        } finally {  
	            workerGroup.shutdownGracefully();  
	        }  
	    
	}
}
