package treebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//#########################################################################

/**
 * @author DEI-ESINF
 */
/*
public interface:

public BST()
public boolean isEmpty()
public int size()
public void insert(E element)
public void remove(E element)
public String toString()

public int height()
public smallestElement()
public Iterable<E> inOrder()
public Iterable<E> preOrder()
public Iterable<E> posOrder()
public Map<Integer,List<E>> nodesByLevel(){

*/
public class BST<E extends Comparable<E>> {
    /**
     * The root of the binary search tree
     */
    public Node<E> root = null;     // root of the tree

    /**
     * Constructs an empty binary search tree.
     */
    public BST() {    // constructs an empty binary search tree
        root = null;
    }
//****************************************************************

    /**
     * Returns the root Node of the tree (or null if tree is empty).
     *
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<E> root() {
        return root;
    }
//****************************************************************

    /**
     * Verifies if the tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
//****************************************************************

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    public int size() {
        return size(root);
    }


    private int size(Node<E> node) {

        if (node == null) {
            return 0;
        } else {
            return (size(node.left) + 1 + size(node.right));
        }

        //  throw new UnsupportedOperationException("Not supported yet.");
    }
//****************************************************************

    /**
     * Inserts an element in the tree.
     */
    public void insert(E element) {
        root = insert(element, root);
    }

    private Node<E> insert(E element, Node<E> node) {

        if (node == null) {
            return new Node<>(element, null, null);
        }

        if (node.getElement() == element){
            node.setElement(element);
        }

        else{

           if (node.getElement().compareTo(element) > 0){
               node.setLeft(insert(element, node.getLeft()));
           }
            if (node.getElement().compareTo(element) < 0){
                node.setRight(insert(element, node.getRight()));
            }
        }


      /*  if (node.element.compareTo(element) < 0 && find(element,node)==null ) {
            node.left = insert(element, node.getLeft());
        } else if(find(element, node)==null) {
            node.right = insert(element, node.getRight());
        }*/

        return node;

        //throw new UnsupportedOperationException("Not supported yet.");


    }
//****************************************************************

    /**
     * Removes an element from the tree maintaining its consistency as a Binary Search Tree.
     */
    public void remove(E element) {
        root = remove(element, root());
    }


    private Node<E> remove(E element, Node<E> node) {

        // compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than
        // other and 0 if they are supposed to be equal

        if( node == null )
            return node;   // Item not found; do nothing

        if( element.compareTo( node.element ) < 0 )  // element < node.element
            node.left = remove( element, node.getLeft() );

        else if( element.compareTo( node.element ) > 0 ) // element > node.element
            node.right = remove( element, node.getRight() );
        else if( node.getLeft() != null && node.getRight() != null ) // Two children
        {
            node.element = smallestElement( node.getRight() );
            node.right = remove( node.element, node.getRight() );
        }
        else // 1 children
            node = ( node.getLeft() != null ) ? node.getLeft() : node.getRight();
        return node;





     /*   if (node == null){
            return  null;
        }

        if(node.getElement() == element){
            if (node.getLeft() == null && node.getRight() ==null){
                return null;
            }
            if (node.getLeft() == null){
                return node.getRight();
            }
            if (node.getRight() == null){
                return node.getLeft();
            }
            return (Node<E>) smallestElement(node.getLeft());

        }

        else if (node.getElement().compareTo(element) < 0){
            return node.setLeft(remove(element, node.getLeft()));
        }
        else {
            return node.setRight(remove(element,node.getRight()));
        }*/


//        throw new UnsupportedOperationException("Not supported yet.");
    }
//****************************************************************

    /**
     * Returns the smallest element within the tree.
     *
     * @return the smallest element within the tree
     */
    public E smallestElement() {
        return smallestElement(root);
    }

    protected E smallestElement(Node<E> node) {

            // recursive method the find the smallest ( the more left element in tree until it gets no more left node )
            if( node == null )
                return null;
            else if( node.left == null )
                return node.getElement();
            return smallestElement( node.left );



        /*

        if (node.getElement() == null){
            return null;
        }
        if (node.getLeft() == null){
            return node.getElement();
        }
        else{
            return smallestElement(node.getLeft());
        }
*/

       // throw new UnsupportedOperationException("Not supported yet.");
    }
    //****************************************************************

