public class Food {
    private String name;
    private double cost;

    public Food(String n, double c){
        name = n;
        cost = c;
    }

    public double getCost(){return cost;}
    public String getName(){return name;}

    public void setCost(double c){cost = c;}
    public void setName(String s){name = s;}
}
