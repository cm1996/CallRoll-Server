package stud;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

/**
 * 包含id和name两个属性
 */
public class Student {
    private String sId;
    private String sName;
    private double x;
    private double y;
    public Student(String a,String b,double gx,double gy){
        sId = a;
        sName = b;
        x=gx;
        y=gy;
    }


    public String getsId(){
        return sId;
    }
    public String getsName(){
        return sName;
    }
    public void setsId(String id){
        sId = id;
    }
    public void setsName(String name){
        sName = name;
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
	 return sId+" "+sName ;
 }   
    
    
}

