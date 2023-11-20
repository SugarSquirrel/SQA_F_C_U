package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseBallGameTest {

    //宣告1個Calculator
    BaseBallGame b;
    //初始化所用到的東東
    @BeforeEach
    void init(){
        System.out.println("Process in progress!...\n");
    }

    //表示程式結束執行
    @AfterAll
    static void finish(){
        System.out.println("Process done...");
    }

    // 正確測資
    //以下開始進行Test
    @Test
    // 第9局上半，後攻者B已領先A
    void check_score1_true1() throws Exception{
        String[] inningA = {"1", "3", "1", "0", "1", "0", "0", "2", "3"}; // sumA = 11
        String[] inningB = {"3", "2", "3", "1", "0", "0", "2", "1", "X"}; // sumB = 12
        int[] playerA = {0, 2, 4, 0, 0, 1, 2, 1, 1}; // sumA = 11
        int[] playerB = {1, 2, 3, 0, 1, 0, 2, 2, 1}; // sumB = 12
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        int score = b.check_score();
        assertEquals(-1, score);
    }

    // 打滿第9局 (A贏)
    @Test
    void check_score1_true2() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3"}; // sumA = 16
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "2"}; // sumB = 15
        int[] playerA = {1, 2, 4, 1, 1, 1, 2, 2, 2}; // sumA = 16
        int[] playerB = {1, 2, 3, 1, 1, 1, 2, 2, 2}; // sumB = 15
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        int score = b.check_score();
        assertEquals(1, score);
    }

    // 打滿第9局 (B贏)
    @Test
    void check_score1_true3() throws Exception{
        String[] inningA = {"1", "3", "1", "0", "1", "0", "0", "2", "3"}; // sumA = 11
        String[] inningB = {"3", "2", "3", "1", "0", "0", "0", "1", "2"}; // sumB = 12
        int[] playerA = {0, 2, 4, 0, 0, 1, 2, 1, 1}; // sumA = 11
        int[] playerB = {1, 2, 3, 0, 1, 0, 2, 2, 1}; // sumB = 12
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        int score = b.check_score();
        assertEquals(-1, score);
    }

    // 第9局平手，延長賽 (A贏)
    @Test
    void check_score1_true4() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "2"}; // sumA = 18
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3", "1"}; // sumB = 17
        int[] playerA = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumA = 18
        int[] playerB = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumB = 17
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        int score = b.check_score();
        assertEquals(1, score);
    }

    // 第9局平手，延長賽 (B贏)
    @Test
    void check_score1_true5() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        int score = b.check_score();
        assertEquals(-1, score);
    }

    // 出錯測資 (Exception)
    // 不合理的第9局
    @Test
    void check_score_error1() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "2"}; // sumA = 15
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "3", "0"}; // sumB = 16
        int[] playerA = {1, 2, 2, 2, 2, 0, 2, 2, 2}; // sumA = 15
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 16
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("不合理的第9局:後攻者在第8局已經贏過前攻者的9局!", e.getMessage());
    }

    // A(10)局中有負數分數
    @Test
    void check_score_error2() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "-1", "3", "2", "3", "1"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局分不對:不可能有負數分數!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // B(10)局中有負數分數
    @Test
    void check_score_error3() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "-2", "4", "2", "0", "3", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局分不對:不可能有負數分數!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // A成員中有負數分數
    @Test
    void check_score_error4() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "3", "2"}; // sumA = 16
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3"}; // sumB = 16
        int[] playerA = {-1, 4, 3, 2, 2, 1, 2, 2, 1}; // sumA = 16
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 0}; // sumB = 16
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("成員分數不對:不可能有負數分的情況!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // B成員中有負數分數
    @Test
    void check_score_error5() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "3", "2"}; // sumA = 16
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3"}; // sumB = 16
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 1}; // sumA = 16
        int[] playerB = {-1, 4, 4, 2, 2, 1, 2, 2, 0}; // sumB = 16
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("成員分數不對:不可能有負數分的情況!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // A局數不足9局
    @Test
    void check_score_error6() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, -2, 5, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局數不對:至少打9局!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // B局數不足9局
    @Test
    void check_score_error7() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, -2, 5, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局數不對:至少打9局!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // A成員不足9人
    @Test
    void check_score_error8() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1"}; // sumA = 16/17
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3", "2"}; // sumB = 16/18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("前攻者人數不對:比賽成員只能有9個!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // B成員不足9人
    @Test
    void check_score_error9() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("後攻者人數不對:比賽成員只能有9個!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // A隊分數加總不相等
    @Test
    void check_score_error10() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 1}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("前攻隊總分不一致!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // B隊分數加總不相等
    @Test
    void check_score_error11() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 1}; // sumB = 17
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("後攻隊總分不一致!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // Jacoco後 補充的測試案例
    // A隊局數超過10局
    @Test
    void check_score_error12() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1", "2"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局數不對:不是9局就是10局，兩邊局數要一樣!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // B隊局數超過10局
    @Test
    void check_score_error13() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3", "2", "2"}; // sumB = 20
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局數不對:不是9局就是10局，兩邊局數要一樣!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // A(9)局中有負數分數
    @Test
    void check_score_error14() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "-1", "3", "1", "2", "3"}; // sumA = 16
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3"}; // sumB = 16
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 16
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 16
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局分不對:不可能有負數分數!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // B(9)局中有負數分數
    @Test
    void check_score_error15() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3"}; // sumA = 16
        String[] inningB = {"3", "2", "3", "-1", "4", "0", "2", "0", "3"}; // sumB = 16
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 16
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 16
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局分不對:不可能有負數分數!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // A第9局有X情況
    @Test
    void check_score_error16() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "X"}; // sumA = 16
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3"}; // sumB = 16
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 16
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 16
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局分不對:前攻者不可能有X的情況!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // 後攻者第九局為X，但又總分又跟前攻者相同
    @Test
    void check_score_error17() throws Exception{
        String[] inningA = {"1", "2", "1", "2", "1", "1", "1", "2", "2"}; // sumA = 13
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "X"}; // sumB = 13
        int[] playerA = {1, 2, 0, 2, 2, 1, 2, 2, 2}; // sumA = 16
        int[] playerB = {1, 2, 1, 2, 2, 1, 2, 2, 2}; // sumB = 16
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("總分不對:不可能有平手的情況!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // 後攻者第九局為X，但又總分輸給前攻者
    @Test
    void check_score_error18() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "2"}; // sumA = 16
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "X"}; // sumB = 16
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 16
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 16
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("不合理的提前結束:後攻者只打到8下，不可能輸給前攻者!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // A隊比延長賽卻有X情況
    @Test
    void check_score_error19() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "X", "1", "2", "3", "1"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "3", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局分不對:延長賽不可能有X的情況!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // B隊比延長賽卻有X情況
    @Test
    void check_score_error20() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1"}; // sumA = 17
        String[] inningB = {"3", "2", "3", "1", "2", "X", "2", "0", "3", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局分不對:延長賽不可能有X的情況!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // A隊比10局，卻第9局時總分和B隊相同
    @Test
    void check_score_error21() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1"}; // sumA = 17
        String[] inningB = {"1", "3", "1", "3", "1", "1", "1", "2", "2", "2"}; // sumB = 18
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumA = 17
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("不合理的延長賽:第9局已經分出勝負!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // B隊比10局，卻第9局時總分和A隊相同
    @Test
    void check_score_error22() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "2"}; // sumA = 18
        String[] inningB = {"1", "3", "1", "3", "1", "1", "1", "2", "2", "1"}; // sumB = 17
        int[] playerA = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumA = 18
        int[] playerB = {1, 2, 3, 2, 2, 1, 2, 2, 2}; // sumB = 17
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("不合理的延長賽:第9局已經分出勝負!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // 比了延長賽，雙方卻還平手
    @Test
    void check_score_error23() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "2"}; // sumA = 18
        String[] inningB = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "2"}; // sumB = 18
        int[] playerA = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumA = 18
        int[] playerB = {1, 2, 4, 2, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("總分不對:不可能有平手的情況!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // 兩隊成員人數都不正確
    @Test
    void check_score_error24() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "2"}; // sumA = 18
        String[] inningB = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1"}; // sumB = 17
        int[] playerA = {1, 2, 4, 2, 2, 1, 2, 2, 1, 1}; // sumA = 18
        int[] playerB = {1, 2, 3, 2, 2, 1, 2, 2, 1, 1}; // sumB = 17
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("2隊人數都不對:比賽成員各只能有9個!", e.getMessage());
        System.out.println(e.getMessage());
    }

    //第2次jacoco後新增的
    //B隊分數加總不相等
    @Test
    void check_score_error25() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3"}; // sumA = 16
        String[] inningB = {"3", "2", "3", "1", "2", "0", "2", "0", "2", "0"}; // sumB = 15
        int[] playerA = {1, 2, 3, 2, 2, 1, 2, 2, 1}; // sumA = 16
        int[] playerB = {1, 2, 1, 2, 2, 1, 2, 2, 2}; // sumB = 15
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("局數不對:不是9局就是10局，兩邊局數要一樣!", e.getMessage());
        System.out.println(e.getMessage());
    }

    // 第三次jacoco後新增的
    // 比了延長賽，但前幾局已分出勝負
    @Test
    void check_score_error26() throws Exception{
        String[] inningA = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "2", "1"}; // sumA = 19
        String[] inningB = {"1", "3", "1", "3", "1", "1", "1", "2", "3", "1", "2"}; // sumB = 19
        int[] playerA = {1, 2, 4, 3, 2, 1, 2, 2, 2}; // sumA = 18
        int[] playerB = {1, 2, 4, 3, 2, 1, 2, 2, 2}; // sumB = 18
        b = new BaseBallGame(inningA, inningB, playerA, playerB);
        Exception e = assertThrows(Exception.class, b::check_score);
        assertEquals("不合理的延長延長賽:第10局已經分出勝負!", e.getMessage());
        System.out.println(e.getMessage());
    }
}