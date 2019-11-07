package harsh.rane;
import java.time.Duration;
import java.util.Properties;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class KafkaMain2
{
	
		private static final Logger LOGGER = LogManager.getLogger(KafkaMain2.class);
	
	public static void main(String[] args)
	
	{	
		Properties streamsConfiguration = new Properties();
		
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "KafkaMain");
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
	
		StreamsBuilder builder = new StreamsBuilder();  								// building Kafka Streams Model
		
		
		KStream<String, String> stream1 = builder.stream("topic2");   					//get Stream
		
	    KStream<String, String> stream2 = builder.stream("topic3");	 					//get Stream
	    
	  //  KStream<String, Patient> patternKStream =stream1.
	    
	    KStream<String, String> joined = stream1.join(stream2,(firststream, secondstream) -> firststream + secondstream, JoinWindows.of(Duration.ofMinutes(1)));
	  
	    joined.to("topicresult");         													 //sending joined result to topicresult

	    LOGGER.info("KAFKA Message sent");
	    
        KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration); 	//Starting kafka stream
        streams.setUncaughtExceptionHandler(new MyKStreamExceptionHandler());
        streams.start();
        
        //https://github.com/bbejeck/kafka-streams/blob/master/src/main/java/bbejeck/streams/purchases/PurchaseKafkaStreamsDriver.java
	}

}
