import java.util.*;

import javax.swing.event.SwingPropertyChangeSupport;

public class tester {
    public static void main(String[] args) {
        Map<String, Integer> myMap = new HashMap<>();

        myMap.put("Davidson", 28036);
        myMap.put("Raleigh", 27617);
        myMap.put("NY", 12345);
        myMap.put("NY", 69696969);
        

        System.out.println(myMap.get("NY"));
        System.out.println(myMap.);

        Set<String> coolCities = new HashSet<>(); 
        
        coolCities.add("Davidson");
        coolCities.add("Raleigh");
        coolCities.add("NY");

        


        System.out.println(coolCities.size()); 


        for(String city: coolCities){
            System.out.println(city);
        }




    }

}
