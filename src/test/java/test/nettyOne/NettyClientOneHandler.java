package test.nettyOne;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class NettyClientOneHandler extends SimpleChannelInboundHandler<ByteBuf> {

	public NettyClientOneHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, ByteBuf arg1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("client:"+arg1.toString(CharsetUtil.UTF_8));
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		ctx.writeAndFlush(Unpooled.copiedBuffer("holle world",CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		cause.fillInStackTrace();
		ctx.close();
	}

}
