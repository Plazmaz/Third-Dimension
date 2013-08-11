package me.dylan.ThirdDimension.src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Point3D {
	double posx=0,posy=0,posz=0,x2d=0,y2d=0;
	Main main=null;
	public Point3D(double x, double y, double z,Main m) {
		posx=x;
		posy=y;
		posz=z;
		main=m;
	}
	public void update() {
		x2d=main.convert3DTo2D(posx, posy, posz).x;
		y2d=main.convert3DTo2D(posx, posy, posz).y;
//		rotateZ(0.05);
//		if(posz<=0)
//			posz+=5;
		
	}
    public void rotateX(double angle) {

        double cosRY = Math.cos(angle);
        double sinRY = Math.sin(angle);

        double tempz = posz; 
        double tempy = posy; 

        posy= ((tempy*cosRY)+(tempz*sinRY));
        posz= ((tempy*-sinRY)+(tempz*cosRY));

    }
    public void rotateY(double angle) {

        double cosRY = Math.cos(angle);
        double sinRY = Math.sin(angle);

        double tempx = posx; 
        double tempz = posz; 

        posy= ((tempz*cosRY)+(tempx*sinRY));
        posx= ((tempz*-sinRY)+(tempx*cosRY));

    }
    public void rotateZ(double angle) {

        double cosRY = Math.cos(angle);
        double sinRY = Math.sin(angle);

        double tempx = posx; 
        double tempy = posy; 

        posy= ((tempy*cosRY)+(tempx*sinRY));
        posx= ((tempy*-sinRY)+(tempx*cosRY));

    }
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D tmp = new Rectangle2D.Double(x2d, y2d, main.scale+5, main.scale+5);
		g2d.fill(tmp);
	}

}
