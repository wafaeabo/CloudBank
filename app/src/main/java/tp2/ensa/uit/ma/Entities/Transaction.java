package tp2.ensa.uit.ma.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

import tp2.ensa.uit.ma.Entities.Helpers.DateConverter;

@Entity(tableName = "transactions")
public class Transaction implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String type;
    private double price;
    @TypeConverters(DateConverter.class)
    private Date date;
    @ColumnInfo(name = "image_id")
    private int imageId;
    private String reference;
    private String description;

    public Transaction() {
    }

    public Transaction(String type, double price, Date date, int imageId, String reference, String description) {
        this.type = type;
        this.price = price;
        this.date = date;
        this.imageId = imageId;
        this.reference = reference;
        this.description = description;
    }

    public Transaction(int id, String type, double price, Date date, int imageId, String reference, String description) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.date = date;
        this.imageId = imageId;
        this.reference = reference;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public int getImageId() {
        return imageId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
