package edu.wctc.bean.demo5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Instlogin
 */
@ManagedBean
@ViewScoped
public class SimpleDataTableBean implements Serializable {
    private List<Plant> plantList;
    private List<String> plantCategories;
    private String categorySelected;
    private List<Plant> matchList;
    
    public SimpleDataTableBean() {
        plantList = new ArrayList<Plant>();
        plantList.add(new Plant("Tulip",29.95,"Perennial"));
        plantList.add(new Plant("Rose",19.95,"Perennial"));
        plantList.add(new Plant("Mum",2.95,"Annual"));
        
        plantCategories = new ArrayList<String>();
        plantCategories.add("Perennial");
        plantCategories.add("Annual");
        
    }

    public void onRowSelect(SelectEvent event) {
        List<Plant> list = new ArrayList<Plant>();
        for(Plant p : plantList) {
            if(p.getCategory().equals(categorySelected)) {
                list.add(p);
            }
        }
        matchList = list;
    }
    
    public List<Plant> getPlantList() {
        return plantList;
    }

    public void setPlantList(List<Plant> plantList) {
        this.plantList = plantList;
    }

    public List<String> getPlantCategories() {
        return plantCategories;
    }

    public void setPlantCategories(List<String> plantCategories) {
        this.plantCategories = plantCategories;
    }

    public String getCategorySelected() {
        return categorySelected;
    }

    public void setCategorySelected(String categorySelected) {
        this.categorySelected = categorySelected;
    }

    public List<Plant> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Plant> matchList) {
        this.matchList = matchList;
    }
    
    
    
}