    /**
     * Returns a map with a list of nodes by each tree level.
     *
     * @return a map with a list of nodes by each tree level
     */
    public Map<Integer, List<E>> nodesByLevel() {

        Map<Integer, List<E>> result = new HashMap();
        processBstByLevel(root, result, 0);

        return result;

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    private void processBstByLevel(Node<E> node, Map<Integer, List<E>> result, int level) {

        if (node == null) {
            return;
        }
        if (result.containsKey(level)) {
            result.get(level).add(node.getElement());

        } else {
            ArrayList<E> qbfs = new ArrayList<>();
            qbfs.add(node.getElement());
            result.put(level, qbfs);
        }
        processBstByLevel(node.getLeft(), result, level + 1);
        processBstByLevel(node.getRight(), result, level + 1);

       // throw new UnsupportedOperationException("Not supported yet.");
    }
    //---------------------------------------------------------------

    /**
     * Returns the height of the tree
     *
     * @return
     */
    public int height() {
        root = root();
        if (root() == null ){
            return -1;
        }

        int lefth = height(root.left);
        int righth = height(root.right);

        if(lefth > righth)
            return lefth + 1;
        else
            return righth +1;

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the height of the subtree rooted at Node node.
     *
     * @param node A valid Node within the tree
     * @return
     */
    protected int height(Node<E> node) {

        if(node == null)
            return -1;

        int lefth = height(node.left);
        int righth = height(node.right);

        if(lefth > righth)
            return lefth + 1;
        else
            return righth +1;

       // throw new UnsupportedOperationException("Not supported yet.");
    }


//****************************************************************

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element the element to find
     * @return the Node that contains the Element, or null otherwise
     * <p>
     * This method despite not being essential is very useful.
     * It is written here in order to be used by this class and its
     * subclasses avoiding recoding.
     * So its access level is protected
     */
    protected Node<E> find(E element, Node<E> node) {

        // Finds the node that contains the value and returns a reference to the node.
        // Returns null if value does not exist in the tree.
        if (node == null) return null;
        if (node.element == element) {
            return node;
        } else {
            Node left = find( element, node.left);
             Node right = find(element, node.right);
            if (left != null) {
                return left;
            }else {
                return right;
            }
        }

       // throw new UnsupportedOperationException("Not supported yet.");
    }
//****************************************************************

    /**
     * Returns an iterable collection of elements of the tree, reported in in-order.
     *
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<E> inOrder() {

      List<E> myList = new ArrayList<>();


       root = root();
        if ( root == null ){
            return null;
        }else{
            inOrderSubtree(root, myList);
        }

        return myList;

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an in-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(Node<E> node, List<E> snapshot) {

        if ( node == null ){
            return;
        }

        inOrderSubtree(node.left, snapshot);
        snapshot.add(node.getElement());
        //System.out.print(currRoot.value+", ");
        inOrderSubtree(node.right, snapshot);


        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in pre-order.
     *
     * @return iterable collection of the tree's elements reported in pre-order
     */
    public Iterable<E> preOrder() {

        List<E> snapshot = new ArrayList<>();
        preOrderSubtree(root, snapshot);
        return snapshot;

       // throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an pre-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void preOrderSubtree(Node<E> node, List<E> snapshot) {

        if (node == null) {
            return;
        }
        snapshot.add(node.getElement());
        preOrderSubtree(node.getLeft(), snapshot);
        preOrderSubtree(node.getRight(), snapshot);

       // throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in post-order.
     *
     * @return iterable collection of the tree's elements reported in post-order
     */
    public Iterable<E> postOrder() {

        List<E> snapshot = new ArrayList<>();
        postOrderSubtree(root, snapshot);
        return snapshot;

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Adds positions of the subtree rooted at Node node to the given
     * snapshot using an post-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void postOrderSubtree(Node<E> node, List<E> snapshot) {

        if (node == null) {
            return;
        }
        postOrderSubtree(node.getLeft(), snapshot);
        postOrderSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());

       // throw new UnsupportedOperationException("Not supported yet.");
    }

//#########################################################################
    //---------------- nested Node class ----------------

    /**
     * Nested static class for a binary search tree node.
     */
    protected static class Node<E> {
        private E element;          // an element stored at this node
        private Node<E> left;       // a reference to the left child (if any)
        private Node<E> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e          the element to be stored
         * @param leftChild  reference to a left child node
         * @param rightChild reference to a right child node
         */
        public Node(E e, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        public E getElement() {
            return element;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        // update methods
        public void setElement(E e) {
            element = e;
        }

        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    } //----------- end of nested Node class -----------

//#########################################################################
//#########################################################################

    /**
     * Returns a string representation of the tree.
     * Draw the tree horizontally
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    private void toStringRec(Node<E> root, int level, StringBuilder sb) {
        if (root == null)
            return;
        toStringRec(root.getRight(), level + 1, sb);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++)
                sb.append("|\t");
            sb.append("|-------" + root.getElement() + "\n");
        } else
            sb.append(root.getElement() + "\n");
        toStringRec(root.getLeft(), level + 1, sb);
    }

} //----------- end of BST class -----------




  

  