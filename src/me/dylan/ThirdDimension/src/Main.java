package me.dylan.ThirdDimension.src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	private static final long serialVersionUID = -1753025786916667101L;
	ArrayList<Poly3D> faces = new ArrayList<Poly3D>();
	ArrayList<Poly3D> sortedfaces = new ArrayList<Poly3D>();
	JFrame projector=new JFrame("ThirdDimension Projector");
	int width,height;
	double FOV=200;
	double scale;
	ArrayList<Point3D> Vertices = new ArrayList<Point3D>();
	ArrayList<Line3D> Lines = new ArrayList<Line3D>();
	ArrayList<Cube3D> cubes = new ArrayList<Cube3D>();

	MouseCListener clisten = new MouseCListener();
	MouseMListener mlisten = new MouseMListener(clisten);
	public Main() {
		projector.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		width = Toolkit.getDefaultToolkit().getScreenSize().width;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;
		projector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		projector.addMouseListener(clisten);
		projector.addMouseMotionListener(mlisten);
		projector.add(this);
//		projector.setResizable(false);
		projector.setVisible(true);
		cubes.add(new Cube3D(0, 0, 0, 100, 100, 100, this));
//		faces.add(new Poly3D(new Point3D[] {
//				new Point3D(100, 50, 100, this),
//				new Point3D(500, 50, 100, this),
//				new Point3D(500, 50, 700, this)
//		}, this, Color.GRAY));
		getLoop().start();
//		faces.add(new Poly3D(new Point3D[] {
//				new Point3D(100, 50, 100, this),
//				new Point3D(500, 50, 100, this),
//				new Point3D(500, 50, 700, this)
//		}, this, Color.GRAY));
//		getLoop().start();
	}
	public static void main(String[] args) {
		new Main();
	}
	public Thread getLoop() {
		return new Thread(new Runnable() {
			public void run() {
				while(true) {
					for(Point3D p : Vertices) {

//						p.rotateX(0.04);
						p.update();
					}
					
					for(Poly3D face : faces) {
						face.update(mlisten.distfromorigin);
						
					}
					for(Cube3D cube : cubes) {
						cube.update(mlisten.distfromorigin);
						
					}
//					mlisten.distfromorigin=new Point(0,0);

				     Collections.sort(faces, new Comparator<Object>() {
							public int compare(Object o, Object o2) {
								Poly3D tmp = (Poly3D) o;
								Poly3D tmp2 = (Poly3D) o2;
								return (int) (tmp2.farthestpoint.posz - tmp.farthestpoint.posz);
							}
						});
					  Collections.sort(cubes, new Comparator<Object>() {
							public int compare(Object o, Object o2) {
								Cube3D tmp = (Cube3D) o;
								Cube3D tmp2 = (Cube3D) o2;
								return (int) (tmp2.furthestface.farthestpoint.posz - tmp.furthestface.farthestpoint.posz);
							}
						});
					projector.repaint();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.red);
		for(int i=0;i<Vertices.size();i++) {
			Vertices.get(i).paint(g);
		}
		for(Poly3D face : faces) {
			face.paint(g);
		}
		for(Line3D l : Lines) {
			l.paint(g);
		}
		for(int i=0;i<cubes.size();i++) {
			cubes.get(i).paint(g);
		}
	}
	public Point convert3DTo2D(double posx, double posy, double posz) {
		scale = FOV/(posz+FOV);
		Point tmp = new Point();
		tmp.setLocation((posx*scale)+width/2,(posy*scale)+height/2);
		return tmp;
	}
	@SuppressWarnings("unchecked")
	public void InsertRectIntoScene(Object[] tmp) {
		Lines.addAll((ArrayList<Line3D>) tmp[0]);
		Vertices.addAll((ArrayList<Point3D>) tmp[1]);
	}

}
