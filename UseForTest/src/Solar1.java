import java.util.*;

public class Solar1
{
	public static final int MAXP = 10000;
	public static final long INF = 1000000000;
	public static final long UNUSED = -1;
	public static final long USED = 0;

	public static planetoid[] pl = new planetoid[MAXP];
	public static long[] dim = new long[3];

	public static long gcd(long a, long b, retval rv)
	{
		long s, t;
		long r=b, prevr = a;
		long prevs=1, prevt = 0;
		s = 0;
		t = 1;
		while (r != 0) {
			long q = prevr/r;
			long tmp = r;
			r = prevr%r;
			if (r < 0)
				r += tmp;
			prevr = tmp;
			tmp = s;
			s = prevs - q*s;
			prevs = tmp;
			tmp = t;
			t = prevt - q*t;
			prevt = tmp;
		}
		rv.v1 = prevs;
		rv.v2 = prevt;
		return prevr;
	}

	public static boolean itime(long[] a, long[] b, long[] mod, int i, retval rval)
	{
		long s, t;
		long n = rval.v1, m = rval.v2;
		retval rv = new retval();
		if (a[i] == 0 && b[i] != 0)
			return false;
		long div = gcd(a[i], mod[i], rv);
//System.out.println("div = " + div);
		s = rv.v1;
		t = rv.v2;
		if (b[i]%div != 0)
			return false;
		long tmpn = s*b[i]/div;
		long tmpm = mod[i]/div;
		n += m*tmpn;
		m *= tmpm;
		if (m != 0)
			n = n%m;
		if (n < 0)
			n += m;
		if (i < 2) {
			i++;
			b[i] -= a[i]*n;
			a[i] *= m;
			rv.v1 = n;
			rv.v2 = m;
			boolean flag = itime(a, b, mod, i, rv);
			n = rv.v1;
			m = rv.v2;
			if (!flag)
				return false;
		}
		rval.v1 = n;
		rval.v2 = m;
		return true;
	}
	
	public static long calcITime(int i1, int i2)
	{
		long [] a = new long[3];
		long [] b = new long[3];
		for(int i=0; i<3; i++) {
			a[i] = pl[i1].v[i] - pl[i2].v[i];
			b[i] = pl[i2].xyz[i] - pl[i1].xyz[i];
			if (a[i] < 0) {
				a[i] = -a[i];
				b[i] = -b[i];
			}
		}
		long n=0, m=1;
		retval rv = new retval();
		rv.v1 = n;
		rv.v2 = m;
		if (itime(a, b, dim, 0, rv) ) {
			n = rv.v1;
			m = rv.v2;
//System.out.println("n,m = " + n + ',' + m);
			n = n%m;
//System.out.println("n,m = " + n + ',' + m);
			if (n < 0) 
				n += m;
			return n;
		}
		else
			return -1;
	}

	public static boolean lessThan(planetoid a, planetoid b)
	{
		if(a.mass < b.mass)
   			return true;
		if(a.mass == b.mass){
   			if(a.xyz[0] > b.xyz[0])
   				return true;
   			if(a.xyz[0] == b.xyz[0]){
   				if(a.xyz[1] > b.xyz[1])
					return true;
   				if(a.xyz[1]==b.xyz[1]){
					if(a.xyz[2] > b.xyz[2])
						return true;
   				}
   			}
		}
		return false;
	}

	public static void swapLong(Long a, Long b)
	{
		Long temp = a;
		a = b;
		b = temp;
	}

	public static void swap(planetoid[] a, int i, int j)
	{
		planetoid tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
/*
		swapLong(a.mass, b.mass);
		swapLong(a.itime, b.itime);
		swapLong(a.cmass,b.cmass);
		swapLong(a.nint, b.nint);
		for(int i = 0; i < 3; i++){
			swapLong(a.xyz[i], b.xyz[i]);
			swapLong(a.v[i], b.v[i]);
			swapLong(a.ixyz[i], b.ixyz[i]);
			swapLong(a.cv[i], b.cv[i]);
		}
*/
	}
    
