import java.util.List;
import java.util.ArrayList;
public class Calculador {
    Calculador() {
    }
    public Vector suma(Vector v1, Vector v2) {
        double i = v1.geti() + v2.geti();
        double j = v1.getj() + v2.getj();
        double k = v1.getk() + v2.getk();
        return new Vector(i, j, k);
    }
    public Vector resta(Vector v1, Vector v2) {
        double i = v1.geti() - v2.geti();
        double j = v1.getj() - v2.getj();
        double k = v1.getk() - v2.getk();
        return new Vector(i, j, k);
    }
    public Vector productoEscalar(Vector v1, double escalar) {
        double i = v1.geti() * escalar;
        double j = v1.getj() * escalar;
        double k = v1.getk() * escalar;
        return new Vector(i, j, k);
    }
    public double   productoPunto(Vector v1, Vector v2) {
        return v1.geti() * v2.geti() + v1.getj() * v2.getj() + v1.getk() * v2.getk();
    }
    public Vector productoCruz(Vector v1, Vector v2) {
        Matriz m = new Matriz(new ArrayList<>(), 0, 0);
        m.addFila(new Vector(1, -1, 1)); // Fila de la matriz para el producto cruz
        m.addFila(v1);
        m.addFila(v2);
        return determinanteCruz(m);
    }
    public double magnitud(Vector v) {
        return Math.sqrt(v.geti() * v.geti() + v.getj() * v.getj() + v.getk() * v.getk());
    }
    public Vector normalizar(Vector v) {
        double magnitud = magnitud(v);
        if (magnitud == 0) {
            throw new ArithmeticException("No se puede normalizar un vector nulo");
        }
        double i = v.geti() / magnitud;
        double j = v.getj() / magnitud;
        double k = v.getk() / magnitud;
        return new Vector(i, j, k);
    }
    public double angulo(Vector v1, Vector v2) {
        double productoPunto = productoPunto(v1, v2);
        double magnitudV1 = magnitud(v1);
        double magnitudV2 = magnitud(v2);
        if (magnitudV1 == 0 || magnitudV2 == 0) {
            throw new ArithmeticException("No se puede calcular el ángulo con un vector nulo");
        }
        return Math.acos(productoPunto / (magnitudV1 * magnitudV2));
    }
    public Vector proyeccion(Vector v1, Vector v2) {
        double productoPunto = productoPunto(v1, v2);
        double magnitudV2 = magnitud(v2);
        if (magnitudV2 == 0) {
            throw new ArithmeticException("No se puede proyectar sobre un vector nulo");
        }
        double escalar = productoPunto / (magnitudV2 * magnitudV2);
        return productoEscalar(v2, escalar);
    }
    public Matriz sumaMatrices(Matriz m1, Matriz m2) {
        if (m1.getNumFilas() != m2.getNumFilas() || m1.getNumColumnas() != m2.getNumColumnas()) {
            throw new IllegalArgumentException("Las matrices deben tener las mismas dimensiones para sumar");
        }
        List<Vector> filas = new ArrayList<>();
        for (int i = 0; i < m1.getNumFilas(); i++) {
            Vector filaSuma = suma(m1.getFila(i), m2.getFila(i));
            filas.add(filaSuma);
        }
        return new Matriz(filas, m1.getNumFilas(), m1.getNumColumnas());
    }
    public Matriz restaMatrices(Matriz m1, Matriz m2) {
        if (m1.getNumFilas() != m2.getNumFilas() || m1.getNumColumnas() != m2.getNumColumnas()) {
            throw new IllegalArgumentException("Las matrices deben tener las mismas dimensiones para restar");
        }
        List<Vector> filas = new ArrayList<>();
        for (int i = 0; i < m1.getNumFilas(); i++) {
            Vector filaResta = resta(m1.getFila(i), m2.getFila(i));
            filas.add(filaResta);
        }
        return new Matriz(filas, m1.getNumFilas(), m1.getNumColumnas());
    }
    public Matriz productoMatrices(Matriz m1, Matriz m2) {
        if (m1.getNumColumnas() != m2.getNumFilas()) {
            throw new IllegalArgumentException("El número de columnas de la primera matriz debe ser igual al número de filas de la segunda matriz");
        }
        List<Vector> filas = new ArrayList<>();
        for (int i = 0; i < m1.getNumFilas(); i++) {
            List<Double> filaProducto = new ArrayList<>();
            for (int j = 0; j < m2.getNumColumnas(); j++) {
                double suma = 0;
                for (int k = 0; k < m1.getNumColumnas(); k++) {
                    suma += m1.getFila(i).geti() * m2.getFila(k).geti();
                }
                filaProducto.add(suma);
            }
            filas.add(new Vector(filaProducto.get(0), filaProducto.get(1), filaProducto.get(2)));
        }
        return new Matriz(filas, m1.getNumFilas(), m2.getNumColumnas());
    }
    public Matriz transpuesta(Matriz m) {
        List<Vector> filas = new ArrayList<>();
        for (int i = 0; i < m.getNumColumnas(); i++) {
            List<Double> filaTranspuesta = new ArrayList<>();
            for (int j = 0; j < m.getNumFilas(); j++) {
                filaTranspuesta.add(m.getFila(j).geti());
            }
            filas.add(new Vector(filaTranspuesta.get(0), filaTranspuesta.get(1), filaTranspuesta.get(2)));
        }
        return new Matriz(filas, m.getNumColumnas(), m.getNumFilas());
    }
    public Matriz inversa(Matriz m) {
        if (m.getNumFilas() != m.getNumColumnas()) {
            throw new IllegalArgumentException("La matriz debe ser cuadrada para calcular su inversa");
        }
        // Implementación de la inversa de una matriz (solo para matrices 2x2 y 3x3)
        if (m.getNumFilas() == 2) {
            double a = m.getFila(0).geti();
            double b = m.getFila(0).getj();
            double c = m.getFila(1).geti();
            double d = m.getFila(1).getj();
            double det = a * d - b * c;
            if (det == 0) {
                throw new ArithmeticException("La matriz no tiene inversa (determinante cero)");
            }
            List<Vector> filas = new ArrayList<>();
            filas.add(new Vector(d, -b, 0));
            filas.add(new Vector(-c, a, 0));
            return new Matriz(filas, 2, 2);
        } else if (m.getNumFilas() == 3) {
            // Implementación para matrices 3x3
            // ...
            throw new UnsupportedOperationException("Inversa de matrices 3x3 no implementada");
        } else {
            throw new UnsupportedOperationException("Inversa solo implementada para matrices 2x2 y 3x3");
        }
    }
    public double determinante(Matriz m) {
        if (m.getNumFilas() != m.getNumColumnas()) {
            throw new IllegalArgumentException("La matriz debe ser cuadrada para calcular su determinante");
        }
        // Implementación del determinante (solo para matrices 2x2 y 3x3)
        if (m.getNumFilas() == 2) {
            double a = m.getFila(0).geti();
            double b = m.getFila(0).getj();
            double c = m.getFila(1).geti();
            double d = m.getFila(1).getj();
            return a * d - b * c;
        } else if (m.getNumFilas() == 3) {
            double a = m.getFila(0).geti();
            double b = m.getFila(0).getj();
            double c = m.getFila(0).getk();
            double d = m.getFila(1).geti();
            double e = m.getFila(1).getj();
            double f = m.getFila(1).getk();
            double g = m.getFila(2).geti();
            double h = m.getFila(2).getj();
            double i = m.getFila(2).getk();
            return a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g);
        } else {
            throw new UnsupportedOperationException("Determinante solo implementado para matrices 2x2 y 3x3");
        }
    }
    public Matriz adjunta(Matriz m) {
        if (m.getNumFilas() != m.getNumColumnas()) {
            throw new IllegalArgumentException("La matriz debe ser cuadrada para calcular su adjunta");
        }
        // Implementación de la adjunta (solo para matrices 2x2 y 3x3)
        if (m.getNumFilas() == 2) {
            double a = m.getFila(0).geti();
            double b = m.getFila(0).getj();
            double c = m.getFila(1).geti();
            double d = m.getFila(1).getj();
            List<Vector> filas = new ArrayList<>();
            filas.add(new Vector(d, -b, 0));
            filas.add(new Vector(-c, a, 0));
            return new Matriz(filas, 2, 2);
        } else if (m.getNumFilas() == 3) {
            // Implementación para matrices 3x3
            // ...
            throw new UnsupportedOperationException("Adjunta de matrices 3x3 no implementada");
        } else {
            throw new UnsupportedOperationException("Adjunta solo implementada para matrices 2x2 y 3x3");
        }
    }
    public Vector determinanteCruz(Matriz m) {
        if (m.getNumFilas() != 3 || m.getNumColumnas() != 3) {
            throw new IllegalArgumentException("La matriz debe ser de 3x3 para calcular el determinante cruz");
        }
        double i = m.getFila(1).getj() * m.getFila(2).getk() - m.getFila(1).getk() * m.getFila(2).getj();
        double j = m.getFila(1).getk() * m.getFila(2).geti() - m.getFila(1).geti() * m.getFila(2).getk();
        double k = m.getFila(1).geti() * m.getFila(2).getj() - m.getFila(1).getj() * m.getFila(2).geti();
        return new Vector(i, j, k);
    }
    public Vector campoElectrico(Vector[] cargas, Vector punto) {
        Vector campoTotal = new Vector(0, 0, 0);
        for (Vector carga : cargas) {
            Vector r = resta(punto, carga);
            double magnitudR = magnitud(r);
            if (magnitudR == 0) {
                throw new ArithmeticException("No se puede calcular el campo eléctrico en el mismo punto que la carga");
            }
            Vector rNormalizado = normalizar(r);
            double magnitudCampo = 1 / (4 * Math.PI * 8.854e-12) * (1 / (magnitudR * magnitudR));
            Vector campo = productoEscalar(rNormalizado, (int) magnitudCampo);
            campoTotal = suma(campoTotal, campo);
        }
        return campoTotal;
    }
    public Vector campoMagnetico(Vector[] corrientes, Vector punto) {
        Vector campoTotal = new Vector(0, 0, 0);
        for (Vector corriente : corrientes) {
            Vector r = resta(punto, corriente);
            double magnitudR = magnitud(r);
            if (magnitudR == 0) {
                throw new ArithmeticException("No se puede calcular el campo magnético en el mismo punto que la corriente");
            }
            Vector rNormalizado = normalizar(r);
            double magnitudCampo = 1e-7 * (1 / (magnitudR * magnitudR));
            Vector campo = productoEscalar(rNormalizado, (int) magnitudCampo);
            campoTotal = suma(campoTotal, campo);
        }
        return campoTotal;
    }
    public Vector fuerza(Vector carga, Vector campo) {
        return productoEscalar(campo, carga.geti());
    }
    public Vector flujo(Vector campo, Vector area) {
        return productoEscalar(campo, area.geti());
    }
    public Vector induccion(Vector flujo, Vector area) {
        return productoEscalar(flujo, 1 / area.geti());
    }
    public Vector resistencia(Vector voltaje, Vector corriente) {
        return productoEscalar(voltaje, 1 / corriente.geti());
    }
    public Vector potencia(Vector voltaje, Vector corriente) {
        return productoEscalar(voltaje, corriente.geti());
    }
    public Vector energia(Vector potencia, Vector tiempo) {
        return productoEscalar(potencia, tiempo.geti());
    }
    public int potenciaDisipada(int Resistencia, int Corriente) {
        return Resistencia * Corriente * Corriente;
    }
    public int potencia(int Voltaje, int Corriente) {
        return Voltaje * Corriente;
    }
    public int resistencia(int Voltaje, int Corriente) {
        return Voltaje / Corriente;
    }
    public int corriente(int Voltaje, int Resistencia) {
        return Voltaje / Resistencia;
    }
    public int voltaje(int Corriente, int Resistencia) {
        return Corriente * Resistencia;
    }
    public Vector VectorHall(Vector corriente, Vector campo, int densidad) {
        return productoCruz(corriente, campo);
    }
    public Vector VectorHall(Vector corriente, Vector campo, int densidad, int grosor) {
        Vector hall = productoCruz(corriente, campo);
        return productoEscalar(hall, densidad / grosor);
    }
    public Vector VectorHall(Vector corriente, Vector campo, int densidad, int grosor, int longitud) {
        Vector hall = productoCruz(corriente, campo);
        return productoEscalar(hall, densidad / (grosor * longitud));
    }
    public Vector CampoMagnetico(Vector corriente, Vector distancia) {
        Vector campo = productoCruz(corriente, distancia);
        return productoEscalar(campo, (1 / (4 * Math.PI * 1e-7)));
    }
    public static double flujoElectrico(double E, double A, double theta) {
        return E * A * Math.cos(Math.toRadians(theta));
    }
    public static double CargaEncerradaFlujo(double E, double A, double theta) {
        return flujoElectrico(E, A, theta) / 8.854187817e-12;
    }



}
