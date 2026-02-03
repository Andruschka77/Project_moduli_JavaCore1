package org.example.OOP;

public class Magazine extends Publication implements Printable {

    private int issueNumber; // Номер выпуска

    public Magazine(String title, String author, int year, int issueNumber) {
        super(title, author, year);
        this.issueNumber = issueNumber;
    }

    @Override
    public String getType() {
        return "Magazine";
    }

    @Override
    public String toString() {
        return "Magazine{title='" + getTitle() + "', author='" + getAuthor() + "', year=" + getYear() + ", issueNumber=" + issueNumber + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Magazine magazine = (Magazine) obj;
        return issueNumber == magazine.issueNumber;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + issueNumber;
        return result;
    }

    @Override
    public void printDetails() {
        System.out.println("Автор произведения: " + getAuthor());
        System.out.println("Название произведения: " + getTitle());
        System.out.println("Год выпуска: " + getYear());
        System.out.print("Номер выпуска: " + issueNumber);
    }

}