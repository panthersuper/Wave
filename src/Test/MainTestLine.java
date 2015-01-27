package Test;

import igeo.IVec2;
import Geo.grid;
import Geo.lineWave;
import Geo.ptWave;
import processing.core.PApplet;

public class MainTestLine extends PApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float wid = 500;
	float hei = 500;
	
	int wnum = 50;
	int hnum = 50;
	
	grid[][] gs;
	lineWave line;
	IVec2 vReco;
	int count = 0;
	
	public void setup(){
		size((int)wid,(int)hei);
		gs =  new grid[wnum][hnum];
		line = new lineWave(new IVec2(mouseX,mouseY),0,100);
		line.setWH(wid, hei);
		for (int i=0;i<wnum;i++){
			for(int j=0;j<hnum;j++){
				gs[i][j] = new grid(wid/wnum*i,hei/hnum*j,0);
			}
		}
		vReco = new IVec2(mouseX,mouseY);

	}
	
	public void draw(){
		count ++;
		
		if(count%5==0){
		//line.resetPos(mouseX,mouseY);
		IVec2 dir = new IVec2(mouseX-vReco.x,mouseY-vReco.y);
		//dir.unit();
		if (dir.x!=0 && dir.y!=0)
		line.resetDir(dir);
		
		vReco = new IVec2(mouseX,mouseY);
		}
		
		line.time(0.1f);
		line.affect(gs);
		background(255);
		for (grid[] gg : gs){
			for(grid g : gg){
				g.draw(this);
				
			}
		}
		
		line.draw(this);

	}
	
	
}
