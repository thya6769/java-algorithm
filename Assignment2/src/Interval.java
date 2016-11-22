
public class Interval {
	private int start;
	private int end;
	private int height;
	
	public Interval(int start, int end, int height) {
		this.start = start;
		this.end = end;
		this.height = height;
	}
	
	public int getStart(){
		return start;
	}
	public int getEnd(){
		return end;
	}
	public int getHeight(){
		return height;
	}
}
