/**
 * Status: Accepted
 * 45 / 45 test cases passed.
 * Runtime: 59 ms
 * Memory Usage: 60 MB
 * Details:
 * - Your runtime is faster than 68.18% of Java online submissions for Bus Routes.
 * - Your memory usage are less than 80.39% of Java online submissions for Bus Routes.
*/

import java.awt.Point;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source==target) return 0;
        int N = routes.length;

        // Estrutura em que cada índice da lista corresponde a um nó (ónibius) e a lista contida em cada índice 
        // corresponde à lista de todos os nós visinhos (corresponde a todos os outros ônibus que compartilham ao menos   
        // uma parada com esse ônibus representado pelo índice em questão)
        List<List<Integer>> graph = new ArrayList(); 
        
        // Inicializando o grafo
        for (int i = 0; i < N; ++i) {
            Arrays.sort(routes[i]); // (I)
            graph.add(new ArrayList()); // (II)
        }
        
        Set<Integer> seen = new HashSet(); // Armazena todos os nós (ônibus) que já foram enfileirados na fila
        Set<Integer> targets = new HashSet(); // Armazena todos os ônibus que passam pela parada de destino
        Queue<Point> queue = new ArrayDeque(); // Armazena todos os ônibus (números) que podem fazer parte da solução 
        // final. Estrutura que será utilizada para a busca em largura. Inicia com todos os ônibus que passam pela
        // parada inicial

        // Preenchendo o grafo
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                // (III)
                if (intersect(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }

        // Initializando seen, queue, targets.
        for (int i = 0; i < N; ++i) {
            if (Arrays.binarySearch(routes[i], source) >= 0) {
                // Adiciona o ônibus na lista de vistos
                seen.add(i);
                // Adiciona no fim da fila o número que representa o nó (ônibus), juntamente com a profundidade desse nó
                queue.offer(new Point(i, 0));
            }
            if (Arrays.binarySearch(routes[i], target) >= 0)
                // Adiciona o número que representa o nó (ônibus) na lista de todos os ônibus que passam para parada alvo 
                targets.add(i);
        }

        while (!queue.isEmpty()) {
            // Pega o último elemento da fila
            Point info = queue.poll();
            int node = info.x, depth = info.y;
            // Se a lista de todos os ônibus que param na parada de destino contém o ônibus atual, retornamos
            // profundidade do nó (ônibus) atual + 1
            if (targets.contains(node)) return depth+1;
            // Caso contrário, para todos os vizinhos do nó atual (todos os ônibus que compartilham uma mesma parada     
            // atual): 
            for (Integer nei: graph.get(node)) {
                // Se esse nó náo foi visitado (se náo pegaram esse ônibus ainda)
                if (!seen.contains(nei)) {
                    // Adiciona o ônibus na lista de vistos
                    seen.add(nei);
                    // Adiciona no fim da o número que representa o nó (ônibus), juntamente com a profundidade desse nó
                    queue.offer(new Point(nei, depth+1));
                }
            }
        }

        return -1;
    }
    
    public boolean intersect(int[] routeOne, int[] routeTwo) {
        int i = 0, j = 0;
        // Equanto ambas as rotas ainda não foram totalmente percorridas, faça:
        while (i < routeOne.length && j < routeTwo.length) {
            // Quando uma mesma parada pertence às duas rotas:
            if (routeOne[i] == routeTwo[j]) return true;
            // (IV):
            // Já sabemos que routeTwo não tem a mesma parada que a parada atual de routeOne
            if (routeOne[i] < routeTwo[j]) i++;  // Passamos para a próxima parada de routeOne
            else j++; // Continuamos procurando se routeTwo tem a mesma parada que a parada atual de routeOne
        }
        return false;
    }
    
    /**
    * Comentários:
    * I: Ordenação necessária para a melhoria de desempenho durante a criação das arestas do grafo
    * II: Para cada ônibus teremos um nó do grafo
    * III: Dois ônibius estão conectados se eles compartilham ao menos uma parada de ônibus
    * IV: Por estatem ordenadas, significa que nenhuma outra parada de routeTwo poderá ser igual a 
          parada atual de routeOne (routeOne[i]), pois todas elas com certeza são maiores que a parada 
          atual de routeTwo (routeTwo[j]), logo, maiores que a parada autal de routeOne (routeOne[i])
    */
}