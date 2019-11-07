package harsh.rane;

import java.lang.Thread.UncaughtExceptionHandler;

public class MyKStreamExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		
		System.err.println(throwable);
	}

}
