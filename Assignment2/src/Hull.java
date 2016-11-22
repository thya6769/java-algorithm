import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hull {
	
	private List<Interval> hull;
	public Hull(){
		hull = new ArrayList<Interval>();
	}
	public void setHull(List<Interval> hull){
		this.hull = hull; 
	}
	public List<Interval> getHull(){
		return hull;
	}
    // Called when a new line of numbers is read from stdin.
    public void update(String s) {
        // for each line make new interval and add it to the list.
		String[] strs = s.trim().split("\\s+");
		this.hull.add(new Interval(Integer.parseInt(strs[0])
				, Integer.parseInt(strs[1]), Integer.parseInt(strs[2])));
    }

}
