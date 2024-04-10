package asd;




public class Run {
	public static void main(String[] args) {
		Albero_m_ario<String> tree = new Albero_m_ario<String>(100);

		Nodo_m_ario<String> rootA = new Nodo_m_ario<String>("A");
		Nodo_m_ario<String> childB = new Nodo_m_ario<String>("B");
		Nodo_m_ario<String> childC = new Nodo_m_ario<String>("C");
		Nodo_m_ario<String> childD = new Nodo_m_ario<String>("D");

		childC.addChild(childD);
		rootA.addChild(childB);
		rootA.addChild(childC);

		tree.setRoot(rootA);
		//Nodo_m_ario<String> R = tree.addRoot("Pippo");
		//R.addChildAt(0, childB);
		//R.addChildAt(1, childC);
		System.out.println("Numbero of Nodes: "+tree.getNumberOfNodes());
		System.out.println("Root of Tree: "+tree.getRoot());
		System.out.println("children of childC: "+childC.getChildren());
		System.out.println("InfoList children of rootA: "+rootA.getChildren());
		System.out.println("father of childD: "+childD.getParent());
		System.out.println("info of childD: "+childD.getData());
		System.out.println("level of childD: "+childD.livello());
		System.out.println("level of RootA: "+rootA.livello());
		//tree.changeInfo(childD, "BOH");
		//System.out.println(childD.getData());
		System.out.println("height of tree: "+rootA.getHeight(rootA));
		System.out.println("Numbero of children of childC: "+childC.getNumberOfChildren());
		
		
		
		
		
		
		
		

		

	    /*System.out.println("\nList of Nodes : " + T.figli);
	    System.out.println("NumNodes : " + T.numNodi());
	    System.out.println("Padre di carlo : " + carlo.getPadre());
	    System.out.println("Info of a NODE : " + T.info(pippone));
	    System.out.println("Livello : " + T.livello(carlo));
	    T.cambiaInfo(R, "BOH");
	    System.out.println("Change Info of a NODE : " + T.info(R));
	    System.out.println("\nInfo List: " + T.infoList(R).toString());
	    System.out.println("\nchild in 0 position : " + T.getChildAt(0));
	    System.out.println("\nstring : " + carlo.getChildAt(0).toString());
	  // System.out.println("Numero figli : " + T.auxiliaryGetNumberOfNodes(R));*/
	    //System.out.println("Numero Foglie : " + T.numFoglie());
	    //System.out.println("Altezza : " + T.altezza_());
	    
	    //System.out.println("Info List : " + T.infoList(R));
	    
	    
	     //Credo NON FUNZIONANO 
		//System.out.println("Foglie : " + T.numFoglie());
		//System.out.println("Altezza : " + T.altezza_());

		/*Nodo_m_ario<String> a = new Nodo_m_ario<String>("a");
		Nodo_m_ario<String> b = new Nodo_m_ario<String>("b");
		Nodo_m_ario<String> c = new Nodo_m_ario<String>("c");
		T.addRoot("Root");
		T.radice.addChildAt(a, 0);
		a.addChildAt(b, 0);
		T.radice.addChildAt(c, 1);
		System.out.println(T.getNumberOfNodes());
*/
	}

}