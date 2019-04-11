package lab07;

import java.util.Comparator;

public abstract class Animal implements Actions, Classifications
{
    @Override
    public String toString()
    {
        return "This is the higher level abstract animal class. " +
            "It's subclasses will implement the interfaces.\n";
    }

    abstract String getAnimalName();
    
    private static Comparator<Animal> animalComp = (a, b) -> {
        String aPrey = "";
    	String bPrey = "";
    	
    	if (a instanceof Carnivore)
    		aPrey = ((Carnivore)a).getPrey();
    	
    	if (b instanceof Carnivore)
    		bPrey = ((Carnivore)b).getPrey();
    	
    	if (aPrey.length() == 0 && bPrey.length() == 0)
    		return ((Herbivore)a).getAggressionLevel() - ((Herbivore)b).getAggressionLevel();
    	
    	if (a.getAnimalName().equals(bPrey))
    		return -1;
    	
    	if (b.getAnimalName().equals(aPrey))
    		return 1;
    	
    	if (aPrey.length() > 0 && bPrey.length() > 0)
    		return ((Carnivore)a).getPredatoryLevel() - ((Carnivore)b).getPredatoryLevel();
    	
    	return aPrey.length() - bPrey.length();
    };
    
    public static Comparator<Animal> getComp()
    {
        return Animal.animalComp;
    }
}
