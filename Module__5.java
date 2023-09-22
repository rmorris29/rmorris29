import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Module_5 {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;
  
@ElementCollection
ArrayList<String> todo = new ArrayList<String>();
  
public Long getId() {
return id;
}

public List<String> getToDo() {
return todo;
}
public void setToDo(ArrayList<String> todo) {
this.todo = todo;
}

public void addItem(String item){
this.todo.add(item);
}

public void deleteItem(int num){
this.todo.remove(num);
}

public void listAll(){
for (int i = 0; i < this.todo.size(); i++) {
System.out.println((i+1)+"."+this.todo.get(i));
}
}
}