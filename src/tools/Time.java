package tools;

import java.util.Calendar;



public class Time {

	 /**
    判断课程时间
    每天四节课，每周五天，课程按时间顺序排1-20
 */
public static int timeCalculate(){
    Calendar calendar = Calendar.getInstance();
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    int hour = calendar.get(Calendar.HOUR);
    int minute = calendar.get(Calendar.MINUTE);

    int minuteInAll = hour * 60 + minute;

    if(minuteInAll < 570){
        switch(dayOfWeek){
            case 1:
                return 1;
            case 2:
                return 5;
            case 3:
                return 9;
            case 4:
                return 13;
            case 5:
                return 17;
            default:
                return -1;
        }
    }
    else if (minuteInAll >= 570 && minuteInAll <= 720){
        switch(dayOfWeek){
            case 1:
                return 2;
            case 2:
                return 6;
            case 3:
                return 10;
            case 4:
                return 14;
            case 5:
                return 18;
            default:
                return -1;
        }
    }
    else if(minuteInAll > 720 && minuteInAll <= 915){
        switch(dayOfWeek){
            case 1:
                return 3;
            case 2:
                return 7;
            case 3:
                return 11;
            case 4:
                return 15;
            case 5:
                return 19;
            default:
                return -1;
        }
    }
    else{
        switch(dayOfWeek){
            case 1:
                return 4;
            case 2:
                return 8;
            case 3:
                return 12;
            case 4:
                return 16;
            case 5:
                return 20;
            default:
                return -1;
        }
    }
}
	
	
	
}
