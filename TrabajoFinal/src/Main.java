import java.util.ArrayList;
import java.util.List;

abstract class Arma {
    protected int municiones;
    protected double alcance;
    protected String marca;
    protected double calibre;
    protected String estado;

    public Arma(int municiones, double alcance, String marca,
                double calibre, String estado) {
        this.municiones = municiones;
        this.alcance = alcance;
        this.marca = marca;
        this.calibre = calibre;
        this.estado = estado;
    }

    public boolean estaEnCondiciones() {
        return this.estado.equals("EN USO") && this.calibre >= 9;
    }
}

class ArmaCorta extends Arma {
    private boolean automatica;

    public ArmaCorta(int municiones, double alcance, String marca,
                     double calibre, String estado, boolean automatica) {
        super(municiones, alcance, marca, calibre, estado);
        this.automatica = automatica;
    }

    public boolean puedeDispararALargaDistancia() {
        return this.alcance > 200;
    }
}

// Clase para armas largas
class ArmaLarga extends Arma {
    private boolean tieneRenar;
    private String descripcion;
    private int nivel;

    public ArmaLarga(int municiones, double alcance, String marca,
                     double calibre, String estado, boolean tieneRenar,
                     String descripcion, int nivel) {
        super(municiones, alcance, marca, calibre, estado);
        this.tieneRenar = tieneRenar;
        this.descripcion = descripcion;
        this.nivel = nivel;
    }

    public int compararCon(ArmaLarga otraArma) {
        return Integer.compare(this.nivel, otraArma.nivel);
    }
}

// Clase Policia
class Policia {
    private String nombre;
    private String apellido;
    private int legajo;
    private Arma arma;

    public Policia(String nombre, String apellido, int legajo, Arma arma) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.arma = arma;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getLegajo() { return legajo; }
    public Arma getArma() { return arma; }
}

// Clase Escuadron
class Escuadron {
    private String nombre;
    private String ubicacion;
    private List<Policia> policiaList;

    public Escuadron(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.policiaList = new ArrayList<>();
    }

    public void agregarPolicia(Policia policia) {
        policiaList.add(policia);
    }

    public int getCantidadIntegrantes() {
        return policiaList.size();
    }

    public int getArmasEnCondiciones() {
        int contador = 0;
        for (Policia policia : policiaList) {
            if (policia.getArma().estaEnCondiciones()) {
                contador++;
            }
        }
        return contador;
    }

    public void mostrarReporte() {
        System.out.println("Reporte de Escuadrón " + nombre);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Cantidad de integrantes: " + getCantidadIntegrantes());
        System.out.println("Armas en condiciones: " + getArmasEnCondiciones());
        System.out.println("\nDetalles del personal:");
        for (Policia policia : policiaList) {
            System.out.println("Policía: " + policia.getNombre() + " " +
                    policia.getApellido() +
                    "\nLegajo: " + policia.getLegajo());
            System.out.println("Arma asignada: " + policia.getArma().getClass().getSimpleName());
            System.out.println("------");
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Escuadron escuadron = new Escuadron("Grillo", "Comisaría de Tigre");

        ArmaCorta pistola = new ArmaCorta(15, 150, "Glock", 9.5, "EN USO", true);
        ArmaLarga rifle = new ArmaLarga(30, 500, "FN", 7.62, "EN USO", true,
                "Uso táctico", 4);

        Policia policiaUno = new Policia("Marcelo", "Gustambo", 1234, pistola);
        Policia policiaDos = new Policia("Marcela", "González", 5678, rifle);
        Policia policiaTres = new Policia("Agustin", "Martinez", 9101, pistola);
        Policia policiaCuatro = new Policia("Agustina", "Maldonado", 1121, rifle);

        // Agregar policías al escuadrón
        escuadron.agregarPolicia(policiaUno);
        escuadron.agregarPolicia(policiaDos);
        escuadron.agregarPolicia(policiaTres);
        escuadron.agregarPolicia(policiaCuatro);

        escuadron.mostrarReporte();
    }
}