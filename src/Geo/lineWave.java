package Geo;

import processing.core.PApplet;
import igeo.IVec;
import igeo.IVec2;

public class lineWave {
		
	IVec2[] DirList;//awaited direction list
	
	
	IVec2 dir; //direction of wave movement, line is vertival to that
	double L=50;//how long the length of a wava is
	double phase=0;//current location
	double S=100;//how high the wave is
	boolean cir=false;//is it a one time wave or a circular wave
	IVec2 pos;//one point on the line wave
	
	Line2d l;
	
	float wid, height;
	
	
	public lineWave(){
		//this.l = new Line2d(IVec);
	}
	
	public lineWave(IVec2 pos, int dir, double s){
		//this.l = new Line2d(IVec);
		DirList = new IVec2[8];
		DirList[0] = new IVec2(0,-1);
		DirList[1] = new IVec2(0,1);
		DirList[2] = new IVec2(-1,0);
		DirList[3] = new IVec2(1,0);
		DirList[4] = new IVec2(-1,1);
		DirList[5] = new IVec2(-1,-1);
		DirList[6] = new IVec2(1,-1);
		DirList[7] = new IVec2(1,1);

		this.dir = this.DirList[dir];
		this.S = s;
		this.pos = pos;
		
		IVec2 lv = this.dir.dup().rot(Math.PI/2);
		IVec2 vv = this.pos.dup().add(lv);
		this.l = new Line2d(this.pos,vv);
	}

	public double zLocation(IVec2 v){
		IVec2 vv = this.l.VerticalPoint(v);
		double dis = vv.dist(v);
		return Math.cos(dis/this.L-this.phase)*this.S;
		
	}

	public void resetPos(double x, double y){
		this.pos.x = x;
		this.pos.y = y;
		IVec2 lv = this.dir.dup().rot(Math.PI/2);
		IVec2 vv = this.pos.dup().add(lv);
		this.l = new Line2d(this.pos,vv);
		
	}
	
	public void setWH(float wid2,float hei){
		this.wid = wid2;
		this.height = hei;
	}
	
	public void resetDir(IVec2 vv){
		IVec2 dd = new IVec2();
		double angle = Double.MAX_VALUE;
		int count = 0;
		
		for (int i=0;i<this.DirList.length;i++){
			if (DirList[i].angle(vv)<angle){
				angle = DirList[i].angle(vv);
				dd = DirList[i];
				count = i;
			}
			
			
		}
		if (count==0) this.pos = new IVec2(wid/2,0);
		if (count==1) this.pos = new IVec2(wid/2,height);
		if (count==2) this.pos = new IVec2(0,height/2);
		if (count==3) this.pos = new IVec2(wid,height/2);
		if (count==4) this.pos = new IVec2(0,height);
		if (count==5) this.pos = new IVec2(0,0);
		if (count==6) this.pos = new IVec2(wid,0);
		if (count==7) this.pos = new IVec2(wid,height);

			
			
			
			
			
		this.dir = dd;
		IVec2 lv = this.dir.dup().rot(Math.PI/2);
		IVec2 vvv = this.pos.dup().add(lv);
		this.l = new Line2d(this.pos,vvv);

	}
	
	public void time(double i){
		this.phase+=i;
		
	}
	
	public void affect(grid[][] gs){
		for (grid[] gg : gs){
			for(grid g : gg){
				g.setZ(this.zLocation(g.location2d()));
		
				
			}
		}

		
		
		
	}
	
	
	public void draw(PApplet app){
		app.pushStyle();
		//app.line(x1, y1, x2, y2);
		this.l.draw(app);
		
		
		app.popStyle();
		
	}


	
	
	
	
}
