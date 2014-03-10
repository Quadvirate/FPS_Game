package convenience;

import org.lwjgl.input.Keyboard;

public class KeyboardUtils
{
	
	public static boolean keyPressed( String key )
	{
		return Keyboard.isKeyDown( Keyboard.getKeyIndex( key.toUpperCase() ) );
	}

	public static boolean anyKeyPressed()
	{
		Keyboard.next();
		return Keyboard.getEventKeyState();
	}

	public static String getKeyPressed()
	{
		Keyboard.next();
		return Keyboard.getKeyName( Keyboard.getEventKey() );
	}
	
}
