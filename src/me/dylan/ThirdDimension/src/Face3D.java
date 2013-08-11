package me.dylan.ThirdDimension.src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Face3D {
	ArrayList<Poly3D> polys = new ArrayList<Poly3D>();
	Point3D farthestpoint = new Point3D(0, 0, 0, null);
	
	public Face3D(int x, int y, int z, int width, int height, int depth, Main m,Color c) {
		polys.add(new Poly3D(new Point3D[] {
				new Point3D(x, y, z, m),
				new Point3D(x+width, y+height, z+depth, m),
				new Point3D(x, y+height, z, m)
		}, m, c));
		polys.add(new Poly3D(new Point3D[] {
				new Point3D(x, y, z, m),
				new Point3D(x+width, y, z+depth, m),
				new Point3D(x+width, y+height, z+depth, m)
		}, m, c));

	}

	public void update(Point distfromorigin) {
		@SuppressWarnings("unchecked")
		ArrayList<Poly3D> tv = (ArrayList<Poly3D>) polys.clone();
	     Collections.sort(tv, new Comparator<Object>() {
			public int compare(Object o, Object o2) {
				Poly3D tmp = (Poly3D) o;
				Poly3D tmp2 = (Poly3D) o2;
				return (int) (tmp2.farthestpoint.posz - tmp.farthestpoint.posz);
			}
		});
	     farthestpoint = tv.get(0).farthestpoint;
		
		for(Poly3D p : polys) {
			p.update(distfromorigin);
			

		}
	}
	public void paint(Graphics g) {
		for(Poly3D p : polys) {
			g.setColor(p.color);
			p.paint(g);
		}
	}
}
