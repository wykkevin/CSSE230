package libr;

public abstract class Media {
	protected String name;
	protected int index;
	protected int remainAmount;
	protected boolean borrowed;
	protected String auther;
	protected Borrower currentOwner;
	
	public abstract String getName();
}
