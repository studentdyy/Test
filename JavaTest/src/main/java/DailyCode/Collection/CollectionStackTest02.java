package DailyCode.Collection;
/*
有空吧这个练习题在做了
*/
public class CollectionStackTest02 {
    public static void main(String[] args) {
        String exp = "1 + 2 * (9 - 5)";
        SuffixExpression se = compile(exp);
        int result = se.execute();
        System.out.println(exp + " = " + result + " " + (result == 1 + 2 * (9 - 5) ? "✓" : "✗"));
    }
    static SuffixExpression compile(String exp) {
        // TODO:

        return new SuffixExpression();
    }
}
class SuffixExpression {
    int execute() {
        // TODO:
        return 0;
    }
}