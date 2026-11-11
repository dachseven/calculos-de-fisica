public class Cerebrito {
    Cerebrito() {
    }
    public void descripcionGauss(int calculo,int sistema){
        String flujo="";
        String carga="";
        String campo="";
        String area="";
        String densidad="";
        String longitud="";
        switch(calculo){
            case 1:
                System.out.println("Flujo electrico seleccionado");
                System.out.println("la ley para calacular el flujo es: Φ = ∮ E · dA");
                System.out.println("donde Φ es el flujo eléctrico, E es el campo eléctrico y dA es el vector de área diferencial");
                break;
            case 2:
                System.out.println("Carga encerrada seleccionada");
                System.out.println("la ley para calacular la carga encerrada es: Q = ∮ E · dA / ε₀");
                System.out.println("donde Q es la carga encerrada, E es el campo eléctrico, dA es el vector de área diferencial y ε₀ es la permitividad del vacío");
                break;
            case 3:
                System.out.println("Campo electrico seleccionado");
                System.out.println("la ley para calacular el campo electrico es: E = Q / (ε₀ * A)");
                System.out.println("donde E es el campo eléctrico, Q es la carga encerrada, A es el área y ε₀ es la permitividad del vacío");
                break;
            default:
                System.out.println("Opción no válida, se seleccionará flujo electrico por defecto");
        }
        if(sistema==1){
                    flujo="N·m²/C";
                    carga="C";
                    campo="N/C";
                    area="m²";
                    densidad="C/m²";
                    longitud="m";
                    System.out.println("en el sistema internacional (SI) el flujo electrico se mide en "+ flujo+", la carga encerrada en "+carga+", el campo electrico en "+campo+", el area en "+area+", la densidad de carga en "+densidad+" y la longitud en "+longitud);
                }
                else{
                    flujo="N·ft²/C";
                    carga="C";
                    campo="N/C";
                    area="ft²";
                    densidad="C/ft²";
                    longitud="ft";
                    System .out.println("en el sistema ingles (Imperial) el flujo electrico se mide en N·ft²/C, la carga encerrada en C, el campo electrico en N/C, el area en ft², la densidad de carga en C/ft² y la longitud en ft");
                }
    }
    
}
