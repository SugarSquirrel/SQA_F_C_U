import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {
    //宣告1個Triangle
    Triangle t;

    //表示程式開始執行
    @BeforeAll
    static void setUp(){
        System.out.println("Here is Before All!...");
    }

    //初始化所用到的東東
    @BeforeEach
    void init(){
        t = new Triangle();
        //表示下個測試即將開始
        System.out.println("Here is Before Each!...");
    }

    //表示此測試結束
    @AfterEach
    void AE(){
        System.out.println("Here is After Each!...");
    }

    //表示程式結束執行
    @AfterAll
    static void end() {
        System.out.println("Here is After All!...");
    }

    //checkTriangle Test 1
    @Test
    void checkTriangle() throws Exception{
        //表示這是第一個檢查三角形的測試
        System.out.println("<<Here is the first testing of checkTriangle>>");
        //若是斷言錯誤就會噴錯
        assertAll("Triangle",
                () -> assertEquals("正三角形", t.checkTriangle(6,6,6)),
                () -> assertEquals("直角三角形", t.checkTriangle(3,4,5)),
                () -> assertEquals("三角形", t.checkTriangle(3,3,9)) //實際上為TriangleException的例外錯誤
        );
    }

    //checkTriangle Test 2
    @Test
    void checkTriangle2() throws Exception{
        //表示這是第二個檢查三角形的測試
        System.out.println("<<Here is the second testing of checkTriangle>>");
        //若是斷言錯誤就會噴錯
        assertAll("Triangle",
                () -> assertEquals("直角三角形", t.checkTriangle(3, 4, 5)),
                () -> assertEquals("等腰三角形", t.checkTriangle(7, 7, 8)),
                () -> assertEquals("三角形", t.checkTriangle(0, -1, -2)) //實際上為zero Edge的例外錯誤
        );
    }
}