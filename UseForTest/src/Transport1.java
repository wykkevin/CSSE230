import java.util.*;


public class Transport1
{
	public static final int MAXV = 200;

	public static void main(String [] args)
	{
		int ntot, ns, nf, nt;
		String name;
		Scanner in = new Scanner(System.in);

		ntot = in.nextInt();
		ns = in.nextInt();
		nf = in.nextInt();
		nt = in.nextInt();
			graph g = new graph(2+ntot+2*nt);
			g.addVertex("source");
			g.addVertex("sink");
			for(int i=0; i<ns; i++) {
				name = in.next();
				g.addEdge("source", name);
			}
			for(int i=0; i<nf; i++) {
				name = in.next();
				g.addEdge(name, "sink");
			}
			for(int i=0; i<nt; i++) {
				String s = "a"+i;
				g.addVertex(s);	// name for debugging only
				s = "b"+i;
				g.addVertex(s);
				g.addEdge(2+ns+nf+2*i, 2+ns+nf+2*i+1);
			}
			for(int i=0; i<nt; i++) {
				int n;
				n = in.nextInt();
				for(int j=0; j<n; j++) {
					name = in.next();
					int v = g.getVertexNum(name);
					if (v < 2+ns)						// raw material state
						g.addEdge(v, 2+ns+nf+2*i);
					else if (v < 2+ns+nf)				// factory state
						g.addEdge(2+ns+nf+2*i+1, v);
					else {								// other state
						g.addEdge(v, 2+ns+nf+2*i);
						g.addEdge(2+ns+nf+2*i+1, v);
					}
				}
			}
			int [] path = new int[g.nvert];
			int len = -1;
			int count=0;
			while (g.findPath(path)) {
				len = g.len;
				count++;
				for(int i=1; i<len; i++) {
					g.deleteEdge(path[i-1], path[i]);
					g.addEdge(path[i], path[i-1]);
				}
			}
			System.out.println(count);

	}
}
	
class edge
{
	public int dest;
	public edge next;

	public edge(int d)
	{
		dest = d;
		next = null;
	}

	public edge(int d, edge n)
	{
		dest = d;
		next = n;
	}
}

class vertex
{
	public String name;
	public long nsquawk;
	public edge adj;

	public vertex()
	{
		name = "";
		nsquawk = 0;
		adj = new edge(-1);	// dummy header
	}

	public vertex(String n)
	{
		name = n;
		nsquawk = 0;
		adj = new edge(-1);	// dummy header
	}

	void addEdge(int w)
	{
		adj.next = new edge(w, adj.next);
	}
}

class graph
{
	public vertex [] vlist;
	public int nvert;
	public int len;

	public graph()
	{
		nvert = 0;
		vlist = new vertex[10];
	}

	public graph(int nv)
	{
		nvert = 0;
		vlist = new vertex[nv];
	}

	public void addVertex(String n)
	{
		vlist[nvert] = new vertex();
		vlist[nvert++].name = n;
	}

	public int getVertexNum(String n)
	{
		for(int i=0; i<nvert; i++) {
			if(vlist[i].name.equals(n))
				return i;
		}
		addVertex(n);
		return nvert-1;
	}

	public void addEdge(int v, int w)
	{
		vlist[v].addEdge(w);
	}

	public void addEdge(String n1, String n2)
	{
		int v1 = getVertexNum(n1);
		int v2 = getVertexNum(n2);
		vlist[v1].addEdge(v2);
	}

	public void deleteEdge(int v, int w)
	{
		edge p = vlist[v].adj;
		while (p.next != null) {
			if (p.next.dest == w)
				break;
			p = p.next;
		}
		if (p.next == null) {
			System.out.println("ERROR: vertex "  + w + " not adjacent to vertex " + v);
			return;
		}
		edge tmp = p.next;
		p.next = tmp.next;
	}

	public boolean findPath(int curr, int dest, int path[], boolean visited[])
	{
		path[len++] = curr;
		if (curr == dest) {
			return true;
		}
		for(edge p = vlist[curr].adj.next; p != null; p = p.next) {
			if (!visited[p.dest]) {
				visited[p.dest] = true;
				if (findPath(p.dest, dest, path, visited))
					return true;
				len--;
			}
		}
		return false;
	}

	public boolean findPath(int path[])
	{
		boolean [] visited = new boolean[nvert];
		for(int i=0; i<nvert; i++)
			visited[i] = false;
		len = 0;
		return findPath(0, 1, path, visited);
	}
}
