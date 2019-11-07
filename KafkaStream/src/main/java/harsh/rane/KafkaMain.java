package harsh.rane;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.SessionWindows;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.SessionStore;
import org.apache.kafka.streams.state.WindowStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KafkaMain {
	
	private static final Logger LOGGER = LogManager.getLogger(KafkaMain.class);

	public static void main(String[] args) throws InterruptedException {
		
		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "KafkaMain2");
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		
		
		
		StreamsBuilder builder = new StreamsBuilder();  // building Kafka Streams Model
		
		KStream<String, String> stream = builder.stream("topic3");
	 
	
		
		
	    KGroupedStream<String, String> groupedStream = stream.groupByKey();

		     //   .windowedBy(TimeWindows.of(TimeUnit.SECONDS.toMillis(10)))
	    KTable<Windowed<String>, Long> aggregate2 = groupedStream.windowedBy(TimeWindows.of(TimeUnit.SECONDS.toMillis(10)))
	    	    .aggregate(
	    	        () -> 0L, /* initializer */
	    	        (aggKey, newValue, aggValue) -> aggValue + newValue.length(), /* adder */
	    	        Materialized.<String, Long, WindowStore<Bytes, byte[]>>as("time-windowed-aggregated-stream-store") /* state store name */
	    	        .withValueSerde(Serdes.Long())); /* serde for aggregate value */
	    
	    LOGGER.info("grouped by key");
	    System.out.println("grouped by key");
	    
	   
	    KTable<String, Long> aggregate = groupedStream.aggregate(() -> 0L, (aggKey, newValue, aggValue) -> aggValue + newValue.length()+3,
	    		Materialized.<String,Long,KeyValueStore<Bytes,byte[]>>as("aggregated-stream-store") /* state store name */
	            .withValueSerde(Serdes.Long()));
	    
        LOGGER.info("aggragation done");
        System.out.println("aggragation done");
	    
        aggregate2.toStream().to("topicresult");
	    
        KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration); //Starting kafka stream
        streams.start();
        
     
	}

}
