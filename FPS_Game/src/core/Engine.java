package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import player.Player;
import static org.lwjgl.opengl.GL11.*;
import static convenience.Utility.*;

public class Engine
{
	
	public Player testPlayer;

	private boolean initComplete;
	
	public static HashMap< String, Byte > map = new HashMap< String, Byte >();

	public void start()
	{
		setNatives();
		setUpDisplay();
		setUpConfig();
		//	display until close requested or esc is pressed
		while( !Display.isCloseRequested() && !keyPressed( "escape" ) )
		{
			if( !initComplete )
			{
				init();
				initComplete = true;
			}
			mainLoop();
			Display.update();
			Display.sync( 60 );
		}
		Display.destroy();
	}
	
	public void setNatives()
	{
		String os = System.getProperty( "os.name" );
		if( os.toLowerCase().indexOf( "windows" ) != -1 ) System.setProperty( "org.lwjgl.librarypath", System.getProperty( "user.dir" ) + File.separator + "lwjgl-2.9.0" + File.separator + "native" + File.separator + "windows" );
		else if( os.toLowerCase().indexOf( "mac" ) != -1 ) System.setProperty( "org.lwjgl.librarypath", System.getProperty( "user.dir" ) + File.separator + "lwjgl-2.9.0" + File.separator + "native" + File.separator + "macosx" );
	}

	public void setUpDisplay()
	{
		try
		{
			for( DisplayMode current : Display.getAvailableDisplayModes() )
			{
				if( ( current.getWidth() == Display.getDesktopDisplayMode().getWidth() ) && ( current.getHeight() == Display.getDesktopDisplayMode().getHeight() ) )
				{
					System.out.println( "creating display" );
					Display.setDisplayMode( current );
					Display.setFullscreen( true );
					Display.create();
					System.out.println( "display created" );
					break;
				}
			}
		}
		catch( LWJGLException e )
		{
			System.out.println( "failed to enter fullscreen" );
			System.exit( 1 );
		}
	}

	public void setUpConfig()
	{
		glMatrixMode( GL_PROJECTION );
		glLoadIdentity();
		glFrustum( -Display.getWidth() / 2, Display.getWidth() / 2, -Display.getHeight() / 2, Display.getHeight() / 2, Display.getWidth() * 2, Display.getWidth() * 100 );
		glTranslated( 0, -Display.getHeight() / 2, -Display.getWidth() * 2 - .1 );
		glMatrixMode( GL_MODELVIEW );
		glEnable( GL_BLEND );
		glBlendFunc( GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA );
		glEnable( GL_DEPTH_TEST );
		glDepthFunc( GL_LEQUAL );
		glCullFace( GL_BACK );
		
		//	Invisible mouse
		Cursor emptyCursor = null;
		try
		{
			emptyCursor = new Cursor( 1, 1, 0, 0, 1, BufferUtils.createIntBuffer( 1 ), null );
			Mouse.setNativeCursor( emptyCursor );
		}
		catch( LWJGLException e1 ){}
	}

	private void loadMap( String mapName )
	{
		//	load map into an array of Strings
		try
		{
			Scanner mapReader = new Scanner( new File( "res/" + mapName + ".txt" ) );
			while( mapReader.hasNextLine() )
			{
				//	load map
				String[] currStr = mapReader.nextLine().split( "," );
				byte val = Byte.valueOf( currStr[1] );
				map.put( currStr[2] + "," + currStr[3] + "," + currStr[4], val );
			}
			mapReader.close();
			System.out.println( "\"" + mapName + "\" map loaded" );
		}
		catch( FileNotFoundException e )
		{
			System.out.println( "failed to load \"" + mapName + "\" map" );
		}

	}

	private void init()
	{
		testPlayer = new Player();
		loadMap( "pinpoint_ish_fps" );
	}

	private void mainLoop()
	{
		//	track fps
		manageFPS();
		
		//	background red
		glClearColor( .4f, .4f, .4f, 1 );
		glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
		
		//	draw map
		glPushMatrix();
		
		//	move player
		testPlayer.move();
		
		//	block size
		glScaled( 150, 150, 150 );
		
		glLineWidth( 3 );
		glColor3f( 0, .3f, .3f );
		glBegin( GL_QUADS );

		String[] binaryOf = new String[map.size()];
		int at = 0;
		for( String key : map.keySet() )
		{
			String directBinaryOf = Integer.toBinaryString( map.get( key ) + 128 );
			binaryOf[at] = String.format( "%08d", Integer.valueOf( directBinaryOf ) );
			at++ ;
		}
		at = 0;
		//	go through map - blocks
		for( String key : map.keySet() )
		{
			String[] currStr = key.split( "," );
			cubeOffsetX = Integer.valueOf( currStr[0] ) * 2;
			cubeOffsetY = Integer.valueOf( currStr[2] ) * 2;
			cubeOffsetZ = -Integer.valueOf( currStr[1] ) * 8 * 2;

			for( char b : binaryOf[at].toCharArray() )
			{
				if( b == '1' )
				{
					cube();
				}
				cubeOffsetZ -= 2;
			}
			at++ ;
		}
		cubeOffsetX = cubeOffsetY = cubeOffsetZ = 0;
		glEnd();

		glColor3f( 0, 0, 0 );
		glBegin( GL_LINES );
		at = 0;
		//	go through map - lines
		for( String key : map.keySet() )
		{
			String[] currStr = key.split( "," );
			cubeOffsetX = Integer.valueOf( currStr[0] ) * 2;
			cubeOffsetY = Integer.valueOf( currStr[2] ) * 2;
			cubeOffsetZ = -Integer.valueOf( currStr[1] ) * 8 * 2;

			for( char b : binaryOf[at].toCharArray() )
			{
				if( b == '1' )
				{
					lineCube();
				}
				cubeOffsetZ -= 2;
			}
			at++ ;
		}
		cubeOffsetX = cubeOffsetY = cubeOffsetZ = 0;
		glEnd();
		
		glPopMatrix();
		
		//	cross hare
		glColor3f( 0, 0, 0 );
		glBegin( GL_QUADS );
		
		glVertex2d( -10, Display.getHeight() / 2 - 1 );
		glVertex2d( 10, Display.getHeight() / 2 - 1 );
		glVertex2d( 10, Display.getHeight() / 2 + 1 );
		glVertex2d( -10, Display.getHeight() / 2 + 1 );
		
		glVertex2d( -1, Display.getHeight() / 2 - 10 );
		glVertex2d( 1, Display.getHeight() / 2 - 10 );
		glVertex2d( 1, Display.getHeight() / 2 + 10 );
		glVertex2d( -1, Display.getHeight() / 2 + 10 );
		
		glEnd();
		
		//	fps
		glPushMatrix();
		glTranslated( -Display.getWidth() / 2 + 50, Display.getHeight() - 150, 0 );
		glScaled( 50, -50, 1 );
		basicText( fps );
		glPopMatrix();
		
	}

}
