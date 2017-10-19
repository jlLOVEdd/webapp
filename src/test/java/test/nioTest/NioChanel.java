package test.nioTest;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.RandomAccess;

import com.hazelcast.spi.MigrationAwareService;


public class NioChanel {

	public NioChanel() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		try {
			String RowData = "this is a currenttime:"+System.currentTimeMillis();
			ByteBuffer buf = ByteBuffer.allocate(1000);
				buf.clear();
		
				buf.put(RowData.getBytes());
			
				buf.flip();
			
			
				while(buf.hasRemaining()){
					 System.out.print((char)buf.get());
				}
				
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
