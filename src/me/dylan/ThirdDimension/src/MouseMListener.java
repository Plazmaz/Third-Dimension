package me.dylan.ThirdDimension.src;

import java.awt.Point;
import java.awt.event.MouseMotionListener;

public class MouseMListener implements MouseMotionListener {
	MouseCListener clicklistener;
	Point distfromorigin = new Point(0,0);
	public MouseMListener(MouseCListener c) {
		clicklistener = c;
	}
	@Override
	public void mouseDragged(java.awt.event.MouseEvent arg0) {
		distfromorigin.x=clicklistener.mOrigin.x-arg0.getX();
		distfromorigin.y=clicklistener.mOrigin.y-arg0.getY();
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
