package convenience;

import org.lwjgl.Sys;

public class FPSUtils
{
	
	public static boolean initFpsCounter;
	public static long lastFPS;
	public static int fps, fpsCounter;
	
	public static void manageFPS()
	{
		if( !initFpsCounter )
		{
			lastFPS = ( Sys.getTime() * 1000 ) / Sys.getTimerResolution();
			initFpsCounter = true;
		}

		if( ( Sys.getTime() * 1000 ) / Sys.getTimerResolution() - lastFPS > 1000 )
		{
			fps = fpsCounter;
			fpsCounter = 0;
			lastFPS += 1000;
		}
		fpsCounter++ ;
	}
	
}
