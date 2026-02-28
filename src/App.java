import lib.ExpressionEvaluator;

public class App {
    public static void main(String[] args) throws Exception {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        for (String arg : args) {
            System.out.println("Expression: " + arg);
            System.out.println("Result: " + evaluator.evaluate(arg));
        }
        System.out.println("Done evaluating expressions!");
    }
}
