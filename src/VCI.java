import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class VCI {
    public static void main(String[] args) {

        String archivoEntrada = "C:\\Users\\Asus TUF\\OneDrive - Tecnologico Nacional de Mexico Campus Saltillo\\6 Semestre\\automatas 2\\VCI\\src\\Tokens.txt";
        String archivoSalida = "C:\\Users\\Asus TUF\\OneDrive - Tecnologico Nacional de Mexico Campus Saltillo\\6 Semestre\\automatas 2\\VCI\\src\\VCI.txt";
        List<String> vci = new ArrayList<>();
        Stack<Operador> operaciones = new Stack<Operador>();
        HashMap<String, Integer> operador = new HashMap<>();
        operador.put("*", 60);
        operador.put("/", 60);
        operador.put("+", 50);
        operador.put("-", 50);
        operador.put("<", 40);
        operador.put(">", 40);
        operador.put("==", 40);
        operador.put("!", 30);
        operador.put("&&", 20);
        operador.put("||", 10);
        operador.put("=", 0);

        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))){

            // linea almacena la linea leida del archivo
            String linea;
            // en este ciclo lo que se hace es separar en 4 partes la cadena de entrada
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", 4);
                String primeraParte = partes[0].trim();
                String segundaParte = partes[1].trim();
                String terceraParte = partes[2].trim();
                String cuartaParte = partes[3].trim();
                // aqui de la primera parte de la cadena lo que se hace es asignar el tipo de valor que es

                // aqui se escribe en el archivo de salida al hacer la comparativa de si no es una palabra reservada
                // y no es numero
                if(primeraParte.equals(";")){
                    while (!operaciones.isEmpty()){
                        bw.write(operaciones.pop().getToken()+"\n");
                    }
                }else if (!esPalabraReservada(primeraParte)) {
                    bw.write(primeraParte + ", " +segundaParte + ", " + terceraParte + ", " +cuartaParte +"\n" );
                }else if(esPalabraReservada(primeraParte)){
                    if(primeraParte.equals("(")){
                        operaciones.push(new Operador("(",0,primeraParte+","+segundaParte+","+terceraParte+","+cuartaParte));
                    }else if(primeraParte.equals("=")){
                        operaciones.push(new Operador("=",0,primeraParte+","+segundaParte+","+terceraParte+","+cuartaParte));
                    } else if (primeraParte.equals(")")) {
                        while (!operaciones.peek().equals("(")){
                            bw.write(operaciones.pop().getToken() +"\n");
                        }
                        operaciones.pop();
                    }else{
                     if(operador.get(primeraParte) > operaciones.peek().getValor()){
                         operaciones.push(new Operador(primeraParte,operador.get(primeraParte),primeraParte+","+segundaParte+","+terceraParte+","+cuartaParte));
                     } else if (operador.get(primeraParte)<= operaciones.peek().getValor()) {
                         bw.write(operaciones.pop().getToken()+"\n");
                         operaciones.push(new Operador(primeraParte,operador.get(primeraParte),primeraParte+","+segundaParte+","+terceraParte+","+cuartaParte));
                     }
                    }
                }

            }

            System.out.println("Procesamiento completado. Se ha generado el archivo: " + archivoSalida);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //crearDirecciones(archivoEntrada,archivoDirecciones);

    }
    private static boolean esPalabraReservada(String palabra) {
        if (palabra.contains("@")){
            return true;
        }
        String[] palabrasReservadas = {"programa", "inicio", "Inicio", "fin", "Fin", "leer", "escribir", "si", "sino",
                "mientras", "repetir", "hasta", "entero", "real", "string", "cadena",
                "Logico", "logico", "Variables", "variables", "Entonces", "entonces",
                "Hacer", "hacer", "*", "/", "%", "+", "-", "=", "<", "<=",
                ">", ">=", "==", "!=", "and", "or", "!", "(", ")", ";", ",",
                "true", "false"};
        for (String palabraReservada : palabrasReservadas) {
            if (palabra.equals(palabraReservada)) {
                return true;
            }
        }
        return false;
    }


    private static boolean esNumero(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

    
