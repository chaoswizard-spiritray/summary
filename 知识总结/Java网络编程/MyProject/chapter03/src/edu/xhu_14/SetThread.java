package edu.xhu_14;

public class SetThread implements Runnable {
	private Student s;
	private int x=0;
	public SetThread(Student s) {
		this.s=s;
	}

	@Override
	public void run() {
		while(true){
			synchronized(s){
				if(x%2==0){
					s.name="����";
					s.age=18;
				}
				else
				{
					s.name="����";   //��һ��ѧ������"����",����18,
					s.age=30;
				}
				x++;
			}
		}
				

	}

}
