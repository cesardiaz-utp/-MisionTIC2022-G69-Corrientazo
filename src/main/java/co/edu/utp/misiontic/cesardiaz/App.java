package co.edu.utp.misiontic.cesardiaz;

import co.edu.utp.misiontic.cesardiaz.exception.PagoException;
import co.edu.utp.misiontic.cesardiaz.modelo.Adicional;
import co.edu.utp.misiontic.cesardiaz.modelo.Corrientazo;
import co.edu.utp.misiontic.cesardiaz.modelo.Mesa;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionCarne;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionEnsalada;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionJugo;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionPrincipio;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionSopa;
import co.edu.utp.misiontic.cesardiaz.modelo.Pedido;

/**
 * Hello world!
 */
public final class App {
    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        var mesa = new Mesa("01");

        var almuerzo1 = new Corrientazo(12_000,
                new OpcionSopa("Patacón"),
                new OpcionPrincipio("Frijoles"),
                new OpcionCarne("Cerdo a la plancha"),
                new OpcionEnsalada("Cesar"),
                new OpcionJugo("Limonada"));
        var anderson = new Pedido("Anderson", almuerzo1);

        var almuerzo2 = new Corrientazo(12_000,
                new OpcionSopa("Raiz"),
                new OpcionPrincipio("Espaguetis"),
                new OpcionCarne("Pollo sudado"),
                new OpcionJugo("Maracuya"));
        var cristian = new Pedido("Cristian", almuerzo2);

        mesa.agregarPedido(anderson);
        mesa.agregarPedido(cristian);

        anderson.agregarAdicional(new Adicional("Chicharron", 3_000));
        anderson.agregarAdicional(new Adicional("Huevo", 800));

        System.out.printf("Total de la mesa $ %,d %n", mesa.calcularValor());
        var efectivo = 27_700;
        try {
            System.out.printf("Se paga con $ %,d y le devuelven $ %,d %n",
                    efectivo, mesa.pagar(efectivo));
        } catch (PagoException ex) {
            System.err.printf("Se paga con $ %,d y %s. %n", efectivo, ex.getMessage());
        }

        System.out.println(mesa.calcularValor());
    }
}
