//用到的Junit指令
import org.junit.jupiter.api.*;
//斷言
import static org.junit.jupiter.api.Assertions.*;

class personTest {
    //宣告3個person
    person Anne, Peter, Mike;

    //表示程式開始執行
    @BeforeAll
    static void setUp() {
        System.out.println("Set Up...");
    }

    //初始化所用到的東東
    @BeforeEach
    void init() throws Exception{
        //表示下個測試即將開始
        System.out.println("Here is before each...");
        //Initial
        Anne = new person("Anne", 2003);
        Peter = new person("Peter", 2004);
        Mike = new person("Mike", 2005);
        Anne.setHW(170, 60);
        Peter.setHW(167, 68);
        Mike.setHW(180, 70);
    }

    //表示程式結束執行
    @AfterAll
    static void end() {
        System.out.println("Here is After All!...");
    }

    //表示此測試結束
    @AfterEach
    void AE(){
        System.out.println("Here is After Each!...");
    }

    //setHW Test
    @Test
    void setHW(){
        //表示這是set身高體重的測試
        System.out.print("<<Here is the test of setting Height and Weight>>");
        //若是斷言錯誤就會噴錯，噴錯的地方多補上了預期的敘述 ex:() -> "\n預期身高為: 170"(方便觀察)
        assertAll("height & weight",
                () -> assertEquals(170, Anne.height, () -> "\n預期身高為: 170"),
                () -> assertEquals(167, Peter.height, () -> "\n預期身高為: 167"),
                () -> assertEquals(60, Anne.weight, () -> "\n預期體重為: 60"),
                () -> assertEquals(50, Mike.weight, () -> "\n預期體重為: 50"));
    }

    //getAge Test
    @Test
    void getAge() {
        //表示這是get年齡的測試
        System.out.print("<<Here is the test of getting age>>");
        //若是斷言錯誤就會噴錯，噴錯的地方多補上了預期的敘述 ex:() -> "\n預期年齡為: 20"(方便觀察)
        assertAll("age",
                () -> assertEquals(20, Anne.getAge(), () -> "\n預期年齡為: 20"),
                () -> assertEquals(19, Peter.getAge(), () -> "\n預期年齡為: 19"),
                () -> assertEquals(19, Mike.getAge(), () -> "\n預期年齡為: 19"));
    }

    //getBmi Test
    @Test
    void getBmi(){
        //表示這是get BMI的測試
        System.out.print("<<Here is the test of getting bmi>>");
        //若是斷言錯誤就會噴錯，噴錯的地方多補上了預期的敘述 ex:() -> "\n預期BMI為: 20.76"(方便觀察)
        assertAll("BMI",
                () -> assertEquals(20.76, Anne.getBmi(), () -> "\n預期BMI為: 20.76"),
                () -> assertEquals(24.38, Peter.getBmi(), () -> "\n預期BMI為: 24.38"),
                () -> assertEquals(20.60, Mike.getBmi(), () -> "\n預期BMI為: 20.60"));
    }
}