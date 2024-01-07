package com.example.demo.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="books")
public class Book {
    public Book(){}
    public Book(String title, String publisher, float price) {
        this.title = title;
        this.publisher = publisher;
        this.price = price;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="publisher")
    private String publisher;
    @Column(name="price")
    private float price;
    @Column(name = "description")
    private String description;
    @Column(name = "image_url")
    private String image_url;

    public String getDescription() {
        return description;
    }

    public void setDescription(String opis) {
        this.description = opis;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @ManyToOne(cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="category_id")
    private Category category;
    @ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name="authors_to_books",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="author_id")
    )
    private Set<Author> authors;
    public void addAuthor(Author author){
        if (authors == null)
            authors = new HashSet<>();
        authors.add(author);
    }
    public void removeAuthor(Author author){
        if (authors == null)
            return;
        authors.remove(author);
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public Category getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public Set<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
    @Override
    public String toString() {
        return "Ksiazka{" +
                "id=" + id +
                ", nazwa='" + title + '\'' +
                ", wydawnictwo='" + publisher + '\'' +
                ", cena=" + price +
                ", kategoria=" + category +
                '}';
    }
}
