package harsh.rane;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KafkaStreamMain {

	private static final Logger LOGGER = LogManager.getLogger(KafkaMain2.class);
		
	public static void main(String[] args) 
	{
		Properties streamsConfiguration = new Properties();
		
			streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "KafkaMain");
			streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
			streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
			streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		
		StreamsBuilder builder = new StreamsBuilder();  									// building Kafka Streams Model
		
		KStream<String, String> data = builder.stream("topic2");
		
		//KStream<String, String> datalowercase = data.flatMapValues(value -> Arrays.asList(value.toLowerCase()));
		//KStream<String, String> spacesep = data.flatMapValues(value -> Arrays.asList(value.split(" ")));
		
		KStream<String, String> concatstr = data.flatMapValues(value -> Arrays.asList(value.concat("Harshatej")));
		
		data.foreach((key, value) -> 
									{
									  System.out.println(key + " => " + value);         	 //printing key and value 
									  LOGGER.info(key + " => " + value);
									});
		
		concatstr.to("topicresult");
        
        KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration); //Starting kafka stream
        streams.start();
	
	
	}

}
