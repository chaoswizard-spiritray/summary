package edu.xhu_13;

public class SetThread implements Runnable {
	private Student s;

	public SetThread(Student s) {
		this.s=s;
	}

	@Override
	public void run() {
		
		s.name="����";
		s.age=18;
				

	}

}
