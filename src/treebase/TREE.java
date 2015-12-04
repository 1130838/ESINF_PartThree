package treebase;

import treebase.BST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 *
 * @author DEI-ESINF
 * @param <E>
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
 public E smallestElement()
 public Iterable<E> inOrder()
 public Iterable<E> preOrder()
 public Iterable<E> postOrder()
 public Map<Integer,List<E>> nodesByLevel()
 ++++++++++++++++++++++++++++++
 public Iterator<E> iterator()

 public List<E> ascdes()


 */
public class TREE<E extends Comparable<E>> extends BST<E> {

//****************************************************************
    public Iterator<E> iterator() {
        return new BSTIterator();
    }
//#########################################################################
//#########################################################################
//---------------------------------------------------------------   

    /**
     * Returns the tree without leaves.
     *
     * @return tree without leaves
     */
    public BST<E> autumnTree() {
        BST<E> newTree = new TREE(); //cria árvore vazia
        newTree.root = copyRec(root);//cópia recursiva a partir desta raiz, mas esta cópia despreza os nós  folhas

        return newTree;
    }

    private Node<E> copyRec(Node<E> node) {
        if (node == null) {
            return node;
        }

        if (node.getLeft() != null || node.getRight() != null) { //se não for uma folha
            return (new Node(node.getElement(), //retornno um novo nó que tem o elemento nó e à esquerda tem a cópia recursiva e à direita a cópia recursiva
                    copyRec(node.getLeft()),
                    copyRec(node.getRight()))); // o nó raiz é o último a ser criado, dado que para fazer o new Node, tenho de ter a esquerda e a direita antes
        }
        return null;
        /* PODIA SER - em que temos 2 condições de paragem
        
         if (node == null) {
         return node;
         }
         if (node.getLeft() == null && node.getRight() == null){
         return null;
         }
        
         return (new Node(node.getElement(), //retornno um novo nó que tem o elemento nó e à esquerda tem a cópia recursiva e à direita a cópia recursiva
         copyRec(node.getLeft()),
         copyRec(node.getRight()))); // o nó raiz é o último a ser criado, dado que para fazer o new Node, tenho de ter a esquerda e a direita antes
        
         }*/

    }

//#########################################################################
//#########################################################################  
    private class BSTIterator implements Iterator<E> {

        private final Stack<Node<E>> stack;
        E curElement;       //current element
        boolean canRemove;  //to enable remove()

        public BSTIterator() {
            stack = new Stack<>();
            Node<E> cur = (Node<E>) root();
            while (cur != null) {
                stack.push(cur);
                cur = cur.getLeft(); //ando para esquerda até ao máximo e preencho a pilha com os elementos à esquerda. Com os métodos hasNExt e Next preencho os restantes
            }
            curElement = null;
            canRemove = false;
        }

        /**
         * @return whether we have a next smallest element
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * @return the next smallest element
         */
        @Override
        public E next() {
            Node<E> cur = stack.pop();
            curElement = cur.getElement();

            cur = cur.getRight(); //tinha de ir à direita 1 vez e colocar tudo á esquerda
            while (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();

            }
            canRemove = true; //enable remove. POdia verificar se o elemento corrente (cur) é null e portanto podeia remover
            return curElement;
        }

        /**
         * remove the current element
         */
        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Can only remove() content after a call to next()");
            }
            TREE.super.remove(curElement);
            //disable iterator
            curElement = null;
            canRemove = false;
            stack.clear();
        }
    }
//#########################################################################
//######################################################################### 

    /**
     * build a list with all elements of the tree. The elements in the left
     * subtree in ascending order and the elements in the right subtree in
     * descending order.
     *
     * @return returns a list with the elements of the left subtree in ascending
     * order and the elements in the right subtree is descending order.
     */
    public Iterable<E> ascdes() {
        List<E> result = new ArrayList<>();
        if (root != null) {
            ascSubtree(root.getLeft(), result);
            result.add(root.getElement());
            desSubtree(root.getRight(), result);
        }
        return result;
    }

    private void ascSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        ascSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        ascSubtree(node.getRight(), snapshot);

    }

    private void desSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }

        desSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());
        desSubtree(node.getLeft(), snapshot);
    }

}
