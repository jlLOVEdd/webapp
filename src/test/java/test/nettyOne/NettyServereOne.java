package test.nettyOne;

import com.hazelcast.cluster.impl.operations.ChangeClusterStateOperation;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServereOne {
	private int port;
	public NettyServereOne(int port) {
		// TODO Auto-generated constructor stub
		this.port =port;
	}
	public void run() throws Exception{
	EventLoopGroup BossGroup = new NioEventLoopGroup();
	EventLoopGroup WorkGroup = new NioEventLoopGroup();
	try{
		ServerBootstrap serverBootstrap =new ServerBootstrap();
		serverBootstrap.group(BossGroup, WorkGroup)
		.channel(NioServerSocketChannel.class)
		.childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				// TODO Auto-generated method stub
				ch.pipeline().addLast(new ServerHandlerOne());
			}
		}).option(ChannelOption.SO_BACKLOG, 128)
		.childOption(ChannelOption.SO_KEEPALIVE, false);
		ChannelFuture cf = serverBootstrap.bind(port).sync();
		cf.channel().closeFuture().sync();
		}finally{
			WorkGroup.shutdownGracefully();
			BossGroup.shutdownGracefully();
	}
	
	}
	public static void main(String[] args) throws Exception {
		   int port;
           if (args.length > 0) {
               port = Integer.parseInt(args[0]);
           } else {
               port = 8080;
           }
           new NettyServereOne(port).run();
       }
	
}
