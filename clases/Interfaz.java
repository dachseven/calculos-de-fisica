public class Interfaz {

    public static void main(String[] args) {
        System.out.println("Bienvenido al programa de cálculo");
        System.out.println("Seleccione de unidades");
        System.out.println("1. Sistema Internacional (SI)");
        System.out.println("2. Sistema Cegesimal (CGS)");
        System.out.println("3. Sistema Técnico (MKS)");
        System.out.println("4. Sistema Inglés (Imperial)");
        int sistema=Integer.parseInt(System.console().readLine());
        switch(sistema){
            case 1:
                System.out.println("Sistema Internacional (SI) seleccionado");
                break;
            case 2:
                System.out.println("Sistema Inglés (Imperial) seleccionado");
                break;
            default:
                System.out.println("Opción no válida, se seleccionará el Sistema Internacional (SI) por defecto");
        }
        System.out.println("Seleccione el sistema de referencia:");
        System.out.println("1. Coordenadas cartesianas");
        System.out.println("2. Coordenadas cilíndricas");
        System.out.println("3. Coordenadas esféricas");
        System.out.println("4. Coordenadas polares");
        int referencia=Integer.parseInt(System.console().readLine());
        switch(referencia){
            case 1:
                System.out.println("Coordenadas cartesianas seleccionadas");
                break;
            case 2:
                System.out.println("Coordenadas cilíndricas seleccionadas");
                break;
            case 3:
                System.out.println("Coordenadas esféricas seleccionadas");
                break;
            case 4:
                System.out.println("Coordenadas polares seleccionadas");
                break;
            default:
                System.out.println("Opción no válida, se seleccionarán coordenadas cartesianas por defecto");
        }
        System.out.println("Seleccione una opción:");
        System.out.println("1. Gauss");
        System.out.println("2. Coulomb");
        System.out.println("3. Biot-savart");
        System.out.println("4. Amperé");
        System.out.println("5. Faraday");
        System.out.println("6. Lenz");
        System.out.println("7. Ohm");
        System.out.println("8. Hall");   
        int eleccion=Integer.parseInt(System.console().readLine());
        switch(eleccion){
            case 1:
                System.out.println("Ley de Gauss seleccionada");
                System.out.println("que es lo que queres calcular?");
                System.out.println("1. Flujo electrico");
                System.out.println("2. Carga encerrada");
                System.out.println("3. Campo electrico");
                int calculo=Integer.parseInt(System.console().readLine());
                calculoGauss(calculo,sistema,referencia);
                break;
            case 2:
                System.out.println("Ley de Coulomb seleccionada");
                break;
            case 3:
                System.out.println("Ley de Biot-savart seleccionada");
                break;
            case 4:
                System.out.println("Ley de Amperé seleccionada");
                break;
            case 5:
                System.out.println("Ley de Faraday seleccionada");
                break;
            case 6:
                System.out.println("Ley de Lenz seleccionada");
                break;
            case 7:
                System.out.println("Ley de Ohm seleccionada");
                break;
            case 8:
                System.out.println("Efecto Hall seleccionado");
                break;
            default:
                System.out.println("Opción no válida, se seleccionará la Ley de Gauss por defecto");
        }

    }
    public static void calculoGauss(int calculo,int sistema,int referencia){
        Cerebrito cerebrito = new Cerebrito();
        Calculador calculador = new Calculador();
        String flujo="";
        String carga="";
        String campo="";
        String area="";
        String densidad="";
        String longitud="";
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
        switch(calculo){
            case 1:
                cerebrito.descripcionGauss(calculo,sistema);
                System.out.println("ingrese el valor del campo electrico (E):");
                double E=Double.parseDouble(System.console().readLine());
                System.out.println("ingrese el valor del area (A):");
                double A=Double.parseDouble(System.console().readLine());
                System.out.println("ingrese el valor del angulo (θ) en grados:");
                double theta=Double.parseDouble(System.console().readLine());
                double flujoElectrico=Calculador.flujoElectrico(E,A,theta);
                System.out.println("el flujo electrico es: "+flujoElectrico+" "+flujo);
                break;
            case 2:
                cerebrito.descripcionGauss(calculo,sistema);
                System.out.println("ingrese el valor del campo electrico (E):");
                double E2=Double.parseDouble(System.console().readLine());
                System.out.println("ingrese el valor del area (A):");
                double A2=Double.parseDouble(System.console().readLine());
                System.out.println("ingrese el valor del angulo (θ) en grados:");
                double theta2=Double.parseDouble(System.console().readLine());
                double cargaEncerrada=Calculador.CargaEncerradaFlujo(E2,A2,theta2);
                System.out.println("la carga encerrada es: "+cargaEncerrada+" "+carga);
                break;
            case 3:
                cerebrito.descripcionGauss(calculo,sistema);
                System.out.println("ingrese el valor de la carga encerrada (Q):");
                double Q=Double.parseDouble(System.console().readLine());
                System.out.println("ingrese el valor del area (A):");
                double A3=Double.parseDouble(System.console().readLine());
                System.out.println("ingrese la normal del area (vector unitario) en formato (i,j,k):");
                Vector normal=calculador.normalizar(new Vector(Double.parseDouble(System.console().readLine()), Double.parseDouble(System.console().readLine()), Double.parseDouble(System.console().readLine())));
                Vector campoElectrico=calculador.campoElectrico(Q, A3);
                System.out.println("el campo electrico es: "+campoElectrico+" "+campo);
                break;
            default:
                System.out.println("Opción no válida, se seleccionará flujo electrico por defecto");
        }
    }
    

}
