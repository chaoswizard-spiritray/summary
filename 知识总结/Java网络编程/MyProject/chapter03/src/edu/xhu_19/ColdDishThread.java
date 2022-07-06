package edu.xhu_19;

import java.util.concurrent.Callable;

public class ColdDishThread implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "凉菜准备好了";
	}

}
