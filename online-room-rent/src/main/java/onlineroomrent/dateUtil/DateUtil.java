package onlineroomrent.dateUtil;

import onlineroomrent.constant.OnlineRoomRentConstant;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date todayDate(){
        return new Date();
    }

    public static Date addDay(){
        Calendar calendar= Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date=calendar.getTime();
        return date;
    }

    public static Date addMintues(Integer minutes){
        Calendar calendar= Calendar.getInstance();
        calendar.add(Calendar.HOUR,0);
        calendar.add(Calendar.MINUTE,(1));
        long t1=OnlineRoomRentConstant.JWT_SESSION_EXPIRED;
        long t2= OnlineRoomRentConstant.JWT_SESSION_EXPIRED;
        calendar.add(Calendar.MINUTE, (int) (t1-t2));
        calendar.add(Calendar.SECOND, 0);
        Date date=calendar.getTime();
        return date;
    }
}
