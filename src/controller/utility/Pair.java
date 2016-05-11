package controller.utility;

/*
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString well implemented. 
 */

public class Pair<X, Y> {
	
	private final X x;
	private final Y y;
	
	private static final int PRIME = 31;
	
	public Pair(final X xValue, final Y yValue) {
		super();
		this.x = xValue;
		this.y = yValue;
	}

	public X getX() {
		return x;
	}

	public Y getY() {
		return y;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = PRIME * result + ((x == null) ? 0 : x.hashCode());
		result = PRIME * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Pair other = (Pair) obj;
		if (x == null) {
			if (other.x != null) {
				return false;
			}
		} else if (!x.equals(other.x)) {
			return false;
		}
		if (y == null) {
			if (other.y != null) {
				return false;
			}
		} else if (!y.equals(other.y)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Pair [x=" + x + ", y=" + y + "]";
	}
	
	

}
