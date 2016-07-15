package org.hebut.java.yu;

class JavaCar{
	private String manufactor;
	private String model;
	private String year;
	private int number;
	public JavaCar(){}
	public JavaCar(String manufactor,String model){
		this.manufactor=manufactor;
		this.model=model;
		this.year=null;
		this.number=-1;
	}
	public JavaCar(String manufactor,String model,String year){
		this.manufactor=manufactor;
		this.model=model;
		this.year=year;
		this.number=-1;
	}
	public JavaCar(String manufactor,String model,String year,int number){
		this.manufactor=manufactor;
		this.model=model;
		this.year=year;
		this.number=number;
	}
	public String getManufactor(){
		return this.manufactor;
	}
	public String getModel(){
		return this.model;
	}
	public String getYear(){
		return this.year;
	}
	public void setNumber(int number){
		this.number=number;
	}
	public int getNumber(){
		return number;
	}
	public static void main(String[] args) {
		
	}
}
public class CarTest{
	public static void main(String[] args) {
		JavaCar Chevrolet=new JavaCar("通用","雪佛兰-爱唯欧");
		JavaCar Volkswagen=new JavaCar("一汽","大众-斯柯达","2015-1-1");
		JavaCar Volvo=new JavaCar("吉利","Volvo-S40","2015-1-2",66666);
		String[] nameArr={"雪佛兰","大众","沃尔沃"};
		JavaCar[] carinfoArr={Chevrolet,Volkswagen,Volvo};
		CarTest cartest=new CarTest();
		cartest.OutInfo(nameArr,carinfoArr);
	}
	public void OutInfo(String[] nameArr,JavaCar[] carinfoArr){
		for(int i=0;i<nameArr.length;i++){
			System.out.println(nameArr[i]);
			System.out.println("汽车制造商: "+carinfoArr[i].getManufactor());
			System.out.println("汽车型号: "+carinfoArr[i].getModel());
			System.out.println("汽车年份: "+carinfoArr[i].getYear());
			System.out.println("车牌号: "+carinfoArr[i].getNumber());
		}
	}
}
