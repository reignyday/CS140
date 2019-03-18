package lab07;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ComparatorTester {
	Animal topPredator;
	Animal primaryPredator;
	Animal unrelatedPredator;
	Animal herb1;
	Animal herb2;
	ArrayList<Animal> list;
	
	Comparator<Animal> animalComp = Animal.getComp();
	
	@BeforeEach
	public void init(){
		topPredator =  new Tiger("Hyena", 3, "Tony");
		primaryPredator = new Hyena("Deer", 1, "Ed");
		unrelatedPredator = new Tiger("Antelope", 2, "Troy");
		herb1 = new Deer("grass", 0, "Bambi");
		herb2 = new Deer("apple", 2, "Pippin");
		
		//DO NOT CHANGE THE ORDERING IN WHICH WE ADD TO THIS LIST.
		list = new ArrayList<Animal>();
		list.add(topPredator);
		list.add(unrelatedPredator);
		list.add(herb1);
		list.add(primaryPredator);
		list.add(herb2);
	}
	
	@Test
	@DisplayName("two carnivores where A eats B")
	public void test01(){
		assertEquals(1, animalComp.compare(topPredator, primaryPredator));
	}
	
	@Test
	@DisplayName("two carnivores where B eats A")
	public void test02(){
		assertEquals(-1, animalComp.compare(primaryPredator, topPredator));
	}
	
	@Test
	@DisplayName("two carnivores, A higher predator than B")
	public void test03(){
		assertTrue(animalComp.compare(topPredator, unrelatedPredator) > 0);
	}


	@Test
	@DisplayName("two carnivores, B higher predator than A")
	public void test04(){
		assertTrue(animalComp.compare(primaryPredator, unrelatedPredator) < 0);
	}

	@Test
	@DisplayName("two carnivores, A and B both same predatoryLevel")
	public void test05(){
		assertEquals(0, animalComp.compare(primaryPredator, primaryPredator));
	}
	
	@Test
	@DisplayName("two herbivores, equal agressionLevel")
	public void test06(){
		assertEquals(0, animalComp.compare(herb1, herb1));
	}
	@Test
	@DisplayName("two herbivores, A more agressive than B")
	public void test07(){
		assertTrue(animalComp.compare(herb2, herb1) > 0);
	}
	@Test
	@DisplayName("two herbivores, B more agressive than A")
	public void test08(){
		assertTrue(animalComp.compare(herb1, herb2) < 0);
	}

	
	@Test
	@DisplayName("A carnivore that eats Herbivore B")
	public void test09(){
		assertEquals(1, animalComp.compare(primaryPredator, herb1));
	}
	
	@Test
	@DisplayName("A carnivore unrelated to Herbivore B")
	public void test10(){
		assertTrue(animalComp.compare(topPredator, herb1) > 0);
	}
	
	@Test
	@DisplayName("A herbivore which is eaten by Carnivore B")
	public void test11(){
		assertTrue(animalComp.compare(herb1, primaryPredator) < 0);
	}
	
	@Test
	@DisplayName("A herbivore which is unrelated to Carnivore B")
	public void test12(){
		assertTrue(animalComp.compare(herb1, topPredator) < 0);
	}
	
	@Test
	@DisplayName("test insertion sorting")
	public void test13(){
		System.out.println("***********Animal list before insertion sort************");
		for(Animal a : list){ 
			String animalType = "";
			String eats = "";
			String name = "";
			if(a instanceof Tiger){
				animalType = "Tiger";
				eats = ((Tiger)a).getPrey();
				name = ((Tiger)a).getName();
			}
			else if(a instanceof Hyena){
				animalType = "Hyena";
				eats = ((Hyena)a).getPrey();
				name = ((Hyena)a).getName();
			}
			else if(a instanceof Deer){
				animalType = "Deer";
				eats = ((Deer)a).getFoliage();
				name = ((Deer)a).getName();
			}
			System.out.println(name + " the " + animalType + " which eats " + eats);
		}
		list.sort(animalComp);
		System.out.println("***********Animal list after insertion sort************");
		for(Animal a : list){ 
			String animalType = "";
			String name = "";
			String eats = "";
			if(a instanceof Tiger){
				animalType = "Tiger";
				eats = ((Tiger)a).getPrey();
				name = ((Tiger)a).getName();
			}
			else if(a instanceof Hyena){
				animalType = "Hyena";
				eats = ((Hyena)a).getPrey();
				name = ((Hyena)a).getName();
			}
			else if(a instanceof Deer){
				animalType = "Deer";
				eats = ((Deer)a).getFoliage();
				name = ((Deer)a).getName();
			}
			System.out.println(name + " the " + animalType + " which eats " + eats);
		}
	}
}
