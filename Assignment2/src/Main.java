import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
	private static List<Interval> mergeList(List<Interval> list1, List<Interval> list2){
    	List<Interval> mergedList = new ArrayList<Interval>();
   
    	int i = 0;
    	int j = 0;
    	while (i < list1.size() && j < list2.size()) {
    		// if its equal add the one with higher height
    		if (list1.get(i).getStart() == list2.get(j).getStart()) {
    			if(list1.get(i).getHeight() < list2.get(j).getHeight()){
	    			mergedList.add(new Interval(list2.get(j).getStart(),
	    					list2.get(j).getEnd(), list2.get(j).getHeight()));
	    			j++;
    			} else {
    				mergedList.add(new Interval(list1.get(i).getStart(),
	    					list1.get(i).getEnd(), list1.get(i).getHeight()));
	    			i++;
    			}
    		} else if(list1.get(i).getStart() < list2.get(j).getStart()) {
    			mergedList.add(new Interval(list1.get(i).getStart(),
    					list1.get(i).getEnd(), list1.get(i).getHeight()));
    			i++;
    		} else {
    			mergedList.add(new Interval(list2.get(j).getStart(),
    					list2.get(j).getEnd(), list2.get(j).getHeight()));
    			j++;
    		}
    	}
    	 while (i < list1.size()){
    	        mergedList.add(new Interval(list1.get(i).getStart()
    	        		, list1.get(i).getEnd(), list1.get(i).getHeight()));
    	        i++;
    	 }
    	 while (j < list2.size()){
    	       mergedList.add(new Interval(list2.get(j).getStart()
    	    		   , list2.get(j).getEnd(), list2.get(j).getHeight()));
    	       j++;
    	}

    	return mergedList;
	}
	
	private static void reorganise(Interval temp, Stack<Interval> stack, Interval next){
		// we need to add temp to the right place according to new start time
		List<Interval> starts = new ArrayList<Interval>();
		// let popped be interval of next
		Interval popped = new Interval(next.getStart(), next.getEnd(), next.getHeight());
		// change the contents of stack according to the new start time.
		// loop until popped start time is more than the temp.
		while(true){
			if(!stack.isEmpty()){
				popped = stack.pop();
				if(popped.getStart() < temp.getStart()){
					starts.add(popped);
				} else {
					stack.push(popped);
					break;
				}
			} else {
				break;
			}
		}
		starts.add(temp);
		// loop through and push to the stack
		for(int i = starts.size() - 1; i >= 0; i--){
//			System.out.println(starts.get(i).getStart());
			stack.push(starts.get(i));
		}	
	}
	
	private static List<Interval> merge(List<Interval> intervals) {
	    List<Interval> result = new ArrayList<Interval>();
	    
	    Stack<Interval> stack = new Stack<Interval>();
	    // push intervals onto stack in increasing order of intervals
	    // first in last out.
	    for(int i = intervals.size() - 1; i >= 0; i--){
	    	stack.push(intervals.get(i));
	    }
	    
	    if(intervals == null || intervals.size() == 0){
	        return result;
	    }
	 
	    Interval curr = stack.pop();
	    Interval next = null;
	    // loop until you looped through every interval.
	    while(!stack.isEmpty()){
	    	next = stack.pop();
	    	// same level case
	    	if(curr.getHeight() == next.getHeight()){
	    		// it it doesn't overlap
	    		if(next.getStart() > curr.getEnd()){
	    			result.add(curr);
		            curr = next;
		            continue;
	    		}
	    		// o---*----o---*
	    		if(next.getEnd() > curr.getEnd()){
	    			Interval temp = new Interval(curr.getStart(), next.getEnd(), curr.getHeight());
	    			curr = temp;
	    		} 
	    		// if current is longer than next just ignore next and continue

	    	// case when next is higher than current
	    	} else if(curr.getHeight() < next.getHeight()){
	    		// check if its overlaps
	    		if(next.getStart() >= curr.getEnd()){
	    			result.add(curr);
		            curr = next;
		            continue;
	    		}
	    		Interval temp;
	    		// if there is overlap at the end
	    		//       *--*
	    		//  o-------o
	    		if(next.getStart() > curr.getStart()){
	    			temp = new Interval(curr.getStart(), next.getStart(), curr.getHeight());
	    			result.add(temp);
	    		} 
	    		// if there is overlap at the start
	    		// *--*
	    		// o--------o	    		
	    		if(next.getEnd() < curr.getEnd()){
	    			temp = new Interval(next.getEnd(), curr.getEnd(), curr.getHeight());
	    			reorganise(temp, stack, next);
	    		} 
    			curr = next;
	    	// when next is lower level than current
	    	} else {
	    		// check if its overlaps
	    		if(next.getStart() >= curr.getEnd()){
	    			result.add(curr);
		            curr = next;
		            continue;
	    		}
	    		// next is longer
	    		// o--o
	    		// *--------*
	    		if(curr.getEnd() < next.getEnd()) {
	    			Interval temp = new Interval(curr.getEnd(), next.getEnd(), next.getHeight());
	    			reorganise(temp, stack, next);
	    		}
	    	}
	    }
	    if(curr != next){
	    	result.add(curr);
	    } 
	    if(next.getStart() >= result.get(result.size() - 1).getEnd()){
	    	result.add(next);
	    }
	    return result;
	}
	
    // Main part of your code - actually merge the hulls. TODO.
    public static Hull mergeHulls(Hull a, Hull b) {
    	Hull merged = new Hull();
    	List<Interval> list1 = a.getHull();
    	List<Interval> list2 = b.getHull();
    	List<Interval> mergedList = merge(mergeList(list1, list2));
    	merged.setHull(mergedList);
    	return merged;
    }

    // Formats the merged hull for output. TODO.
    public static String fmt(Hull h) {
    	String output = "";
    	
    	// loop through each element in the list list
    	for(Interval interval : h.getHull()){
    		output += interval.getStart() + " " + interval.getEnd() + " " 
    				+ interval.getHeight();
    		if(!interval.equals(h.getHull().get(h.getHull().size() - 1))){
        		output += "\n";    			
    		}
    	}
        return output;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // Read hull 1 from stdin.
        Hull hull1 = new Hull();
        while (true) {
            String s = scan.nextLine();
            if (s.equals("x")) {
                break;
            } else {
                hull1.update(s);
            }
        }
        
        // Read hull 2 from stdin.
        Hull hull2 = new Hull();
        while (scan.hasNextLine()) {
        	String s = scan.nextLine();
        	if(!s.isEmpty()){
        		hull2.update(s);
        	} else {
        		break;
        	}
        }
        scan.close();

        // Merge and output the hulls.
        Hull merged = mergeHulls(hull1, hull2);
        String output = fmt(merged);
        System.out.println(output);
    }
}