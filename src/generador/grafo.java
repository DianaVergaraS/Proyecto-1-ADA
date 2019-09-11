package generador;

import java.io.FileWriter; //Para escribir archivos
import java.io.PrintWriter; //Para imprimir archivos
import java.util.HashMap;  //Para las claves únicas

public class grafo {

    private HashMap<Integer, arista> Aristas;
    private HashMap<Integer, vertice> Vertices;


    public grafo(HashMap<Integer, vertice> N, HashMap<Integer, arista> A) {
        this.Aristas = new HashMap();
        for (int i = 0; i < A.size(); i++) {
            this.Aristas.put(i, new arista(A.get(i)));
        }
        this.Vertices = new HashMap();
        for (int i = 0; i < N.size(); i++) {
            this.Vertices.put(i, new vertice(N.get(i)));
        }
    }

    public grafo() {
        this.Aristas = new HashMap();
        this.Vertices = new HashMap();
    }

    public grafo(grafo k) {
        this.Aristas = new HashMap();
        for (int i = 0; i < k.getAristas().size(); i++) {
            this.Aristas.put(i, new arista(k.getAristas().get(i)));
        }
        this.Vertices = new HashMap();
        for (int i = 0; i < k.getNodos().size(); i++) {
            this.Vertices.put(i, new vertice(k.getNodos().get(i)));
        }
    }

    public void setNodos(HashMap<Integer, vertice> w) {
        this.Vertices = w;
    }

    public void setAristas(HashMap<Integer, arista> w) {
        this.Aristas = w;
    }

    public HashMap<Integer, arista> getAristas() {
        return this.Aristas;
    }

    public HashMap<Integer, vertice> getNodos() {
        return this.Vertices;
    }

    // Método de Erdos-Rényi
    public static grafo ErdosRenyi(int NumNodos, int NumAristas, int dirigido) {
        HashMap<Integer, vertice> NodoS = new HashMap();
        HashMap<Integer, arista> AristaS = new HashMap();

        int AristasHechas;

        for (int i = 0; i < NumNodos; i++) {
            NodoS.put(i, new vertice(i));
        }

        int x1 = (int) (Math.random() * NumNodos), x2 = (int) (Math.random() * NumNodos);
        AristaS.put(0, new arista(NodoS.get(x1).get(), NodoS.get(x2).get(), Math.random()));

        while (x1 == x2 && dirigido == 0) {
            x1 = (int) (Math.random() * NumNodos);
            x2 = (int) (Math.random() * NumNodos);
            AristaS.put(0, new arista(NodoS.get(x1).get(), NodoS.get(x2).get(), Math.random()));
        }

        NodoS.get(x1).conectar();
        NodoS.get(x2).conectar();
        if (x1 != x2) {
            NodoS.get(x1).IncGrado(1);
        }
        NodoS.get(x2).IncGrado(1);

        AristasHechas = 1;
        while (AristasHechas < NumAristas) {
            x1 = (int) (Math.random() * NumNodos);
            x2 = (int) (Math.random() * NumNodos);

            if (x1 != x2 || dirigido == 1) {
                int c1 = 1, cont = 0;
                while (c1 == 1 && cont < AristasHechas) {
                    int a = AristaS.get(cont).getAn1(), b = AristaS.get(cont).getAn2();
                    if ((x1 == a && x2 == b) || (x1 == b && x2 == a)) {
                        c1 = 0;
                    }
                    cont++;
                }
                if (c1 == 1) {
                    AristaS.put(AristasHechas, new arista(NodoS.get(x1).get(), NodoS.get(x2).get(), Math.random()));
                    NodoS.get(x1).conectar();
                    NodoS.get(x2).conectar();
                    if (x1 != x2) {
                        NodoS.get(x1).IncGrado(1);
                    }
                    NodoS.get(x2).IncGrado(1);
                    AristasHechas++;
                }
            }
        }

       
        grafo G = new grafo(NodoS, AristaS);
        return G;

    }
    
