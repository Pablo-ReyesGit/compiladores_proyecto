
import Javacc.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        GramaticaTokenManager lexer = null;
        try {
            // Ruta del archivo de prueba
            FileReader fileReader = new FileReader("src\\Javacc\\Txt_Prueba_AL.txt");
            TablaSimbolos tablaSimbolos = new TablaSimbolos();

            try (BufferedReader br = new BufferedReader(new FileReader("src\\Javacc\\Txt_Prueba_AL.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    procesarLinea(linea, tablaSimbolos);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            String rutaHTML = "src\\ArchivosHTML\\tabla_simbolos.html";
            ReporteHTML.generarTablaSimbolos(tablaSimbolos.getSimbolos(), rutaHTML);

            // Inicializa el lexer
            lexer = new GramaticaTokenManager(new SimpleCharStream(fileReader));

        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró el archivo de entrada.");
            e.printStackTrace();
            return; // Sale del programa si hay error
        }

        Token t;
        try {
            while ((t = lexer.getNextToken()).kind != 0) { // EOF en JavaCC es `kind == 0`
                System.out.println("Token: " + t.image + " - Tipo: " + t.kind);

                // Agregar tokens al reporte HTML con el número en vez del nombre
                ReporteHTML.agregarToken(String.valueOf(t.kind), t.image, t.beginLine, t.beginColumn);
            }
        } catch (Exception e) {
            System.err.println("Error durante el análisis léxico.");
            e.printStackTrace();
        }

        // Generar el archivo HTML
        ReporteHTML.generarReporte();

        System.out.println("Análisis léxico terminado.");
    }

    private static void procesarLinea(String linea, TablaSimbolos tablaSimbolos) {
        // Expresión regular para capturar declaraciones de variables
        Pattern pattern = Pattern.compile("(int|float|double|String)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*=\\s*([^;]+);");
        Matcher matcher = pattern.matcher(linea);

        while (matcher.find()) {
            String tipo = matcher.group(1);
            String nombre = matcher.group(2);
            String valor = matcher.group(3);

            tablaSimbolos.agregarSimbolo(nombre, tipo, valor);
        }
    }
}
