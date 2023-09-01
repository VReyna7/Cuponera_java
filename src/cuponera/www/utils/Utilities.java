package cuponera.www.utils;

//LIBRERIAS PARA EL ENCRIPTAMIENTO
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Utilities {
	
	// función para generar una cadena aleatoria de bytes que se usará como sal
    private static byte[] generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }
	
	public static String encriptarPassword(String password) {
        try {
            // generar una sal aleatoria
            byte[] salt = generateSalt();

            // crear un objeto MessageDigest con el algoritmo SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // agregar la sal a la contraseña y calcular el hash
            messageDigest.update(salt);
            byte[] hashedPassword = messageDigest.digest(password.getBytes());

            // codificar la sal y la contraseña cifrada en Base64 y concatenarlas
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            String encodedPassword = Base64.getEncoder().encodeToString(hashedPassword);
            String encryptedPassword = encodedSalt + ":" + encodedPassword;

            return encryptedPassword;
        } catch (NoSuchAlgorithmException e) {
            // manejar la excepción adecuadamente
        }
        return null;
    }
	
	public boolean verificarPassword(String password, String encryptedPassword) {
        try {
            // separar la sal y la contraseña cifrada
            String[] parts = encryptedPassword.split(":");
            String encodedSalt = parts[0];
            String encodedPassword = parts[1];

            // decodificar la sal y la contraseña cifrada de Base64
            byte[] salt = Base64.getDecoder().decode(encodedSalt);
            byte[] hashedPassword = Base64.getDecoder().decode(encodedPassword);

            // crear un objeto MessageDigest con el algoritmo SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // agregar la sal a la contraseña y calcular el hash
            messageDigest.update(salt);
            byte[] hashedInputPassword = messageDigest.digest(password.getBytes());

            // comparar los hashes
            return MessageDigest.isEqual(hashedPassword, hashedInputPassword);
        } catch (NoSuchAlgorithmException e) {
            System.out.print("algo salio mal");
        }
        return false;
    }
}
