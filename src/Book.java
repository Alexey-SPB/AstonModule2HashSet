public class Book {
    String name;
    int year;
    int lists;
    Book(String name, int year, int lists){
        this.name = name;
        this.year = year;
        this.lists = lists;
    }
    public String getName(){
        return name;
    }
    public int getYear(){
        return year;
    }
    public int getLists(){
        return lists;
    }
    public String toString(){
        return "Название: " + name+ " Год выхода: "+year+" Листы: "+lists+" ";
    }
}


