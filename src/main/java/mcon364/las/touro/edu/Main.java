package mcon364.las.touro.edu;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

    }
    public static Optional<String> getUserName(String envVarName){
        return  Optional.ofNullable(System.getenv(envVarName));
    }
    public static String getGreeting(String envVarName){
        Optional<String> environmentVarName= getUserName(envVarName);
        int number=10;
        var name= "Aviva";



    }
}
