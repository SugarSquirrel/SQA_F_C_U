package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    //宣告1個Calculator
    Calculator c;
    //初始化所用到的東東
    @BeforeEach
    void init(){
        c = new Calculator();
        System.out.println("Process in progress!...\n");
    }

    //表示程式結束執行
    @AfterAll
    static void finish(){
        System.out.println("Process done...");
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,})
    void addOneTest(int v) {
        assertEquals(v + 1, c.addOne(v));
    }
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n" })
    void nullEmptyAndBlankStrings(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "apple,         1",
        "banana,        2",
        "'lemon, lime', 0xF1",
        "strawberry,    700_000"
    })
    void testWithCsvSource(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }


    @ParameterizedTest
    //Way1 CSV格式餵資料
//    @CsvSource({
//            "1, 2, 3",
//            "2, 3, 5",
//            "1, -1, 0",
//            "-1, -2, -3",
//    })
    //Way2 CSV檔餵資料
    @CsvFileSource(resources = "/testdata.csv", numLinesToSkip = 1)
    void testAdd(int a, int b, int r) {
        assertEquals(r, c.add(a,b));
    }


    //參數化-計算2021年的日子的星期
    @ParameterizedTest
    //Way1 CSV格式餵資料
//    @CsvSource({
//            "17, 4",
//            "23, 3",
//            "36, 6",
//            "48, 4",
//            "102, 6",
//            "119, 2",
//    })
    //Way2 CSV檔餵資料
    @CsvFileSource(resources = "/2021date.csv", numLinesToSkip = 1)
    void testWeek(String day, int r) {
        //斷言兩個值應當相等，否則顯示警告訊息! ex: 預期為星期[1]
        assertEquals(r, c.week(day), () -> "\n預期為星期["+r+"]");
    }

    //參數化 計算BMI
    @ParameterizedTest
    //Way1 CSV格式餵資料
//    @CsvSource({
//            "180, 70, 21.6",
//            "170, 60, 20.76",
//            "160, 50, 19.53",
//            "167, 56, 20.08",
//            "178, 70, 22.09",
//    })
    //Way2 CSV檔餵資料
    @CsvFileSource(resources = "/BMI.csv", numLinesToSkip = 1)
    void testBMI(int h, int w, double r) throws Exception{
        //斷言兩個值應當相等，否則顯示警告訊息! ex: 預期BMI為: 21.26
        assertEquals(r, c.BMI(h, w), () -> "\n預期BMI為: " + r);
    }

    //參數化-計算貨幣(Currency)
    @ParameterizedTest
    //Way1 CSV格式餵資料
//    @CsvSource({
//            "100.0, NT, 100.0, NT, 130.0, NT",
//            "100.0, NT, 100.0, US, 103.33, NT",
//            "100.0, US, 100.0, US, 3000.0, US",
//            "100.0, US, 100.0, NT, 103.0, US",
//    })
    //Way2 CSV檔餵資料
    @CsvFileSource(resources = "/coinchange.csv", numLinesToSkip = 1)
    void testAdd(float amount1, String symbol1, float amount2, String symbol2, float expectedAmount, String expectedSymbol) {
        //建構子1 & 建構子2
        Calculator.Currency currency1 = new Calculator.Currency(amount1, symbol1);
        Calculator.Currency currency2 = new Calculator.Currency(amount2, symbol2);

        //計算結果再建構一個建構子
        Calculator.Currency result = currency1.add(currency2);
        //只取到小數點後兩位
        String result_amount = String.format("%.2f", result.amount);

        //斷言兩個值應當相等，否則顯示警告訊息! ex: 預期金額 & 幣值是:130.0NT
        assertEquals(Float.toString(expectedAmount) + expectedSymbol
                     , result_amount + result.symbol
                     , () -> "\n預期金額 & 幣值是:" + (Float.toString(expectedAmount) + expectedSymbol));
    }

    //明天
    @ParameterizedTest
    //Way1 CSV格式餵資料
//    @CsvSource({
//            "2003-4-8, 2003-4-9",
//            "2019-2-28, 2019-2-29",
//            "2020-2-28, 2020-2-29",
//            "2023-12-31, 2024-1-1",
//    })
    //Way2 CSV檔餵資料
    @CsvFileSource(resources = "/tomorrow.csv", numLinesToSkip = 1)
    void testTomorrow(String day, String r) throws Exception{
        //斷言兩個值應當相等，否則顯示警告訊息! ex: 預期日期為: 20
        assertEquals(r, c.tomorrow(day), () -> "\n預期日期為: " + r);
    }
}