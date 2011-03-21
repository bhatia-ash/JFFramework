package com.quantisan.JFFramework.Trade;

public class OrderCommentMaker {
	private OrderCommentMaker() {}
	
	public static String getComment(AbstractSetup setup, 
									AbstractEntry entry,
									IStop stop,
									AbstractExit exit) 
	{
		char split = ';';
		String label;
				
		label = setup.getTag();
		label += split + entry.getTag();
		label += split + stop.getTag();
		label += split + exit.getTag();
		label.replaceAll(" ", "");
		
		return label;
	}
}