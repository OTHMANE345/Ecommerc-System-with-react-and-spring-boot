
package com.Login.com.Login.Wrapper;
        import org.springframework.web.multipart.MultipartFile;

public class productwrapperfordisplay  {

    private Integer id;
    private String description;
    private String price;

    private String name;

    private Integer category_id;
    private String categoryName;
    private String image;

    public productwrapperfordisplay() {

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public productwrapperfordisplay(Integer id, String description, String price, String name, Integer category_id, String categoryName, String image) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.name = name;
        this.category_id = category_id;
        this.categoryName = categoryName;
        this.image = image;
    }
}