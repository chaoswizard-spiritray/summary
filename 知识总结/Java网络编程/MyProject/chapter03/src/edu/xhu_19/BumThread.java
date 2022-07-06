package edu.xhu_19;

import java.util.concurrent.Callable;

public class BumThread implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(3000);
		return "包子准备好了";
	}

}
