package scAutomate;

import java.util.Random;

public class MessageCreator {

	protected static String conformMessage(int start, String message) {
		// TODO Auto-generated method stub
		String finalMessage;
		String space = " ";
		String one = "\n\nBless Up !";
		String two = "\n\nMuch Love";
		String three = "\n\nOne Love";
		
		Random r = new Random();
		int Low = 1;
		int High = 100;
		int Result = r.nextInt(High-Low) + Low;
		
		message = message.substring(0, Result) + space + message.substring(Result, message.length());
		
		int option;
		if (start < 3) {
			option = start;
		} else {
			option = start % 3;
		}
		switch (option) {
		case 1:
			finalMessage = message + one;
			break;
		case 2:
			finalMessage = message + two;
			break;
		default:
			finalMessage = message + three;
		}
		return finalMessage;
	}
	
//	protected static String addSpaces(int[] {floor, ceiling}) {
//		return;
//	}
	
	
}
