import java.util.function.Function;

public class a {
    public static void main(String[] args) {
        Function<String, String> toUpperCase = s -> s.toUpperCase();

        
        String input = "tech m";
        String result = toUpperCase.apply(input);

        System.out.println(result);  
    }
}
