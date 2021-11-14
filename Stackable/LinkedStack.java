import java.util.*;

public class LinkedStack extends LinkedList{

    private LinkedList<Integer> list;

    public LinkedStack(){
        this.list = new LinkedList();
    }

    public int size(){
        return this.list.size();
    }

    public void push(int v){
        if (this.list == null){
            this.list.addFirst(v);
        }else{
            this.list.add(v);
        }
    }

    public int pop(){
        int value;
        if(this.list != null){
            value = this.list.pop();
        }else{
            value = null;
        }
        return value;
    }
}