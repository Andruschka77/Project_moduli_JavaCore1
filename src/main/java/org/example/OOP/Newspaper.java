package org.example.OOP;

public class Newspaper extends Publication implements Printable {

    private String publicationDay; // День публикации

    public Newspaper(String title, String author, int year, String publicationDay) {
        super(title, author, year);
        this.publicationDay = publicationDay;
    }

    @Override
    public String getType() {
        return "Newspaper";
    }

    @Override
    public String toString() {
        return "Newspaper{title='" + getTitle() + "', author='" + getAuthor() + "', year=" + getYear() + ", publicationDay='" + publicationDay + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Newspaper newspaper = (Newspaper) obj;
        return publicationDay != null ? publicationDay.equals(newspaper.publicationDay) : newspaper.publicationDay == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (publicationDay != null ? publicationDay.hashCode() : 0);
        return result;
    }

    @Override
    public void printDetails() {
        System.out.println("Автор произведения: " + getAuthor());
        System.out.println("Название произведения: " + getTitle());
        System.out.println("Год выпуска: " + getYear());
        System.out.print("День публикации: " + publicationDay);
    }

}