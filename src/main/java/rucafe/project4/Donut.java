package rucafe.project4;

public class Donut extends MenuItem {
    private String type;
    private String flavor;
    public Donut(String type, String flavor) {
        this.type = type;
        this.flavor = flavor;
    }

    public double itemPrice() {
        switch (type) {
            case "Yeast": {
                return 1.59;
            }
            case "Cake": {
                return 1.79;
            }
            case "Donut Hole": {
                return 0.39;
            }

        }

        return -1;
    }
}
