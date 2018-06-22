package test.nioTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 18:09 2018/4/27
 */
public class ChanelTest {
    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("C:/Users/js170830xlr/Desktop/1.txt", "rw");
            FileInputStream fileInputStream = new FileInputStream("C:/Users/js170830xlr/Desktop/1.txt");
            FileChannel fileChannel = fileInputStream.getChannel();
            FileChannel channel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            CharBuffer charBuffer = CharBuffer.allocate(100);
            byteBuffer.clear();
            int readnum = fileChannel.read(byteBuffer);
            while (readnum != -1) {

                byteBuffer.flip();

                System.out.print(new String(byteBuffer.array()));

                byteBuffer.clear();
                readnum = fileChannel.read(byteBuffer);
            }
            randomAccessFile.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
