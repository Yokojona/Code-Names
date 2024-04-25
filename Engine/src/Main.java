public class Main {
    public static void main(String[] args) {
        String[] words = {"apple", "banana", "cucumber", "drink", "escargot",
                "fish", "grape", "heel", "ion", "jack",
                "koala", "lasso", "mango", "nectar", "oath",
                "pineapple", "quality", "resort", "sky", "trip",
                "umbrella", "vacation", "weekend", "x-men", "yogurt", "zebra"};
        Board board = new Board(words, 5, 5, 7, 1);
        Engine engine = new EngineImp();
    }
}
