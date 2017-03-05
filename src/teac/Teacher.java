package teac;

public class Teacher {
	private String tId;
    private String tName;
    private double x;
    private double y;
    public Teacher(String a,String b,double gx,double gy){
        tId = a;
        tName = b;
        x=gx;
        y=gy;
    }
    public Teacher(){
    	
    }


    public String getsId(){
        return tId;
    }
    public String getsName(){
        return tName;
    }
    public void setsId(String id){
        tId = id;
    }
    public void setsName(String name){
        tName = name;
    }
    public void setx(double gx){
        x=gx;
    }
    public void sety(double gy){
        y=gy;
    }
    public double getx(){
        return x;
    }
    public double gety(){
        return y;
    }
 @Override
 public String toString()
 {
	 return tId+" "+tName ;
 }   
}