 // Método de Gilbert
    public static grafo Gilbert(int NumNodos, double probabilidad, int dirigido) {
        HashMap<Integer, vertice> NodoS = new HashMap();
        HashMap<Integer, arista> AristaS = new HashMap();

        int NumAristas = 0;

        for (int i = 0; i < NumNodos; i++) {
            NodoS.put(i, new vertice(i));
        }

        for (int i = 0; i < NumNodos; i++) {
            for (int j = i; j < NumNodos; j++) {
                if (j != i || dirigido == 1) {
                    if (Math.random() <= probabilidad) {
                        AristaS.put(NumAristas, new arista(NodoS.get(i).get(), NodoS.get(j).get()));
                        NodoS.get(i).conectar();
                        NodoS.get(j).conectar();
                        if (i != j) {
                            NodoS.get(i).IncGrado(1);
                        }
                        NodoS.get(j).IncGrado(1);
                        NumAristas++;
                    }
                }
            }
        }
        grafo G = new grafo(NodoS, AristaS);
        return G;
    }
    
    //Método Geográfico
    public static grafo Geografico(int NumNodos, double distancia, int dirigido) {
        HashMap<Integer, vertice> NodoS = new HashMap();
        HashMap<Integer, arista> AristaS = new HashMap();
        int NumAristas = 0;

        for (int i = 0; i < NumNodos; i++) {
            NodoS.put(i, new vertice(i, Math.random(), Math.random()));
        }

        for (int i = 0; i < NumNodos; i++) {
            for (int j = i; j < NumNodos; j++) {
                if (j != i || dirigido == 1) {
                    double dis = Math.sqrt(Math.pow(NodoS.get(j).getX() - NodoS.get(i).getX(), 2)
                            + Math.pow(NodoS.get(j).getY() - NodoS.get(i).getY(), 2));
                    if (dis <= distancia) {
                        AristaS.put(NumAristas, new arista(NodoS.get(i).get(), NodoS.get(j).get()));
                        NodoS.get(i).IncGrado(1);
                        NodoS.get(i).conectar();
                        if (j != i) {
                            NodoS.get(j).IncGrado(1);
                            NodoS.get(j).conectar();
                        }
                        NumAristas++;
                    }
                }
            }
        }
        grafo G = new grafo(NodoS, AristaS);
        return G;
    }

// Método Barábasi
    public static grafo Barabasi(int NumNodos, double d, int dirigido) {
        HashMap<Integer, vertice> NodoS = new HashMap();
        HashMap<Integer, arista> AristaS = new HashMap();

        int NumAristas = 0;

        for (int i = 0; i < NumNodos; i++) {
            NodoS.put(i, new vertice(i));
        }

        for (int i = 0; i < NumNodos; i++) {
            int j = 0;
            while (j <= i && NodoS.get(i).getGrado() <= d) {
                if (j != i || dirigido == 1) {
                    if (Math.random() <= 1 - NodoS.get(j).getGrado() / d) {
                        AristaS.put(NumAristas, new arista(NodoS.get(i).get(), NodoS.get(j).get()));
                        NodoS.get(i).IncGrado(1);
                        NodoS.get(i).conectar();
                        if (j != i) {
                            NodoS.get(j).IncGrado(1);
                            NodoS.get(j).conectar();
                        }
                        NumAristas++;
                    }
                }
                j++;
            }
        }
        grafo G = new grafo(NodoS, AristaS);
        return G;
    }

    public static void construir(String nombre, grafo g) {
        FileWriter fichero = null;
        PrintWriter pw = null;

        System.out.println(g.getNodos().size());

        try {
            fichero = new FileWriter(nombre + ".gv");
            pw = new PrintWriter(fichero);
            pw.println("graph {"); //Por Gephi
            pw.println();
            for (int i = 0; i < g.getAristas().size(); i++) {
                pw.println(g.getAristas().get(i).getAn1() + "--" + g.getAristas().get(i).getAn2() + "  " + " ");
            }
            pw.println("}"); // print nodos y enlaces

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {

        grafo grafo_creado = new grafo();
        //grafo_creado = ErdosRenyi(500,500,0);
        //grafo_creado = Gilbert(500,.19,0);
        //grafo_creado= Geografico(500,.4,0);        
        grafo_creado = Barabasi(500,1024,0);  
        construir("Bar3",grafo_creado);
    }
}
