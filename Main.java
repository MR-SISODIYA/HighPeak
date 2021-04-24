import java.io.*;
import java.util.*;
class Item {
  String itemname;
  int itemprice;

  public Item(String itemname, int itemprice) {
    this.itemname = itemname;
    this.itemprice = itemprice;
  }

  public String toString() { 
      return this.itemname + ": " + this.itemprice;
  }
}

public class Main 
{
  public static void main(String[] args) throws Exception {
    FileInputStream fis=new FileInputStream("input.txt");       
    Scanner sc=new Scanner(fis);
    int numberOfEmployee = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Item> goodies_items = new ArrayList<Item>();

    while(sc.hasNextLine())  
    {
      String now[] = sc.nextLine().split(": ");
      goodies_items.add(new Item(now[0], Integer.parseInt(now[1])));
    }
    sc.close();

    Collections.sort(goodies_items, new Comparator<Item>(){
      public int compare(Item a, Item b) { 
        return a.itemprice - b.itemprice; 
      } 
    });

    int min_difference = goodies_items.get(goodies_items.size()-1).itemprice;
    int minimumIndex = 0;
    for(int i=0;i<goodies_items.size()-numberOfEmployee+1;i++) {
      int difference = goodies_items.get(numberOfEmployee+i-1).itemprice-goodies_items.get(i).itemprice;

      if(difference<=min_difference) {
        min_difference = difference;
        minimumIndex = i;
      }
    }
    
    

    FileWriter fw = new FileWriter("output.txt");
    fw.write("The goodies selected for distribution are:\n\n");
    for(int i=minimumIndex;i<minimumIndex + numberOfEmployee; i++) {
      fw.write(goodies_items.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_difference);
	  fw.close();
  }
}