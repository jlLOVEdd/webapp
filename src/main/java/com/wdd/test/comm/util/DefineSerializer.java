package com.wdd.test.comm.util;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 13:38 2018/5/11
 */
public class DefineSerializer<T extends SpecificRecordBase>  implements Serializer<T> {
    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, T data) {

            DatumWriter<T> userDatumWriter = new SpecificDatumWriter<T>(data.getSchema());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(outputStream, null);
            try {
                userDatumWriter.write(data, binaryEncoder);
            } catch (IOException e) {
                throw new SerializationException(e.getMessage());
            }
            return outputStream.toByteArray();

    }

    @Override
    public void close() {

    }
}
