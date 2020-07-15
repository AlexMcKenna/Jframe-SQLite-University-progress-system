package planner;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Alex
 */
public class CSV_Data {

    /**
     * this method convert String to Date
     *
     * @param sdate string of the date
     * @return date in Date type
     * @throws ParseException
     */
    public static Date convertDate(String sdate) throws ParseException {
        Date date = null;
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = fmt.parse(sdate);
            fmt.format(date);
//            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }




    
}
