package edu.xhu_16;

public class SetThread implements Runnable {
	private Student s;
	private int x=0;
	public SetThread(Student s) {
		this.s=s;
	}

	@Override
	public void run() {
		while(true){
			if (x % 2 == 0) {
				s.set("����", 18);
			} else {
				s.set("����", 20);
			}
			x++;
		}			
	}
}
