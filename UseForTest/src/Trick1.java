import java.util.*;

public class Trick1
{
	public static final boolean DEBUG = false;

	public static final double PI = 4.0*Math.atan(1.0);
	public static final double TOL = 0.01;

	public static boolean ok(double val)
	{
		return (val > TOL && val <= 1.0);
	}

	public static void calcInt(double a1, double a2, double b1, double b2, double c1, double c2, double r, retType t)
//
// calculate intersection of circle at (c1, c2), radius r with line defined by
//
// 		x = a1*t + a2
// 		y = b1*t + b2
//
	{
//System.out.println("a1, a2, b1, b2 = " + a1 + "," + a2 + ' ' + b1 + ',' + b2);
		double a = a1*a1+b1*b1;
		double b = 2*(a1*a2 + b1*b2);
		double c = a2*a2+b2*b2-r*r;
		double disc = Math.sqrt(b*b - 4*a*c);
		t.t1 = (-b-disc)/2/a;
		t.t2 = (-b+disc)/2/a;
	}

	public static point calcPoint(point p1, point p2, double r)
	{
		double a1 = p2.x - p1.x;
		double a2 = p1.x - p2.x;
		double b1 = p2.y - p1.y;
		double b2 = p1.y - p2.y;
		retType t = new retType();
		calcInt(a1, a2, b1, b2, p2.x, p2.y, r, t);
//System.out.println("t1, t2 = " + t1 + ',' + t2);
		point ans = new point(p2.x+a1*t.t2+a2, p2.y+b1*t.t2+b2);
		return ans;
	}

	public static point calcRefl(point p1, point p2, point p3)
//
// calc reflection of point p3 across line connecting p1 to p2
//
	{
		double delx = p2.x-p1.x;
		double dely = p2.y-p1.y;
		//
		// look for intersection of line (p1.x+delx*t, p1.y+dely*t) with
		// line (p3.x-dely*u, p3.y+delx*u)
		double u = (-delx*(p3.y-p1.y)+dely*(p3.x-p1.x))/(delx*delx+dely*dely);
		//
		// reflected point found using parametric value 2*u
		point ans = new point(p3.x-dely*2*u, p3.y + delx*2*u);
		return ans;
	}

	public static boolean tooClose(double x1, double y1, double x2, double y2, double x3, double y3, double r)
//
// check if ball at (x3, y3) is less than 2r away from line from (x1,y1) to (x2, y2)
//
	{
		double dx = x2-x1;
		double dy = y2-y1;
		double denom = dx*dx + dy*dy;
		double t = ((x3-x1)*dx + (y3-y1)*dy)/denom;
		double u = ((y3-y1)*dx - (x3-x1)*dy)/denom;
		double xint = x1 + t*dx;
		double yint = y1 + t*dy;
		dx = x3-xint;
		dy = y3-yint;
		double dist = Math.sqrt(dx*dx + dy+dy);
		return (dist - 2*r < TOL);
	}

	public static void main(String [] args)
	{
		Scanner in = new Scanner(System.in);
		double w, l, h, r;
		point p1 = new point(), p2 = new point(), p3 = new point();
		double d, theta;
//		point lhole = new point(), rhole = new point();

		double pi = 4*Math.atan(1.0);
		w = in.nextDouble();
		l = in.nextDouble();
			point lhole = new point(0,l), rhole = new point(w,l);
			r = in.nextDouble();
			p1.x = in.nextDouble();
			p1.y = in.nextDouble();
			p2.x = in.nextDouble();
			p2.y = in.nextDouble();
			p3.x = in.nextDouble();
			p3.y = in.nextDouble();
			h = in.nextDouble();
			point p4 = calcPoint(lhole, p2, 2*r);
			point p5 = calcPoint(rhole, p3, 2*r);
			point p6 = calcPoint(p5, p1, 2*r);
			point p7 = calcRefl(p5, p6, p4);
			vector v1 = new vector(p2,p4),	// direction from ball 2 to left hole
				   v2 = new vector(p3,p5),	// direction from ball 3 to right hole
				   v3 = new vector(p5,p1),	// direction from ball 1 to ball 3
				   v4 = new vector(p6,p7),	// initial direction of cue ball
				   v5 = new vector(p4,p6);	// direction from carom to ball 2
if (DEBUG) {
System.out.println("p4 = " + p4);
System.out.println("p5 = " + p5);
System.out.println("p6 = " + p6);
System.out.println("p7 = " + p7);
System.out.println("v1 = " + v1);
System.out.println("v2 = " + v2);
System.out.println("v3 = " + v3);
System.out.println("v4 = " + v4);
System.out.println("v5 = " + v5);
System.out.println("v1 dot v5 = " + v1.dot(v5));
System.out.println("v2 dot v3 = " + v2.dot(v3));
System.out.println("v3 dot v4 = " + v3.dot(v4));
}
			//
			// direction is p6-p7 (= v4)
			//
			if (!ok(v1.dot(v5)))
				System.out.println("impossible");
			else if (!ok(v2.dot(v3)))
				System.out.println("impossible");
			else if (!ok(v3.dot(v4)))
				System.out.println("impossible");
			else {
				theta = Math.atan2(p6.y-p7.y, p6.x-p7.x);
				theta *= (180/PI);
				d = (h-p7.y)/(p6.y-p7.y)*(p6.x-p7.x) + p7.x;
if (DEBUG)
System.out.println("d = " + d);
				if (theta < 0.0 || d < r || d > w-r || tooClose(d, h, p1.x, p1.y, p2.x, p2.y, r))
					System.out.println("impossible");
				else
					System.out.printf("%.2f %.2f\n", d, theta);
			}
	}
}

class point
{
	public double x, y;

	public point()
	{
		x = 0.0;
		y = 0.0;
	}

	public point(double xx, double yy)
	{
		x = xx;
		y = yy;
	}

	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
}

class vector
{
	public double x, y;

	public vector(point p1, point p2)	// initialize as difference between 2 points
	{
		x = p1.x - p2.x;
		y = p1.y - p2.y;
	}

	public double dot(vector rhs)
	{
		double ans = x*rhs.x + y*rhs.y;
		return ans/len()/rhs.len();
	}

	public double len()
	{
		return Math.sqrt(x*x + y*y);
	}

	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
}

class retType
{
	public double t1, t2;
}
