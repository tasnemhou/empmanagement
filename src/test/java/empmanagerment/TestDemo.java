package empmanagerment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class TestDemo {

	public static void main(String[] args) {
		Date date = new Date();
		
		Date end = new Date();
		
		System.out.println(date);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		try {
			Date dd = sdf.parse("20170209 05:39:40");
			Date ee = sdf.parse("20170404 19:20:20");
			
			
			long l = ee.getTime()-dd.getTime();
			long s = l/(60*1000)%24;
			System.out.println(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