	public static void main(String [] args)
	{
		int n;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		dim[0] = in.nextLong();
		dim[1] = in.nextLong();
		dim[2] = in.nextLong();
			for(int i=0; i<n; i++) {
				pl[i] = new planetoid();
				pl[i].mass = in.nextLong();
				pl[i].xyz[0] = in.nextLong();
				pl[i].xyz[1] = in.nextLong();
				pl[i].xyz[2] = in.nextLong();
				pl[i].v[0] = in.nextLong();
				pl[i].v[1] = in.nextLong();
				pl[i].v[2] = in.nextLong();
			}
			long earliest = 0;
			while (earliest != INF) {
				earliest = INF;
				for(int i=0; i<n; i++)
   	            	pl[i].itime = -1;
				for(int i=0; i<n; i++) {
					pl[i].nint = UNUSED;
					for(int j=0; j<i; j++) {
						long t = calcITime(j, i);
						if (t > 0 && t <= earliest) {
							earliest = t;
							if (pl[j].nint == UNUSED || pl[j].itime > t) {
								pl[j].itime = t;
								for(int k=0; k<3; k++) {
									pl[j].ixyz[k] = (pl[j].xyz[k]+t*pl[j].v[k])%dim[k];
									if (pl[j].ixyz[k] < 0)
										pl[j].ixyz[k] += dim[k];
									pl[j].cv[k] = pl[j].v[k]+pl[i].v[k];
								}
								pl[j].cmass = pl[j].mass + pl[i].mass;
								pl[j].nint = 2;
							}
							else if (pl[j].nint != USED) {
								for(int k=0; k<3; k++) {
									pl[j].cv[k] += pl[i].v[k];
								}
								pl[j].cmass += pl[i].mass;
								pl[j].nint++;
							}
							pl[i].nint = USED;
							pl[i].itime = t;
						}
					}
				}
				int newn = n;
				for(int i=n-1; i>=0; i--) {
					if (pl[i].itime == earliest) {
						if (pl[i].nint > 0) {
							pl[i].mass = pl[i].cmass;
								for(int k=0; k<3; k++) {
									pl[i].xyz[k] = pl[i].ixyz[k];
									pl[i].v[k] = pl[i].cv[k]/pl[i].nint;
								}
						}
						else if (pl[i].nint == USED) {
							pl[i] = pl[--newn];
						}
					}
					else if (earliest != INF) {
						for(int k=0; k<3; k++) {
							pl[i].xyz[k] += earliest*pl[i].v[k];
							pl[i].xyz[k] %= dim[k];
							if (pl[i].xyz[k] < 0)
								pl[i].xyz[k] += dim[k];
						}
					}
				}
				n = newn;
			}

			for(int i = 0; i < n; i++)
				for(int j = 0; j < n-1; j++){
					if(lessThan(pl[j], pl[j+1]))
						swap(pl, j, j+1);
				}
	
			System.out.println(n);
			for(int i=0; i<n; i++) {
				System.out.print("P" + i + ": " + pl[i].mass);
				for(int k=0; k<3; k++)
					System.out.print(" " + pl[i].xyz[k]);
				for(int k=0; k<3; k++)
					System.out.print(" " + pl[i].v[k]);
				System.out.println();
			}

	}
}

class planetoid
{
	public long mass;
	public long [] xyz = new long[3];
	public long [] v = new long[3];
	public long itime;					// next intersection time
	public long [] ixyz = new long[3];	// location of next intersection
	public long cmass;					// cumulative mass
	public long [] cv = new long[3];	// cumulative velocities of intersecting rocks
	public long nint;					// # of rocks intersecting, if lowest indexed rock; -1 otherwise
}

class retval
{
	public long v1, v2;
}
