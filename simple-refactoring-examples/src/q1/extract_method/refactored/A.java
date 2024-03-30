package q1.extract_method.refactored;

import java.util.List;

public class A {
   Node m1(List<Node> nodes, String p) {
      // TODO: Your answer
	   extracted(nodes, p);
      // other implementation
      return null;
   }

   Edge m2(List<Edge> edgeList, String p) {
      // TODO: Your answer
	   extracted(edgeList, p);
      // other implementation
      return null;
   }

   // TODO: Your answer
	private <T> void extracted(List<T> edgeList, String p) {
		for (T input1 : edgeList) {
			if (input1.contains(p))
				System.out.println(input1);
		}
	}
}

class Node {
   String name;

   public boolean contains(String p) {
      return name.contains(p);
   }
}

class Edge {
   String name;

   public boolean contains(String p) {
       return name.contains(p);
   }
}