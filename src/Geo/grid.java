package Geo;

import processing.core.PApplet;
import igeo.IVec;
import igeo.IVec2;

public class grid {
	
	IVec v;
	
	
	public grid(){
		
	}
	
	public grid(IVec v){
		this.v = v;		
	}
	
	public grid(double x,double y, double z){
		this.v = new IVec(x,y,z);
	}
	
	public void setZ(double z){
		this.v.z = z;
	}
	
	public IVec location(){
		return this.v;
		
	}
	
	public IVec2 location2d(){
		return this.v.to2d();
	}
	
	public void draw(PApplet app){
		app.pushStyle();
		app.stroke(0);
		app.strokeWeight(PApplet.map((float)this.v.z, -100, 100, 0, 10));
		app.point((float)v.x, (float)v.y);		
		
		app.popStyle();
	}
	
	public void draw3d(PApplet app){
		app.pushStyle();
		app.stroke(0);
		//app.strokeWeight(PApplet.map((float)this.v.z, -10, 10, 0, 10));
		//float zz = PApplet.map((float)v.z, start1, stop1, start2, stop2)
		app.line((float)v.x, (float)v.y,0,(float)v.x, (float)v.y,(float)v.z+100);		
		
		app.popStyle();

	}

	
	
	
}
