package edu.wctc.bean.demo5;

/**
 *
 * @author Instlogin
 */
public class Plant {
    private String name;
    private double cost;
    private String category;

    public Plant() {
    }

    public Plant(String name, double cost, String category) {
        this.name = name;
        this.cost = cost;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
