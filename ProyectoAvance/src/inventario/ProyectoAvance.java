package inventario;

public class ProyectoAvance {

    public static void mostrarMenu() {

        System.out.println("\n=========== CONTROL DE INVENTARIO ===========");
        System.out.println("1. Registrar productos");
        System.out.println("2. Listar productos");
        System.out.println("3. Reporte Stock");
        System.out.println("4. Valor de Productos");
        System.out.println("5. Buscar producto");
        System.out.println("6. Producto mas barato y mas caro");
        System.out.println("7. Productos por categoria");
        System.out.println("8. Salir");
    }

    public static void main(String[] args) {

        Inventario inventario = new Inventario();

        int opcion;

        do {

            mostrarMenu();

            opcion = Utilidades.leerEntero("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    inventario.registrarProducto();
                    break;

                case 2:
                    inventario.listarProductos();
                    break;

                case 3:
                    inventario.mostrarStock();
                    break;

                case 4:
                    inventario.mostrarValorInventario();
                    break;

                case 5:
                    inventario.buscarProducto();
                    break;

                case 6:
                    inventario.productoBaratoYCaro();
                    break;

                case 7:
                    inventario.productosPorCategoria();
                    break;

                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 8);
    }
}