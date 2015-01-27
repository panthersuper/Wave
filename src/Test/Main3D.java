package Test;

import Geo.grid;
import Geo.ptWave;
import processing.core.PApplet;
import peasy.*;
import processing.serial.*;

public class Main3D extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PeasyCam cam;

	float wid = 500;
	float hei = 500;
	
	int wnum = 50;
	int hnum = 50;
	
	grid[][] gs;
	ptWave pt;
	
	public void setup(){
		size((int)wid,(int)hei,P3D);
		cam = new PeasyCam(this, 1000);
		cam.lookAt((int)wid/2,(int)hei/2, 0);
		cam.setMinimumDistance(50);
		cam.setMaximumDistance(500000);
		cam.setRotations(100, 0, 0);

		pt = new ptWave();
		gs =  new grid[wnum][hnum];
		
		for (int i=0;i<wnum;i++){
			for(int j=0;j<hnum;j++){
				gs[i][j] = new grid(wid/wnum*i,hei/hnum*j,0);
			}
		}
		
		
	}
	
	public void draw(){
		pt.setLocation(mouseX,mouseY);
		pt.time(0.1f);
		pt.affect(gs);
		background(255);
		for (grid[] gg : gs){
			for(grid g : gg){
				g.draw3d(this);
				
			}
		}
		
		pt.draw(this);
	}
	
	
	
	
	
}
