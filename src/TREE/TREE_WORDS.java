package TREE;

import BST.BST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author DEI-ESINF
 */
public class TREE_WORDS extends BST<TextWord> {

    public void createTree() throws FileNotFoundException {
        Scanner ler = new Scanner(new File("xxx.xxx"));
        while (ler.hasNextLine()) {
            String temp = ler.next().replaceAll("[^a-zA-Z ]", "");// para ignorar as virgulas e pontos
            String[] tempArray = temp.split(" ");       //separa por espacos
            for (int i = 0; i < tempArray.length; i++) {
                this.insert(new TextWord(tempArray[i], 1));
            }
        }
        ler.close();
    }
//================================================================ 

    /**
     * Inserts a new word in the tree, or increments the number of its
     * occurrences.
     *
     * @param element
     */
    @Override
    public void insert(TextWord element) {
//        root = insert(element, root);
        Node<TextWord> no = this.find(element, root);
        if (no != null) {
            element.setWord(element.getWord(), element.getOcorrences() + no.getElement().getOcorrences());
        }
        super.insert(element);
    }

//    //NEste caso não precisei de implementar o método
//  private Node<TextWord> insert(TextWord element, Node<TextWord> node) {
//
//        Node<TextWord> test = this.find(element, node);
//        if (test == null) {
//            super.insert(element);
//        } else {
//            test.getElement().incOcorrences();
//        }
//        return test;
//    }
//****************************************************************
    /**
     * Returns a map with a list of words for each occurrence found.
     *
     * @return a map with a list of words for each occurrence found.
     */
    public Map<Integer, List<String>> getWordsOccurrences() {
        Map<Integer, List<String>> occurs = new HashMap<>();

        Iterable<TextWord> todosOsNos = super.preOrder();//a lista de nós

        Iterator<TextWord> it = todosOsNos.iterator();      //iterador dos nós

        while (it.hasNext()) {
            TextWord palavra = it.next();
            int nOccurs = palavra.getOcorrences();

            if (occurs.containsKey(nOccurs)) {   //se já existe uma lista com o numero de ocorrencias, só adiciona. Senão cria e adiciona
                occurs.get(nOccurs).add(palavra.getWord());
            } else {
                List<String> novaLista = new ArrayList<>(); //crio a lista
                novaLista.add(palavra.getWord());           //adiciono a palavra à lista
                occurs.put(nOccurs, novaLista);             //adiciono a lista ao HashMap

            }

        }

        return occurs;
    }

}
