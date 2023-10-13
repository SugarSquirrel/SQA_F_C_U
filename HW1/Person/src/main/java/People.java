public class People {
    public static void main(String[] args) {
        //new一個物件 ex: Anne, 2003年出生
        person a = new person("Anne", 2003);
        person p = new person("Peter", 2004);
        person m = new person("Mike", 2005);
        try {
            //賦身高、體重給a、p、m
            a.setHW(170,60);
            p.setHW(167,68);
            m.setHW(180,70);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Anne的age和BMI
        System.out.println("Anne: ");
        System.out.println("Age: " + a.getAge());
        System.out.println("BMI: " + a.getBmi());
        //Peter的age和BMI
        System.out.println("Peter: ");
        System.out.println("Age: " + p.getAge());
        System.out.println("BMI: " + p.getBmi());
        //Mike的age和BMI
        System.out.println("Mike: ");
        System.out.println("Age: " + m.getAge());
        System.out.println("BMI: " + m.getBmi());
    }
}

class person{
    //初始化
    String name = "";
    int year = 0;
    int age = 0;
    int height = 0;
    int weight = 0;
    double bmi = 0;

    //生成person物件(建構子)
    public person(String name, int year) {
        //斷言birthyear必定小於2024年
        assert year < 2024 : "WARNING: invalid birthyear!";
        this.name = name;
        this.year = year;
    }

    //讓建構子能夠賦予身高體重，可丟出例外
    public void setHW(int height, int weight) throws Exception{
        //身高不合理時，丟出例外並噴出警告
        if (height > 220 || height < 50){
            throw new Exception("WARNING: invalid height!");
        }
        //體重不合理時，丟出例外並噴出警告
        if (weight < 20 || weight > 350){
            throw new Exception("WARNING: invalid weight!");
        }
        //若都合理，就assign身高體重
        this.height = height;
        this.weight = weight;
    }

    //讓建構子能夠取得年齡
    public int getAge(){
        this.age = 2023 - this.year;
        //斷言年齡必定大於等於0，否則噴出警告
        assert age >= 0 : "WARNING: invalid age!";
        return age;
    }

    //讓建構子能夠取得BMI
    public double getBmi(){
        // 先將身高從公分轉為公尺
        double d1 = this.height / 100.0;
        //assign BMI給bmi變數，並且只取到小數點後第二位
        this.bmi = Math.round((this.weight / ((d1) * (d1)))*100.0) / 100.0;
        // 斷言bmi不會小於10也不會大於等於50，否則噴出警告
        assert this.bmi > 10 && this.bmi <= 50 : "WARNING: invalid BMI!";
        return bmi;
    }
}