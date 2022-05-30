package conjuntistas;

public class NodoAVL {
    
    private Comparable elem;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
   private int altura;
    
    
   //Constructor

   public NodoAVL(Comparable elem)
   {
       /*
       Este metodo crea un nodo AVL.
       */
       
       this.elem = elem;
       altura = 0;
   }
   
     
      //Observadores
    
    public Comparable getElem()
    {
        /*
        Este metodo retorna el elemento del NodoAVL.
        */
        
        return elem;
    }
    
    public NodoAVL getIzquierdo()
    {
        /*
        Este metodo retorna hijo izquierdo del NodoAVL.
        */
        
        return izquierdo;
    }
    
    public NodoAVL getDerecho()
    {
        /*
        Este metodo retorna el hijo derecho del NodoAVL.
        */
        
        return derecho;
    }
    
    public int getAltura()
    {
        /*
        Este metodo retorna la altura del NodoAVL.
        */
        return altura;
    }
    
    //Modificadores
    
    public void setElem(Comparable elem)
    {
        /*
        Este metodo modifica el atributo elemento.
        */
        
        this.elem = elem;       
    }
    
    public void setIzquierdo(NodoAVL izquierdo)
    {
        /*
        Este metodo modifica el atributo izquierdo.
        */
        
        this.izquierdo = izquierdo;     
        
        
    }
    
    public void setDerecho(NodoAVL derecho)
    {
        /*
        Este metodo modifica el atributo derecho.
        */
        
        this.derecho = derecho;       
    }
    
    //Propios del tipo
    
    public void recalcularAltura()
    {
        /*
        Este metodo recalcula la altura de un nodoAVL
        */
        
        int alturaIzq, alturaDer,resultado;
        
        if(this.izquierdo != null)
        {
            alturaIzq = this.izquierdo.altura;
        }
        else
        {
            alturaIzq = -1;
        }
        
        if(this.derecho != null)
        {
            alturaDer = this.derecho.altura;
        }
        else
        {
            alturaDer = -1;
        }
        
        altura = Math.max(alturaIzq, alturaDer) + 1;
        
    }
    
    

    
   
   
    
}
}
