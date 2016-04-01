package TestClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

public class TestCountDownTimer 
{
//	
//	public static void main (String [] args)
//	{
//		int hour = 2, min = 0, sec = 10;
//		
//	
//		while(true)
//		{
//			logic(hour,min,sec);
//		}
//	}
	
	
	public static String logic (int min, int hour, int sec){
		if (hour > 0) {
			if (min > 0) {
				if (sec > 0)
					sec--;
				else {
					min--;
					sec = 59;
				}
			} else if (min == 0) {
				if (sec > 0)
					sec--;
				else {
					hour--;
					min = 59;
					sec = 59;
				}
			}
			String formatHour = String.format("%02d", hour);
			String formatMin = String.format("%02d", min);
			String formatSec = String.format("%02d", sec);
			return (formatHour +" : "+ formatMin +" : " + formatSec);
		} else if (min > 0) {
			if (sec > 0) {
				sec--;

			} else {
				min--;
				sec = 59;
			}
			String formatHour = String.format("%02d", hour);
			String formatMin = String.format("%02d", min);
			String formatSec = String.format("%02d", sec);
			return (formatHour+" : "+formatMin+" : "+formatSec);
		} else if (sec > 0) {
			sec--;
			String formatHour = String.format("%02d", hour);
			String formatMin = String.format("%02d", min);
			String formatSec = String.format("%02d", sec);
			return (formatHour+" : "+formatMin+" : "+formatSec);
		} 

		else {
			return "Done";	
		}
		
	}
}
