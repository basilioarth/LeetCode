/**
 * Status: Accepted
 * 45 / 45 test cases passed.
 * Runtime: 1 ms
 * Memory Usage: 41.9 MB
 * Details:
 * - Your runtime is faster than 92.73% of Java online submissions for Dungeon Game.
 * - Your memory usage are less than 5.98% of Java online submissions for Dungeon Game.
*/

class Solution {
    // (I)
    public int calculateMinimumHP(int[][] dungeon) {
        int numRows = dungeon.length;
        int numColumns = dungeon[0].length;
        // Estrutura que irá armazenar a quantidade mínima de vida 
        // necessária para se estar naquela célula.
        int[][] dp = new int[numRows][numColumns];
        
        // O mínimo de vida necessário para se ter na última célula é 1, a não ser que 
        // exista um demônio nela. Nesse último caso, o valor mínimo para se ter nessa célula
        // é 1 unidade a mais que o valor absoluto do valor negativo contido nessa célula
        dp[numRows-1][numColumns-1] = Math.max(1, 1 - dungeon[numRows-1][numColumns-1]); // (II)
        
        // Começamos pela última célula
        for(int r=numRows-1; r >= 0; r--){
          for(int c=numColumns-1; c >= 0; c--){
            // Ignorando o cálculo da última célula como já fizemos
            if(r == numRows-1 && c == numColumns-1)
              continue;

            int minHealthToGoRight = minHealth(r, c, r, c+1, dungeon, dp);
            int minHealthToGoDown = minHealth(r, c, r+1, c, dungeon, dp);

            // A quantidade mínima de vida necessária no quarto [r][c] é a quantidade mínima 
            // entre a quantidade mínima de vida para descer e a quantidade mínima de vida para 
            // ir para a direita
            dp[r][c] = Math.min(minHealthToGoRight, minHealthToGoDown);
          }
        }
        
        return dp[0][0]; 
        // O valor pertencente à origem contém a quantidade mínima de vida do cavaleiro para 
        // que ele   possa resgatar a princesa.
      }
    
      int minHealth(int currRow, int currCol, int nextRow, int nextCol, int[][] dungeon, int[][]dp){
         // Se ultrapassarmos ao menos uma das extremidades da masmorra:
         if(nextRow >= dungeon.length || nextCol >= dungeon[0].length)
           return Integer.MAX_VALUE;

         // A quantidade mínima de vida para mover em uma célula é 1, a menos que a próxima célula 
         // exija vida adicional. Então essa vida adicional é calculada e o máximo entre a vida adicional 
         // exigida e o valor 1 será a quantidade de vida mínima necessária para mover para a próxima célula
         return Math.max(1, dp[nextRow][nextCol] - dungeon[currRow][currCol]); // (III)
      }
}

/*
* Comentários:
* I: Para este problema a abordagem gulosa de verificar qual caminho é menos
*    custoso tendo em vista somente a célula atual não leva ao melhor resultado.
*    Veja o exemplo a seguir:
*
*                   [-2, -3, -8]         [1,   1, -8]
*                   [-5, -9,  1]         [5,  -9, -3]
*                   [10,  5,  1]         [10, -5, -2]
*                                   
*    Seguindo a estratégia gulosa teríamos o seguinte caminho formado: -2 -> -3 
*    -> -8 -> 1 -> 1 que resultaria em um total mínimo de 14 pontos de vida inical. 
*    Porém, o caminho -2 -> -5 -> 10 -> 5 -> 1 resultaria num total mínimo de 8 pontos.
*    Pensar dessa mesma forma, porém començando do fim nos leva ao mesmo engano (basta
*    olhar para a matriz de exemplo mais a direita). 
*
*    Portanto, a ideia adotada consiste em começar na quarto final e ir preenchendo cada
*    quarto com a quantidade mínima de vida necessária para se estar naquele quarto. 
*    O valor a ser recebido por aquele quarto depende da movimentação a ser feita. Entre 
*    se mover para a direita ou para baixo, move-se para aquela célula que exigir uma menor 
*    quantidade de vida mínima.
*
*    Para se estar em uma célula, a quantidade mínima de vida necessária é 1,
*    ao menos que aquela célula exija uma quantidade adicional de vida. Para esses casos,
*    a quantidade mínima de vida necessária para se estar naquela célula é o maior valor
*    entre o número 1 e a diferença entre vida exigida pela célula e a vida atual.
*
* II: Value reforçar que esse cálculo faz sentido. Quando o valor existente na célula é um valor
*     positivo (não existe um demônio), a quantidade mínima de vida exigida sempre será 1, pois 
*     estamos escolhendo entre o número 1 e o valor de 1 - um número positivo (essa subtração 
*     sempre vai resultar em um valor menor ou igual a 1).
*
*     Caso haja um demônio no quarto, o valor escolhido será o segundo parâmetro. Ele é justamente 
*     a quantidade de vida que o demônio retira + 1 (que é o mínimo necessário para que o guerreiro
*     sobreviva); 
*
* III: Value reforçar que essa cálculo faz sentido. O menor valor possível assimido por uma célula
*      da matriz dp é 1. Se o valor existente na próxima célula for maior que o valor existente
*      na célula atual, significa que a movimentação para essa próxima célula exige uma vida adicional.
*      Isso porque o valor que existe em cada uma das células de dp representa justamente a quantidade
*      mínima de vida necessária para se ficar naquela célula com vida.
*
*      Se o valor existente na próxima célula for menor ou igual ao valor existente na célula atual, 
*      significa que não é necessária uma quantidade de vida adicional para se mover para aquela célula.
*      Logo, a quantidade mínima de vida para estar naquela célula é 1, pois não há quantidade menor
*      de vida possível para se estar em uma célula com vida.
*/