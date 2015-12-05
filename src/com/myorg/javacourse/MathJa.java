package com.myorg.javacourse;

public class MathJa {

	private int radius, hypotenuse, base, exp;
	private double angleB;

	public void setValues(int radius,int hypotenuse,int base,int exp, double angleB)
	{
		this.radius=radius;
		this.hypotenuse=hypotenuse;
		this.base=base;
		this.exp=exp;
		this.angleB=angleB;
	}
	public static double calcCircleArea(int radiusVal)// calc the area of circle
	{
		return Math.PI*radiusVal*radiusVal;
	}
	
	public static double calcOpposite(double angleBVal, int hypotenuseVal) //calc the size of opposite with a given hypotenuse and a given angle
	{
		return Math.sin(Math.toRadians(angleBVal))*hypotenuseVal;
	}
	
	public static long calcPower(int baseVal, int expVal) //cala the power of a number in an exponant
	{
		long res=1;
		for(int i=0;i<expVal;i++)
			res*=baseVal;
		return res;
	}
	
	public String getResults()
	{
		String line1, line2, line3, resultStr;
		line1= ("Calculation 1: area of circle with radius " +radius+" is:"+MathJa.calcCircleArea(radius));
		line2=("lenght of opposite where angle is: "+" 30"+ "is "+MathJa.calcOpposite(angleB, hypotenuse));
		line3=("power of "+ base+" with exp "+ exp+" is "+MathJa.calcPower(base,exp));
		resultStr=line1+"<br>"+line2+"<br>"+line3;
		return resultStr;
	}
}
