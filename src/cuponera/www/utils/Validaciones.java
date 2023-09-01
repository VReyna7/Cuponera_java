package cuponera.www.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
	private static int entero;
    private static double decimal;
    private static String cadena;

    public static boolean campoEstaVacio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }
    
    public static boolean validarDUI(String dui) {
        String patron = "\\d{8}-\\d{1}";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(dui);
        return matcher.matches();
    }
    
    public static boolean validarTelefono(String telefono) {
        String patron = "\\d{4}-\\d{4}";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(telefono);
        return matcher.matches();
    }
    
    public static boolean validarEmail(String email) {
        String patron = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean validarPassword(String password) {
        if (password.length() < 8) {
            return false;
        }else {
        	return true;
        }
    }
    
    public static boolean validarCodigoEmpresa(String codigo) {
    	String patron = "^[EM]+[0-9]{4}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(codigo);
        return matcher.matches();
    }
}
