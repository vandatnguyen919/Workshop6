
import java.io.*;
import java.util.*;

public class ClockList extends Clock implements I_List {
    List<Clock> list = new ArrayList<>();
    
    @Override
    public void AddFromFile(String fName) {
        try {
            File f = new File(fName); //check the file
            if (!f.exists()) return;
            FileReader fr = new FileReader(f);          //read
            BufferedReader bf = new BufferedReader(fr); //readLine
            String line;
            while ((line=bf.readLine()) != null) {                
                //splitting details into elements
                StringTokenizer stk = new StringTokenizer(line, ",");
                String code = stk.nextToken().toUpperCase();
                String make = stk.nextToken().toUpperCase();
                int size = Integer.parseInt(stk.nextToken());
                int price = Integer.parseInt(stk.nextToken());
                
                //create a clock
                Clock c = new Clock(code, make, size, price);
                list.add(c);
            }
            bf.close(); fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void saveToFile(String fName) {
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f); //write
            PrintWriter pw = new PrintWriter(fw); // println
            for (Clock x:list) {
                pw.println(x.getCode() + "- " + x.getMake() + "- " + x.getSize() + "- " + x.getPrice());
            }
            pw.close(); fw.close();
            
            System.out.println("All Clocks have been saved.");
        } catch (Exception e) {
            System.out.println(e);
        }
    } 

    @Override
    public int find(String code){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equals(code)) return i;
        }        
        return -1;
    }

    @Override
    public void add() {
        String newCode, newMake;
        int newSize = 0, newPrice = 0;
        int pos;
        boolean valid = true;
        System.out.println("Enter new Clock's information: ");
        do {
            newCode = Inputter.inputStr("- Enter a new code (Cxxx)");
            pos = find(newCode);
            valid = newCode.matches("^C\\d{3}$");
            if (pos >= 0) System.out.println("This code is duplicated.");
            if (!valid) System.out.println("This code only consists of C and 3 digits.");
        } while (pos >= 0 || (!valid));
        
        newMake = Inputter.inputNonBlankStr("- Enter a new maker");
        boolean cont = false;
        do {            
            try {
                newSize = Integer.parseInt(Inputter.inputNonBlankStr("- Enter new size"));
                cont = false;
            } catch (NumberFormatException e) {
                cont = true;
            }
        } while (cont);
        do {            
            try {
                newPrice = Integer.parseInt(Inputter.inputNonBlankStr("- Enter new price"));
                cont = false;
            } catch (NumberFormatException e) {
                cont = true;
            }
        } while (cont);
        list.add(new Clock(newCode, newMake, newSize, newPrice));
        
        System.out.println("New Clock has been added.");
    }

    @Override
    public void remove() {
        String code;
        code = Inputter.inputStr("- Enter a code to remove");
        int pos = find(code);
        if (pos < 0) System.out.println("This code is not exist.");
        else {
            list.remove(pos);
            System.out.println("The clock " + code + " has been removed.");
        }
    }

    @Override
    public void update() {
        String code;
        code = Inputter.inputNonBlankStr("- Enter a code to update");
        int pos = find(code);
        if (pos < 0) System.out.println("This code is not exist.");
        else {
            String newMake, newSize, newPrice;
            newMake = Inputter.inputStr("- Enter a new maker");
            list.get(pos).setMake(newMake);
            boolean cont = false;
            do {                
                try {
                    newSize = Inputter.inputStr("- Enter new size");
                    if (!newSize.isEmpty()) {
                        list.get(pos).setSize(Integer.parseInt(newSize));
                    }
                    cont = false;
                } catch (NumberFormatException e) {
                    cont = true;
                }
            } while (cont);
            
            do {                
                try {
                    newPrice = Inputter.inputStr("- Enter new price");
                    if (!newPrice.isEmpty()) {
                        list.get(pos).setSize(Integer.parseInt(newPrice));
                    }
                    cont = false;
                } catch (NumberFormatException e) {
                    cont = true;
                }
            } while (cont);
            
            System.out.println("All information has been updated.");
        }
    }

    @Override
    public void sort(){
        //using Anonymous inner class
        Collections.sort(list, new Comparator<Clock>(){
            @Override
            public int compare(Clock o1, Clock o2) {
                if (o1.getPrice() > o2.getPrice()) return -1;
                else return 1;
            }
    }); 
   }
    
    @Override
    public void print(){
        list.forEach((x) -> {
            System.out.println(x.getCode() + " - " + x.getMake() + " - " + x.getSize() + " - " + x.getPrice());
        });
    }
}
