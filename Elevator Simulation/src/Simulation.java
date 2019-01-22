import java.util.LinkedList;
import java.util.Vector; 

interface Comparable {
    boolean lessThan(Comparable y);
}

abstract class  AbstractEvent implements Comparable {
    abstract void execute(AbstractSimulator simulator);
}

abstract class OrderedSet {
    abstract void insert(Comparable x);
    abstract Comparable  removeFirst();
    abstract int size();
    abstract Comparable remove(Comparable x);
}
    
class AbstractSimulator {
    LinkedList<Person> events;
    void insert1(Person e) {
        events.add(e);
        System.out.println("Im here");
    }
    AbstractEvent cancel(AbstractEvent e)  {
        throw new java.lang.RuntimeException("Method not implemented");
    }
}
abstract class Event extends AbstractEvent {
    double time;
    public boolean lessThan(Comparable y) {
        Event e = (Event) y;  // Will throw an exception if y is not an Event
        return this.time < e.time;
    }
}

class Simulator extends AbstractSimulator {
    double time;
    double now() {
        return time;
    }
    void doAllEvents() {
        Event e;
        
        while(!events.isEmpty())
        {	
        	e = events.getFirst(); 
        	time = e.time; 
        	e.execute(this); 
        	events.removeFirst();
        }
//        while ( (e= (Event) events.pop()) != null) {
//            time = e.time;
//            e.execute(this);
//        }
    }
}
class ListQueue extends OrderedSet {
	Vector<Comparable> elements = new Vector<Comparable>();
	void insert(Comparable x) {
		int i = 0;
		while (i < elements.size() && ((Comparable) elements.elementAt(i)).lessThan(x)) {
			i++;
		}
		elements.insertElementAt(x,i);
	}
	Comparable removeFirst() {
		if (elements.size() ==0) return null;
		Comparable x = (Comparable) elements.firstElement();
		elements.removeElementAt(0);
		return x;
	}
	Comparable remove(Comparable x) {
		for (int i = 0; i < elements.size(); i++) {
			if (elements.elementAt(i).equals(x)) {
				Object y = elements.elementAt(i);
				elements.removeElementAt(i);
				return (Comparable) y;
			}
		}
		return null;
	}
	public int size() {
		return elements.size();
	}

}




