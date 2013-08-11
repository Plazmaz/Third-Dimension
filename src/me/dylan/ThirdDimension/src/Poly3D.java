package me.dylan.ThirdDimension.src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Poly3D {
	Polygon face=new Polygon();
	ArrayList<Line3D> Lines = new ArrayList<Line3D>();
	ArrayList<Point3D> Vertices = new ArrayList<Point3D>();
	Point3D farthestpoint = new Point3D(0, 0, 0, null);
	Color color;
	public Poly3D(Point3D verts[],Main m,Color c) {
		color=c;
		Vertices.add(verts[0]);
		Vertices.add(verts[1]);
		Vertices.add(verts[2]);
//		for(int i=0;i<Vertices.size();i++) {			
//			Vertices.get(i).update();
//			Point2D tmp = new Point2D.Double();
//			tmp.setLocation((int)Vertices.get(i).x2d, (int)Vertices.get(i).y2d);
//			face.addPoint((int)Math.round(tmp.getX()),(int)Math.round(tmp.getY()));
//		}
		
		
	}

	public void paint(Graphics g) {
		g.setColor(color);
		Graphics2D g2d = (Graphics2D)g;
		face=new Polygon();
		for(int i=0;i<Vertices.size();i++) {			
			Vertices.get(i).update();
			Point2D tmp = new Point2D.Double();
			tmp.setLocation((int)Vertices.get(i).x2d, (int)Vertices.get(i).y2d);
			face.addPoint((int)tmp.getX(),(int)Math.round(tmp.getY()));
		}
		g2d.fillPolygon(face);
		g.setColor(Color.GRAY);
		g2d.drawPolygon(face);
		
	}
	public void update(Point distfromorigin) {
		for(Point3D p : Vertices) {
			p.update();
			p.rotateX(distfromorigin.x*0.001);
			p.rotateZ(distfromorigin.y*0.001);
		}
		
		
		@SuppressWarnings("unchecked")
		ArrayList<Point3D> tv = (ArrayList<Point3D>) Vertices.clone();
	     Collections.sort(tv, new Comparator<Object>() {
			public int compare(Object o, Object o2) {
				Point3D tmp = (Point3D) o;
				Point3D tmp2 = (Point3D) o2;
				return (int) (tmp2.posz - tmp.posz);
			}
		});
	     farthestpoint = tv.get(0);
	}

}
