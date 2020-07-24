package com.wcg.caoxian.user;

public class HelloB extends HelloA{

		public HelloB(){
			System.out.println("HelloB");
		}
		{
			System.out.println("I'm B class");
		}
		static {
			System.out.println("static B");
		}
		
		public static void main(String[] args) {
			System.out.println("*****主方法开始*****");
			new HelloB();
			System.out.println("*****主方法结束*****");
		}
		//执行顺序，父类静态代码块、子类静态代码块(一次加载)，父类非静态代码块、父类构造函数、子类非静态代码块、子类构造函数
}
