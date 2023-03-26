
package lexico;

import compilador.*;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestLexico {

    static final int LEXICAL_ERROR = 0;
    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Debe de ingresar un nombre de archivo.");
            System.exit(-1);
        }

        try {

            FileReader sourceFileReader = new FileReader(args[0]);

            // Instanciar objeto de TokenManager y obtener todos los tokens. Ej:
            ECTokenManager lexico = new ECTokenManager(
                    new SimpleCharStream(sourceFileReader)
            );

            // Lista de errores
            ArrayList<String> errores = new ArrayList<String>();

            Token t = null;
            do {
                try {
                    t = lexico.getNextToken();

                    if (t.kind == ECTokenManager.INVALIDO)
                        throw new TokenMgrError("Error léxico en línea " +
                                t.beginLine + ", columna " + t.beginColumn +
                                ". Se encontró símbolo inválido '" + t.image + "'.\n        " +
                                "Posible solución: Remover símbolo.", LEXICAL_ERROR);

                    if (t.kind == ECTokenManager.NUMERO_INVALIDO)
                        throw new TokenMgrError("Error léxico en línea " +
                                t.beginLine + ", columna " + t.beginColumn +
                                ". Se encontró un número inválido '" + t.image + "'.\n        " +
                                "Posible solución: Remover o revisar número.", LEXICAL_ERROR);

                } catch (TokenMgrError ex) {
                    errores.add(ex.getMessage());
                }
            } while (t.kind != ECTokenManager.EOF);

            System.out.println();

            if (errores.isEmpty()) {
                System.out.println("Se pasó el test léxico con éxito.\nNo se encontraron errores.");
                System.exit(0);
            }

            System.err.println("No se pasó el test léxico.");

            if (errores.size() == 1) {
                System.err.println("Se encontró " + errores.size() + " error:");
            } else {
                System.err.println("Se encontraron " + errores.size() + " errores:");
            }

            for (int i = 0; i < errores.size(); i++) {
                System.err.println("    " + (i + 1)  + ".- " + errores.get(i));
            }

            System.exit(-1);

        } catch (
                FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

    }

}

