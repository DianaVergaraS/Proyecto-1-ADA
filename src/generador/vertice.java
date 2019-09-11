package generador;

public class vertice {
    
    private int Grado; // Grado del v�rtice
    private int ID; // identificador de v�rtice
    private double s,t; 
    private double printnodo;
    private int conectado;
    private boolean flag_nodo;

     
    public vertice (){
        this.Grado = 0;
        this.s = 0;
        this.t = 0;
        this.conectado = 0;
        this.flag_nodo = false;
        this.printnodo = 0;
    }
    
    public vertice (vertice nx){
        this.Grado = nx.getGrado();
        this.ID = nx.get();
        this.s = nx.getX();
        this.t = nx.getY();
        this.conectado = nx.getConectado();
        this.flag_nodo = nx.getfal();
        this.printnodo = nx.getwin();
    }   
    
    public vertice (int id){    
        this.ID = id;
        this.Grado = 0;
        this.s = 0;
        this.t = 0;
        this.conectado = 0;
        this.flag_nodo = false;
        this.printnodo = 0;
    } 
    
    public vertice (int id, int connected, int degree, double x1, double y1, boolean g, double p){
    	
        this.ID = id;
        this.conectado = connected;
        this.Grado = degree;
        this.s = x1;
        this.t = y1;
        this.flag_nodo = g;
        this.printnodo = p;
    }
    
    public vertice (int id, double x1, double y1){    
        this.ID = id;
        this.Grado = 0;
        this.s = x1;
        this.t = y1;
        this.flag_nodo = false;
        this.conectado = 0;
        this.printnodo = 0;
    }
    
    public void Cop (vertice ny){
        this.Grado = ny.getGrado();
        this.ID = ny.get();
        this.s = ny.getX();
        this.t = ny.getY();
        this.conectado = ny.getConectado();
        this.flag_nodo = ny.getfal();
        this.printnodo = ny.getwin();
    }
    
    public void set (int id){ 
        this.ID = id;
    }
    public int get (){
        return this.ID;
    }    
    public void setGrado (int i){
        this.Grado = i;
    }    
    public int getGrado (){
        return this.Grado;
    }    
    public void IncGrado (int i){
        this.Grado=this.Grado+i;
    } 
    public void DecGrado (int i){
        this.Grado=this.Grado-i;
    }
    public void setX (double x1){
        this.s=x1;
    }
    public void setY (double x1){
        this.t=x1;
    }
    public double getX (){
        return this.s;
    }
    public double getY (){
        return this.t;
    }
    
    public void conectar(){
        this.conectado=1;
    }
    public int getConectado(){
        return this.conectado;
    }
    
    public void setfal (boolean a){
        this.flag_nodo = a;
    }
    public boolean getfal (){
        return this.flag_nodo;
    }
    
    public void setwi (double p){
        this.printnodo = p;
    }
    public double getwin (){
        return this.printnodo; 
    }
}
