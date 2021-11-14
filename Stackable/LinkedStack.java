import java.util.*;

public class LinkedStack implements IStackable
{

    private LinkedList<Integer> list;

    public LinkedStack(){
        this.list = new LinkedList();
    }

    public int size(){
        return this.list.size();
    }

    public void push(int v){
        if (this.list.size() == 0){
            this.list.addFirst(v);
        }else{
            this.list.addLast(v);
        }
    }

    public int pop(){
        if(this.list.size() > 1){
            return this.list.removeLast();
        }
        return -1;
    }
}