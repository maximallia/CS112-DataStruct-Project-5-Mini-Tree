package apps;

import structures.*;

import java.io.IOException;

import app.PartialTreeList;

public class Driver2 {

	public static void main(String[] args) throws IOException{
		Graph test1 = new Graph("graph1.txt");
		PartialTreeList list1 = PartialTreeList.initialize(test1);
		
		Graph test2 = new Graph("graph2.txt");
		PartialTreeList list2 = PartialTreeList.initialize(test2);
		
		Graph test3 = new Graph("graph3.txt") ;
		PartialTreeList list3 = PartialTreeList.initialize(test3) ;
		
		Graph test4 = new Graph("graph4.txt" ) ;
		PartialTreeList list4 = PartialTreeList.initialize(test4 ) ;
		

		Vertex v1 = list1.remove().getArcs().getMin().getv2() ;
		list1.removeTreeContaining(v1);
		//while(list1.size() != 0){
			//System.out.println(list1.remove());
	//	}
		
		Vertex v2 = list1.remove().getArcs().getMin().getv2() ;
		System.out.println(list1.removeTreeContaining(v2)) ;
		while(list1.size() != 0){
			//System.out.println(list1.remove());
			System.out.println(list1.remove().toString() ) ;
		}
	
		Vertex v3 = list2.remove().getArcs().getMin().getv2();
		list2.removeTreeContaining(v3);
		
		System.out.println() ;
		
		System.out.println();
		Vertex v4 = list2.remove().getArcs().getMin().getv2() ;
		System.out.println(list2.removeTreeContaining(v4) ) ;
		while(list2.size() != 0){
			System.out.println(list2.remove().toString());
		}
		
		System.out.println() ;
		System.out.println();
		
		Vertex v5 = list3.remove().getArcs().getMin().getv2();
		System.out.println( list3.removeTreeContaining(v5) ) ;
		while( list3.size() != 0 ) {
			System.out.println(list3.remove().toString() ) ;
		}
		
		System.out.println() ;
		System.out.println();
		
		Vertex v6 = list4.remove().getArcs().getMin().getv2();
		System.out.println( list4.removeTreeContaining(v6 ) ) ;
		while( list4.size() != 0 ) {
			System.out.println( list4.remove().toString() ) ;
		}
	}
	

}