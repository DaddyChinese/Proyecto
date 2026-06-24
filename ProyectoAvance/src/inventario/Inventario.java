package inventario;

import java.io.*;

public class Inventario {

    private Producto[] productos;
    private int contador;
    private static final String ARCHIVO = "inventario.dat";

    public Inventario() {
        cargarDatos();
    }

    public void registrarProducto() {

        if (contador == productos.length) {
            System.out.println("Inventario lleno.");
            return;
        }

        System.out.println("\n------ Registrar Producto ------");
        System.out.println("1. Limpieza");
        System.out.println("2. Oficina");
        System.out.println("3. Construccion");
        System.out.println("0. Volver");

        int opCat = Utilidades.leerEntero("Seleccione categoria: ");

        if (opCat == 0) {
            return;
        }

        String categoria;

        switch (opCat) {
            case 1:
                categoria = "Limpieza";
                break;
            case 2:
                categoria = "Oficina";
                break;
            case 3:
                categoria = "Construccion";
                break;
            default:
                System.out.println("Categoria invalida.");
                return;
        }

        String nombre;

        do {
            System.out.print("Nombre del producto: ");
            nombre = Utilidades.getScanner().nextLine();
        } while (!Utilidades.validarNombre(nombre));

        int stock = Utilidades.leerEntero("Cantidad: ");
        double precio = Utilidades.leerDouble("Precio: ");

        productos[contador] = new Producto(nombre, categoria, stock, precio);
        contador++;

        guardarDatos();

        System.out.println("Producto registrado correctamente.");
    }

    public void listarProductos() {

        if (contador == 0) {
            System.out.println("No existen productos.");
            return;
        }

        System.out.println("\n------ LISTA DE PRODUCTOS ------");

        for (int i = 0; i < contador; i++) {
            System.out.println((i + 1) + ". " + productos[i]);
        }
    }

    public void mostrarStock() {

        if (contador == 0) {
            System.out.println("No existen productos.");
            return;
        }

        System.out.println("\n------ STOCK ------");

        for (int i = 0; i < contador; i++) {

            System.out.printf(
                    "%-15s -> %d unidades%n",
                    productos[i].getNombre(),
                    productos[i].getStock()
            );
        }
    }

    public void mostrarValorInventario() {

        if (contador == 0) {
            System.out.println("No existen productos.");
            return;
        }

        double total = calcularTotalInventario();

        System.out.println("\n------ VALOR INVENTARIO ------");

        for (int i = 0; i < contador; i++) {

            double valor = productos[i].getStock() * productos[i].getPrecio();

            System.out.printf(
                    "%-15s S/. %.2f%n",
                    productos[i].getNombre(),
                    valor
            );
        }

        System.out.printf("\nTOTAL GENERAL: S/. %.2f%n", total);
    }

    public void buscarProducto() {

        if (contador == 0) {
            System.out.println("No existen productos.");
            return;
        }

        System.out.print("Ingrese nombre: ");
        String buscar = Utilidades.getScanner().nextLine();

        boolean encontrado = false;

        for (int i = 0; i < contador; i++) {

            if (productos[i].getNombre().equalsIgnoreCase(buscar)) {

                System.out.println("\nProducto encontrado");
                System.out.println("Nombre    : " + productos[i].getNombre());
                System.out.println("Categoria : " + productos[i].getCategoria());
                System.out.println("Stock     : " + productos[i].getStock());
                System.out.printf("Precio    : S/. %.2f%n",
                        productos[i].getPrecio());

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }

    public void productoBaratoYCaro() {

        if (contador == 0) {
            System.out.println("No existen productos.");
            return;
        }

        int posMin = 0;
        int posMax = 0;

        for (int i = 1; i < contador; i++) {

            if (productos[i].getPrecio() < productos[posMin].getPrecio()) {
                posMin = i;
            }

            if (productos[i].getPrecio() > productos[posMax].getPrecio()) {
                posMax = i;
            }
        }

        System.out.printf(
                "Mas barato: %s - S/. %.2f%n",
                productos[posMin].getNombre(),
                productos[posMin].getPrecio()
        );

        System.out.printf(
                "Mas caro: %s - S/. %.2f%n",
                productos[posMax].getNombre(),
                productos[posMax].getPrecio()
        );
    }

    public void productosPorCategoria() {

        int limpieza = 0;
        int oficina = 0;
        int construccion = 0;

        for (int i = 0; i < contador; i++) {

            switch (productos[i].getCategoria()) {

                case "Limpieza":
                    limpieza++;
                    break;

                case "Oficina":
                    oficina++;
                    break;

                case "Construccion":
                    construccion++;
                    break;
            }
        }

        System.out.println("\n------ CATEGORIAS ------");
        System.out.println("Limpieza     : " + limpieza);
        System.out.println("Oficina      : " + oficina);
        System.out.println("Construccion : " + construccion);
        System.out.println("Total        : " + contador);
    }

    public double calcularTotalInventario() {

        double total = 0;

        for (int i = 0; i < contador; i++) {
            total += productos[i].getStock() * productos[i].getPrecio();
        }

        return total;
    }

    private void guardarDatos() {

        try {

            ObjectOutputStream salida = new ObjectOutputStream(
                    new FileOutputStream(ARCHIVO));

            salida.writeObject(productos);
            salida.writeInt(contador);

            salida.close();

        } catch (IOException e) {
            System.out.println("Error al guardar el inventario.");
        }
    }

    private void cargarDatos() {

        try {

            ObjectInputStream entrada = new ObjectInputStream(
                    new FileInputStream(ARCHIVO));

            productos = (Producto[]) entrada.readObject();
            contador = entrada.readInt();

            entrada.close();

        } catch (IOException | ClassNotFoundException e) {

            productos = new Producto[10];
            contador = 0;
        }
    }
}