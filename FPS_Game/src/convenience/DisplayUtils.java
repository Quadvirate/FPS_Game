package convenience;

import static org.lwjgl.opengl.GL11.*;

public class DisplayUtils
{

	public static int cubeOffsetX, cubeOffsetY, cubeOffsetZ;

	public static void cube()
	{
		//		glBegin( GL_QUADS );

		//		glNormal3d( 0, 1, 0 );
		//	top
		glTexCoord2d( 0, 0 );
		glVertex3f( 1.0f + cubeOffsetX, 1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );
		glTexCoord2d( 1, 0 );
		glVertex3f( -1.0f + cubeOffsetX, 1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );
		glTexCoord2d( 1, 1 );
		glVertex3f( -1.0f + cubeOffsetX, 1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );
		glTexCoord2d( 0, 1 );
		glVertex3f( 1.0f + cubeOffsetX, 1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );

		//		glNormal3d( 0, -1, 0 );
		//	bottom
		glTexCoord2d( 0, 0 );
		glVertex3f( 1.0f + cubeOffsetX, -1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );
		glTexCoord2d( 1, 0 );
		glVertex3f( -1.0f + cubeOffsetX, -1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );
		glTexCoord2d( 1, 1 );
		glVertex3f( -1.0f + cubeOffsetX, -1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );
		glTexCoord2d( 0, 1 );
		glVertex3f( 1.0f + cubeOffsetX, -1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );

		//		glNormal3d( 0, 0, 1 );
		//	near
		glTexCoord2d( 0, 0 );
		glVertex3f( 1.0f + cubeOffsetX, 1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );
		glTexCoord2d( 1, 0 );
		glVertex3f( -1.0f + cubeOffsetX, 1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );
		glTexCoord2d( 1, 1 );
		glVertex3f( -1.0f + cubeOffsetX, -1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );
		glTexCoord2d( 0, 1 );
		glVertex3f( 1.0f + cubeOffsetX, -1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );

		//		glNormal3d( 0, 0, -1 );
		//	far
		glTexCoord2d( 0, 1 );
		glVertex3f( 1.0f + cubeOffsetX, -1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );
		glTexCoord2d( 1, 1 );
		glVertex3f( -1.0f + cubeOffsetX, -1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );
		glTexCoord2d( 1, 0 );
		glVertex3f( -1.0f + cubeOffsetX, 1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );
		glTexCoord2d( 0, 0 );
		glVertex3f( 1.0f + cubeOffsetX, 1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );

		//		glNormal3d( -1, 0, 0 );
		//	left
		glTexCoord2d( 0, 0 );
		glVertex3f( -1.0f + cubeOffsetX, 1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );
		glTexCoord2d( 1, 0 );
		glVertex3f( -1.0f + cubeOffsetX, 1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );
		glTexCoord2d( 1, 1 );
		glVertex3f( -1.0f + cubeOffsetX, -1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );
		glTexCoord2d( 0, 1 );
		glVertex3f( -1.0f + cubeOffsetX, -1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );

		//		glNormal3d( 1, 0, 0 );
		//	right
		glTexCoord2d( 1, 0 );
		glVertex3f( 1.0f + cubeOffsetX, 1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );
		glTexCoord2d( 0, 0 );
		glVertex3f( 1.0f + cubeOffsetX, 1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );
		glTexCoord2d( 0, 1 );
		glVertex3f( 1.0f + cubeOffsetX, -1.0f + cubeOffsetY, 1.0f + cubeOffsetZ );
		glTexCoord2d( 1, 1 );
		glVertex3f( 1.0f + cubeOffsetX, -1.0f + cubeOffsetY, -1.0f + cubeOffsetZ );

		//		glEnd();

		//		//	only set color for the lines ( push/popStyle in processing )
		//		glPushAttrib( GL_CURRENT_BIT );
		//		glColor3f( 0, 0, 0 );
		//		
		//		//	same as color but with the line width
		//		glPushAttrib( GL_LINE_BIT );
		//		glLineWidth( 5 );
		//
		//		glBegin( GL_LINES );
		//
		//		glVertex3f( -1.005f, -1.005f, -1.005f );
		//		glVertex3f( -1.005f, -1.005f, 1.005f );
		//		glVertex3f( -1.005f, -1.005f, 1.005f );
		//		glVertex3f( 1.005f, -1.005f, 1.005f );
		//		glVertex3f( 1.005f, -1.005f, 1.005f );
		//		glVertex3f( 1.005f, -1.005f, -1.005f );
		//		glVertex3f( 1.005f, -1.005f, -1.005f );
		//		glVertex3f( -1.005f, -1.005f, -1.005f );
		//		
		//		glVertex3f( -1.005f, 1.005f, -1.005f );
		//		glVertex3f( -1.005f, 1.005f, 1.005f );
		//		glVertex3f( -1.005f, 1.005f, 1.005f );
		//		glVertex3f( 1.005f, 1.005f, 1.005f );
		//		glVertex3f( 1.005f, 1.005f, 1.005f );
		//		glVertex3f( 1.005f, 1.005f, -1.005f );
		//		glVertex3f( 1.005f, 1.005f, -1.005f );
		//		glVertex3f( -1.005f, 1.005f, -1.005f );
		//		
		//		glVertex3f( -1.005f, -1.005f, -1.005f );
		//		glVertex3f( -1.005f, 1.005f, -1.005f );
		//		glVertex3f( -1.005f, -1.005f, 1.005f );
		//		glVertex3f( -1.005f, 1.005f, 1.005f );
		//		glVertex3f( 1.005f, -1.005f, 1.005f );
		//		glVertex3f( 1.005f, 1.005f, 1.005f );
		//		glVertex3f( 1.005f, -1.005f, -1.005f );
		//		glVertex3f( 1.005f, 1.005f, -1.005f );
		//
		//		glEnd();
		//		
		//		glPopAttrib();
		//		
		//		glPopAttrib();

	}

	public static void lineCube()
	{
		//		glBegin( GL_LINES );

		glVertex3f( -1.005f + cubeOffsetX, -1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );
		glVertex3f( -1.005f + cubeOffsetX, -1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( -1.005f + cubeOffsetX, -1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, -1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, -1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, -1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, -1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );
		glVertex3f( -1.005f + cubeOffsetX, -1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );

		glVertex3f( -1.005f + cubeOffsetX, 1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );
		glVertex3f( -1.005f + cubeOffsetX, 1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( -1.005f + cubeOffsetX, 1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, 1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, 1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, 1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, 1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );
		glVertex3f( -1.005f + cubeOffsetX, 1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );

		glVertex3f( -1.005f + cubeOffsetX, -1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );
		glVertex3f( -1.005f + cubeOffsetX, 1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );
		glVertex3f( -1.005f + cubeOffsetX, -1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( -1.005f + cubeOffsetX, 1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, -1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, 1.005f + cubeOffsetY, 1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, -1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );
		glVertex3f( 1.005f + cubeOffsetX, 1.005f + cubeOffsetY, -1.005f + cubeOffsetZ );

		//		glEnd();
	}
	
}