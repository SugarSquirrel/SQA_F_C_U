package org.example;

public class Calculator {
    public int addOne(int a){
        return a+1;
    }

    public int add(int a, int b){
        return a+b;
    }

    //星期幾 2021/1/1 是星期五，輸入月份和日期（一樣是2021年），輸出是星期幾。
    public int week(String day){
        // 這邊先偵測輸入值的月份、日期
        // (因為輸入格式是直接輸入月日，ex: 11(1月1號))
        //先力用date變數判斷月份開頭是否為1
        int date = Integer.parseInt(day.substring(0,1));
        int month = 0;
        //如果是1
        if (date == 1)
        {
            //接著判斷第2位是否為0、1、2
            int tmp = Integer.parseInt(day.substring(1, 2));
            if (tmp < 3)
            {
                month = Integer.parseInt(day.substring(0, 2));
                date = Integer.parseInt(day.substring(2, day.length()));
            }
            //若不是則為1月
            else{
                month = Integer.parseInt(day.substring(0, 1));
                date = Integer.parseInt(day.substring(1, day.length()));
            }
        }
        //若不是1開頭，代表為其他月份
        else {
            month = Integer.parseInt(day.substring(0, 1));
            date = Integer.parseInt(day.substring(1, day.length()));
        }
        //System.out.printf("%-8s"+month+"\n", "Month:");
        //System.out.printf("%-8s"+date+"\n", "Day:");

        //開始計算星期
        int total = 0;
        //建一個月份日子表
        int [] month_day = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month > 12 || date > month_day[month - 1]){
            return -1;
        }
        for(int i = 0; i < month-1; i++)
        {
            total += month_day[i];
        }
        total += date;
        //以2021/1/1為準 計算星期
        int weekday = (total+5-2)%7+1;
        // System.out.printf("%-8s"+weekday+"\n\n", "weekday:");
        return weekday;
    }

    //BMI 輸入身高體重，輸出 BMI，bmi = h/(w**2)
    public double BMI(int h, int w)throws Exception {
        // 身高不合理時，丟出例外並噴出警告
        if (h > 220 || h < 50){
            throw new Exception("WARNING: invalid height!");
        }
        //體重不合理時，丟出例外並噴出警告
        if (w < 20 || w > 350){
            throw new Exception("WARNING: invalid weight!");
        }
        // BMI = 體重/身高平方 (計算到小數點第二位)
        double bmi = Math.round(w/Math.pow((h/100.0), 2) * 100.0) / 100.0;
        // System.out.println("BMI: "+bmi);
        // System.out.println(Math.round(w/Math.pow((h/100.0), 2) * 100.0) / 100.0);
        return bmi;
    }

    // 貨幣 一個 Currency 的類別，內封裝金額與幣值的屬性，
    // 幣值可以有 NT 和 US 兩種幣值，其匯率為 30。
    // 如果是台幣+美金則回傳台幣，若美金+台幣則回傳為美金（由第一個決定）
    // 請設計程式與測試案例。
    static class Currency {
        //Init
        float amount;
        String symbol;

        //Currency 建構子(紀錄金額 & 幣值)
        public Currency(float a, String s) {
            amount = a;
            symbol = s;
        }

        //兩幣相加的函式
        public Currency add(Currency other) {
            //取出當下幣值的金額 & 幣值
            float totalAmount = this.amount;
            String resultingSymbol = this.symbol;

            //若兩幣值不相等
            if (!this.symbol.equals(other.symbol)) {
                //首幣若為NT
                if (this.symbol.equals("NT")) {
                    totalAmount += other.amount / 30;
                    //否則
                } else if (this.symbol.equals("US")) {
                    totalAmount += other.amount * 30;
                }
                //若兩幣值相等，直接相加
            } else {
                totalAmount += other.amount;
            }

            //回傳一個建構子
            return new Currency(totalAmount, resultingSymbol);
        }
    }

    //計算是否為閏年
    public Boolean is_leapyear(int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 明天
    // 寫一函式，輸入是一個 y-m-d 的日期(2023-01-02)，輸出同樣為 y-m-d 的格式
    public String tomorrow (String day) throws Exception{
        // 把日期做格式化 分割成年、月、日
        String[] tokens = day.split("-");
        String syear = tokens[0];
        String smonth = tokens[1];
        String sdate = tokens[2];
        // 若是有小於0的數值，噴出錯誤訊息!
        if(Integer.parseInt(tokens[0])<0 || Integer.parseInt(tokens[1])<0 || Integer.parseInt(tokens[2])<0) {
            throw new Exception(">>>WARNING : The day isn't correct!");
        }
        // 沒錯就將字串轉為整數進行計算
        int year = Integer.parseInt(syear);
        int month = Integer.parseInt(smonth);
        int date = Integer.parseInt(sdate);
        int [] month_day = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //若月份不合法， 噴出錯誤訊息
        if(month > 12){
            throw new Exception(">>>WARNING : The month isn't correct!");
        }
        //若日期不合法， 噴出錯誤訊息
        if(date > month_day[month-1]){
            throw new Exception(">>>WARNING : The date isn't correct!");
        }

        // 開始計算tomorrow，先把date+1
        date += 1;
        //若是閏年，並且為2月28號的話，隔天為2月29號
        if(is_leapyear(year)) {
            if (month == 2) {
                if (month_day[month - 1] + 1 < date) {
                    month += 1;
                    date = 1;
                    if (month > 12) {
                        year += 1;
                        month = 1;
                    }
                }
                //若不是2月，照常計算即可
            } else {
                if (month_day[month - 1] < date) {
                    month += 1;
                    date = 1;
                    if (month > 12) {
                        year += 1;
                        month = 1;
                    }
                }
            }
        }
        // 平年的話，就照常計算
        else {
            if (month_day[month - 1] < date) {
                month += 1;
                date = 1;
                if (month > 12) {
                    year += 1;
                    month = 1;
                }
            }
        }

        //最後將年月日拼回字串型態並傳回
        String new_day = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(date);
        return new_day;
    }
}
