package startup;

import static convenience.Utility.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class LoadMenu
{

	public void start()
	{
		setUpDisplay();
		setUpConfig();
		glTranslated( 0, Display.getHeight() / 2, -500 );
		while( !Display.isCloseRequested() && !keyPressed( "escape" ) )
		{
			mainLoop();
			Display.update();
			Display.sync( 60 );
		}
		Display.destroy();
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
		catch( LWJGLException e1 )
		{}
	}

	private double time, size = 200, speed;

	public void mainLoop()
	{
		
		if( keyPressed( "space" ) ) { time = 0; size = 200; speed = 0; }
			
		speed += .004;
		time += speed;
			
		if( time >= 405 )
		{
			time = 405;
			if( size < 1600 ) size += speed * 8;
		}

		background( 80, 80, 80 );

		glPushMatrix();

		if( time >= 90 ) glRotated( time - 90, 0, 0, 1 );

		glPushMatrix();

		glScaled( size, size, 0 );

		glRotated( time, -1, 1, 0 );

		cube( 1 - (float)size / 400 / 4 );
		glColor4f( .5f, .5f, .5f, (float)1 - (float)size / 400 / 4 );
		lineCube();

		glPopMatrix();

		glPopMatrix();
		
		
		
		glPushMatrix();
		
		glLineWidth( 3 );
		glTranslated( -275, -400, 0 );
		glScaled( 75, -20, 10 );
		glColor4f( .6f, .6f, .6f, (float)time / 405 * 2 * ( 1 - (float)size / 400 / 4 ) );
		basicText( "Quadvirate" );
		glTranslated( 0, 2.5, 0 );
		glScaled( .9, 1, 1 );
		basicText( "  Studios" );
		
		glPopMatrix();
	}

	private void cube( float alpha )
	{
		glBegin( GL_QUADS );
		
		glColor4f( 0, 0, 0, alpha );
		glNormal3d( 0, 1, 0 );
		glVertex3f( 1.0f, 1.0f, -1.0f );
		glVertex3f( -1.0f, 1.0f, -1.0f );
		glVertex3f( -1.0f, 1.0f, 1.0f );
		glVertex3f( 1.0f, 1.0f, 1.0f );
		
		glColor4f( 0, 0, 0, alpha );
		glNormal3d( 0, -1, 0 );
		glVertex3f( 1.0f, -1.0f, 1.0f );
		glVertex3f( -1.0f, -1.0f, 1.0f );
		glVertex3f( -1.0f, -1.0f, -1.0f );
		glVertex3f( 1.0f, -1.0f, -1.0f );

		glColor4f( 0, 0, 0, alpha );
		glNormal3d( 0, 0, 1 );
		glVertex3f( 1.0f, 1.0f, 1.0f );
		glVertex3f( -1.0f, 1.0f, 1.0f );
		glVertex3f( -1.0f, -1.0f, 1.0f );
		glVertex3f( 1.0f, -1.0f, 1.0f );

		//	center color
		glColor4f( 0, .2f, .2f, alpha );
		glNormal3d( 0, 0, -1 );
		glVertex3f( 1.0f, -1.0f, -1.0f );
		glVertex3f( -1.0f, -1.0f, -1.0f );
		glVertex3f( -1.0f, 1.0f, -1.0f );
		glVertex3f( 1.0f, 1.0f, -1.0f );

		glColor4f( 0, 0, 0, alpha );
		glNormal3d( -1, 0, 0 );
		glVertex3f( -1.0f, 1.0f, 1.0f );
		glVertex3f( -1.0f, 1.0f, -1.0f );
		glVertex3f( -1.0f, -1.0f, -1.0f );
		glVertex3f( -1.0f, -1.0f, 1.0f );

		glColor4f( 0, 0, 0, alpha );
		glNormal3d( 1, 0, 0 );
		glVertex3f( 1.0f, 1.0f, -1.0f );
		glVertex3f( 1.0f, 1.0f, 1.0f );
		glVertex3f( 1.0f, -1.0f, 1.0f );
		glVertex3f( 1.0f, -1.0f, -1.0f );

		glEnd();
	}

	private void background( double r, double g, double b )
	{
		glClearColor( (float) ( r / 255. ), (float) ( g / 255. ), (float) ( b / 255. ), 1 );
		glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
	}

}
