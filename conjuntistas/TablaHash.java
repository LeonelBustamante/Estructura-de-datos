package conjuntistas;

public class TablaHash {
    
    private static int TAMANIO = 10;
    private Nodo[] tabla;
    private int cant;
    
    //Constructor
    
    public TablaHash()
    {
        /*
        Este metodo crea una tabla hash abierta.
        */
        
        tabla = new Nodo[TAMANIO];
        cant =  0;
    }
    
    //Modificadores
    
    public boolean insertar(Object elemento)
    {
        /*
        Este metodo busca a elemento en la tabla,y si no existe, se lo inserta.
        */
        
        int posc = elemento.hashCode() % TAMANIO;
        Nodo previo = null,actual = this.tabla[posc];
        boolean exito = true;
       
        if(actual == null)
        {
            //Si la casilla esta vacia,se agrega el primer elemento.
            tabla[posc] = new Nodo(elemento,null);
            cant++;
        }
        else
        {
            //Si no esta vacia,se recorre la lista.
            do
            {
                if(actual.getElemento().equals(elemento))
                {
                    //Si se encuentra el elemento,falla la insercion.
                    exito = false;
                }
                else
                {
                    //Si no, se sigue buscando en la lista.
                    previo = actual;
                    actual = actual.getEnlace();
                }
            }while(exito && actual != null);
            
            if(exito)
            {
                //Si no se encontro elemento en la tabla,se lo agrega al final de la lista.
                previo.setEnlace(new Nodo(elemento,null));
                cant++;
            }
        }
        
        return exito;
    }
    
    public boolean eliminar(Object elemento)
    {
        /*
        Este metodo busca a elemento en la lista,y si existe en esta,lo elimina.
        */
        boolean exito = false;
        int posc = elemento.hashCode() % TAMANIO;
        Nodo previo = null,actual = this.tabla[posc];
        
        if(actual != null)
        {
            //Si la casilla no esta vacia,se busca el elemento en la lista.
            do
            {
                if(actual.getElemento().equals(elemento))
                {
                    //Si se encuentra el elemento,la eliminacion tiene exito.
                    exito = true;
                    cant--;
                }
                else
                {
                    //Si no, se sigue buscando en la lista.
                    previo = actual;
                    actual = actual.getEnlace();
                }
            }while(!exito && actual != null);
        
        
            if(exito)
            {
               //Si se encontro el elemento,se lo elimina.
                if (previo == null) {
                    //El elemento es el primero de la lista.
                    tabla[posc] = actual.getEnlace();
                } 
                else
                {
                    //El elemento no es el primero ni ultimo de la lista.
                    previo.setEnlace(actual.getEnlace());
                }
            }
        }
        
        return exito;
    }
    
    //Observadores
    
    public boolean pertenece(Object elemento)
    {
        /*
        Este metodo retorna un boolean dependiendo de si el elemento pertenece a la tabla.
        */
        
        boolean exito = false;
        int posc = elemento.hashCode() % TAMANIO;
        Nodo actual = this.tabla[posc];
        
        if(actual != null)
        {
            //Si la casilla no esta vacia,se busca el elemento en la lista.
            do
            {
                if(actual.getElemento().equals(elemento))
                {
                    //Si se encuentra el elemento,la eliminacion tiene exito.
                    exito = true;
                }
                else
                {
                    //Si no, se sigue buscando en la lista.
                    actual = actual.getEnlace();
                }
            }while(!exito && actual != null);                          
        }
        
        return exito;
    }
    
    public boolean esVacio()
    {
        /*
        Este metodo retorna un boolean dependiendo si la tabla se encuentra vacia.
        */
        
       return cant == 0;
    }
   
    //Propios del tipo
    
    public Lista listar()
    {
        /*
        Este metodo genera una lista con los elemento de la tabla.
         */

        Lista resultado = new Lista();
        int poscTabla, poscLista;
        Nodo actual;

        if (cant > 0) 
        {
            //Si la tabla no esta vacia,se empieza a listar.
            
            poscTabla = 0;
            poscLista = 1;
            do 
            {
                //Se itera sobre las posiciones de la tabla,mientras sean menores a TAMANIO.
                
                actual = tabla[poscTabla];

                while (actual != null) 
                {
                    //Se itera sobre las posiciones de la lista almacenada en la casilla poscTabla.
                    //Se listan elementos mientras el actual no sea nulo.
                    
                    resultado.insertar(actual.getElemento(), poscLista++);
                    actual = actual.getEnlace();
                }

            } while (++poscTabla < TAMANIO);

        }
        return resultado;
    }
}
}
