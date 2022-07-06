package edu.xhu_08;
/*
 * 一个对象要写入到流中，必须实现序列化接口，该接口没有方法需要实现，
 * 但有一个:private static final long serialVersionUID
 * 该UID的作用是什么呢？
 * 这个serialVersionUID是用来辅助对象的序列化与反序列化的，
 * 原则上序列化后的数据当中的serialVersionUID与当前类当中的serialVersionUID一致，
 * 那么该对象才能被反序列化成功。
 * 这个serialVersionUID的详细的工作机制是：
 * 在序列化的时候系统将serialVersionUID写入到序列化的文件中去，
 * 当反序列化的时候系统会先去检测文件中的serialVersionUID是否跟当前的文件的serialVersionUID
 * 是否一致，如果一致则反序列化成功，否则就说明当前类跟序列化后的类发生了变化，
 * 比如是成员变量的数量或者是类型发生了变化，那么在反序列化时就会发生crash，并且会报错误。
 * 即序列化的类有一个UID,若没有指定UID，则会是一个随机值，一个类发生了改变，则UID也会发生改变。
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
