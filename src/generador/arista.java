package generador;

public class arista implements Comparable <arista>{
    
    private int A1, A2; // para identificar nodos
    private int ID; // identificador de arista
    private double printarista;
    private boolean flag_arista;
    
    
    public arista (int An1, int An2){
        this.A1=An1;
        this.A2=An2;
        this.printarista=0;
        this.flag_arista=false;
    }
    
    public arista (int An1, int An2, double pb){
        this.A1=An1;
        this.A2=An2;
        this.printarista=pb;
        this.flag_arista=false;
    }
    
    public arista (arista ax){
        this.A1=ax.getAn1();
        this.A2=ax.getAn2();
        this.printarista=ax.getP();
        this.flag_arista=ax.getfal();
    }
    
    public int getAn1 (){
        return this.A1;
    }
    public int getAn2 (){
        return this.A2;
    }
    public int getID(){
        return this.ID;
    }
    public boolean getfal(){
        return this.flag_arista;
    }
    public void setAn1 (int idNodo){
        this.A1 = idNodo;
    }
    public void setAn2 (int idNodo){
        this.A2 = idNodo;
    }
    public void setID (int a){
        this.ID = a;
    }
    public void setfal (boolean a){
        this.flag_arista = a;
    }
    public void setP (double p){
        this.printarista=p;
    }
    public double getP (){
        return this.printarista;
    }
    
    public void Cop (arista ay){
        this.A1=ay.getAn1();
        this.A2=ay.getAn2();
        this.printarista=ay.getP();
    }
    
@Override
public int compareTo(arista A1){
    if (this.printarista > A1.getP()) return 1;
    else if (this.printarista < A1.getP()) return -1;
    else return 0;
}
}
