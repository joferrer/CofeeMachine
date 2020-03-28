/**
 * Complete
 * @author (Milton Jesus Vera Contreras - miltonjeussvc@ufps.edu.co) 
 * @version 0.000000000000001 :) --> Math.sin(Math.PI-Double.MIN_VALUE)
 */
class MaquinaCafetera { 

    int cafe;
    int azucar;
    int vasos;
    int precioBaseCafe;
    int egresos;
    int ingresos;
    int gananciasBrutas;
    int impuestos;
    int gananciasNetas;
    /*No requiere propiedades adicionales, pero es libre de usarlas*/

    MaquinaCafetera(){
        //complete
        
    }

    MaquinaCafetera(int cantidadInicialCafe, int cantidadInicialAzucar, int cantidadInicialVasos){
        //complete
        this.cafe=cantidadInicialCafe;
        this.azucar= cantidadInicialAzucar;
        this.vasos=cantidadInicialVasos;
    }
    public int getVasos(){
    
        return this.vasos;
    }

    public int getAzucar(){
    return this.azucar;
    }
    public int getCafe(){
    return this.cafe;}
    public int getPrecioBaseCafe(){
    return this.precioBaseCafe;}
    public int getEgresos(){
    return this.egresos;
    }
    public int getIngresos(){
    return this.ingresos;
    }
    public int getGananciasBrutas(){
    return this.gananciasBrutas;
    }
    public int getGananciasNetas(){
    return this.gananciasNetas;
    }
    public int getImpuestos(){
    return this.impuestos;
    }
    public void setAzucar(int azucar){
     this.azucar=azucar;
    }
    public void setCafe(int cafe){
     this.cafe=cafe;
    }
    public void setVasos(int vasos){
     this.vasos=vasos;
    }
    public void setPrecioBaseCafe(int pPrecioBaseCafe){
     this.precioBaseCafe=pPrecioBaseCafe;
    }
     public void setEgresos(int egresos){
     this.egresos=egresos;
    }
     public void setIngresos(int ingresos){
     this.ingresos=ingresos;
    }
     public void setImpuestos(int impuestos){
     this.impuestos=impuestos;
    }
     public void setGananciasBrutas(int gananciasBrutas){
     this.gananciasBrutas=gananciasBrutas;
    }
     public void setGananciasNetas(int gananciasNetas){
     this.gananciasNetas=gananciasNetas;
    }
    //complete metodos GET / SET

    public int calcularPrecio(int tipoCafe, int cantidadAzucar) {
        //complete
        int precio=0;
       // if(prepararCafe(tipoCafe, cantidadAzucar)){
        switch(tipoCafe){
            case 1:    
            precio += (precioBaseCafe/1000)*55;
            break;
            case 2:
            precio+=(precioBaseCafe/1000)*55*2;
            break;
            case 3:
            precio+=(precioBaseCafe/1000)*55*3;
            break;
        }
        switch(cantidadAzucar){             
            case 2:
            precio+=precio*0.05;
            break;
            case 3:
            precio+=precio*0.10;
            break;
        
        }
        precio+=precio*0.15;
        registrarFactura(precio);
   // }
        return precio;
    }

    public boolean recargarCafe(int cantidadCafe, int costoCompraCafe) {
        //complete
        boolean puede =false;
        if(cantidadCafe*costoCompraCafe<=this.gananciasNetas){
            this.cafe+=cantidadCafe;
            this.egresos+=costoCompraCafe;
           
            this.gananciasBrutas-=costoCompraCafe;
             this.gananciasNetas=gananciasBrutas-costoCompraCafe;
            puede =true;
        }
        return puede;
    }

    public boolean recargarAzucar(int cantidadAzucar, int costoCompraAzucar) {
        //complete
        boolean puede =false;
        if(cantidadAzucar*costoCompraAzucar<=this.gananciasNetas){
            this.azucar+=cantidadAzucar;
            this.egresos+=costoCompraAzucar;
             
             this.gananciasBrutas-=costoCompraAzucar;
             this.gananciasNetas=gananciasBrutas-costoCompraAzucar;
            puede = true;
        }
        return puede;
    }

    public boolean recargarVasos(int cantidadVasos, int costoCompraVasos) {
        //complete
        boolean puede =false;
        if(cantidadVasos*costoCompraVasos<=this.gananciasNetas){
            this.vasos+=cantidadVasos; 
            this.egresos+=costoCompraVasos;
            this.gananciasBrutas-=costoCompraVasos;
            this.gananciasNetas=gananciasBrutas-costoCompraVasos;
            
            
            puede = true;
        }
        return puede;
    }

    public boolean prepararCafe(int tipoCafe, int cantidadAzucar) {
        //complete
        boolean puede=false;
        int canAz=0;
        switch(cantidadAzucar){
            case 1:
            puede=true;
            break;
            case 2:
            if(this.azucar>=5){puede=true; canAz=5;}break;
            case 3:
            if(this.azucar>=10){puede=true;canAz=10;} break;
        }
        if(vasos>0&&cafe>=55&&puede)
        {
            switch(tipoCafe){
                case 1:
                this.cafe-=55;              
                break;
                case 2:
                if(this.cafe>=55*2){this.cafe-=55*2;}
                else puede=false;
                break;
                case 3:
                if(this.cafe>=55*3){this.cafe-=55*3;}
                else puede=false;
                break;
                
            }
           
            if(puede){
            this.vasos--;
            this.azucar-=canAz;
           // calcularPrecio(tipoCafe, cantidadAzucar);
        }
        }
        else puede =false;
        return puede;
    }

    public void registrarFactura(int valorFactura) {
     //complete
     this.gananciasBrutas+=valorFactura;
     this.gananciasNetas+=valorFactura- valorFactura*0.16;
     this.ingresos += valorFactura;
     this.impuestos+=valorFactura*0.16; 
    
    }

}
