/*
 Copyright 2010 Vivin Suresh Paliath
 Distributed under the BSD License
*/

package asd;

import java.util.ArrayList;

public class Albero_m_ario<T> {

    private Nodo_m_ario<T> root;
	public static int maxFigli; // Equal to the k-arity;
	private int numFoglie;// Alternativa alla gestione ricorsiva
	private int altezza;
	

    public Albero_m_ario(int m) {
        super();
        Nodo_m_ario.maxFigli = m;
    }

    // Restituisce la Radice dell'albero o NULL se esso è vuoto
    public Nodo_m_ario<T> getRoot() {
        return this.root;
    }

    public void setRoot(Nodo_m_ario<T> root) {
        this.root = root;
    }
    
	// Cambia il contenuto di un nodo interno
	public void changeInfo(Nodo_m_ario<T> v, T info) {
		v.setData(info);
	}
    
	// Restituisce il numero di foglie  ????????????????????????????????
	public int numFoglie() {
		return numFoglie;
	}
	
	// Restituisce l'altezza ????????????????????????????????????????
	public int altezza() {
		return altezza;
	}
	

	// Restituisce il livello del nodo
	public int livello(Nodo_m_ario<T> v) {
		return v.livello();
	}
	
/////////////////////////////NumNodi
    public int getNumberOfNodes() {
        int numberOfNodes = 0;

        if(root != null) {
            numberOfNodes = auxiliaryGetNumberOfNodes(root) + 1; //1 for the root!
        }

        return numberOfNodes;
    }

    private int auxiliaryGetNumberOfNodes(Nodo_m_ario<T> node) {
        int numberOfNodes = node.getNumberOfChildren();

        for(Nodo_m_ario<T> child : node.getChildren()) {
            numberOfNodes += auxiliaryGetNumberOfNodes(child);
        }

        return numberOfNodes;
    }
/////////////////////////////
    public boolean exists(T dataToFind) {
        return (find(dataToFind) != null);
    }

    public Nodo_m_ario<T> find(T dataToFind) {
        Nodo_m_ario<T> returnNode = null;

        if(root != null) {
            returnNode = auxiliaryFind(root, dataToFind);
        }

        return returnNode;
    }

    private Nodo_m_ario<T> auxiliaryFind(Nodo_m_ario<T> currentNode, T dataToFind) {
        Nodo_m_ario<T> returnNode = null;
        int i = 0;

        if (currentNode.getData().equals(dataToFind)) {
            returnNode = currentNode;
        }

        else if(currentNode.hasChildren()) {
            i = 0;
            while(returnNode == null && i < currentNode.getNumberOfChildren()) {
                returnNode = auxiliaryFind(currentNode.getChildAt(i), dataToFind);
                i++;
            }
        }

        return returnNode;
    }

    public boolean isEmpty() {
        return (root == null);
    }
    //////////////////////////////////////////////////////////
    
	// Inserire la radice di cui è nota linformazione che essa conterrà
	// e la restituisce
	public Nodo_m_ario<T> addRoot(T info) {
		if (root != null) {
			return null;
		}
		//numNodi++;
		numFoglie = 1;
		root = new Nodo_m_ario<T>(info);
		root.parent = null;
		root.children = new ArrayList<Nodo_m_ario<T>>(maxFigli);
		return root;
	}
	
	// Inserire un anuova radice in un albero non vuoto in modo???????????????????????
	// che la vecchia radice sia figlia i_esima della nuova i=0,...,m
	public Nodo_m_ario<T> changeRoot(Nodo_m_ario<T> newRoot, int i) {
		Nodo_m_ario<T> oldRoot = this.root; // setRadice
		newRoot.parent = null;
		newRoot.addChildAt(i, oldRoot);
		oldRoot.parent = newRoot;
		this.root = newRoot;
		return root;
	}
	
	
    
    /////////////////////////////////////////////////////////
    
    


    /*public List<GenericTreeNode<T>> build(GenericTreeTraversalOrderEnum traversalOrder) {
        List<GenericTreeNode<T>> returnList = null;

        if(root != null) {
            returnList = build(root, traversalOrder);
        }

        return returnList;
    }

    public List<GenericTreeNode<T>> build(GenericTreeNode<T> node, GenericTreeTraversalOrderEnum traversalOrder) {
        List<GenericTreeNode<T>> traversalResult = new ArrayList<GenericTreeNode<T>>();

        if(traversalOrder == GenericTreeTraversalOrderEnum.PRE_ORDER) {
            buildPreOrder(node, traversalResult);
        }

        else if(traversalOrder == GenericTreeTraversalOrderEnum.POST_ORDER) {
            buildPostOrder(node, traversalResult);
        }

        return traversalResult;
    }

    private void buildPreOrder(GenericTreeNode<T> node, List<GenericTreeNode<T>> traversalResult) {
        traversalResult.add(node);

        for(GenericTreeNode<T> child : node.getChildren()) {
            buildPreOrder(child, traversalResult);
        }
    }

    private void buildPostOrder(GenericTreeNode<T> node, List<GenericTreeNode<T>> traversalResult) {
        for(GenericTreeNode<T> child : node.getChildren()) {
            buildPostOrder(child, traversalResult);
        }

        traversalResult.add(node);
    }

    public Map<GenericTreeNode<T>, Integer> buildWithDepth(GenericTreeTraversalOrderEnum traversalOrder) {
        Map<GenericTreeNode<T>, Integer> returnMap = null;

        if(root != null) {
            returnMap = buildWithDepth(root, traversalOrder);
        }

        return returnMap;
    }

    public Map<GenericTreeNode<T>, Integer> buildWithDepth(GenericTreeNode<T> node, GenericTreeTraversalOrderEnum traversalOrder) {
        Map<GenericTreeNode<T>, Integer> traversalResult = new LinkedHashMap<GenericTreeNode<T>, Integer>();

        if(traversalOrder == GenericTreeTraversalOrderEnum.PRE_ORDER) {
            buildPreOrderWithDepth(node, traversalResult, 0);
        }

        else if(traversalOrder == GenericTreeTraversalOrderEnum.POST_ORDER) {
            buildPostOrderWithDepth(node, traversalResult, 0);
        }

        return traversalResult;
    }

    private void buildPreOrderWithDepth(GenericTreeNode<T> node, Map<GenericTreeNode<T>, Integer> traversalResult, int depth) {
        traversalResult.put(node, depth);

        for(GenericTreeNode<T> child : node.getChildren()) {
            buildPreOrderWithDepth(child, traversalResult, depth + 1);
        }
    }

    private void buildPostOrderWithDepth(GenericTreeNode<T> node, Map<GenericTreeNode<T>, Integer> traversalResult, int depth) {
        for(GenericTreeNode<T> child : node.getChildren()) {
            buildPostOrderWithDepth(child, traversalResult, depth + 1);
        }

        traversalResult.put(node, depth);
    }

    public String toString() {
        
        //We're going to assume a pre-order traversal by default
         

        String stringRepresentation = "";

        if(root != null) {
            stringRepresentation = build(GenericTreeTraversalOrderEnum.PRE_ORDER).toString();

        }

        return stringRepresentation;
    }

    public String toStringWithDepth() {
        
       // We're going to assume a pre-order traversal by default
         

        String stringRepresentation = "";

        if(root != null) {
            stringRepresentation = buildWithDepth(GenericTreeTraversalOrderEnum.PRE_ORDER).toString();
        }

        return stringRepresentation;
    }*/
}