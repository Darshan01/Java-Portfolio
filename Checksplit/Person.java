public class Person {
    private String name;
    private Food[] order;
    private double due;
    private boolean birthday;

    public Person(String s, boolean b){
        name = s;
        birthday = b;
    }

    public String getName(){return name;}
    public boolean isBDay(){return birthday;}
    public double getDue(){return due;}
    public Food[] getOrder(){return order;}

    public void setName(String s){name = s;}
    public void setBDay(boolean b){birthday = b;}
    public void setDue(double d){due = d;}
    public void updateDue(double d){due += d;}




    public void makeOrder(Food[] o){
        order = o;
        for(int i = 0; i < order.length; i++){
            due += order[i].getCost();
        }
    }


}
