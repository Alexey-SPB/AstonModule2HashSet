import java.util.List;
import java.util.Queue;

public class Studends {
    String name;
    private List<Book> books;
    Studends (String name, List<Book> books){
        this.name = name;
        this.books = books;
    }

    public String getName(){
        return name;
    }
    public List<Book> getBooks(){
        return books;
    }
    public String toString(){
        return "Имя студента: "+name+" Список книг:"+books;
    }
}


