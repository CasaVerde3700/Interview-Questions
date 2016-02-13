/**
 * List:
 * [1, 2, [3, 4, [5], 6], 7, 8]
 *
 * An Iterable is a simple representation of a series of elements 
 * that can be iterated over. It does not have any iteration state 
 * such as a "current element". Instead, it has one method that produces an Iterator.
 * 
 * An Iterator is the object with iteration state. It lets you check if it has more 
 * elements using hasNext() and move to the next element (if any) using next().
 */

public class DeepIterator implements Iterator<Integer> {
	private Stack<Iterator> st = new Stack<Iterator>();
	private Integer next = null; // important

	public DeepIterator(Iterable iterable) {
		this.st.push(iterable.iterator()); 
	}

	@override
	public boolean hasNext() {
		if (next != null) { // prevent multiple next() call on a level
			return true;
		}

		while (!st.isEmpty()) {
			Iterator it = st.peek();

			if (it.hasNext()) {
				Object obj = it.next(); // move in level
				if (obj instanceOf Integer) {
					next = (Integer) obj;
					return true;
				} else {
					st.push(((Iterable) obj).iterator());
				}
			} else {
				st.pop(); // level finished
			}
		}

		return false;
	}

	@override
	public Integer next() {
		if (hasNext()) {
			Integer tmp = next;
			next = null;
			return tmp;
		}
		throw new NoSuchElementException();
	}
}

