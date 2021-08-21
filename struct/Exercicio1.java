public class Exercicio1{

    public static void main(String[] args)
    {
        TextField text = new TextField(100, 100, "Hello world!");
        text.print();
    }

}
class TextField
    {
        int len;
        int x; int y;
        String text;

        TextField(int x, int y, String text)
        {
            this.x = x;
            this.y = y;
            this.text = text;
            this.len = text.length();
        }

        void print()
        {
            System.out.format("Texto de tamanho: %d\nNa posição: (%d, %d)\nConteudo: %s", this.len, this.x, this.y, this.text);
        }
    }