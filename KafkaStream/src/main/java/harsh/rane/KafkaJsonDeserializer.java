package harsh.rane;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaJsonDeserializer<T> implements Deserializer {

	private Class<T> type;

	public KafkaJsonDeserializer(Class type) {
		this.type = type;
	}


	@Override
	public Object deserialize(String s, byte[] bytes) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		T obj = null;
		try {
			obj = mapper.readValue(bytes, type);
		} catch (Exception e) {

			System.err.println(e.getMessage());
		}
		return obj;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void configure(Map configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

}
