package Tree;
/**
 * Klasa Tree<T> - drzewo
 * @author kodatnik.blogspot.com
 */
class Tree<T> {
 // referencja do węzła będącego korzeniem
 private Node<T> root;

 public Tree() {
  // brak korzenia
  this.setRoot(null);
 }

 public Tree(Node<T> root) {
  // ustawiamy korzeń
  this.setRoot(root);
 }

public Node<T> getRoot() {
	return root;
}

public void setRoot(Node<T> root) {
	this.root = root;
} 
}
