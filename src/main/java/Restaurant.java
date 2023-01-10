import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
    
   
    
    private boolean compare(List<Item> l1, List<Item> l2) {
    	int count = 0;
    	for(Item item1: l1) {
    		for(Item item2:l2) {
    			if(item2.getName() == item1.getName() && item2.getPrice()== item1.getPrice()) {
    				count+=1;
    			}
    		}
    	}
    	return count == l1.size();
    }

    @Override
    public boolean equals(Object o) {
    	System.out.println("hello");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant rest = (Restaurant) o;
        return this.name == rest.name &&
                this.location== rest.location && compare(this.menu, rest.menu) && this.openingTime.equals(rest.openingTime) && this.closingTime.equals(rest.closingTime);
    }

    
    public boolean isRestaurantOpen() {
    	LocalTime time = getCurrentTime();
    	if( time.isAfter(this.openingTime) && time.isBefore(this.closingTime)) {
    		return true;
    	}
    	else {
    		return false;
    	}	
        
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public LocalTime getCurrentTime(){ 
    	return  LocalTime.now();
    	}

    public List<Item> getMenu() {
        return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
