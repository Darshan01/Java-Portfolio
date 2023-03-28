public class Main{
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void printer(Person[] guests, double total, double tax, double tip){
        for(Person p : guests){
            String s = "";
            s = s + p.getName() + " ";
            s = s + "$" + p.getDue() + " ";

            if(p.isBDay()){
                s += "Happy Birthday! ";
            }

            if(p.getOrder().length > 0){
                s += "(" + p.getOrder()[0].getName();
                for(int i = 1; i < p.getOrder().length; i++){
                    s = s + ", ";
                    s = s + p.getOrder()[i].getName();
                }
                s += ")";
            }

            StdOut.println(s);
        }
        StdOut.println();
        StdOut.println("Total before tax and tip: " + total);
        double tot = 0;
        for(int i = 0; i < guests.length; i++){
            tot += guests[i].getDue();
        }
        StdOut.println("Tax: " + tax);
        StdOut.println("Tip: " + tip);
        StdOut.println("Total: " + tot);
    }

    public static String[] splitLine(){
        String name = StdIn.readString();
        String next = StdIn.readString();
        int len;
        String ret = name + " ";

        if(next.equals("birthday") || next.equals("Birthday")){
            len = Integer.parseInt(StdIn.readString());
            ret = ret + next + " ";
            ret = ret + Integer.toString(len) + " ";
 
        } else {
            len = Integer.parseInt(next);
            ret = ret + next + " ";
        }
        
        int ptr = 0;
        while(ptr < len){
            String s = StdIn.readString();
            if(!isNumeric(s)){
                ret = ret + s + " ";
            } else {
                ret = ret + s + " ";
                ptr++;
            }
        }
        return ret.split(" ");
    }

    public static Person[] initializeGuests(){
        int n = StdIn.readInt();
        Person[] guests = new Person[n];

        for(int i = 0; i < n; i++){
            String[] l = splitLine();
            int k = 1;
            boolean b = false;
            int ptr = 0;

            if(l[1].equals("birthday") || l[1].equals("Birthday")) {
                b = true;
                k = 2;
            };

            guests[i] = new Person(l[0], b);

            Food[] o = new Food[Integer.parseInt(l[k])];

            String foodName = "";
            for(int j = k + 1; ptr < Integer.parseInt(l[k]); j++){
                if(!isNumeric(l[j])){
                    foodName = foodName + l[j] + " ";
                } else {
                    o[ptr] = new Food(foodName, Double.parseDouble(l[j]));
                    foodName = "";
                    ptr++;
                }
            }

            guests[i].makeOrder(o);  
        }
        return guests;
    }

    public static void redistribute(double total, double tax, double tip, Person[] g){
        int people = 0;
        double amountToDistribute = tax + tip;
        for(int i = 0; i < g.length; i++){
            if(!g[i].isBDay()){
                people++;
            } else {
                amountToDistribute += g[i].getDue();
                g[i].setDue(0);
            }
        }

        for(int i = 0; i < g.length; i++){
            if(!g[i].isBDay()) g[i].updateDue(amountToDistribute/people);
        }
       

    }

    public static void main(String[] args){
        StdIn.setFile("guests.in");
        StdOut.setFile("split.out");
        Person[] guests = initializeGuests();
        double tax = StdIn.readDouble();
        double tip = StdIn.readDouble();
        double total = 0;
        for(int i = 0; i < guests.length; i++){
            total += guests[i].getDue();
        }
        redistribute(total, tax, tip, guests);
        printer(guests, total, tax, tip);
        

    }
}