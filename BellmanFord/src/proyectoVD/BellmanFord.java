package proyectoVD;

import java.util.Scanner;

public class BellmanFord {
    private int distancias[]; //distancias almacenadas desde el nodo origen a los demas nodos
    private int numvertices; //numero de vertices o nodos de la matriz de adyacencia
    public static final int INFINITO = 99999; //se colocó el numero 99999 en representacion del valor infinito

    public BellmanFord(int numvertices) {
        this.numvertices = numvertices;
        distancias = new int[numvertices + 1]; //se crea el array que almacenará las distancias
                                                // del nodo origen al resto de nodos
    }

    public void EnrutamientoBF(int valor, int matrixady[][]) {
        for (int nodo = 1; nodo <= numvertices; nodo++) {
            distancias[nodo] = INFINITO; //se llena el array con infinitos para luego colocar el valor correspondiente
                                            //en cada uno de los valores del array.

            //en el caso de que el valor hacia algun nodo quede en INFINITO, significa que no exite conexion hacia
            //ese nodo. Esto se podrá apreciar en la impresion de las distancias
        }
        distancias[valor] = 0; //la distancia del nodo origen a si mismo se llena con cero


        //en la siguiente parte está la evaluacion para la minima distancia entre los vertices.
        for (int nodo = 1; nodo <= numvertices; nodo++) { //para cada nodo se realiza la evaluacion
            // se hace un recorrido de total de la matriz con un "doble for"
            // para evaluar las distancias hacia los nodos
            for (int inicio = 1; inicio <= numvertices; inicio++) {
                for (int destino = 1; destino <= numvertices; destino++) {
                    if (matrixady[inicio][destino] != INFINITO) { //si existe conexion entre los nodos seleccionados
                                                                    //se realizará la evaluacion de distancia minima
                        if (distancias[destino] > distancias[inicio] + matrixady[inicio][destino]) {
                            //si la distancia hacia el nodo destino evaluado es mayor que la distancia desde el nodo
                            //origen hacia el nodo inicio + la distancia desde el nodo inicio a destino guardada en
                            //la matriz, se reemplazara la distancia almacenada en el array por
                            //la nueva distancia minima encontrada
                            distancias[destino] = distancias[inicio] + matrixady[inicio][destino];
                        }
                    }
                }
            }
        }
        //el siguiente fragmento de codigo permite evaluar la existencia de algun ciclo negativo existente en las
        //distancias almacenadas en el array
        for (int inicio = 1; inicio <= numvertices; inicio++) {
            for (int destino = 1; destino <= numvertices; destino++) {
                if (matrixady[inicio][destino] != INFINITO) {
                    if (distancias[destino] < distancias[inicio] + matrixady[inicio][destino]) {
                        System.out.println("El grafo tiene ciclo negativo");
                    }
                }
            }
        }

        //impresion de las distancias hacia los vertices
        for (int vertice = 1; vertice <= numvertices; vertice++) {
            if (distancias[vertice]!=INFINITO) {
                System.out.println("La distancia minima de " + valor + " a " + vertice + " es " + distancias[vertice]);
            }else{
                System.out.println("No hay conexion desde el vertice "+ valor + " hacia el vertice "+ vertice);
            }
        }

    }
    public static void main(String[] args) {
        int numeroVertices=0;
        int valor;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el numero de vertices: ");
        numeroVertices = scanner.nextInt();
        int matrizAdj[][] = new int[numeroVertices+1][numeroVertices+1];
        System.out.println("La matriz de adyacencia debera ser ingresada de la siguiente manera (respetando los espacios):\n");
        System.out.println(" [ 0 1 2 3 4 5 ] \n [ 0 1 2 3 4 5 ] \n [ 0 1 2 3 4 5 ] \n [ 0 1 2 3 4 5 ] \n [ 0 1 2 3 4 5 ] \n");
        System.out.println("Ingrese los pesos de la matriz : ");

        //en la siguiente parte, se prefirió llenar con el valor infinito donde la distancia es 0, a excepcion
        //de los casos en que la distancia es 0 porque es la distancia del nodo a si mismo (por eso el uso de la
        //funcion "continue")
        for (int inicio = 1; inicio <= numeroVertices; inicio++){
            for (int destino=1;destino<=numeroVertices;destino++){
                matrizAdj[inicio][destino] = scanner.nextInt();
                if (inicio==destino){
                    matrizAdj[inicio][destino]=0;
                    continue;
                }
                if (matrizAdj[inicio][destino] == 0){
                    matrizAdj[inicio][destino]= INFINITO;
                }
            }
        }
        System.out.println("Ingrese el vertice de origen: ");
        valor = scanner.nextInt();
        BellmanFord bellmanFord = new BellmanFord(numeroVertices);
        bellmanFord.EnrutamientoBF(valor,matrizAdj);
        scanner.close();
    }
}








