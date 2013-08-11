package me.dylan.ThirdDimension.src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Line3D {
	Point3D Parent=null,end=null;
	public Line3D(Point3D Parent,Point3D end) {
		this.Parent=Parent;
		this.end=end;
	}
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.draw(new Line2D.Double(Parent.x2d,Parent.y2d,end.x2d,end.y2d));
	}
}
