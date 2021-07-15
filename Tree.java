/**
 *
 * @author Mansur Ismailov 
 *
 */
 
public class Tree {
	
	private int value;
	private Tree lhs;
	private Tree rhs;
	
	/**
	* Erzeugen eines Baumes mit Wert als Baumwurzel.
	* @param value Der initiale Wert.
	*/
	
	Tree(int value) {
		this.value = value;
	}
	
	/**
	* Fuegt eine neue Zahl in den Baum eine.
	* @param value Zahl die hinzugefuegt wird.
	*/
	
	void insert(int value) {
		if (this.value == value) {
			return;
		}
		
		if (value < this.value) {
			if (this.lhs == null) {
				this.lhs = new Tree(value);
			} else {
				this.lhs.insert(value);
			}
		}
		
		if (value > this.value) {
			if (this.rhs == null) {
				this.rhs = new Tree(value);
			} else {
				this.rhs.insert(value);
			}
		}
	}
	
	/**
	* Anzahl der Werte im Baum.
	* @return Anzahl der Knoten.
	*/
	
	int size() {
		int lhsSize = this.lhs != null ? this.lhs.size() : 0;
		int rhsSize = this.rhs != null ? this.rhs.size() : 0;
		
		return 1 + lhsSize + rhsSize;
	}
	
	/**
	* Hoehe des Baumes.
	* @return Hoehe.
	*/
	
	int height() {
		int lh = (this.lhs == null ? 0 : this.lhs.height()) + 1;
		int rh = (this.rhs == null ? 0 : this.rhs.height()) + 1;
		return Math.max(lh, rh);
	}
	
	/**
	* Ueberprueft ob der Baum Werte enthaelt.
	* @param value Wert zum ueberpruefen.
	* @return Ist der Wert im Baum enthalten.
	*/
	boolean exists(int value) {
		if (this.value == value) {
			return true;
		}
		
		if (this.lhs != null && value < this.value) {
			return this.lhs.exists(value);
		}
		if (this.rhs != null && value > this.value) {
			return this.rhs.exists(value);
		}
		return false;
	}
	
	/**
	* Ruft den kleinsten Wert im Baum auf
	* @return Das Minimum im Baum
	*/
	
	int min() {
		if (this.lhs == null) {
			return this.value;
		}
		return this.lhs.min();
	}
	
	/**
	* Ruft den groessten Wert im Baum auf
	* @return Das Maximum im Baum
	*/
	
	int max() {
		if (this.rhs == null) {
			return this.value;
		}
		return this.rhs.max();
	}
	
	/**
	* Ein degenerierter Baum ist ein Baum dessen Knoten 0 oder 1 Kind haben.
	* Diese Methode ueberprueft ob der Baum degeneriert ist.
	* @return Baum degeneriert oder nicht.
	*/
	
	boolean isDegenerate() {
		if (this.lhs != null && this.rhs != null) {
			return false;
		}
		
		if (this.lhs != null) {
			return this.lhs.isDegenerate();
		}
		
		if(this.rhs != null) {
			return this.rhs.isDegenerate();
		}
		
		return false;
	}
	
	/**
	* Ueberprueft ob das Praedikat wahr fuer alle Zahlen im Baum ist.
	* @param func Das Praedikat welches getestet wird.
	* @return Stimmt das Praedikat fuer alle Zahlen?
	*/
	
	boolean forAll(Predicate func) {
		boolean res = true;
		if (this.lhs != null) {
			res = this.lhs.forAll(func);
		}
		res = res && func.place(this.value);
		if (this.rhs != null) {
			res = res && this.rhs.forAll(func);
		}
		return res;
	}
	
	private String toFormat() {
		String lhsFor = this.lhs == null ? "" : String.format("%s ", this.lhs.toFormat());
		String rhsFor = this.rhs == null ? "" : String.format(" %s", this.rhs.toFormat());
		
		return String.format("(%s%d%s)", lhsFor, this.value, rhsFor);
	}
	
	private static int space = 2;
	
	/**
	* Wiedergabe des Baumes
	* @param increase Erhoehung der Zahl
	* @return Tree
	*/
	
	public String printT(int increase) {
		String tree = "";
		String add = "";
		
		for (int i = 0; i < increase; ++i) {
			add =  add + " ";
		}
		
		String line = "";
		if (this.lhs != null) {
			tree += line + this.lhs.printT(increase + space);
			line = "\n";
		}
		
		tree += line + add + this.value;
		
		if (this.rhs != null) {
			tree += this.rhs.printT(increase + space);
		}
		return tree;
	}
	
	@Override
	public String toString() {
		return this.toFormat();
	}
}