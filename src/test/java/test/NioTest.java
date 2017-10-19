package test;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.mvel2.debug.Debugger;

public class NioTest {
	public NioTest() {
		// TODO Auto-generated constructor stub
	}
	 public static void startService() throws Exception
	 {
		 int channels =0;
		 int nkeys =0;
		 int currentSelector=0;
		 Selector selector = Selector.open();
		 ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		 InetSocketAddress ipsocket = new InetSocketAddress(InetAddress.getLocalHost(),9000);
		 serverSocketChannel.socket().bind(ipsocket);
		 serverSocketChannel.configureBlocking(false);
		
		//向Selector注册Channel及我们有兴趣的事件
	SelectionKey s = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	printKeyInfo(s);

		while(true) //不断的轮询
	{
	debug("NBTest: Starting select");

		//Selector通过select方法通知我们我们感兴趣的事件发生了。
	nkeys = selector.select();
		//如果有我们注册的事情发生了，它的传回值就会大于0
		if(nkeys > 0)
	{
		debug("NBTest: Number of keys after select operation: " +nkeys);

	//Selector传回一组SelectionKeys
//我们从这些key中的channel()方法中取得我们刚刚注册的channel。
		Set selectedKeys = selector.selectedKeys();
		Iterator i = selectedKeys.iterator();
		while(i.hasNext())
		{
			s = (SelectionKey) i.next();
		 printKeyInfo(s);
		debug("NBTest: Nr Keys in selector: " +selector.keys().size());

		 //一个key被处理完成后，就都被从就绪关键字（ready keys）列表中除去
		 i.remove();
		if(s.isAcceptable())
		{
		// 从channel()中取得我们刚刚注册的channel。
		 Socket socket = ((ServerSocketChannel)s.channel()).accept().socket();
		SocketChannel sc = socket.getChannel();

		 sc.configureBlocking(false);
		sc.register(selector, SelectionKey.OP_READ |SelectionKey.OP_WRITE);
		System.out.println(++channels);
		}
	 else
	 {
			  	debug("NBTest: Channel not acceptable");
		}
		}
}
else
	{
	debug("NBTest: Select finished without any keys.");
	}

	}

		}


		private static void debug(String s)
		{
		System.out.println(s);
		}


		private static void printKeyInfo(SelectionKey sk)
		{
		String s = new String();

		 s = "Att: " + (sk.attachment() == null ? "no" : "yes");
		s += ", Read: " + sk.isReadable();
		 s += ", Acpt: " + sk.isAcceptable();
		s += ", Cnct: " + sk.isConnectable();
		s += ", Wrt: " + sk.isWritable();
		 s += ", Valid: " + sk.isValid();
		 s += ", Ops: " + sk.interestOps();
		 debug(s);
		}


		/**
		* @param args the command line arguments
		*/
		public static void main (String args[])
		{
			NioTest nbTest = new NioTest();
		 try
		 {
			 nbTest.startService();	
		}catch(Exception e)
		 {
		e.printStackTrace();
		 }
		}
}


