import java.util.*;
import java.util.ArrayList; //importing ArrayList
import java.util.Scanner; //Scanner for reading input from user
public class Main {
        public static void main(String[] args) {
                ToDoList todo=new ToDoList(); //creating new ToDoList Object
                int flag=0; 
                while(flag==0){
                
                System.out.print("\n----------To do list----------\n\n1.Add item\n2.Delete item\n3.List of todo Item\n4.exit\nEnter your choice:");
                Scanner input=new Scanner(System.in); //reading choice from user
                int option=input.nextInt(); input.nextLine();
                if(option==1){
                        System.out.println("Enter the item:");
                        String item=input.nextLine();
                        todo.addItem(item);
                        System.out.println("1 item added!");
        }
        else if(option==2){
                todo.listAll();
                System.out.println("Enter item number to delete");
                int n=input.nextInt();
                todo.deleteItem(n-1);
                System.out.println("1 item deleted!");
                
        }
        else if(option==3){
                todo.listAll();

}else{flag=1;}}
}
}
class ToDoList{ //class ToDoList
ArrayList<String> todo = new ArrayList<String>(); //ArrayList of Strings for string todo Item

//function for adding item
public  void addItem(String item){
        this.todo.add(item);
}

//our function for deleting item
public void deleteItem(int num){
        this.todo.remove(num);
}
        

//function for listing all itemsToDoList todo=new 
// ToDoList();ToDoList todo=new ToDoList();
public void listAll(){ 
        for (int i = 0; i < this.todo.size(); i++) {
      System.out.println((i+1)+"."+this.todo.get(i));
    }
        }
}