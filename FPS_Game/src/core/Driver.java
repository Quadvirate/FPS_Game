package core;

import startup.LoadMenu;


public class Driver
{
	
	public static void main( String[] args )
	{
		
		Engine e = new Engine();
		e.setNatives();
		LoadMenu eng = new LoadMenu();
		eng.start();
		
	}
	
}
