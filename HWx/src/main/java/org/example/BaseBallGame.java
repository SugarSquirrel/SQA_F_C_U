package org.example;

import java.util.Objects;

public class BaseBallGame {
    String[] inningA;
    String[] inningB;
    int[] playerA;
    int[] playerB;
    int score1 = 0;
    int score2 = 0;
    int score3 = 0;
    int score4 = 0;

    //局數、隊員建構子
    public BaseBallGame(String[] inningA, String[] inningB, int[] playerA, int[] playerB) {
        this.inningA = inningA;
        this.inningB = inningB;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public int check_score() throws Exception
    {
        this.score1 = 0;
        this.score2 = 0;
        this.score3 = 0;
        this.score4 = 0;

        // 一、先測局分
        // 至少會有9筆比賽資料
        if (this.inningA.length < 9)
        {
            throw new ArithmeticException("局數不對:至少打9局!");
        }
        //至少會有9筆比賽資料，後攻者可能會有第9局為X的情況
        else if (this.inningB.length < 9)
        {
            throw new ArithmeticException("局數不對:至少打9局!");
        }
        //正常賽：若是兩隊只比到9局的情況
        else if(this.inningA.length == this.inningB.length && this.inningA.length == 9)
        {
            //先計算兩隊各局各局分是否正確，然後加起來
            for (String s : this.inningA) {
                //因為是前攻者，所以不可能有沒比第9局的情況
                if (Objects.equals(s, "X")) {
                    throw new ArithmeticException("局分不對:前攻者不可能有X的情況!");
                }
                //如果遇到負數分的情況
                else if (Integer.parseInt(s) < 0)
                {
                    throw new ArithmeticException("局分不對:不可能有負數分數!");
                }
                this.score1 += Integer.parseInt(s);
            }
            for (String s : this.inningB) {
                //因為是後攻者，所以可能會有沒比第9局的情況
                if (Objects.equals(s, "X")) {
                    break;
                }
                //如果遇到負數分的情況
                else if (Integer.parseInt(s) < 0)
                {
                    throw new ArithmeticException("局分不對:不可能有負數分數!");
                }
                this.score2 += Integer.parseInt(s);
            }
            //若是後攻者第九局為X，但又總分輸給前攻者
            if(Objects.equals(this.inningB[8], "X") && this.score1 >= this.score2) {
                if(this.score1 == this.score2)
                {
                    throw new ArithmeticException("總分不對:不可能有平手的情況!");
                }
                else {
                    throw new ArithmeticException("不合理的提前結束:後攻者只打到8下，不可能輸給前攻者!");
                }
            }
            else if (!Objects.equals(this.inningB[8], "X"))
            {
                if(this.score1 < this.score2 - Integer.parseInt(this.inningB[8]))
                {
                    throw new ArithmeticException("不合理的第9局:後攻者在第8局已經贏過前攻者的9局!");
                }
            }
        }
        //延長賽：若是兩隊都比10局以上 (因為前面的情況，所以剩下的只會是長度為10以上的陣列)
        else if(this.inningA.length == this.inningB.length)
        {
            //先計算兩隊至第9局的各局分是否正確，然後加起來
            for (int i = 0; i < 9; i++) {
                //因為是延長賽，所以不可能會有X的情況
                if (Objects.equals(this.inningA[i], "X")) {
                    throw new ArithmeticException("局分不對:延長賽不可能有X的情況!");
                }
                //如果遇到負數分的情況
                else if (Integer.parseInt(this.inningA[i]) < 0)
                {
                    throw new ArithmeticException("局分不對:不可能有負數分數!");
                }
                this.score1 += Integer.parseInt(this.inningA[i]);
            }
            for (int i = 0; i < 9; i++) {
                //因為是延長賽，所以不可能會有X的情況
                if (Objects.equals(this.inningB[i], "X")) {
                    throw new ArithmeticException("局分不對:延長賽不可能有X的情況!");
                }
                //如果遇到負數分的情況
                else if (Integer.parseInt(this.inningB[i]) < 0)
                {
                    throw new ArithmeticException("局分不對:不可能有負數分數!");
                }
                this.score2 += Integer.parseInt(this.inningB[i]);
            }
            //兩隊到第9局的總分不相同時
            if(this.score1 != this.score2)
            {
                throw new ArithmeticException("不合理的延長賽:第9局已經分出勝負!");
            }
            //第9局結束為平手，則繼續比延長賽
            else {
//                throw new ArithmeticException("總分不對:不可能有平手的情況!");
                for(int i = 9; i < this.inningA.length; i++)
                {
                    this.score1 += Integer.parseInt(this.inningA[i]);
                    this.score2 += Integer.parseInt(this.inningB[i]);
                    if(this.score1 != this.score2 && i != this.inningA.length-1)
                    {
                        String ex = "不合理的延長延長賽:第" + (i+1) + "局已經分出勝負!";
                        throw new ArithmeticException(ex);
                    }
                }
                if (this.score1 == this.score2)
                {
                    throw new ArithmeticException("總分不對:不可能有平手的情況!");
                }
            }
        }
        else {
            throw new ArithmeticException("局數不對:不是9局就是10局，兩邊局數要一樣!");
        }


        // 二、測隊員得分
        // 先看人數合不合理
        if (this.playerA.length != 9 || this.playerB.length != 9) {
            if(this.playerA.length != 9 && this.playerB.length == 9)
            {
                throw new ArithmeticException("前攻者人數不對:比賽成員只能有9個!");
            }
            else if(this.playerA.length == 9)
            {
                throw new ArithmeticException("後攻者人數不對:比賽成員只能有9個!");
            }
            else
            {
                throw new ArithmeticException("2隊人數都不對:比賽成員各只能有9個!");
            }
        }
        //人物合理的話，計算分數
        else
        {
            //判斷分數是否都>=0
            for (int i = 0; i < this.playerA.length; i++) {
                // 判斷是否會有小於0的狀況
                if (this.playerA[i] < 0 || this.playerB[i] < 0) {
                    throw new ArithmeticException("成員分數不對:不可能有負數分的情況!");
                }
                this.score3 += playerA[i];
                this.score4 += playerB[i];
            }
        }

        // 三、判斷總局分是否和各成員分一致
        if(this.score1 != this.score3)
        {
            throw new ArithmeticException("前攻隊總分不一致!");
        }
        if(this.score2 != this.score4)
        {
            throw new ArithmeticException("後攻隊總分不一致!");
        }

        // 四、回傳值
        if(this.score1 > this.score2)
        {
            System.out.printf("前攻者贏了 %d分\n", this.score1 - this.score2);
        }
        else
        {
            System.out.printf("後攻者贏了 %d分\n", this.score2 - this.score1);
        }
        return this.score1 - this.score2;
    }
}
