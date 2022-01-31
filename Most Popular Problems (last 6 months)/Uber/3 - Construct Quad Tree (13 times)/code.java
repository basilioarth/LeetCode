/**
 * Status: Accepted
 * 22 / 22 test cases passed.
 * Runtime: 3 ms
 * Memory Usage: 48.6 MB
 * Details:
 * - Your runtime is faster than 21.77% of Java online submissions for Construct Quad Tree.
 * - Your memory usage are less than 5.26% of Java online submissions for Construct Quad Tree.
*/

/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};
*/

class Solution {
    public Node construct(int[][] grid) {
        return make(grid, 0, 0, grid.length);
    }
    
    private Node make(int grid[][], int r, int c, int length) {
        // A ideia é construir os quadrantes recursivamente 
        if(length == 1)
            // A codição de parada é quando atingimos um quadrado
            return new Node(grid[r][c] == 1? true : false, true);
        // (I)
        Node topLeft = make(grid, r, c, length/2);
        // Permancemos na esquerda e somente diminuímos o tamanho da grid pela metade
        Node topRight = make(grid, r, c + length/2, length/2);
        // Diminuímos o tamanho da grid pela metade e nos deslocamos para a direita
        Node bottomLeft = make(grid, r + length/2, c, length/2);
        // Diminuímos o tamanho da grid pela metade e nos deslocamos para baixo
        Node bottomRight = make(grid, r + length/2, c + length/2, length/2);
        // Diminuímos o tamanho da grid pela metade e nos deslocamos para a direita e para baixo
        
        if(topLeft.val == topRight.val && bottomLeft.val == bottomRight.val && topLeft.val == bottomLeft.val && topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf)
            // Se todos os valores forem iguais e todos os elementos forem folhas:
            return new Node(topLeft.val, true);
            // Retornamos um novo nó contendo esse valor e contendo o indicativo de que é um nó folha 
        else
            // Caso contrário:
            return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
            // Retornamos um novo nó não folha e todos os seus quatro nós filhos. Vale observar que o valor do
            // do nó em si é true, mas poderia ser false também, uma vez que o enunciado da questão diz que podemos     
            // atribuir o valor de um nó para True ou False quando isLeaf for False.
    }
}

/*
* Comentários:
* I: Como a grid é um quadrado, para andarmos entre os seus quadrantes
*    basta nos deslocarmos em metade do seu tamanho atual para a direção 
*    desejada. Vale lembrar que começamos da esquerda para a direita e de
*    cima para baixo (começamos no índice [0][0])
**/