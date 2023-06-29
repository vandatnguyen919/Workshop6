


public class Clock extends Item {    
    private int size;
    private int price;

    public Clock() {
    }

    public Clock(String code, String make, int size, int price) {
        super(code, make);
        this.size = size;
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public void input() {
        super.input();
        int newSize = Inputter.inputInt("- Enter size: ");
        int newPrice = Inputter.inputInt("- Enter price: ");        
        this.setSize(newSize);
        this.setPrice(newPrice);
    }
    
    @Override
    public void output() {
        System.out.println(this.getCode() + " - " + this.getMake() + " - " + this.getSize() + this.getPrice());        
    }
}
