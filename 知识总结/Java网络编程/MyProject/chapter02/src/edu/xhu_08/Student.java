package edu.xhu_08;
/*
 * һ������Ҫд�뵽���У�����ʵ�����л��ӿڣ��ýӿ�û�з�����Ҫʵ�֣�
 * ����һ��:private static final long serialVersionUID
 * ��UID��������ʲô�أ�
 * ���serialVersionUID������������������л��뷴���л��ģ�
 * ԭ�������л�������ݵ��е�serialVersionUID�뵱ǰ�൱�е�serialVersionUIDһ�£�
 * ��ô�ö�����ܱ������л��ɹ���
 * ���serialVersionUID����ϸ�Ĺ��������ǣ�
 * �����л���ʱ��ϵͳ��serialVersionUIDд�뵽���л����ļ���ȥ��
 * �������л���ʱ��ϵͳ����ȥ����ļ��е�serialVersionUID�Ƿ����ǰ���ļ���serialVersionUID
 * �Ƿ�һ�£����һ�������л��ɹ��������˵����ǰ������л�����෢���˱仯��
 * �����ǳ�Ա�������������������ͷ����˱仯����ô�ڷ����л�ʱ�ͻᷢ��crash�����һᱨ����
 * �����л�������һ��UID,��û��ָ��UID�������һ�����ֵ��һ���෢���˸ı䣬��UIDҲ�ᷢ���ı䡣
 */
import java.io.Serializable;

public class Student implements Serializable {
	private String sno;
	private String name;
	 int age;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [sno=" + sno + ", name=" + name + ", age=" + age + "]";
	}
	

}
