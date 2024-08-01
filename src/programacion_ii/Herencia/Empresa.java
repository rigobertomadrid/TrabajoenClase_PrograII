/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacion_ii.Herencia;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Josue
 */
public class Empresa {

    static Scanner lea = new Scanner(System.in);
    static ArrayList<Empleado> empleados = new ArrayList<>();

    public static void main(String[] args) {
        lea.useDelimiter("\n");
        int op;

        do {
            System.out.println("1- Agregar Empleado");
            System.out.println("2- Pagar Empleado");
            System.out.println("3- Lista de Empleados");
            System.out.println("4- Sub Menu especifico");
            System.out.println("5- Salir");
            System.out.print("Escoja Opcion: ");
            op = lea.nextInt();

            switch (op) {
                case 1:
                    hire();
                    break;
                case 2:
                    pay();
                    break;
                case 3:
                    list();
                    break;
                case 4:
                    submenu();
                    break;
            }
        } while (op != 5);

    }

    /**
     * Recorre la lista de Empleados. Si encuentra un empleado con ese codigo,
     * se retorna, sino, retorna null
     *
     * @param cod Codigo del Empleado
     * @return El obj Empleado si se encuentra, null si no
     */
    private static Empleado search(int cod) {
        for (Empleado empleado : empleados) {
            if (empleado.getCodigo() == cod);
            return empleado;
        }
        return null;
    }

    /**
     * Contrata un nuevo empleado 1- Se pide del teclado el tipo: COMUN, HORA,
     * VENTA o TEMPORAL 2- Se instancia un objeto segun el tipo y se guarda en
     * el arreglo 3- pero siempre y cuando el Codigo NO este repetido 4- LOS
     * DATOS requeridos se ingresan del teclado
     */
    private static void hire() {

        String tipo = lea.next();
        do {
            System.out.print("Tipo de empleado (COMUN, HORA, VENTA o TEMPORAL): ");
            tipo = lea.next().toUpperCase();

            if (!tipo.equals("COMUN") && !tipo.equals("HORA") && !tipo.equals("VENTA") && !tipo.equals("TEMPORAL")) {
                System.out.println("El tipo de empleado que ingreso es incorrecto");
            }

        } while (!tipo.equals("COMUN") && !tipo.equals("HORA") && !tipo.equals("VENTA") && !tipo.equals("TEMPORAL"));

        int codigo;
        do {
            System.out.print("Ingrese el codigo:");
            codigo = lea.nextInt();

            if (search(codigo) != null) {
                System.out.println("Este codigo ya existe");
            }

        } while (search(codigo) != null);

        if (tipo.equals("COMUN")) {
            System.out.print("Ingrese el nombre: ");
            String nombre = lea.next();
            EmpleadoComun comun = new EmpleadoComun(codigo, nombre, 20000);
            empleados.add(comun);
        } else if (tipo.equals("HORA")) {
            System.out.print("Ingrese el nombre: ");
            String nombre = lea.next();
            EmpleadoPorHora hora = new EmpleadoPorHora(codigo, nombre);
            empleados.add(hora);
        } else if (tipo.equals("VENTA")) {
            System.out.print("Ingrese el nombre: ");
            String nombre = lea.next();
            EmpleadoPorVenta venta = new EmpleadoPorVenta(codigo, nombre, 25000);
            empleados.add(venta);
        } else if (tipo.equals("TEMPORAL")) {
            System.out.print("Ingrese el nombre: ");
            String nombre = lea.next();
            EmpleadoTemporal temporal = new EmpleadoTemporal(codigo, nombre);
            empleados.add(temporal);
        } else {
            System.out.println("Tipo de empleado no reconocido.");
        }
    }

    /**
     * Le paga a un empleado 1- Pide del teclado el codigo 2- Buscamos ese
     * empleado 3- Si existe, mostramos en pantalla su pago
     */
    private static void pay() {
        int codigo;
        do {
            System.out.println("Ingrese el codigo");
            codigo = lea.nextInt();

            if (search(codigo) == null) {
                System.out.println("Este codigo ya existe");
            }
        } while (search(codigo) == null);

        Empleado empleado = search(codigo);
        System.out.println("Pago del Empleado: " + empleado.pagar());
    }

    /**
     * Imprimir la lista de empleados
     */
    private static void list() {
        for (Empleado empleado : empleados) {
            System.out.println(empleado.toString());
        }
    }

    private static void submenu() {
        int op;
        do {
            System.out.println("1-Fecha Fin Contrato a Temporales");
            System.out.println("2-Ingresar Venta");
            System.out.println("3-Ingresar Horas de Trabajo");
            System.out.println("4-Regresar al Menu Principal");
            System.out.print("Escoja Opcion: ");
            op = lea.nextInt();

            switch (op) {
                case 1:
                    setFin();
                    break;
                case 2:
                    ventas();
                    break;
                case 3:
                    horas();
            }

        } while (op != 4);
    }

    /**
     * 1- Leer un codigo 2- Buscar el empleado, que existe y q sea Temporal 3-
     * Si concuerda, set fecha fin contrato 4- Leer del teclado los datos
     */
    private static void setFin() {

        System.out.print("Ingrese el codigo: ");
        int codigo = lea.nextInt();

        if (search(codigo) == null) {
            System.out.println("Este codigo ya existe");

        } else {
            Empleado empleado = search(codigo);

            if (empleado instanceof EmpleadoTemporal empleadoTemporal) {
                System.out.println("Ingrese la fecha del fin del contrato");
                System.out.print("Ingrese el año: ");
                int año = lea.nextInt();
                System.out.print("Ingrese el mes: ");
                int mes = lea.nextInt();
                System.out.print("Ingrese el dia: ");
                int dia = lea.nextInt();

                empleadoTemporal.setFinContrato(año, mes, dia);
            }
        }
    }

    /**
     * 1- Leer un codigo 2- Buscar empleado, que exista y que sea PorVentas 3-
     * Si concuerda, agregar una venta 4- Leer del teclado los datos
     */
    private static void ventas() {

        System.out.print("Ingrese el codigo: ");
        int codigo = lea.nextInt();

        if (search(codigo) == null) {
            System.out.println("Este codigo ya existe");

        } else {
            Empleado empleado = search(codigo);

            if (empleado instanceof EmpleadoPorVenta empleadoPorVenta) {
                System.out.print("Agregar el monto de la venta: ");
                double monto = lea.nextDouble();
                empleadoPorVenta.agregarVenta(monto);
            }
        }
    }

    /**
     * 1- Leer un codigo 2- Buscar empleado, que exista y que sea PorHoras 3- Si
     * concuerda, agregar las horas trabajadas 4- Leer del teclado los datos
     */
    private static void horas() {
        System.out.print("Ingrese el codigo: ");
        int codigo = lea.nextInt();

        if (search(codigo) == null) {
            System.out.println("Este codigo ya existe.");

        } else {
            Empleado empleado = search(codigo);

            if (empleado instanceof EmpleadoPorHora empleadoPorHora) {

                System.out.print("Ingresar las horas trabajadas del empleado: ");
                int horas = lea.nextInt();

                empleadoPorHora.setHorasT(horas);
            }
        }
    }
}
