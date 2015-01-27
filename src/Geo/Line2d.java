package Geo;

import processing.core.PApplet;
import igeo.IVec2;

public class Line2d {
	public double A, B, C;
	public IVec2 a, b;

	public Line2d() {

	}

	public Line2d(IVec2 a, IVec2 b) {
		this.a = a;
		this.b = b;
		this.A = a.y - b.y;
		this.B = b.x - a.x;
		this.C = a.x * b.y - a.y * b.x;

	}

	public Line2d(double A, double B, double C) {
		this.A = A;
		this.B = B;
		this.C = C;
	}

	public IVec2 VerticalPoint(IVec2 p) {
		IVec2 v = new IVec2();
		double tmp = A * A + B * B;
		v.x = (B * B * p.x - A * B * p.y - A * C) / tmp;
		v.y = (A * A * p.y - A * B * p.x - B * C) / tmp;

		return v;
	}

	public boolean contain(IVec2 p) {
		// judge edge contain p. on edge doesn't count as contain

		if (Math.abs(A * p.x + B * p.y + C) > 0.0001)
			return false;
		else {
			if (this.a.eq(p) || this.b.eq(p))
				return false;
			else {
				IVec2 ab = b.dup().sub(a);
				IVec2 comp = p.dup().sub(this.a);
				if (comp.x / ab.x > 1 || comp.x / ab.x < 0)
					return false;
				else
					return true;

			}

		}

	}

	public int GetIntersection(IVec2 a, IVec2 b, IVec2 c, IVec2 d) {
		IVec2 intersection = new IVec2(0, 0);

		if (Math.abs(b.y - a.y) + Math.abs(b.x - a.x) + Math.abs(d.y - c.y) + Math.abs(d.x - c.x) == 0) {
			if ((c.x - a.x) + (c.y - a.y) == 0) {
				// System.out.println("ABCD��ͬһ���㣡");
			} else {
				// System.out.println("AB��һ���㣬CD��һ���㣬��AC��ͬ��");
			}
			return 0;
		}

		if (Math.abs(b.y - a.y) + Math.abs(b.x - a.x) == 0) {
			if ((a.x - d.x) * (c.y - d.y) - (a.y - d.y) * (c.x - d.x) == 0) {
				// System.out.println("A��B��һ���㣬����CD�߶��ϣ�");
			} else {
				// System.out.println("A��B��һ���㣬�Ҳ���CD�߶��ϣ�");
			}
			return 0;
		}
		if (Math.abs(d.y - c.y) + Math.abs(d.x - c.x) == 0) {
			if ((d.x - b.x) * (a.y - b.y) - (d.y - b.y) * (a.x - b.x) == 0) {
				// System.out.println("C��D��һ���㣬����AB�߶��ϣ�");
			} else {
				// System.out.println("C��D��һ���㣬�Ҳ���AB�߶��ϣ�");
			}
			return 0;
		}

		if ((b.y - a.y) * (c.x - d.x) - (b.x - a.x) * (c.y - d.y) == 0) {
			// System.out.println("�߶�ƽ�У��޽��㣡");
			return 0;
		}

		intersection.x = ((b.x - a.x) * (c.x - d.x) * (c.y - a.y) - c.x * (b.x - a.x) * (c.y - d.y) + a.x
				* (b.y - a.y) * (c.x - d.x))
				/ ((b.y - a.y) * (c.x - d.x) - (b.x - a.x) * (c.y - d.y));
		intersection.y = ((b.y - a.y) * (c.y - d.y) * (c.x - a.x) - c.y * (b.y - a.y) * (c.x - d.x) + a.y
				* (b.x - a.x) * (c.y - d.y))
				/ ((b.x - a.x) * (c.y - d.y) - (b.y - a.y) * (c.x - d.x));

		if ((intersection.x - a.x) * (intersection.x - b.x) <= 0
				&& (intersection.x - c.x) * (intersection.x - d.x) <= 0
				&& (intersection.y - a.y) * (intersection.y - b.y) <= 0
				&& (intersection.y - c.y) * (intersection.y - d.y) <= 0) {

			// System.out.println("�߶��ཻ�ڵ�(" + intersection.x + "," +
			// intersection.y + ")��");
			return 1; // '�ཻ
		} else {
			// System.out.println("�߶��ཻ���齻��(" + intersection.x + "," +
			// intersection.y + ")��");
			return -1; // '�ཻ�������߶���
		}
	}
	
	public void draw(PApplet app){
		app.line((float)a.x, (float)a.y, (float)b.x, (float)b.y);
		
		
	}

}
