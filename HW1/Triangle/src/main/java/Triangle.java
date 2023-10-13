public class Triangle {
    public static void main(String[] args) {
        //宣告一個Triangle建構子
        Triangle t = new Triangle();
        //String用來被assigned checkTriangle的return ex:正三角形
        String r = "";

        //用try catch來抓取checkTriangle裡的例外處理
        try {
            r = t.checkTriangle(6,6,6);
        }
        catch (Exception e) {
            //若是邊長小於等於0或是長度不符合三角形的law，就會噴錯
            throw new RuntimeException(e);
        }
        //print出三角形type
        System.out.println(r);
    }

    //檢查三角形type，並有例外處理
    public String checkTriangle(int a, int b, int c)throws Exception {
        //假如其中一邊長小於等於0時，就會噴出"zero Edge"的錯誤
        if (a<=0 || b<=0 || c<=0) {
            throw new Exception("zero Edge");
        }
        //假如不符合三角形的規範時，就會噴出自定義的TriangleException例外錯誤
        if (a+b <= c || a+c <= b || b+c <= a) {
            throw new TriangleException(a, b, c);
        }
        //先宣告一個字串用來記錄三角形的type
        String type = "normal";
        if (a == b){
            //a == b == c
            if (b == c) {
                type = "正三角形";
            }
            //a == b && a^2 + b^2 == c^2
            else if (a*a+b*b == c*c) {
                type = "等腰直角三角形";
            }
            //a == b
            else {
                type = "等腰三角形";
            }
        }
        else if(a == c) {
            assert a != b;
            //a == c && a^2 + b^2 == c^2
            if (a*a+b*b == c*c) {
                type = "等腰直角三角形";
            }
            else {
                type = "等腰三角形";
            }
        }
        else if (b == c){
            assert a != b;
            assert a != c;
            //a == b && b^2 + c^2 == a^2
            if (b*b + c*c == a*a) {
                type = "等腰直角三角形";
            }
            //b == c
            else {
                type = "等腰三角形";
            }
        }
        else if (a*a + b*b == c*c || b*b + c*c == a*a || a*a + c*c == b*b) {
            assert a != b;
            assert a != c;
            assert b != c;
            type = "直角三角形";
        }
        else {
            type = "三角形";
        }
        //傳回三角形型態
        return type;
    }

    //三角形 -> 例外處理
    static class TriangleException extends Exception {
        //假如不符合三角形的規範時，就會噴"not allowed Triangle length"的訊息
        public TriangleException(int a, int b, int c) {
            super("not allowed Triangle length");
        }
    }
}