package player;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
<<<<<<< HEAD
import core.Engine;
=======
>>>>>>> origin/Class_Stuff
import static convenience.KeyboardUtils.*;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import static org.lwjgl.opengl.GL11.*;

public class Player
{

	public double xPos, yPos, zPos, camAngX, camAngY, turnSensitivity, turnSpeed, mouseSensitivity, moveSpeed;

	private Robot mouseControl;

	public Player()
	{
		xPos = yPos = zPos = camAngX = camAngY = 0;

		//	inverse increasing
		turnSensitivity = 1; // default 1, recommended to stay such
		turnSpeed = 15; // default 20 ( how fast everything moves )
		mouseSensitivity = 0; // default 20 ( box for mouse to move in )

		//	regular
		moveSpeed = .15; // default .5

		try
		{
			mouseControl = new Robot();
		}
		catch( AWTException e )
		{}
	}

	public void move()
	{
		//	zoom testing
		turnSpeed = Mouse.isButtonDown( 1 ) ? 60 : 15;
		moveSpeed = Mouse.isButtonDown( 1 ) ? .15 * 2 / 5 : .15;
		
		
		//	turning algorithm
		double camAng = toRadians( camAngX + 90 );
		if( keyPressed( "w" ) )
		{
			zPos += ( sin( camAng ) ) * moveSpeed;
			xPos -= cos( camAng ) * moveSpeed;
		}
		if( keyPressed( "a" ) )
		{
			zPos += ( sin( camAng - PI / 2 ) ) * moveSpeed;
			xPos -= cos( camAng - PI / 2 ) * moveSpeed;
		}
		if( keyPressed( "s" ) )
		{
			zPos -= ( sin( camAng ) ) * moveSpeed;
			xPos += cos( camAng ) * moveSpeed;
		}
		if( keyPressed( "d" ) )
		{
			zPos -= ( sin( camAng - PI / 2 ) ) * moveSpeed;
			xPos += cos( camAng - PI / 2 ) * moveSpeed;
		}

		//	moving algorithm
		double absMouseX = MouseInfo.getPointerInfo().getLocation().x;
		double absMouseY = MouseInfo.getPointerInfo().getLocation().y;
		if( abs( - ( Display.getX() + Display.getWidth() / 2 - absMouseX ) / mouseSensitivity ) > turnSensitivity ) camAngX += - ( Display.getX() + Display.getWidth() / 2 - absMouseX ) / turnSpeed;
		if( abs( - ( Display.getY() + Display.getHeight() / 2 - absMouseY ) / mouseSensitivity ) > turnSensitivity ) camAngY += - ( Display.getY() + Display.getHeight() / 2 - absMouseY ) / turnSpeed / 2;
		if( absMouseX >= Display.getX() + Display.getWidth() / 2 + mouseSensitivity ) mouseControl.mouseMove( Display.getX() + Display.getWidth() / 2 + (int) mouseSensitivity, MouseInfo.getPointerInfo().getLocation().y );
		if( absMouseX <= Display.getX() + Display.getWidth() / 2 - mouseSensitivity ) mouseControl.mouseMove( Display.getX() + Display.getWidth() / 2 - (int) mouseSensitivity, MouseInfo.getPointerInfo().getLocation().y );
		if( absMouseY >= Display.getY() + Display.getHeight() / 2 + mouseSensitivity ) mouseControl.mouseMove( MouseInfo.getPointerInfo().getLocation().x, Display.getY() + Display.getHeight() / 2 + (int) mouseSensitivity );
		if( absMouseY <= Display.getY() + Display.getHeight() / 2 - mouseSensitivity ) mouseControl.mouseMove( MouseInfo.getPointerInfo().getLocation().x, Display.getY() + Display.getHeight() / 2 - (int) mouseSensitivity );

		glRotated( camAngY, 1, 0, 0 );
		glRotated( camAngX, 0, 1, 0 );

		//	block collision
		if( xPos >= 0 && yPos >= 0 && zPos >= 0 )
		{
			for( int i = 0; i < Engine.map.size(); i++ )
				//while( Engine.map.get( i ).get( (int) ( ( -xPos + 100 ) / 2 / 150. ) + "," + (int) ( zPos / 150. / 16 ) + "," + (int) ( -yPos / 2 / 150. ) ) != null && String.format( "%08d", Integer.valueOf( Integer.toBinaryString( Engine.map.get( i ).get( (int) ( ( -xPos + 100 ) / 2 / 150. ) + "," + (int) ( zPos / 150. / 2 / 8 ) + "," + (int) ( -yPos / 2 / 150. ) ) + 128 ) ) ).toCharArray()[(int) ( zPos / 150. ) % 8] == '1' )
				while( Engine.map.get( i ).get( (int) ( xPos - 1 ) + "," + (int) ( ( zPos ) / 8 ) + "," + (int) yPos ) != null && String.format( "%08d", Integer.valueOf( Integer.toBinaryString( Engine.map.get( i ).get( (int) ( xPos - 1 ) + "," + (int) ( ( zPos ) / 8 ) + "," + (int) yPos ) + 128 ) ) ).toCharArray()[(int) ( ( zPos ) ) % 8] == '1' )
					yPos++;
			if( yPos > 0 ) yPos--;
		}

		glTranslated( -xPos * 150 * 2, -yPos * 150 * 2 - 150, zPos * 150 * 2 );
	}

}