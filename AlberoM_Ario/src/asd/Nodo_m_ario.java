/*
 Copyright 2010 Visin Suresh Paliath
 Distributed under the BSD license
*/

package asd;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Nodo_m_ario<T> {

	private T data;
	public List<Nodo_m_ario<T>> children;
	public Nodo_m_ario<T> parent;
	public static int maxFigli; // Equal to the k-arity;

	public Nodo_m_ario() {
		super();
		children = new ArrayList<Nodo_m_ario<T>>(maxFigli);
	}

	public Nodo_m_ario(T data) {
		this();
		setData(data);
	}

	// Restituisce il padre di un nodo 
	public Nodo_m_ario<T> getParent() {
		return this.parent;
	}
	public void setparent(Nodo_m_ario<T> v) {
		parent = v;
	}

    //Dato un nodo interno , restituisce la LISTA delle informazioni dei 
	//suoi figli che siano nodi interni
	public List<Nodo_m_ario<T>> getChildren() {
		return this.children;
	}

//////////////////////////////NumNodi
	public int getNumberOfChildren() {
		return getChildren().size();
	}

	public boolean hasChildren() {
		return (getNumberOfChildren() > 0);
	}

///////////////////////////////////////
	public void setChildren(List<Nodo_m_ario<T>> children) {
		for (Nodo_m_ario<T> child : children) {
			child.parent = this;
		}

		this.children = children;
	}

	public void addChild(Nodo_m_ario<T> child) {
		child.parent = this;
		children.add(child);
	}

	//Nel costruttore già fissata la data
	public void addChildAt(int index, Nodo_m_ario<T> child) throws IndexOutOfBoundsException {
		child.parent = this;
		children.add(index, child);
	}

	public void removeChildren() {
		this.children = new ArrayList<Nodo_m_ario<T>>();
	}

	public void removeChildAt(int index) throws IndexOutOfBoundsException {
		children.remove(index);
	}

	public Nodo_m_ario<T> getChildAt(int index) throws IndexOutOfBoundsException {
		return children.get(index);
	}

	public T getData() {
		return this.data;
	}

	// Restituisce il contenuto di un nodo
	public void setData(T data) {
		this.data = data;
	}
	

	public String toString() {
		return getData().toString();
	}
	
	// Restituisce il livello di un nodo
	public int livello() {
		int livello = 0;
		Nodo_m_ario<T> temp = this.getParent();
		while (temp != null) {
			livello++;
			temp = temp.getParent();
		}
		return livello;

	}
	
	// Altezza ?????????????????????????????????????????????????????????
	public int getHeight(Nodo_m_ario<T> n) {
		if (n == null) {
			return 0;
		} else {
			int maxDepth = 0;

			for (Nodo_m_ario<T> figlio : n.getChildren()) {
				maxDepth = Math.max(maxDepth, getHeight(figlio));
			}

			return maxDepth + 1; // 1 per contare anche radice
		}
	}
	
	// Restituisce il numero delle foglie dell'albero??????????????????????????????????
	// Contare le foglie
	public int contaFoglie(Nodo_m_ario<T> a) {
		if (a == null)// Albero vuoto
			return 0;
		if (a.getChildren() == null)// foglia
			return 1;

		// visita
		int foglie = 0; // = contaFoglie((Nodo_m_ario<T>) a.getChildren());
		for (int i = 0; i < getNumberOfChildren() - 1; i++) {
			foglie = contaFoglie(a.getChildAt(i));

		}

		return foglie;

	}
	
    public boolean isFoglia(Nodo_m_ario<T> a) {
        return a.getNumberOfChildren()==0;
    }


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Nodo_m_ario<?> other = (Nodo_m_ario<?>) obj;
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!data.equals(other.data)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	public String toStringVerbose() {
		String stringRepresentation = getData().toString() + ":[";

		for (Nodo_m_ario<T> node : getChildren()) {
			stringRepresentation += node.getData().toString() + ", ";
		}

		// Pattern.DOTALL causes ^ and $ to match. Otherwise it won't. It's retarded.
		Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(stringRepresentation);

		stringRepresentation = matcher.replaceFirst("");
		stringRepresentation += "]";

		return stringRepresentation;
	}
}