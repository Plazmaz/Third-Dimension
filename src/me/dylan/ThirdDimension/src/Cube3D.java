package me.dylan.ThirdDimension.src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Cube3D {
	ArrayList<Face3D> faces = new ArrayList<Face3D>();
	Face3D furthestface;
	
	public Cube3D(int x, int y, int z, int width, int height, int depth,Main m) {
		faces.add((new Face3D(x, y, z, width, height, 0, m,Color.RED)));
		faces.add((new Face3D(x, y, z, 0, height, depth, m,Color.YELLOW)));
		faces.add((new Face3D(x, y, z+depth, width, height, 0, m,Color.BLUE)));
		faces.add((new Face3D(x+width, y, z, 0, height, depth, m,Color.WHITE)));
		faces.add((new Face3D(x, y, z, 0, 0, depth, m,Color.MAGENTA)));
	}
	public void update(Point distfromorigin) {
		for(Face3D f : faces) {
			f.update(distfromorigin);
		}
		  Collections.sort(faces, new Comparator<Object>() {
				public int compare(Object o, Object o2) {
					Face3D tmp = (Face3D) o;
					Face3D tmp2 = (Face3D) o2;
					return (int) (tmp2.farthestpoint.posz - tmp.farthestpoint.posz);
				}
			});
		  furthestface=faces.get(0);
		Collections.sort(faces, new Comparator<Object>() {
			public int compare(Object o, Object o2) {
				Face3D tmp = (Face3D) o;
				Face3D tmp2 = (Face3D) o2;
				return (int) (tmp2.farthestpoint.posz - tmp.farthestpoint.posz);
			}
		});
	}
	public void paint(Graphics g) {
		for(Face3D f : faces) {
			f.paint(g);
		}
	}
}
