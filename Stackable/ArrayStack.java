import java.util.ArrayList;

public class ArrayStack implements IStackable{
    private ArrayList<Integer> array;

    public ArrayStack(){
        this.array = new ArrayList();
    }

    public int size(){
        return this.array.size();
    }

    public void push(int v){
        this.array.add(v);
    }

    public int pop(){
        int lastIndex = this.array.size() -1;
        if(this.array.size() > 1){
            int lastValue = this.array.get(lastIndex);
            this.array.remove(lastIndex);
            return lastValue;
        }
        return -1;
    }
}