import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantTest {
	    
    Restaurant restaurant2;
    
    Restaurant restaurant;


    //REFACTOR ALL THE REPEATED LINES OF CODE
    
    @BeforeEach
    public void add_restaurant_and_menu() {
    	MockitoAnnotations.initMocks(this);
    	LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =  new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
    	
        //WRITE UNIT TEST CASE HERE
    	LocalTime time = LocalTime.parse("11:30:00");
    	restaurant2 = Mockito.spy(restaurant);
    	when(restaurant2.getCurrentTime()).thenReturn(time);
    	assertTrue(restaurant2.isRestaurantOpen());
    	
    }
    
   

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
 
    	LocalTime time = LocalTime.parse("07:30:00");
    	restaurant2 = Mockito.spy(restaurant);
    	Mockito.when(restaurant2.getCurrentTime()).thenReturn(time);
    	Mockito.when(restaurant2.getCurrentTime()).thenReturn(time);
    	assertFalse(restaurant2.isRestaurantOpen());
    	

    }
    
    @Test
    public void order_total_after_adding_items_should_match_sum_of_item_prices() {
    	List<String> itemName = new ArrayList<String>();
    	itemName.add("Sweet corn soup");
    	itemName.add("Vegetable lasagne");
    	int orderValue = restaurant.calculateOrder(itemName);
    	int expectedOrderValue = 119+269;
    	assertEquals(orderValue, expectedOrderValue);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
    	

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
    	

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
    	

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
//    <<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}