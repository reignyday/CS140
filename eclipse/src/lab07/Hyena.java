package lab07;

public class Hyena extends Carnivore{
    private String name;
    public Hyena(String prey, int predatoryLevel, String name){
        super(prey, predatoryLevel);
        this.name = name;
    }
    public String getName(){
        return name;
    }
    @Override
    public String getAnimalName(){
        return "Hyena";
    }
    @Override
    public String toString(){
        return "Hyenas are known for the noises they make, which is often associtated with laughing and cackling.";
    }
    @Override
    public void speak() {
        System.out.println("hee-hee-hee");
    }
    @Override
    public void eat() {
        System.out.println(name + " the Hyena eats " + super.getPrey());    
    }
    @Override
    public void move() {
        System.out.println("Hyenas like to hunt in packs, and sort of skip when they run, " 
            + "since their back legs are smaller than their front legs.");
    }
    @Override
    public void sleep() {
        System.out.println("Hyenas like to sleep in dens usually.");    
    }
    @Override
    public String kingdom() {
        return "Animalia";
    }
    @Override
    public String genus() {
        return "Hyaena";
    }
    @Override
    public String species() {
        return "H.hyaena (striped hyena)";
    }
    @Override
    void prowl() {
        System.out.println("The hyena chases down a frightened " + super.getPrey() + " and corners it");
    }

}
