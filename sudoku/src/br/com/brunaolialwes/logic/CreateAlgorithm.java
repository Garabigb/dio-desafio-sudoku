package br.com.brunaolialwes.logic;

import java.util.*;


public class CreateAlgorithm {

    private final String[] algorithm = new String[81];


    private final static int FIXED_LIMIT = 32;

    private int  count = 0;


    public CreateAlgorithm(){
        int pos = 0;
        List<List<String>> board = populatePosition();
        System.out.println(board);

        //shuffleRow(board);
        //shuffleColumns(board);
        for(int r=0; r<9; r++){
            for(int c=0; c<9; c++){
                algorithm[pos] = (r+ ","+ c + ";" + board.get(r).get(c) + "," + setFixed());
                pos++;
            }
        }

    }

    private String setFixed(){
        if(this.count>FIXED_LIMIT) return "false";

        Random random = new Random();
        int now = random.nextInt(101);


        if(now > 66){
            this.count++;
            return "true";
        }

        return "false";

    }


    private boolean isPossible(String n, List<List<String>> board, int pos, int posCol, boolean isSameBlock){
        if (posCol == 0) return true;

        if(posCol%3==0) isSameBlock=false;

        if(isSameBlock) {
            if (pos - 3 < 0) {
                for (int i = 0; i < 3; i++) {
                    if (n.equals(board.get(posCol - 1).get(i))) {
                        return false;
                    }
                }

            } else if (pos - 3 < 3) {
                for (int i = 3; i < 6; i++) {
                    if (n.equals(board.get(posCol - 1).get(i))) {
                        return false;
                    }
                }
            } else {
                for (int i = 6; i <= pos; i++) {
                    if (n.equals(board.get(posCol - 1).get(i))) {
                        return false;
                    }
                }
            }
        }else{
            if (n.equals(board.get(posCol - 1).get(pos))) {
                return false;
            }
        }

        return isPossible(n, board, pos, posCol-1, isSameBlock);
    }

    private List<List<String>> populatePosition() {
        List<List<String>> board = new ArrayList<>();
        Random random = new Random();

        for (int r = 0; r < 9; r++) {
            List<String> col = new ArrayList<>();
            ArrayList<String> numbers = new ArrayList<>();
            ArrayList<String> numbersNotPossible = new ArrayList<>();
            for (int i=1; i<10; i++){
                numbers.add(i+"");
            }

            while (!numbers.isEmpty()) {
                int pos = random.nextInt(numbers.size());
                String num = numbers.get(pos);

                if (isPossible(num, board, col.size(), r, true)) {
                    col.add(num);
                    numbers.remove(num);
                    if(col.size()==3 || col.size()>6){
                        numbers.addAll(numbersNotPossible);
                        numbersNotPossible = new ArrayList<>();
                    }
                }else{
                    numbersNotPossible.add(numbers.get(pos));
                    numbers.remove(num);
                }

            }
            if(!(col.size()==9)){
                r--;
                continue;
            }
            board.add(col);
        }
        return board;
    }

// nao fuinciona para que eu quero
//    private void shuffleColumns(List<List<String>> board) {
//        Random random = new Random();
//
//        for (int i = 0; i < board.size(); i++) {
//            int position;
//
//            if (i % 3 == 0) { // Primeira coluna do bloco
//                position = random.nextInt(3) + (i / 3) * 3;
//            }
//            else if (i % 3 == 1) { // Segunda coluna do bloco
//                int blockStart = (i / 3) * 3;
//                position = blockStart + random.nextInt(2); // 0 ou 1 no bloco
//            }
//            else { // Terceira coluna do bloco
//                int blockStart = (i / 3) * 3;
//                position = blockStart + random.nextInt(3); // 0, 1 ou 2 no bloco
//            }
//
//            // Garante que a posição está dentro dos limites
//            position = Math.min(position, board.size() - 1);
//
//            if (i != position) {
//                List<String> aux = board.get(position);
//                board.set(position, board.get(i));
//                board.set(i, aux);
//            }
//        }
//    }
//
//    private void shuffleRow(List<List<String>> board){
//        Random random = new Random();
//
//        for (int i = 0; i < board.size(); i++) {
//            int position;
//
//            if (i % 3 == 0) { // Primeira coluna do bloco
//                position = random.nextInt(3) + (i / 3) * 3;
//            }
//            else if (i % 3 == 1) { // Segunda coluna do bloco
//                int blockStart = (i / 3) * 3;
//                position = blockStart + random.nextInt(2); // 0 ou 1 no bloco
//            }
//            else { // Terceira coluna do bloco
//                int blockStart = (i / 3) * 3;
//                position = blockStart + random.nextInt(3); // 0, 1 ou 2 no bloco
//            }
//
//            // Garante que a posição está dentro dos limites
//            position = Math.min(position, board.size() - 1);
//
//            if (i != position) {
//                for(int z=0; z<board.size(); z++){
//                    String aux = board.get(i).get(z);
//                    board.get(i).set(z, board.get(position).get(z));
//                    board.get(position).set(z, aux);
//                }
//            }
//
//        }
//    }
//

// Muito fixo
//
//    private List<List<String>> populatePosition(){
//        List<List<String>> board = new ArrayList<>();
//        int aux = 1;
//        for(int r=0; r<9; r++){
//            if(r==3) aux=2;
//            if(r==6) aux=3;
//            List<String> row = new ArrayList<>();
//            for(int c=0; c<9; c++){
//                int result = c+aux>9 ? c+aux-9: c+aux;
//                row.add(String.valueOf(result));
//            }
//            board.add(row);
//            aux+=3;
//            aux = aux > 10 ? aux-8 : aux;
//        }
//        return  board;
//
//    }





    public String[] getAlgorithm() {
        return algorithm;
    }

    @Override
    public String toString() {
        return "CreateAlgorithm{" +
                "algorithm=" + Arrays.toString(algorithm) +
                '}';
    }
}
