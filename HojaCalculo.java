
/**
 *  Un objeto de esta clase representa a una sencilla
 *  hoja de cálculo. La hoja tiene hasta un máximo de 3 filas (no más)
 *  En cada fila la empresa "apunta" los ingresos y gastos en 
 *  una determinada fecha
 * 
 * @author - Pedro José Aquerreta  
 *  
 */
public class HojaCalculo
{
    private String nombre;
    private Fila fila1;
    private Fila fila2;
    private Fila fila3;

    /**
     * Constructor  
     * Crea la hoja de cálculo con el nombre indicado 
     * e inicializa las filas al valor null (inicialmente
     * la hoja se crea sin filas)
     */
    public HojaCalculo(String nombre)    {
        this.nombre = nombre;
        this.fila1 = null;
        this.fila2 = null;
        this.fila3 = null;
    }

    /**
     * accesor para el nombre de la hoja
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * accesor para la fila1
     */
    public Fila getFila1() {
        return fila1;
    }

    /**
     * accesor para la fila2
     */
    public Fila getFila2() {
        return fila2;
    }

    /**
     * accesor para la fila3
     */
    public Fila getFila3() {
        return fila3;
    }

    /**
     * Devuelve el nº de filas de la hoja
     * (dependerá de cuántas filas estén a null)
     */
    public int getNumeroFilas() {
        int numeroFilas = 0;
        if(this.fila1 != null){
            numeroFilas++;
        }
        if(this.fila2 != null){
            numeroFilas++;
        }
        if(this.fila3 != null){
            numeroFilas++;
        }
        return numeroFilas;

    }

    /**
     * Devuelve true si la hoja está completa
     * (tiene exactamente 3 filas)
     */
    public boolean hojaCompleta() {
        boolean hojaCompleta = false;
        if(getNumeroFilas() == 0){
            hojaCompleta = true;
        }
        return true;
    }

    /**
     * Se añade una nueva fila a la hoja
     * Si la hoja está completa se muestra el mensaje "FilaX no se puede añadir en HOJAX"
     * Si no está completa se añade la fila a la hoja teniendo en cuenta
     * si se añade como primera, segunda o tercera fila (no han de quedar huecos)
     */
    public void addFila(Fila fila) {
        switch (getNumeroFilas()){
            case 3: System.out.println(fila.getId() + " no se puede añadir en "
                + this.nombre);
            break;
            case 2: this.fila3 = fila;
            break;
            case 1: this.fila2 = fila;
            break;
            case 0: this.fila1 = fila;
            break;
        }
    }

    /**
     * Dada la información a guardar en una fila el método
     * crea la fila y la añade a la hoja
     * (evita repetir código)
     */
    public void addFila(String id, Fecha fecha, double ingresos, double gastos) {
        addFila(new Fila(id, fecha, ingresos, gastos));
    }

    /**
     * Calcula y devuelve el total de ingresos entre
     * todas las filas que incluye la hoja
     */
    public double getTotalIngresos() {
        double totalIngresos = 0;
        switch(getNumeroFilas()){
            case 3: totalIngresos += fila3.getIngresos();
            case 2: totalIngresos += fila2.getIngresos();
            case 1: totalIngresos += fila1.getIngresos();
            break;
        }
        return totalIngresos;
    }

    /**
     * Calcula y devuelve el total de gastos
     * entre todas las filas que incluye la hoja
     */
    public double getTotalGastos() {
        double totalGastos = 0;
        switch(getNumeroFilas()){
            case 3: totalGastos += fila3.getGastos();
            case 2: totalGastos += fila2.getGastos();
            case 1: totalGastos += fila1.getGastos();
            break;
        }
        return totalGastos;
    }

    /**
     * Calcula y devuelve el total del beneficio
     * entre todas las filas que incluye la hoja
     */
    public double getBeneficio() {
        double beneficio = 0;
        switch(getNumeroFilas()){
            case 3: beneficio += fila3.getBeneficio();
            case 2: beneficio += fila2.getBeneficio();
            case 1: beneficio += fila1.getBeneficio();
            break;
        }
        return beneficio;
    }

    /**
     * Representación textual de la hoja
     * con el formato exacto que indica el enunciado
     */
    public String toString() {
        String filas = "";
        switch(getNumeroFilas()){
            case 3: filas += String.format("\n%60s", fila3);
            case 2: filas = String.format("\n%60s", fila2) + filas;
            case 1: filas = String.format("\n%60s", fila1) + filas;
            break;
        }
        return String.format("%s\n%23s%15s%15s%15s" +
            "\n%180s" + 
            "\n------------------------------------------------------------------------" + 
            "\n%37.2f€%14.2f€%14.2f€",
            this.nombre, "FECHA", "INGRESOS", "GASTOS", "BENEFICIO",
            filas, getTotalIngresos(), getTotalGastos(), getBeneficio());

    }

    /**
     * Devuelve un duplicado de la hoja actual.
     * El nombre de la hoja duplicada será "Duplicada HojaX"
     * Al duplicar la hoja se duplicarán también las filas que contenga
     */
    public HojaCalculo duplicarHoja() {
        HojaCalculo copia = new HojaCalculo("Duplicada " + this.nombre);
        copia.addFila(this.fila1);
        copia.addFila(this.fila2);
        copia.addFila(this.fila3);
        return copia;
    }

}
