import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.fxml.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControllerMaquinaCafetera implements Initializable {

   
    
    @FXML
    private ChoiceBox<String> chbC;
    private ObservableList<String> opcionesCafe = FXCollections.observableArrayList();
    
    @FXML
    private ChoiceBox<String> chbAzucar;
    private ObservableList<String> opcionesAzucar = FXCollections.observableArrayList();
    @FXML
    private Button btnPrecioBase;

    @FXML
    private Button btnRecargarCafe;

    @FXML
    private Button btnRecargarAzucar;

    @FXML
    private Button btnRecargarVasos;

    @FXML
    private Button btnRegistrarFactura;

    @FXML
    private Button btnPrepararCafe;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCantidadCafe;

    @FXML
    private TextField txtCantidadAzucar;

    @FXML
    private TextField txtCantidadVasos;

    @FXML
    private TextField txtPrecioBase;

    @FXML
    private TextField txtIngresos;

    @FXML
    private TextField txtEgresos;

    @FXML
    private TextField txtAntesIva;

    @FXML
    private TextField txtIva;

    @FXML
    private TextField txtGananciasNetas;
    
    private MaquinaCafetera maquina;
    
 
    @Override
    public void initialize(URL location,
              ResourceBundle resources){
                  cargarBarras();
                  actualizarPantalla();
                  
                }
    
    public ControllerMaquinaCafetera(){
        maquina= new MaquinaCafetera(1000,1000,100);
        maquina.setPrecioBaseCafe(1000);
    }

    @FXML
    void fijarPrecioBase() {
        int precio=0;
        while(precio<1000){
        precio = Integer.parseInt(desplegarInput("Ingrese precio base: ", "Fijar precio base"));
        if(precio<1000)desplegarError("El precio no puede ser menor que 1000 :(", "Precio invalido");
    }
        maquina.setPrecioBaseCafe(precio);
        actualizarPantalla();
    }

    @FXML
    void prepararCafe() {
        try{
        char tp =chbC.getValue().charAt(0);
        char az =chbAzucar.getValue().charAt(0);
        int tipo=Integer.parseInt(String.valueOf(tp));
        int azucar=Integer.parseInt(String.valueOf(az));
        if(maquina.prepararCafe(tipo,azucar)){
        txtPrecio.setText(String.valueOf(maquina.calcularPrecio(tipo,azucar)));        
        actualizarPantalla();
    }
    else desplegarError("No hay sufiente cafe, azucar o vasos", "No pudimos preparar cafe :(");
    }
    catch (Exception e) {
        desplegarError("Revisa si seleccionaste tipo de cafe y cantidad de azucar", "OHH NOO :(");
    }

    }

    @FXML
    void recargarAzucar() {
        int cantidad = Integer.parseInt(desplegarInput("Ingresar cantidad de azucar a recargar", "Recargar Azucar"));
        int costo = Integer.parseInt(desplegarInput("Cuanto cuesta", "Precio de azucar"));
        if(maquina.recargarAzucar(cantidad, costo))actualizarPantalla();
        else desplegarError("No hay suficientes ingresos :(", "No se pudo recargar azucar" );
    }

    @FXML
    void recargarCafe() {
       int cantidad = Integer.parseInt(desplegarInput("Ingresar cantidad de cafe a recargar", "Recargar Cafe"));
      // int costo = Integer.parseInt(desplegarInput("Cuanto cuesta", "Precio de azucar"));
      if( maquina.recargarCafe(cantidad, maquina.getPrecioBaseCafe()))actualizarPantalla();
      else desplegarError("No hay suficientes ingresos :(", "No se pudo recargar cafe" );
      
    }

    @FXML
    void recargarVasos() {
        int cantidad = Integer.parseInt(desplegarInput("Ingresar cantidad de vasos a recargar", "Recargar Cafe"));
       int costo = Integer.parseInt(desplegarInput("Cuanto cuesta", "Precio de los vasos"));
      if( maquina.recargarVasos(cantidad, costo))actualizarPantalla();
      else desplegarError("No hay suficientes ingresos :(", "No se pudo recargar cafe" );

    }

    @FXML
    void registrarFactura() {
        String msg="Su factura es de:"+txtPrecio.getText()+'\n'+"Pagó "+String.valueOf(Integer.parseInt(txtPrecio.getText())*0.15)+ " de IVA";
        desplegarInfo(msg, "Factura");

    }
    public void cargarBarras(){
           opcionesAzucar.removeAll(opcionesAzucar);
           opcionesCafe.removeAll(opcionesCafe);
       
            String op1="1. Tinto sencillo";
            String op2="2. Tinto normal";
            String op3="3. Tinto doble";
            opcionesCafe.addAll(op1,op2,op3);
            chbC.getItems().addAll(opcionesCafe);
           
            String opb1="1. Sin Azucar";
            String opb2="2. Bajo en Azucar";
            String opb3="3. Normal";
            opcionesAzucar.addAll(opb1,opb2,opb3);
            chbAzucar.getItems().addAll(opcionesAzucar);
          
        }
    public void actualizarPantalla(){
          txtAntesIva.setText(String.valueOf(maquina.getGananciasBrutas()));
          txtCantidadAzucar.setText(String.valueOf(maquina.getAzucar()));
          txtCantidadCafe.setText(String.valueOf(maquina.getCafe()));
          txtCantidadVasos.setText(String.valueOf(maquina.getVasos()));
          txtEgresos.setText(String.valueOf(maquina.getEgresos()));
          txtGananciasNetas.setText(String.valueOf(maquina.getGananciasNetas()));
          txtIngresos.setText(String.valueOf(maquina.getIngresos()));
          txtIva.setText(String.valueOf("15%"));
          
          txtPrecioBase.setText(String.valueOf(maquina.getPrecioBaseCafe()));
    }
       public String desplegarInput(String msg,String titulo){
        String resp="";
       TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(titulo);
        dialog.setHeaderText(titulo);
        dialog.setContentText(msg);
        
       
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){       
         resp= result.get();
        }
        return resp;
               
    }
    public void desplegarError(String msg, String titulo ){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);

        alert.setContentText(msg);

        alert.showAndWait();
    }
    public void desplegarInfo(String msg, String titulo ){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);

        alert.setContentText(msg);

        alert.showAndWait();
    }
    }


