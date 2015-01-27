package Geo;

import processing.core.PApplet;
import igeo.IVec;
import igeo.IVec2;

public class ptWave {
	IVec dir=new IVec(0,1,0); //direction of wave movement, line is vertival to that
	double L=50;//how long the length of a wava is
	double phase=0;//current location
	double S=100;//how high the wave is
	boolean cir=false;//is it a one time wave or a circular wave
	IVec pos = new IVec();//position of the wave point
	
	public ptWave(){
		
		
	}
	
	public void setLocation(double x, double y){
		this.pos.x = x;
		this.pos.y = y;
		
	}
	
	public void time(double i){
		this.phase+=i;
		
	}
	
	public double zLocation(IVec2 v){
		double dis = this.dist(v);
		return Math.cos(dis/this.L-this.phase)*this.S;
		
	}
	
	public void affect(grid[][] gs){
		for (grid[] gg : gs){
			for(grid g : gg){
				g.setZ(this.zLocation(g.location2d()));
		
				
			}
		}

		
		
		
	}
	
	public double dist(IVec2 v){
		return this.location2d().dist(v);
		
	}
	
	public double dist(grid g){
		return this.location2d().dist(g.location2d());
		
	}
	
	public IVec location(){
		return this.pos;
		
	}
	
	public IVec2 location2d(){
		return this.pos.to2d();
	}
	
	public void draw(PApplet app){
		app.pushStyle();
		app.stroke(255,0,0);
		
		app.fill(255,0,0);
		app.ellipse((float)this.pos.x, (float)this.pos.y, 10, 10);		
		
		
		app.popStyle();
	}
	
	
}
