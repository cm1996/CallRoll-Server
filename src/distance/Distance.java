package distance;

public class Distance {
	/**
     *  ���ڼ�����������Ƿ�С��100��
     *  tX��tYΪ��ʦ��γ��
     *  sX��sYΪѧ����γ��
     */
    public static boolean caculateDistance(double tX,double tY,double sX,double sY){
        double PI = 3.14159265358979323; // Բ����
        double R = 6371229; // ����İ뾶

        double x, y, distance;
        x = (tY - sY) * PI * R
                * Math.cos(((tX + sX) / 2) * PI / 180) / 180;
        y = (sX - tX) * PI * R / 180;
        distance = Math.hypot(x, y)/1000-1.9;
        System.out.println(distance+"juli");
        if(distance < 100){
            return true;
        }
        else
            return false;
    }
    
    
    
    //1.9063125290992076juli
   // 1.894248502332745juli
    //1.887853337220999juli
    //1.8920814442521297juli
			

}
